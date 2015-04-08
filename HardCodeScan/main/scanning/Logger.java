package scanning;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

	private static String logPath = "./HardCodeScan.log";
	
	public static void logToFile(String strLog){
		File logFile = new File(logPath);
		if (!logFile.exists()) {
			try{
				logFile.createNewFile();
			} catch (IOException ioe){
				ioe.printStackTrace();
			}
		}
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true));  
			bw.append(strLog + "\r\n");
			bw.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	
	
	
}
