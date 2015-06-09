package tachyon.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tachyon.Constants;
import tachyon.conf.TachyonConf;
import tachyon.master.MasterClient;
import tachyon.thrift.Command;
import tachyon.util.CommonUtils;
import tachyon.util.NetworkUtils;
import tachyon.util.ThreadFactoryUtils;
import tachyon.worker.block.StoreMeta;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Task that carries out the necessary block worker to master communications, including register
 * and heartbeat. This class manages its own {@link tachyon.master.MasterClient}.
 */
// TODO: Find a better name for this
public class BlockWorkerHeartbeat implements Runnable {
  private static final Logger LOG = LoggerFactory.getLogger(Constants.LOGGER_TYPE);

  private final CoreWorker mCoreWorker;
  private final ExecutorService mMasterClientExecutorService;
  private final InetSocketAddress mWorkerAddress;
  private final TachyonConf mTachyonConf;
  private final int mHeartbeatIntervalMs;
  private final int mHeartbeatTimeoutMs;

  private MasterClient mMasterClient;
  private boolean mRunning;
  private int mWorkerId;

  BlockWorkerHeartbeat(CoreWorker coreWorker, TachyonConf tachyonConf, InetSocketAddress
      workerAddress) {
    mCoreWorker = coreWorker;
    mWorkerAddress = workerAddress;
    mTachyonConf = tachyonConf;
    mMasterClientExecutorService =
        Executors.newFixedThreadPool(1, ThreadFactoryUtils.daemon("worker-client-heartbeat-%d"));
    mMasterClient =
        new MasterClient(getMasterAddress(), mMasterClientExecutorService, mTachyonConf);
    mHeartbeatIntervalMs =
        mTachyonConf.getInt(Constants.WORKER_TO_MASTER_HEARTBEAT_INTERVAL_MS, Constants.SECOND_MS);
    mHeartbeatTimeoutMs =
        mTachyonConf.getInt(Constants.WORKER_HEARTBEAT_TIMEOUT_MS, 10 * Constants.SECOND_MS);

    mRunning = true;
    mWorkerId = 0;
  }

  private InetSocketAddress getMasterAddress() {
    String masterHostname =
        mTachyonConf.get(Constants.MASTER_HOSTNAME, NetworkUtils.getLocalHostName(mTachyonConf));
    int masterPort = mTachyonConf.getInt(Constants.MASTER_PORT, Constants.DEFAULT_MASTER_PORT);
    return new InetSocketAddress(masterHostname, masterPort);
  }

  private void handleMasterCommand(Command cmd) {
    if (cmd != null) {
      switch (cmd.mCommandType) {
        case Unknown:
          LOG.error("Unknown command: " + cmd);
          break;
        case Nothing:
          LOG.debug("Nothing command: {}", cmd);
          break;
        case Register:
          LOG.info("Register command: " + cmd);
          registerWithMaster();
          break;
        case Free:
          mCoreWorker.freeBlocks(cmd.mData);
          LOG.info("Free command: " + cmd);
          break;
        case Delete:
          LOG.info("Delete command: " + cmd);
          break;
        default:
          throw new RuntimeException("Un-recognized command from master " + cmd.toString());
      }
    }
  }

  private void registerWithMaster() {
    BlockWorkerReport blockReport = mCoreWorker.getReport();
    StoreMeta storeMeta = mCoreWorker.getStoreMeta();
    int assignedId = 0;
    // TODO: Are retries necessary?
    assignedId = mMasterClient.worker_register(mWorkerAddress, storeMeta.getCapacityBytesOnTiers(),
        blockReport.getUsedBytesOnTiers(), storeMeta.getBlockList());
    mWorkerId = assignedId;
  }

  private void resetMasterClient() {
    mMasterClient.close();
    mMasterClient =
        new MasterClient(getMasterAddress(), mMasterClientExecutorService, mTachyonConf);
  }

  public int getWorkerId() {
    return mWorkerId;
  }

  @Override
  public void run() {
    long lastHeartbeatMs = System.currentTimeMillis();
    Command cmd = null;
    while (mRunning) {
      long diff = System.currentTimeMillis() - lastHeartbeatMs;
      if (diff < mHeartbeatIntervalMs) {
        LOG.debug("Heartbeat process takes {} ms.", diff);
        CommonUtils.sleepMs(LOG, mHeartbeatIntervalMs - diff);
      } else {
        LOG.warn("Heartbeat took " + diff + " ms, expected " + mHeartbeatIntervalMs + " ms.");
      }
      try {
        BlockWorkerReport blockReport = mCoreWorker.getReport();
        cmd = mMasterClient.worker_heartbeat(mWorkerId, blockReport.getUsedBytesOnTiers(),
            blockReport.getRemovedBlocks(), blockReport.getAddedBlocks());
        lastHeartbeatMs = System.currentTimeMillis();
      } catch (IOException e) {
        LOG.error(e.getMessage(), e);
        resetMasterClient();
        CommonUtils.sleepMs(LOG, Constants.SECOND_MS);
        cmd = null;
        diff = System.currentTimeMillis() - lastHeartbeatMs;
        if (diff >= mHeartbeatTimeoutMs) {
          throw new RuntimeException("Heartbeat timeout " + diff + "ms");
        }
      }
      // TODO: Is there a way to make this async? Could take much longer than heartbeat timeout.
      handleMasterCommand(cmd);
    }
  }

  public void stop() {
    mRunning = false;
    mMasterClient.close();
    mMasterClientExecutorService.shutdown();
  }
}
