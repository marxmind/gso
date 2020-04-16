package com.italia.marxmind.gso.filereader;
/**
 * 
 * @author Mark Italia
 * @version 1.0
 * @since 04/09/2020
 *
 */
public enum FileExtension {
	
	XLS("xls"),
	XLSX("xlsx"),
	DOC("doc"),
	DOCX("docx");
	
	private FileExtension(String name) { 
		this.name = name;
	}
	
	private String name;
	
	public String getName() {
		return name;
	}
}
