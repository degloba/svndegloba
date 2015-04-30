package com.degloba.excel;

import org.apache.commons.lang3.StringUtils;
import com.degloba.utils.Assert;

import java.util.Date;

/**
 * Excel Tools
 */
public class ExcelUtils {

	private ExcelUtils() {
	}

	/**
	 * The column names to the column index, for example, the column "A" is converted to 0
	 * @param columnName To convert the column names
	 * @return Index column parameters columnName representatives
	 */
	public static int convertColumnNameToIndex(String columnName) {
		Assert.notBlank(columnName);
		String theColumn = columnName.toUpperCase();
		int length = theColumn.length();
		int result = letterToInt(theColumn.charAt(length - 1));
		if (length == 1) {
			return result;
		}
		for (int i = 1; i < length; i++) {
			int letter = theColumn.charAt(length - i - 1);
			result = (letterToInt(letter) + 1) * ((int) Math.pow(26, i)) + result;
		}
		return result;
	}
	
	private static int letterToInt(int letter) {
		return letter - 65;
	}
	
	public static Double getDouble(Object data) {
		if (data == null) {
			return null;
		}
		if (! (data instanceof Double)) {
			throw new IllegalStateException("Data type error: The data in the cell is not a numeric type");
		}
		return (Double) data;
	}
	
	public static Integer getInt(Object data) {
		Double value = getDouble(data);
		return value == null ? null : value.intValue();
	}
	
	public static Long getLong(Object data) {
		Double value = getDouble(data);
		return value == null ? null : value.longValue();
	}
	
	public static Boolean getBoolean(Object data) {
		if (data == null) {
			return null;
		}
		if (! (data instanceof Boolean)) {
			throw new IllegalStateException("Data type error: The data in the cell is not a Boolean");
		}
		return (Boolean) data;
	}
	
	public static String getString(Object data) {
		if (data == null) {
			return null;
		}
		if (StringUtils.isBlank(data.toString())) {
			return null;
		}
		return data.toString();
	}
	
	public static Date getDate(Object data, Version version, boolean isDate1904) {
		Double value = getDouble(data);
		return version.getDate(value, isDate1904);
	}
	
}
