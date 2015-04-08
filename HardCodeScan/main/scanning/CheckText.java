package scanning;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.htmlparser.sax.Attributes;
import org.htmlparser.*;
import scanning.Logger;

public class CheckText {
	public static boolean checkText(String stringNode){
		boolean b = false;
		
		if (!stringNode.startsWith("comments")){
			if (!stringNode.contains("I18NTRANSLATION") && !stringNode.contains("I18NKEY")
				//Rules control
				&& !stringNode.matches("\\{[^a-zA-Z0-9]?[a-z]+[A-Za-z]+.*\\}.*")    //Filter {+xInfoAvgResTime}_error
				&& !stringNode.matches("[^a-zA-Z0-9]+") //Filter lines only containing symbols
				&& !(stringNode.startsWith("{") && stringNode.endsWith("}"))  //Filter {InfoAvgResTime}
				&& !(stringNode.matches("^\\:? ?\\{[a-zA-Z0-9]+}")) //Filter :{msg}
				&& !(stringNode.matches("[0-9]+")) //Filter numbers
				&& !(stringNode.contains("DUSTIMPORT")) // Filter {>
				&& !(stringNode.equals("&nbsp;"))
								
				) {
				//for debug
				System.out.println("HardCode stringNode="+stringNode);
				//This is hardcode 
				b = true;
			}
		}
		return b;
	}
}
