package com.degloba.excel;

import com.degloba.utils.Assert;

/**
 * ExcelCell
 *
 */
public class ExcelCell {
	private int sheetIndex = 0;
	private String sheetName;
	private int rowIndex;
	private int columnIndex;
	
	/**
	 * Specifies the worksheet numbers, generate ExcelCell examples
	 * @param sheetIndex
	 * @return
	 */
	public static ExcelCell sheetIndex(int sheetIndex) {
		return new ExcelCell(sheetIndex);
	}
	
	private ExcelCell(int sheetIndex) {
		if (sheetIndex < 0) {
			throw new IllegalArgumentException("Sheet index cannot less than 0!");
		}
		this.sheetIndex = sheetIndex;
	}
	
	/**
	 * Specifies the worksheet name, generate ExcelCell examples
	 * @param sheetName
	 * @return
	 */
	public static ExcelCell sheetName(String sheetName) {
		return new ExcelCell(sheetName);
	}

	private ExcelCell(String sheetName) {
		Assert.notBlank(sheetName, "Sheet name cannot be null or blank!");
		this.sheetName = sheetName;
		this.sheetIndex = -1;
	}

	public int getSheetIndex() {
		return sheetIndex;
	}

	public String getSheetName() {
		return sheetName;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public ExcelCell rowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
		return this;
	}
	
	public ExcelCell row(int rowIndex) {
		this.rowIndex = rowIndex;
		return this;
		
	}

	public ExcelCell column(int columnIndex) {
		this.columnIndex = columnIndex;
		return this;
	}

	public ExcelCell column(String columnName) {
		return column(ExcelUtils.convertColumnNameToIndex(columnName));
	}
}