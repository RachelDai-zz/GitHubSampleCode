package scanning;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import scanning.Logger;
import scanning.FilterKeywords;

public class ScanningDir {
	private static List<String> liFilterFiles = new ArrayList<String>();	
	
	public static Collection<File> scanSourceDir(String srcDir, String fileType, String filterConfDir){		
		Collection<File> all = new ArrayList<File>();
		//The dir and files need to be filtered
		liFilterFiles = FilterKeywords.getFilteredFiles(filterConfDir); 
		addTree(new File(srcDir), all, fileType);
		return all;
	}
	
	public static void addTree(File file, Collection<File> all, String fileType){
			File[] children = file.listFiles();
			if (children != null) {
				for (File child : children) {
					//Filter dirs which listed on the conf file
					if (child.isDirectory() && !liFilterFiles.contains(child.getPath())) { 
						addTree(child, all, fileType);
					} else {
							//To filter file types and files which listed on the conf file
							if (!fileType.equals("") && child.getName().endsWith(fileType) && !liFilterFiles.contains(child.getPath())) {
								all.add(child);
							} else if (fileType.equals("") && (child.getName().endsWith("html") || child.getName().endsWith("dust")) && !liFilterFiles.contains(child.getPath())){
								all.add(child);
							} else {
								;
							}
					}
				}
			} else {
				all.add(file);
			}
	}
}
