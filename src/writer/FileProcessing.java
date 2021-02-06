package writer;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileProcessing {
	
	public static File getWriteFilepath (String filepath, String filename) {
		
		String format = "csv";
		
		Pattern patternRepeatedFile = Pattern.compile("([A-Za-z])(\\d{1,})(." + format + ")");
		
		String nameFile = filepath + "\\" + filename + "ModbusDVR." + format;
		
		File file = new File(nameFile);
		String AUXfilepath = file.getAbsolutePath();
		
		while (file.isFile()) {	
			Matcher matcher = patternRepeatedFile.matcher(AUXfilepath);
			
			if (matcher.find()) {
				int num = Integer.valueOf(matcher.group(2)) + 1;
				AUXfilepath = AUXfilepath.replaceAll("([A-Za-z])\\d{1,}(." + format + ")","$1"+ String.valueOf(num) +"$2");
				
				 file = new File(AUXfilepath);
			} else {
				AUXfilepath = AUXfilepath.replaceAll("([A-Za-z])(." + format + ")","$11$2");
				
				file = new File(AUXfilepath);
			}
	    }
		
		return file;
	}
}
