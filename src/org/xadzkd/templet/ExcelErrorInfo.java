package org.xadzkd.templet;

public class ExcelErrorInfo{
	private Integer	row;
	private Integer column;
	private String info;
	
	public ExcelErrorInfo(Integer row,Integer column,String errorInfo)
	{
		this.row=row;
		this.column=column;
		this.info=errorInfo;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
