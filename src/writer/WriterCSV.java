package writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import classes.*;


public class WriterCSV {
	public static void writeFile(GroupColection COLECTION, String filepathWrite) {		
		Vector<CSVInfo> vecFileInfo = createCSVFileFormat(COLECTION, filepathWrite);
		
		for (CSVInfo infoWrite : vecFileInfo) {
			printFileString(infoWrite);
		}
	}
	
	public static void printFileString(CSVInfo fileInfo) {
		
		String info = fileInfo.getInfoToWrite();
		File file = fileInfo.getFile();
		
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsolutePath(),false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.print(info);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Vector<CSVInfo> createCSVFileFormat(GroupColection COLECTION, String filepathWrite) {

		Vector<CSVInfo> vecInfoToWrite = new Vector<CSVInfo>();
		StringBuilder lineWrite = new StringBuilder();
		
		String header = "TagName,Address,DataType,ClientAccess,Description";	
		
		for (GroupInfo info : COLECTION.getVecGroupInfo()) {		
			lineWrite.append(header + "\n");
			
			for (TagInfo tag : info.getTagInfo()) {
				lineWrite.append(tag.getTagName() + ",");
				lineWrite.append(tag.getAddress() + ",");
				lineWrite.append(tag.getDataType() + ",");
				lineWrite.append(tag.getClientAccess()+ ",");
				lineWrite.append(tag.getDescription() + "\n");
			}
			
			File file = FileProcessing.getWriteFilepath(filepathWrite, info.getGroupName());
		
			vecInfoToWrite.add(new CSVInfo(lineWrite.toString(), file));
			
			lineWrite = new StringBuilder();
		}
		
		return vecInfoToWrite;
	}
}
