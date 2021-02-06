package reader;

import classes.OPCinfo;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

public class CsvReader {
	static Vector<OPCinfo> info = new Vector<OPCinfo>();
	static Scanner x;
	
	public static Vector<OPCinfo> getOPCFileInfo (String filepath) {
		Vector<String> infoArray= new Vector<String>();
		int i = 0;
		
		try {
			x = new Scanner(new File(filepath));
			x.useDelimiter("[,\n]");
			
			while(x.hasNext()) {
				for (int j = 0; j < 17; j++) {
					infoArray.add(x.next());
				}	
				
				if (i > 0) {
					formatReadArray(infoArray);
				} else { i++; }
				
				infoArray = new Vector<String>();
			}		
			
			return info;
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "There is an error reading the OPC csv");
		}
		
		return info;
	}
	
	public static void formatReadArray(Vector<String> lineInfo) {
		try {		
			OPCinfo opcInfo = new OPCinfo();
			
			String[] parts = lineInfo.get(0).split("\\.");
			String aux = new String();
			
			if (parts.length == 1) {
				opcInfo.setGroup(FormatString(lineInfo.get(0)));
				opcInfo.setTagName(FormatString(lineInfo.get(0)));
			} else {
				for (int i = 0; i < parts.length - 1; i++) {
					aux = aux + parts[i] + "_";
				}
				opcInfo.setGroup(FormatString(aux));
				opcInfo.setTagName(FormatString(parts[parts.length - 1]));
			}
			
			opcInfo.setAddress(lineInfo.get(1).charAt(0) + "0" + lineInfo.get(1).substring(1));
			opcInfo.setDataType(lineInfo.get(2).toUpperCase());
			
			if (lineInfo.get(4).equals("R/W")) {
				opcInfo.setClientAccess("Read/Write");
			} else if (lineInfo.get(4).equals("RO")) {
				opcInfo.setClientAccess("Read_Only");
			} else {
				opcInfo.setClientAccess("Unknown Error");
			}
				
			opcInfo.setDescription("\"" + lineInfo.get(15).replaceAll("<>", " <> ") + "\"");
			
			info.add(opcInfo);	
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	private static String FormatString(String str) {
		String strFormated = new String();
		
		strFormated = str.replaceAll("[\\#]", "num");
		strFormated = strFormated.replaceAll("[\\&]", "n");
		strFormated = strFormated.replaceAll("[\\@]", "at");
		strFormated = strFormated.replaceAll("[\\%]", "pct");
		strFormated = strFormated.replaceAll("\\-(\\d+)","neg$1");
		strFormated = strFormated.replaceAll("\\+(\\d+)","pos$1");
		strFormated = strFormated.replaceAll("([Ss])mall[\\_\\ ]([Vv])olume[\\_\\ ]([Pp])rover","$1$2$3");
		strFormated = strFormated.replaceAll("([Nn])umber","$1um");
		strFormated = strFormated.replaceAll("([Cc])ommunications","$1omms");
		strFormated = strFormated.replaceAll("([Rr])eference","$1ef");
		strFormated = strFormated.replaceAll("([Aa])utomatically","$1uto");
		strFormated = strFormated.replaceAll("([Mm])anually","$1anual");
		strFormated = strFormated.replaceAll("([Tt])emperature","$1emp");
		strFormated = strFormated.replaceAll("([Pp])ressure","$1ress");
		strFormated = strFormated.replaceAll("([Tt])ransmitter","$1ransmtr");
		strFormated = strFormated.replaceAll("([Dd])ensitometer","$1enstmtr");
		strFormated = strFormated.replaceAll("([Mm])eter","$1tr");
		strFormated = strFormated.replaceAll("([Cc])onstant","$1onst");
		strFormated = strFormated.replaceAll("([Vv])olume","$1ol");
		strFormated = strFormated.replaceAll("([Oo])bserved","$1bs");
		strFormated = strFormated.replaceAll("([Aa])uxiliary","$1ux");
		strFormated = strFormated.replaceAll("([Cc])onfiguration","$1onfig");
		strFormated = strFormated.replaceAll("([Dd])escription","$1escript");
		strFormated = strFormated.replaceAll("([Cc])alculation","$1alc");
		strFormated = strFormated.replaceAll("([Ff])requency","$1req");
		strFormated = strFormated.replaceAll("([Mm])aximum","$1ax");
		strFormated = strFormated.replaceAll("([Mm])inimum","$1ax");
		strFormated = strFormated.replaceAll("([Ss])ource","$1rce");
		strFormated = strFormated.replaceAll("([Vv])alue","$1al");
		strFormated = strFormated.replaceAll("([Gg])enerate","$1ener");
		strFormated = strFormated.replaceAll("([Dd])ensity","$1ens");
		strFormated = strFormated.replaceAll("([Ww])ith","$1");
		strFormated = strFormated.replaceAll("([Bb])utton","$1tn");
		strFormated = strFormated.replaceAll("([Nn])ext","$1xt");
		strFormated = strFormated.replaceAll("([Ll])ow","$1o");
		strFormated = strFormated.replaceAll("([Hh])igh","$1i");
		
		//User 2 -> User2
		strFormated = strFormated.replaceAll("([A-Za-z])\\s(\\d{1,})\\b","$1$2");
		
		strFormated = strFormated.replaceAll("[\\ \\.\\,\\!\\?\\#\\(\\)\\-\\+\\*\\/\\=\\$\\{\\}\\'\\^\\:\\;\\€\\[\\]]", "_");
		
		strFormated = strFormated.replaceAll("\\_\\_\\_","_");
		strFormated = strFormated.replaceAll("\\_\\_","_");	
		
		if (strFormated.charAt(0) == '_') {
			strFormated = strFormated.substring(1);
		} 
			
		if (strFormated.charAt(strFormated.length() - 1) == '_') {
			strFormated = strFormated.substring(0,strFormated.length() - 1);
		} 
		
		return strFormated;
	}
}