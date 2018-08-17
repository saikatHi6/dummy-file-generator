package org.atom.genarator.dto;

import java.util.List;
import java.util.Map;

import org.atom.genarator.util.FieldType;
import org.springframework.stereotype.Component;

@Component
public class FileStructure {
	
	
	Map<String,FieldType> columns;
	int rows;
	
	public Map<String,FieldType> getColumns() {
		return columns;
	}
	public void setColumns(Map<String,FieldType> columns) {
		this.columns = columns;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "FileStructure [columns=" + columns + ", rows=" + rows + "]";
	}
	
	

}
