/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.shalong.zhangpai.rsc.thrift.consts;

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

public class ConstConfigConstants {

  public static final Map<Integer,String> IDTYPE = new HashMap<Integer,String>();
  static {
    IDTYPE.put(2, "军官证");
    IDTYPE.put(1, "身份证");
    IDTYPE.put(3, "护照");
  }

  public static final Map<Integer,String> RELATIONSHIP = new HashMap<Integer,String>();
  static {
    RELATIONSHIP.put(1, "家人");
    RELATIONSHIP.put(2, "亲戚");
    RELATIONSHIP.put(3, "朋友");
    RELATIONSHIP.put(0, "本人");
  }

  public static final Map<Integer,String> GENDER = new HashMap<Integer,String>();
  static {
    GENDER.put(2, "女");
    GENDER.put(3, "未知");
    GENDER.put(1, "男");
  }

  public static final int Hospital_Favorite_Type = 10;

  public static final int Doctor_Favorite_Type = 12;

}
