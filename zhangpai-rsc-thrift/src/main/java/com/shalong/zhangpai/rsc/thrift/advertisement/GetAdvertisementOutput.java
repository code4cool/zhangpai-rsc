/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.shalong.zhangpai.rsc.thrift.advertisement;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetAdvertisementOutput implements org.apache.thrift.TBase<GetAdvertisementOutput, GetAdvertisementOutput._Fields>, java.io.Serializable, Cloneable, Comparable<GetAdvertisementOutput> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("GetAdvertisementOutput");

  private static final org.apache.thrift.protocol.TField RETURN_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("returnCode", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField ADVERTISEMENT_FIELD_DESC = new org.apache.thrift.protocol.TField("advertisement", org.apache.thrift.protocol.TType.STRUCT, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new GetAdvertisementOutputStandardSchemeFactory());
    schemes.put(TupleScheme.class, new GetAdvertisementOutputTupleSchemeFactory());
  }

  public int returnCode; // required
  public Advertisement advertisement; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    RETURN_CODE((short)1, "returnCode"),
    ADVERTISEMENT((short)2, "advertisement");

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
        case 1: // RETURN_CODE
          return RETURN_CODE;
        case 2: // ADVERTISEMENT
          return ADVERTISEMENT;
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
  private static final int __RETURNCODE_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.RETURN_CODE, new org.apache.thrift.meta_data.FieldMetaData("returnCode", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.ADVERTISEMENT, new org.apache.thrift.meta_data.FieldMetaData("advertisement", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Advertisement.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(GetAdvertisementOutput.class, metaDataMap);
  }

  public GetAdvertisementOutput() {
  }

  public GetAdvertisementOutput(
    int returnCode,
    Advertisement advertisement)
  {
    this();
    this.returnCode = returnCode;
    setReturnCodeIsSet(true);
    this.advertisement = advertisement;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public GetAdvertisementOutput(GetAdvertisementOutput other) {
    __isset_bitfield = other.__isset_bitfield;
    this.returnCode = other.returnCode;
    if (other.isSetAdvertisement()) {
      this.advertisement = new Advertisement(other.advertisement);
    }
  }

  public GetAdvertisementOutput deepCopy() {
    return new GetAdvertisementOutput(this);
  }

  @Override
  public void clear() {
    setReturnCodeIsSet(false);
    this.returnCode = 0;
    this.advertisement = null;
  }

  public int getReturnCode() {
    return this.returnCode;
  }

  public GetAdvertisementOutput setReturnCode(int returnCode) {
    this.returnCode = returnCode;
    setReturnCodeIsSet(true);
    return this;
  }

  public void unsetReturnCode() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __RETURNCODE_ISSET_ID);
  }

  /** Returns true if field returnCode is set (has been assigned a value) and false otherwise */
  public boolean isSetReturnCode() {
    return EncodingUtils.testBit(__isset_bitfield, __RETURNCODE_ISSET_ID);
  }

  public void setReturnCodeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __RETURNCODE_ISSET_ID, value);
  }

  public Advertisement getAdvertisement() {
    return this.advertisement;
  }

  public GetAdvertisementOutput setAdvertisement(Advertisement advertisement) {
    this.advertisement = advertisement;
    return this;
  }

  public void unsetAdvertisement() {
    this.advertisement = null;
  }

  /** Returns true if field advertisement is set (has been assigned a value) and false otherwise */
  public boolean isSetAdvertisement() {
    return this.advertisement != null;
  }

  public void setAdvertisementIsSet(boolean value) {
    if (!value) {
      this.advertisement = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case RETURN_CODE:
      if (value == null) {
        unsetReturnCode();
      } else {
        setReturnCode((Integer)value);
      }
      break;

    case ADVERTISEMENT:
      if (value == null) {
        unsetAdvertisement();
      } else {
        setAdvertisement((Advertisement)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case RETURN_CODE:
      return Integer.valueOf(getReturnCode());

    case ADVERTISEMENT:
      return getAdvertisement();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case RETURN_CODE:
      return isSetReturnCode();
    case ADVERTISEMENT:
      return isSetAdvertisement();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof GetAdvertisementOutput)
      return this.equals((GetAdvertisementOutput)that);
    return false;
  }

  public boolean equals(GetAdvertisementOutput that) {
    if (that == null)
      return false;

    boolean this_present_returnCode = true;
    boolean that_present_returnCode = true;
    if (this_present_returnCode || that_present_returnCode) {
      if (!(this_present_returnCode && that_present_returnCode))
        return false;
      if (this.returnCode != that.returnCode)
        return false;
    }

    boolean this_present_advertisement = true && this.isSetAdvertisement();
    boolean that_present_advertisement = true && that.isSetAdvertisement();
    if (this_present_advertisement || that_present_advertisement) {
      if (!(this_present_advertisement && that_present_advertisement))
        return false;
      if (!this.advertisement.equals(that.advertisement))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(GetAdvertisementOutput other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetReturnCode()).compareTo(other.isSetReturnCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReturnCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.returnCode, other.returnCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAdvertisement()).compareTo(other.isSetAdvertisement());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAdvertisement()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.advertisement, other.advertisement);
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
    StringBuilder sb = new StringBuilder("GetAdvertisementOutput(");
    boolean first = true;

    sb.append("returnCode:");
    sb.append(this.returnCode);
    first = false;
    if (!first) sb.append(", ");
    sb.append("advertisement:");
    if (this.advertisement == null) {
      sb.append("null");
    } else {
      sb.append(this.advertisement);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (advertisement != null) {
      advertisement.validate();
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class GetAdvertisementOutputStandardSchemeFactory implements SchemeFactory {
    public GetAdvertisementOutputStandardScheme getScheme() {
      return new GetAdvertisementOutputStandardScheme();
    }
  }

  private static class GetAdvertisementOutputStandardScheme extends StandardScheme<GetAdvertisementOutput> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, GetAdvertisementOutput struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // RETURN_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.returnCode = iprot.readI32();
              struct.setReturnCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ADVERTISEMENT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.advertisement = new Advertisement();
              struct.advertisement.read(iprot);
              struct.setAdvertisementIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, GetAdvertisementOutput struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(RETURN_CODE_FIELD_DESC);
      oprot.writeI32(struct.returnCode);
      oprot.writeFieldEnd();
      if (struct.advertisement != null) {
        oprot.writeFieldBegin(ADVERTISEMENT_FIELD_DESC);
        struct.advertisement.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class GetAdvertisementOutputTupleSchemeFactory implements SchemeFactory {
    public GetAdvertisementOutputTupleScheme getScheme() {
      return new GetAdvertisementOutputTupleScheme();
    }
  }

  private static class GetAdvertisementOutputTupleScheme extends TupleScheme<GetAdvertisementOutput> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, GetAdvertisementOutput struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetReturnCode()) {
        optionals.set(0);
      }
      if (struct.isSetAdvertisement()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetReturnCode()) {
        oprot.writeI32(struct.returnCode);
      }
      if (struct.isSetAdvertisement()) {
        struct.advertisement.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, GetAdvertisementOutput struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.returnCode = iprot.readI32();
        struct.setReturnCodeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.advertisement = new Advertisement();
        struct.advertisement.read(iprot);
        struct.setAdvertisementIsSet(true);
      }
    }
  }

}

