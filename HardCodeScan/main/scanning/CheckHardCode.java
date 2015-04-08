package scanning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import scanning.HTMLParser;
import scanning.ScanningDir;
import scanning.OutputReport;
import scanning.CheckAttributes;
import scanning.CheckDataBind;
import scanning.Logger;

import org.htmlparser.*;
import org.htmlparser.sax.Attributes;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;
import org.htmlparser.visitors.NodeVisitor;


public class CheckHardCode {
//############################################
// There are 3 possiblities for hardcode
// Below exampels AAA should be hardcode
// 1. Between tags <div>AAA</div>
// 2. Inside data-bind, data-bind="text: 'AAA'"
// 3. Attributes outside data-bind, <div text="AAA" data-bind="text: text">
	
	    private static Collection<List> cl = new ArrayList<List>();  //For restore final result storage
	    private static String htmlName = "";
	    private static boolean bMark = true; //Mark the scripts, when bMark true means non-script, non-style
	    //private static boolean bCode = true; //True this tag non-code
	    private static File logFile = new File("./HardCodeScan.log");
	    private static List filterList = new ArrayList();
	    
	   /* public static List readFromFilterConf(){
	    	//List li = new ArrayList();
	    	filterList = FilterKeywords.Filtering();
	    	return filterList;
	    }*/
	    
	    public static void checkHardCode(String srcDir, String reportPath, String filterConfDir, String fileType) {
	    	Collection<File> all = ScanningDir.scanSourceDir(srcDir, fileType, filterConfDir);
	    	Iterator iterFiles = all.iterator();
	    	//readFromFilterConf();
	    	
	    	while (iterFiles.hasNext()) {
	    		File file = (File)iterFiles.next();
	    		htmlName = file.getPath();
	    		//For debug
	    		System.out.println("\r\nNow scanning " + htmlName);
	    		
	    		try {
	    			Parser parser = HTMLParser.getDocument(htmlName);
	    			parser.visitAllNodesWith(new NodeVisitor() {
	    				public void visitTag (Tag tag)
	    			     {
	    					 List<Attribute> v = tag.getAttributesEx();
	    					 Iterator iter = v.iterator();
	    					 while (iter.hasNext()) {
	    						 Attribute attr = (Attribute)iter.next();
	    						 int p = tag.getStartPosition();
	    		    			 int LineNum = tag.getPage().row(p) + 1;
	    		    			 String attName = attr.getName();
	    						 System.out.println("Attname=" + attName + " Row=" + LineNum);
	    						 String result = "";
	    						 if (attName != null  && attName.equals("data-bind") && attr.isValued()) {
	    							 result = CheckDataBind.checkDataBind(attr, htmlName);
	    						 } else if (attName != null && attr.isValued() ) {
	    							 if (attr.getValue().contains("code") || attr.getValue().contains("Code")) {
	    								 //bCode = false; //Mark this tag is code sample, ignore it
	    								 bMark = false;
	    							 }
	    							 result = CheckAttributes.checkAttributes(attr);
	    						 } else if (attName != null  && (attName.equals("script") || attName.equals("style"))) {
	    							 bMark = false;
	    						 } /*else if (attName != null  && attName.equals("textarea") && !bCode){
	    							 bCode = true; //When matching this textarea, set bCode = true, checking work restarts
	    						 }*/
	    						 if (!result.equals("")) {  
	    		    				 List list = new ArrayList();
	    		    				 list.add(htmlName);
	    		    				 list.add(LineNum);
	    		    				 list.add(attr);
	    		    				 list.add(result);
	    		    				 cl.add(list);
	    		    			 }
	    						 
	    					 }
	    			     }

	    			     public void visitStringNode (Text string)
	    			     {
	    			    	 
	    			    	 int p = string.getStartPosition();
	    			    	 int LineNum = string.getPage().row(p + 2) + 1;
	    			    	 
	    			    	 String stringNode = string.getText().trim();
	    			         System.out.println ("StringNode=" + stringNode + " Row=" + LineNum);
	    			         boolean b = false;
	    			         if (!stringNode.equals("") && bMark) {
	    			        	 bMark = true;
	    			        	 b = CheckText.checkText(stringNode);
	    			         } else {
	    			        	 bMark = true;
	    			         }
	    			         if (b) {
    		    				 //To avoid  wrong separation of list  
    		    				 if (stringNode.endsWith(",")) {
    		    					 stringNode = stringNode.substring(0, stringNode.length() - 2);
    		    				 } 
    		    				 if (stringNode.startsWith(",")) {
    		    					 stringNode = stringNode.substring(1);
    		    				 }
		    					 List list = new ArrayList();
    		    				 list.add(htmlName);
    		    				 list.add(LineNum);
    		    				 list.add(stringNode);
    		    				 list.add(stringNode);
    		    				 cl.add(list);
	    			         }
	    			     }
	    			});
	    		} catch (Exception pe) {
	    			pe.printStackTrace();
	    			Logger.logToFile(htmlName + "   " + pe.getMessage());
	    		}
	    	}

	    	//Before print, filtering the results according to conf file
	    	cl = FilterKeywords.filterKeywords(filterConfDir, cl);
	    	
	    	//Get hardcode collection size after filtering
	    	int totalFiles = all.size();
	    	int totalHardCodeString = cl.size();
	    	
	    	//Write report
	    	System.out.println("\r\n" + cl);
			System.out.println("\r\nTotal Scanning " + totalFiles + " files.\r\n");
			System.out.println("\r\nTotal Hardcode " + totalHardCodeString + " strings.\r\n");
			System.out.println("\r\nLog File  " + logFile.getAbsolutePath());
			
			OutputReport.printReport(reportPath, cl, totalFiles, totalHardCodeString);
	    }
	   
		
		public static void main(String[] args){
			if (logFile.exists()) {
				logFile.delete();
			}
			
			String srcPath = "";
			String reportPath = "";
			String confPath = "";
			String fileType = "";
			
			int argNum = args.length;
			switch (argNum) {
			case 2:
				srcPath = args[0].toString().trim();
				reportPath = args[1].toString().trim();
				break;
			case 4:
				srcPath = args[0].toString().trim();
				reportPath = args[1].toString().trim();
				confPath = args[2].toString().trim();
				fileType = args[3].toString().trim();
				break;
			case 3:
				srcPath = args[0].toString().trim();
				reportPath = args[1].toString().trim();
				if (args[2].toString().trim().equals("dust") || args[2].toString().trim().equals("html")) {
					fileType = args[2].toString().trim();
				} else {
					confPath = args[2].toString().trim();
				}
				break;
			default:
				System.out.print("Please input src dir and report path in correct order.");
		    	System.exit(0);
			}
			
			//check validation of parameters
			File srcFile = new File(srcPath);
			File reportFile = new File(reportPath);
			File confFile = new File(confPath);
			if (!srcFile.isDirectory() && !srcFile.isFile()) {
				System.out.print("Please input the correct src dir/file.");
		    	System.exit(0);
			}
			if (reportFile.isDirectory()) {
				System.out.println("Please provide correct report name.");
				System.exit(0);
			}
			if (!confPath.equals("")) {
				if (confFile.isDirectory() || !confFile.exists()) {
					System.out.println("Please provide correct filter conf file name.");
					System.exit(0);
				}
			}
			if (!fileType.equals("") && !fileType.equals("dust") && !fileType.equals("html")) {
				System.out.println("Please provide correct file type name.");
				System.exit(0);
			}
			
			CheckHardCode.checkHardCode(srcPath, reportPath, confPath, fileType);
		}
		
		
		
		
		
}
