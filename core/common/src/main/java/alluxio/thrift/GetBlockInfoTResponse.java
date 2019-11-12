/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package alluxio.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)")
public class GetBlockInfoTResponse implements org.apache.thrift.TBase<GetBlockInfoTResponse, GetBlockInfoTResponse._Fields>, java.io.Serializable, Cloneable, Comparable<GetBlockInfoTResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("GetBlockInfoTResponse");

  private static final org.apache.thrift.protocol.TField BLOCK_INFO_FIELD_DESC = new org.apache.thrift.protocol.TField("blockInfo", org.apache.thrift.protocol.TType.STRUCT, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new GetBlockInfoTResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new GetBlockInfoTResponseTupleSchemeFactory());
  }

  private alluxio.thrift.BlockInfo blockInfo; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    BLOCK_INFO((short)1, "blockInfo");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // BLOCK_INFO
          return BLOCK_INFO;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.BLOCK_INFO, new org.apache.thrift.meta_data.FieldMetaData("blockInfo", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, alluxio.thrift.BlockInfo.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(GetBlockInfoTResponse.class, metaDataMap);
  }

  public GetBlockInfoTResponse() {
  }

  public GetBlockInfoTResponse(
    alluxio.thrift.BlockInfo blockInfo)
  {
    this();
    this.blockInfo = blockInfo;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public GetBlockInfoTResponse(GetBlockInfoTResponse other) {
    if (other.isSetBlockInfo()) {
      this.blockInfo = new alluxio.thrift.BlockInfo(other.blockInfo);
    }
  }

  public GetBlockInfoTResponse deepCopy() {
    return new GetBlockInfoTResponse(this);
  }

  @Override
  public void clear() {
    this.blockInfo = null;
  }

  public alluxio.thrift.BlockInfo getBlockInfo() {
    return this.blockInfo;
  }

  public GetBlockInfoTResponse setBlockInfo(alluxio.thrift.BlockInfo blockInfo) {
    this.blockInfo = blockInfo;
    return this;
  }

  public void unsetBlockInfo() {
    this.blockInfo = null;
  }

  /** Returns true if field blockInfo is set (has been assigned a value) and false otherwise */
  public boolean isSetBlockInfo() {
    return this.blockInfo != null;
  }

  public void setBlockInfoIsSet(boolean value) {
    if (!value) {
      this.blockInfo = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case BLOCK_INFO:
      if (value == null) {
        unsetBlockInfo();
      } else {
        setBlockInfo((alluxio.thrift.BlockInfo)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case BLOCK_INFO:
      return getBlockInfo();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case BLOCK_INFO:
      return isSetBlockInfo();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof GetBlockInfoTResponse)
      return this.equals((GetBlockInfoTResponse)that);
    return false;
  }

  public boolean equals(GetBlockInfoTResponse that) {
    if (that == null)
      return false;

    boolean this_present_blockInfo = true && this.isSetBlockInfo();
    boolean that_present_blockInfo = true && that.isSetBlockInfo();
    if (this_present_blockInfo || that_present_blockInfo) {
      if (!(this_present_blockInfo && that_present_blockInfo))
        return false;
      if (!this.blockInfo.equals(that.blockInfo))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_blockInfo = true && (isSetBlockInfo());
    list.add(present_blockInfo);
    if (present_blockInfo)
      list.add(blockInfo);

    return list.hashCode();
  }

  @Override
  public int compareTo(GetBlockInfoTResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetBlockInfo()).compareTo(other.isSetBlockInfo());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBlockInfo()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.blockInfo, other.blockInfo);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("GetBlockInfoTResponse(");
    boolean first = true;

    sb.append("blockInfo:");
    if (this.blockInfo == null) {
      sb.append("null");
    } else {
      sb.append(this.blockInfo);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (blockInfo != null) {
      blockInfo.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class GetBlockInfoTResponseStandardSchemeFactory implements SchemeFactory {
    public GetBlockInfoTResponseStandardScheme getScheme() {
      return new GetBlockInfoTResponseStandardScheme();
    }
  }

  private static class GetBlockInfoTResponseStandardScheme extends StandardScheme<GetBlockInfoTResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, GetBlockInfoTResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // BLOCK_INFO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.blockInfo = new alluxio.thrift.BlockInfo();
              struct.blockInfo.read(iprot);
              struct.setBlockInfoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, GetBlockInfoTResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.blockInfo != null) {
        oprot.writeFieldBegin(BLOCK_INFO_FIELD_DESC);
        struct.blockInfo.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class GetBlockInfoTResponseTupleSchemeFactory implements SchemeFactory {
    public GetBlockInfoTResponseTupleScheme getScheme() {
      return new GetBlockInfoTResponseTupleScheme();
    }
  }

  private static class GetBlockInfoTResponseTupleScheme extends TupleScheme<GetBlockInfoTResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, GetBlockInfoTResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetBlockInfo()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetBlockInfo()) {
        struct.blockInfo.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, GetBlockInfoTResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.blockInfo = new alluxio.thrift.BlockInfo();
        struct.blockInfo.read(iprot);
        struct.setBlockInfoIsSet(true);
      }
    }
  }

}
