package com.example.demo.utility;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DataConversionUtils {

  private DataConversionUtils(){}

  public static String numberToString(Number value){
    return value == null ? null : value.toString();
  }

  public static Integer stringToInteger(String value){
    return value == null ? null : Integer.parseInt(value);
  }

  public static Long stringToLong(String value){
    return value == null ? null : Long.parseLong(value);
  }

  public static Double stringToDouble(String value){
    return value == null ? null : Double.parseDouble(value);
  }

  public static LocalDateTime stringToLocalDateTime(String value){
    return value == null ? null : LocalDateTime.parse(value);
  }

  public static String localDateTimeToString(LocalDateTime value){
    return value == null ? null : value.toString();
  }

  public static BigDecimal stringToBigDecimal(String value){
    return value == null ? null : new BigDecimal(value);
  }

  public static String bigDecimalToString(BigDecimal value){
    return value == null ? null : value.toString();
  }

  public static Boolean stringToBoolean(String value){
    return value == null ? null : Boolean.valueOf(value);
  }

  public static String booleanToString(Boolean value){
    return value == null ? null : value.toString();
  }

}
