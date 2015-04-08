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

public class CheckAttributes {
	public static String checkAttributes(Attribute attr){
    	String[] strTagNameArray = {"text", "title", "label","alt"};
		String result = "";
		
		String attKey = attr.getName();
		String attValue = attr.getValue().trim(); 
			  
		if (!attKey.startsWith("comments")){
    		for (String strAttribute: strTagNameArray) {
				if (attKey.startsWith(strAttribute)) {
					if (!attValue.equals("") && (attValue != null)
						&& !attValue.contains("I18NTRANSLATION") && !attValue.contains("I18NKEY")
						//Rules control
						&& !attValue.matches("\\{[^a-zA-Z0-9]?[a-z]+[A-Za-z]+.*\\}.*")    //Filter {+xInfoAvgResTime}_error
						&& !attValue.matches("[^a-zA-Z0-9]+") //Filter lines only containing symbols
						&& !(attValue.startsWith("{") && attValue.endsWith("}"))  //Filter {InfoAvgResTime}
						&& !(attValue.matches("\\:? ?\\{[a-zA-Z0-9]+}")) //Filter :{msg}
						&& !(attValue.matches("[0-9]+")) //Filter numbers
						) {
						//for debug
						System.out.println("HardCode attKey="+attKey+"  attValue="+attValue);
						//This is hardcode 
						result = attValue;
					}
				}
			}
		}
		return result;
	}
}
