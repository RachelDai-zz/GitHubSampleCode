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
import scanning.Logger;

public class FilterKeywords {
	//private static String filterPath = "./FilterKeywords.conf";
	//private static List rl= new ArrayList();
	private static List<String> liKeywords = new ArrayList<String>();
	private static List<String> liFiles = new ArrayList<String>();
	
	public static void readConfFile(String filterConfDir){
		File filterFile = new File(filterConfDir);
		List<String> rl= new ArrayList<String>();
		
		try {
			rl = Files.readAllLines(filterFile.toPath(), Charset.defaultCharset());
		} catch (IOException ioe) {
			ioe.printStackTrace();
			Logger.logToFile("Reading keywords conf exception: " + ioe.getMessage());
		}

		for (int i = 1; i < rl.size(); i++) {
			if (!rl.get(i).toString().trim().equals("[FILES]") && !rl.get(i).toString().trim().equals("")) {
				liKeywords.add(rl.get(i));
			}
			if (rl.get(i).toString().trim().equals("[FILES]")) {
				for (int j = i + 1; j < rl.size(); j++) {
					if (!rl.get(j).toString().trim().equals("")) {
						liFiles.add(rl.get(j));
					}
				}
				break;
			}
		}
		//return rl;
	}
	
	public static List<String> getFilteredFiles(String filterConfDir){
		readConfFile(filterConfDir);
		return liFiles;
	}
	
	public static Collection<List> filterKeywords(String filterConfDir, Collection cl){
		Collection<List> result= new ArrayList<List>();
		Collection<List> result1= new ArrayList<List>();
		//Base filtering. Adding filtering regular expressions below as framework level filter
		// 1. non-ascii results
		// 2. html elements
		Iterator i = cl.iterator();
		while (i.hasNext()) {
			List temp = (List)i.next();
			if(!temp.isEmpty() 
			   && (temp.get(3).toString().trim().matches("[a-zA-Z]+")  //match non-ascii strings
			   || temp.get(3).toString().trim().matches("^(&[a-zA-Z0-9]+;)+$"))) //match &lt; &gt html elements
		    {
				// Remove it by setting list to empty
				temp.clear();
			} 
		}
		
		
		//Filter conf file keywords if conf file isn't empty
		if (!liKeywords.isEmpty()) {
			Iterator iterColl = cl.iterator();
			while (iterColl.hasNext()) {
				List temp = (List)iterColl.next();
				if (!temp.isEmpty()) {
					for (String keyWord: liKeywords) {
						//for debug
						//System.out.println("Rachel Debug: " + temp);
						if (!temp.isEmpty() && temp.get(3).toString().trim().equals(keyWord.trim())) {
							//Set it to null 
							temp.clear();
						} 
					}
				}
			}
		}

		Iterator i1 = cl.iterator();
		while (i1.hasNext()) {
			List temp = (List)i1.next();
			if (!temp.isEmpty()) {
				result.add(temp);
			}
		}
		
		return result;
	}
	
	public static void main(String [] args) {
	}
	
}
