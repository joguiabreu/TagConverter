package home;

import classes.GroupColection;
import classes.OPCinfo;
import processInfo.ProcessOPCinfo;
import reader.CsvReader;
import writer.WriterCSV;

import java.util.Vector;

public class Home {

	public static void main(String[] args) {
		GroupColection COLECTION = new GroupColection();
		
		String filepathRead = "C:\\Users\\João Guilherme\\Desktop\\Nano_Stream_1.csv";
		String filepathWrite = "C:\\Users\\João Guilherme\\Desktop\\test";
		
		Vector<OPCinfo> vecOPCinfo = CsvReader.getOPCFileInfo(filepathRead);
		COLECTION = ProcessOPCinfo.Process(vecOPCinfo, COLECTION);
		
		WriterCSV.writeFile(COLECTION, filepathWrite);
	}
}
