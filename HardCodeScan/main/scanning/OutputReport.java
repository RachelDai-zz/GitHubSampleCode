package scanning;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import scanning.Logger;

public class OutputReport {
	public static void printReport(String reportPath, Collection cl,
			int totalFiles, int totalHardcodeStrings){
		//String fileName = reportPath + "\\HardCodeReport.htm";
	    File f = new File(reportPath);
	    
	    try {
	    	if (!f.exists()) {
	    		f.createNewFile();
	    	}
	    	BufferedWriter output = new BufferedWriter(new FileWriter(f));
	    	output.write(""
	    			+ "<html xmlns:stringutils=\"xalan://org.apache.tools.ant.util.StringUtils\" xmlns:lxslt=\"http://xml.apache.org/xslt\">\r\n"
	    			+ "    <head></head>\r\n"
	    			+ "<body onload=\"open('allclasses-frame.html','classListFrame')\">\r\n"
	    			+ "    <h1>Scanning Results.</h1>\r\n"
	    			+ "    <hr size=\"1\"></hr>\r\n"
	    			+ "");
	    			
	       
		   output.append("</tr>Total Scanning " + totalFiles + " files.   "
				    + "Total Hardcode " + totalHardcodeStrings + " strings.</tr>");
	    	
	    			
	       output.append("    <table class=\"details\" width=\"95%\" cellspacing=\"2\" cellpadding=\"5\" border=\"0\">\r\n"
	    			+ "        <tbody>\r\n"
	    			+ "            <tr valign=\"top\">\r\n"
	    			+ "                <th>File Name</th>\r\n"
	    			+ "                <th>Line</th>\r\n"
	    			+ "                <th>Content</th>\r\n"
	    			+ "                <th>String</th>\r\n"
	    			+ "            </tr>\r\n");
	    			
	    	
	    	Iterator iter = cl.iterator();
	    	while (iter.hasNext()) {
	    		List ll = (List)iter.next();
	    		
	    		if (!ll.isEmpty()) {
		    		String fileN = ll.get(0).toString();
		    		String lineN = ll.get(1).toString();
		    		String strHard = "";
		    		String Strcontent = "";
		    		
		    		if (ll.size() > 2) {
		    			Strcontent = ll.get(2).toString();
		    			Strcontent = Strcontent.replaceAll("<", "&lt ").replaceAll(">", " &gt");
		    			if (ll.size() > 3) {
			    			strHard = ll.get(ll.size() - 1).toString();
			    			strHard = strHard.replaceAll("<", "&lt ").replaceAll(">", " &gt");
			    		}
		    		}
		    		 
		    		output.append("<meta  charset='utf-8'></meta>\r\n");
		    		output.append("<tr class=\"HardCode\" valign=\"top\">\r\n"
		    				+ "                <td>" + fileN + "</td>\r\n"
		    				+ "                <td>" + lineN + "</td>\r\n"
		    				+ "                <td>" + Strcontent + "</td>\r\n"
		    				+ "                <td>" + strHard + "</td>\r\n"
		    			    + "            </tr>\r\n");
		    	}
	    	    }
		    	output.append(""
		    			+ "        </tbody>\r\n"
		    			+ "    </table>\r\n"
		    			+ "</body>\r\n"
		    			+ "</html>\r\n");
		    	
	    	output.flush();
	    	cl.clear();
	    } catch (IOException ioe) {
	    	ioe.printStackTrace();
	    	Logger.logToFile("Writing report exception: " + ioe.getMessage());
	    }
	    
	}
}
