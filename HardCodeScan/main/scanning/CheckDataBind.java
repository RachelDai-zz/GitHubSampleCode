package scanning;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Attribute;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.IRFactory;
import org.mozilla.javascript.Token;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NodeVisitor;
import org.mozilla.javascript.ast.StringLiteral;

import scanning.Logger;

public class CheckDataBind {
	private static List<String> hashList = new ArrayList();
	private final static String curlyBrace = "CURLYBRACE";
	private static int colon = -1;
	private static String nodeValue = "";
	private static String nodeKey = "";
	private final static String dustImport = "<DUSTIMPORT>";
	private final static String dustGramma = "<DUSTGRAMM>";
	private final static String dustComments = "<DUSTComments>";
	
	public static String checkDataBind(Attribute attr, String htmlName){
		//for bug fix
		hashList.clear();
		
    	String[] strTagNameArray = {"text", "title", "label", "value"};	
    	
    	String result = "";
		String attKey = attr.getName();
		String attValue = attr.getValue().trim();
			
		if (!attKey.startsWith("comments") && attKey.startsWith("data-bind")) {	
			System.out.println("Attr=" + attr.toString() + "\r\n" + "data-bind="+attValue+"\r\n");
			if (attValue.matches("[a-zA-Z0-9]+")) {
				result = attValue;
			} else {
				//Call javascript parser to parse data-bind value
				String strParse = "var data={" + attValue + "}";
				//for debug
				System.out.println("strParse=" + strParse);
				List<String> parseDataBindMap = hashList;
				//try {
				javascriptParser(strParse, htmlName);
				for(String tempPair: parseDataBindMap){
					int i = tempPair.indexOf("=");
					String tempKey = tempPair.substring(0, i);
					String tempValue = tempPair.substring(i + 1);
					for (String strTag: strTagNameArray) {
					  if (strTag.equals(tempKey) 
							&& !tempValue.contains("I18NTRANSLATION") 
							&& !tempValue.contains("I18NKEY")
							&& !tempValue.contains("CURLYBRACE")
							&& !tempValue.matches("[0-9]+\\.?[0-9]+") //Filter only numbers
						    && !tempValue.matches("[^a-zA-Z]+")  //Filter Only symbols without words
						  ){  
							//This is hardcode
							result = tempKey + " : " + tempValue;
					  	}
					}
			    }
				/*} catch (Exception e) {
					e.printStackTrace();
				}*/
			}
		} 
    	return result;
	}
	
	public static String handleCurlyBrace(String strParse){
		String result;
		Matcher matcher = Pattern.compile("\\{\\+?[a-zA-Z0-9]+\\/?\\}").matcher(strParse);
		//The dustGramma, dustImport, dustComments will cause parse exception here
		if (strParse.contains(dustGramma)){
			strParse = strParse.replace(dustGramma, "");
		} 
		if (strParse.contains(dustImport)) {
			strParse = strParse.replace(dustImport, "");
		} 
		if ( strParse.contains(dustComments)) {
			strParse = strParse.replace(dustComments, "");
		}
		if (matcher.find()) {
			int start = matcher.start();
			int end = matcher.end();
			String replaceStr = "";
			String sub = strParse.substring(start, end);
			if (sub.contains("+") && sub.contains("/")) {
				int i1 = sub.indexOf("+") + 1;
				int i2 = sub.indexOf("/");
				replaceStr = "\\{\\+" + sub.substring(i1, i2) + "\\/\\}";
			} else {
				replaceStr = "\\" + sub.substring(0, sub.lastIndexOf("}")) + "\\}";
			}
			strParse = strParse.replaceAll(replaceStr, curlyBrace);
			System.out.println("The Curly Brace word: " + sub + "\r\nReplace string: " + replaceStr);
		}
		result = strParse;
		//for debug
		System.out.println("CurlyBrace after replace: " + result);
		return result;
	}
	
	public static void javascriptParser(String strParse, String htmlName) {
		CompilerEnvirons env = new CompilerEnvirons();
		env.setRecoverFromErrors(true);
		IRFactory factory = new IRFactory(env);
		AstRoot rootNode =  null;
		try {
			strParse = handleCurlyBrace(strParse);
			rootNode = factory.parse(strParse, null, 0);	
			//hashList.clear();
		
			rootNode.visit(new NodeVisitor(){
				public boolean visit(AstNode node) {
					printNode(node);
					return true;
				}

				void printNode (AstNode node){ 
					int type = node.getType();
					switch (type){
					case Token.COLON:
						//for debug
						System.out.println("This is colon: " + type);
						/*if ((colon == 2) && !hashList.contains(nodeKey + "=" + nodeValue)
								&& !nodeKey.equals("") && !nodeValue.equals("")) {  //for branch nested cases
							hashList.add(nodeKey + "=" + nodeValue);
							colon = 0;  
						}*/ 
						if (colon == 2) {
							colon = 0;
						} else {
							colon = 0; 
							nodeValue = "";
							nodeKey = "";
						}
						break;
					case Token.NAME:
						if (node instanceof Name){
							Name name = (Name)node;
							String nodeName =  name.getIdentifier();
							
							if (colon == 0) {
								nodeKey = nodeName;
								colon = 1;   //1 indicates key
							}
							
							//for debug
							System.out.println(nodeName);
						}
						break;
					case Token.STRING:
						if (node instanceof StringLiteral){ //Only check literal
							String nodeStr = ((StringLiteral)node).getValue();
							//for deubg
							System.out.println(nodeStr);
							if (colon == 0) {
								nodeKey = nodeStr;
								colon = 1; 
							} else if (colon == 1) {
								nodeValue = nodeStr;
								colon = 2; 
							}
						}
						if ((colon == 2) && !hashList.contains(nodeKey + "=" + nodeValue)
								&& !nodeKey.equals("") && !nodeValue.equals("")) {  //for branch nested cases
							hashList.add(nodeKey + "=" + nodeValue);
							colon = 0;  
						}
						break;
				
					default:
						//for debug
						System.out.println("type="+type);
						return;
					}
				}
			});
		} catch (Exception e) {
			strParse = strParse.replace("var data={", "").replace("}", "");
			Logger.logToFile(htmlName + "   " + e.getMessage());
		}
		//for debug
		System.out.println("HashList=" + hashList);
}
}
