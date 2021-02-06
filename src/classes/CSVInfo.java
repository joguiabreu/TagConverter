package classes;

import java.io.File;

public class CSVInfo {
	String infoToWrite;
	File file;
	
	public CSVInfo() {
		super();
	}
	
	public CSVInfo(String infoToWrite, File file) {
		super();
		this.infoToWrite = infoToWrite;
		this.file = file;
	}
	
	public String getInfoToWrite() {
		return infoToWrite;
	}
	
	public void setInfoToWrite(String infoToWrite) {
		this.infoToWrite = infoToWrite;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
}
