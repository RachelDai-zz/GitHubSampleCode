package scanning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.htmlparser.Parser;
import scanning.Logger;


public class HTMLParser {
	private final static String i18nTranslation = "I18NTRANSLATION";
	private final static String i18nKey = "I18NKEY";
	private final static String dustImport = "<DUSTIMPORT>";
	private final static String dustGramma = "<DUSTGRAMM>";
	private final static String dustComments = "<DUSTComments>";
	
	public static Parser getDocument(String htmlName){
		Parser result = null;
		File f = new File(htmlName);
		try {
			BufferedReader bs = new BufferedReader(new FileReader(f));
			
			int numLine = 0;
			String line = null;
			StringBuilder  stringBuilder = new StringBuilder();
			boolean bUnfinished = false;
			while ((line = bs.readLine()) != null) {
				 if (line.trim().startsWith("{") && line.trim().endsWith("}")) {
					line = line.replace(line, dustGramma);
				 }
				 if (line.trim().equals("{")) {
					 line = line.replace(line, dustGramma);
				 }
				 if (line.trim().equals("}")) {
					 line = line.replace(line, dustGramma);
				 }
				 if (line.trim().startsWith("{") && !line.trim().endsWith("}")) {
					 bUnfinished = true;
					 line = line.replace(line, dustGramma);
				 }
				 if (bUnfinished && line.trim().endsWith("}")) {
					 bUnfinished = false;
					 line = line.replace(line, dustGramma);
				 }
				 if (bUnfinished) {
					 line = line.replace(line, dustGramma);
				 }
				 stringBuilder.append(line + "\r\n");
				 numLine = numLine + 1;
			}
			//for debug
			System.out.print("File total has " + numLine  +" lines.\r\n");
			bs.close();
			
			String tempRead = stringBuilder.toString();
			//Remove comments
			String result1 = handleDustComments(tempRead);
			//Handle quotes for replacing i18n blank 
			String result2 = handleQuotes1(result1);
			String result3 = handleQuotes2(result2);
			String result4 = handleDustImport(result3);
			//Parse
			result = new Parser(result4);	
		} catch (Exception e) {
			e.printStackTrace();
			Logger.logToFile(htmlName + "   " + e.getMessage());
		}
		return result;
	}
	
	public static String handleDustComments(String content) {
		String result = "";
		
		while (content.contains("{!") && content.contains("!}")) {
			int i1 = content.indexOf("{!");
			int i2 = content.indexOf("!}");
			String sub = content.substring(i1, i2 + 2);
			content = content.replace(sub, dustComments);
		}
		result = content;
		//for debug
		System.out.println("handleDustComment:  return " + result );
		return result;
	}
	
	public static String handleDustImport(String content) {
		String result = "";
		
		while (content.contains("{>") && content.contains("}")) {
			int i1 = content.indexOf("{>");
			String subStr = content.substring(i1);
			int i2 = subStr.indexOf("}");
			String sub = subStr.substring(0, i2 + 1);
			content = content.replace(sub, dustImport);
		}
		result = content;
		//for debug
		System.out.println("handleDustImport:  return " + result );
		return result;
	}
	
	public static String handleQuotes1(String text){
		String result = "";
		String tempText = "";
		String i18nTrans = "i18n.translate";
		while (text.contains(i18nTrans)) {
		
			tempText = text.substring(text.indexOf(i18nTrans));
			int n1 = 0;
			String replaceStr = "";
			if (tempText.contains(")")){
				n1 = tempText.indexOf(")");
				replaceStr = tempText.substring(tempText.indexOf(i18nTrans), n1) + ")";
				String strLeftBrace = tempText.substring(tempText.indexOf(i18nTrans), n1);
				if (replaceStr.indexOf("(") != replaceStr.lastIndexOf("(")) {  //Indicate there are more than one () in the text
					String temp = tempText.substring(n1);
					char[] charArray = replaceStr.toCharArray();
					int j = 0;
					for (int i = 0; i < charArray.length; i++){
						if (Character.toString(charArray[i]).equals("(")) {
							j = j + 1;
						}
					}
					String strRightBrace = lookForBrace(temp, j);   //temp includes first )
					String strBrace = (strLeftBrace + strRightBrace);
					
					text = text.replace(strBrace, i18nTranslation);
				} else {
					text = text.replace(replaceStr, i18nTranslation);
					
				}
			}
		}
		result = text;
		//for debug
		System.out.println("handleQuotes1: return: " + result);
		return result;
	}
	
	public static String lookForBrace(String tempText, int n){
		int end  = 0;
		String result = "";
		char[] chArray = tempText.toCharArray();
		
		for (int i = 0; i< chArray.length; i++) {
			if (i == 0) {
				n = n - 1;
			}
			//To escape () in pair
			else if ((i > 0) && (Character.toString(chArray[i]).equals(")")) && (!Character.toString(chArray[i - 1]).equals("("))) {
				n = n - 1;
				
			}
			if (n == 0) {
				end = i + 1;
				break;
			}
		}	
		result = tempText.subSequence(0,  end).toString();
		//for debug
		System.out.println("lookForBrace: return: " + result);
		return result;
	}
	
	public static String handleQuotes2(String text){
		String i18Key = "@i18n key=\"";
		String result = "";
		String replaceStr = "";
		String tempText = "";
		while (text.contains(i18Key)) {
			tempText = text.substring(text.indexOf(i18Key) - 1);
			int n1 = 0;
			if (tempText.contains("/}")){
				n1 = tempText.indexOf("/}");
				//For fixing dead-loop bug
				//replaceStr = tempText.substring(tempText.indexOf(i18Key), n1) + "\"";
				replaceStr = tempText.substring(0, n1 + 2);
				text = text.replace(replaceStr, i18nKey);
				
			} else {
				//for debug
				System.out.println("handleQuotes2 No handle " + text);
			}
			
		}
		result = text;
		//for debug
		System.out.println("handleQuotes2: return " + result);
		return result;
	}
	
	public static String handleSquareBrakcet(String text){
		String result = "";
		if (text.contains("{>")) {
			text = text.replaceAll("\\[", "(");
		}
		if (text.contains("]")) {
			text = text.replaceAll("\\]", ")");
		}
		result = text;
		return result;
	}
	
	public static void main(String args[]){
		String aaa = "{@eq key=\"{clickable}\" value=\"false\"}";
		getDocument(aaa);
	}
}
