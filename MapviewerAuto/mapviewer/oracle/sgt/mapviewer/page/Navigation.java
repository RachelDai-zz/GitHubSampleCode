package oracle.sgt.mapviewer.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import oracle.sgt.mapviewer.common.constants.TextConstants;
import oracle.sgt.mapviewer.page.BasePage;
import oracle.sgt.mapviewer.page.Util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import com.oracle.sgt.webdriver.common.VerificationException;
import com.oracle.sgt.webdriver.util.LocaleResourceGetter;
import com.oracle.sgt.webdriver.util.TRSReportUtil;
import com.oracle.sgt.webdriver.util.TestDataReader;


public class Navigation extends BasePage{
	static String login = LocaleResourceGetter.getLocaleString(TextConstants.Login);
	static String logout = LocaleResourceGetter.getLocaleString(TextConstants.Logout);
	
	//Navigate to Admin
	public static void navigateToAdmin(){
		String admin = LocaleResourceGetter.getLocaleString(TextConstants.Admin);
		String admin1 = LocaleResourceGetter.getLocaleString(TextConstants.Admin1);
		
		Util.clickPath("//a[text()=\""+admin+"\"]", "//span[text()=\"" + admin1 + "\"]");
	}
	
	//Navigate to Map Builder
	public static void navigateToMapBuilder() {
		String mapbuilder = LocaleResourceGetter.getLocaleString(TextConstants.Mapbuilder);
		//String styles = LocaleResourceGetter.getLocaleString(TextConstants.Styles);
		
		Util.clickPath("//a[text()=\"" + mapbuilder + "\"]", "//span[text()='MapBuilder']");
		//Choose datasource
		Util.clickPath("//select/option[@value='0']");
	}
	
	//	Navigate to Meta data
	public static void navigateToMetadata() {
		String metadata = LocaleResourceGetter.getLocaleString(TextConstants.Metadata);
		String existingdb = LocaleResourceGetter.getLocaleString(TextConstants.ExistingDB);
		String splitDB = existingdb.split(" ")[0].toString();
		
		Util.clickPath("//div/a[text()=\"" + metadata + "\"]", "//h2[contains(text(), \"" + splitDB + "\")]");
	}
	
	//Navigate to Home
	public static void navigateToHome() {
		String home = LocaleResourceGetter.getLocaleString(TextConstants.Home);
		String getstarted = LocaleResourceGetter.getLocaleString(TextConstants.Homestarted);
		
		Util.clickPath("//div/a[text()=\"" + home + "\"]", "//td[text()=\"" + getstarted + "\"]");
	}
	
	//Navigate to About
	public static void navigateToAbout() {
		String about = LocaleResourceGetter.getLocaleString(TextConstants.About);
		String copyright = LocaleResourceGetter.getLocaleString(TextConstants.Copyright);
		
		Util.clickPath("//div/a[text()=\"" + about + "\"]", "//div[text()=\"" + copyright + "\"]");
	}
	
	//Navigate to Configuration
	public static void navigateToConfiguration(){
		String conf = LocaleResourceGetter.getLocaleString(TextConstants.Configuration);
		String editxml = LocaleResourceGetter.getLocaleString(TextConstants.Editconfigxml);
		
		Util.clickPath("//td/a[text()=\"" + conf + "\"]", "//span[text()=\"" + editxml + "\"]");
	}
	
	//Navigate to Datasource
	public static void navigateToDatasource(){
		String datasource = LocaleResourceGetter.getLocaleString(TextConstants.Datasource);
		String existingdatasource = LocaleResourceGetter.getLocaleString(TextConstants.Existingdatasource);
		
		Util.clickPath("//td/a[text()=\"" + datasource + "\"]", "//h2[text()=\"" + existingdatasource + "\"]");
	}
	
	//Navigate to GeometryCache
	public static void navigateToGeometryCache(){
		String geometrycache = LocaleResourceGetter.getLocaleString(TextConstants.Geocache);
		String geometrysize = LocaleResourceGetter.getLocaleString(TextConstants.Geometrysize);
		
		Util.clickPath("//td/a[text()=\"" + geometrycache + "\"]", "//span[text()=\"" + geometrysize + "\"]");
	}
	
	//Navigate to ManageTileLayer
	public static void navigateToManageTileLayer(){
		String managetile = LocaleResourceGetter.getLocaleString(TextConstants.Managetile);
		String existingTile = LocaleResourceGetter.getLocaleString(TextConstants.ExistingTile);
		
		Util.clickPath("//td/a[text()=\"" + managetile + "\"]", "//h2[text()=\"" + existingTile  + "\"]");
	}
	
	//Navigate to CreateTileLayer
	public static void navigateToCreateTileLayer(){
		String createtile = LocaleResourceGetter.getLocaleString(TextConstants.Createtile);
		String createtiletip = LocaleResourceGetter.getLocaleString(TextConstants.Createtiletip);
		
		Util.clickPath("//td/a[text()=\"" + createtile + "\"]", "//span[text()=\"" +  createtiletip + "\"]");
	}
	
	
	//Navigate to Monitoring
	public static void navigateToMonitoring(){
		String monitoring = LocaleResourceGetter.getLocaleString(TextConstants.Monitoring);
		String monitor_res = LocaleResourceGetter.getLocaleString(TextConstants.Monitoringres);
		
		Util.clickPath("//td/a[text()=\"" + monitoring + "\"]", "//label[text()=\"" + monitor_res + "\"]");
	}
	
	//Navigate to ViewLogs
	public static void navigateToViewLogs(){
		String viewlogs = LocaleResourceGetter.getLocaleString(TextConstants.Viewlogs);
		String logs_viewlogs = LocaleResourceGetter.getLocaleString(TextConstants.Logs_Viewlogs);
		
		Util.clickPath("//td/a[text()=\"" + viewlogs + "\"]", "//td[text()=\"" + logs_viewlogs + "\"]");
	}
		
	//Login
	public static void login(String accessURL, String userName, String passWord) {
		String username = LocaleResourceGetter.getLocaleString(TextConstants.Username).split(" ")[0].toString();
		String password = LocaleResourceGetter.getLocaleString(TextConstants.Password);
		String signed = LocaleResourceGetter.getLocaleString(TextConstants.Signstatus);
		
		//In pt_BR, username didn't match the web displaying in capital letter
		WebElement user = driver.findElement(By.xpath("//label[contains(text(), \""+username+"\")]/parent::td/following-sibling::td/input"));
		WebElement pass = driver.findElement(By.xpath("//label[text()=\"" + password + "\"]/parent::td/following-sibling::td/input"));
		WebElement btSubmit = driver.findElement(By.xpath("//button"));

		if (pass.isDisplayed() && btSubmit.isDisplayed()) {
			user.clear();			
			user.sendKeys(userName);
			pass.clear();
			pass.sendKeys(passWord);
			btSubmit.click();
		}
		//wait until signed as xx showing
		Util.waitElement("//div[contains(text(),\"" + signed.substring(0, signed.indexOf("{")) + "\")]", 30);
	}

			
	// Navigate to login
	public static void accessMapViewer(String accessURL, String userName, String password) {
		String login_pass = LocaleResourceGetter.getLocaleString(TextConstants.Password);
		String loginLink = "//a[text()=\"" + login + "\"]";
		
		driver.manage().window().maximize();
		driver.get(accessURL);
		//Here need to use sleepBySecs in case running too fast
		sleepBySecs(8);
		//Navigate to login UI 
		Util.clickPath(loginLink, "//label[text()=\"" + login_pass + "\"]");
		
		login(accessURL, userName, password);
	}

	//Navigate to logout (and logout)
	public static void logOutMapViewer(){	
		String logout = LocaleResourceGetter.getLocaleString(TextConstants.Logout);
		String login = LocaleResourceGetter.getLocaleString(TextConstants.Login);
		
		Util.clickPath("//a[text()=\""+logout+"\"]", "//a[text()=\""+login+"\"]");
	}
	
	//Expand Styles in MapBuilder
	public static void expandStyles(){
		String styles = LocaleResourceGetter.getLocaleString(TextConstants.Styles);
		//String advanced = LocaleResourceGetter.getLocaleString(TextConstants.Advanced);
		
		Util.clickPath("//span[text()=\"" + styles + "\"]/parent::span/preceding-sibling::span/a");
	}
	
	
	//Expand Area in Styles
	public static void expandArea(){
		String area = LocaleResourceGetter.getLocaleString(TextConstants.Area);
		//String advanced = LocaleResourceGetter.getLocaleString(TextConstants.Advanced);
		
		Util.clickPath("//span[text()=\"" + area + "\"]/parent::span/preceding-sibling::span/a");
	}
	
	//Expand Color in Styles
	public static void expandColor(){
		String color = LocaleResourceGetter.getLocaleString(TextConstants.Color);
		//String advanced = LocaleResourceGetter.getLocaleString(TextConstants.Advanced);
		
		Util.clickPath("//span[text()=\"" + color + "\"]/parent::span/preceding-sibling::span/a");
	}
	
	//Expand Line in Styles
	public static void expandLine(){
		String line = LocaleResourceGetter.getLocaleString(TextConstants.Line);
		//String advanced = LocaleResourceGetter.getLocaleString(TextConstants.Advanced);
		
		Util.clickPath("//span[text()=\"" + line + "\"]/parent::span/preceding-sibling::span/a");
	}
	
	//Expand Text in Styles
	public static void expandText(){
		String text = LocaleResourceGetter.getLocaleString(TextConstants.Text);
		//String advanced = LocaleResourceGetter.getLocaleString(TextConstants.Advanced);
		
		Util.clickPath("//span[text()=\"" + text + "\"]/parent::span/preceding-sibling::span/a");
	}
	
	//Expand Marker in Styles
	public static void expandMarker(){
		String marker = LocaleResourceGetter.getLocaleString(TextConstants.Marker);
		//String advanced = LocaleResourceGetter.getLocaleString(TextConstants.Advanced);
		
		Util.clickPath("//span[text()=\"" + marker + "\"]/parent::span/preceding-sibling::span/a");
	}
	
	//Expand Advanced in Styles
	public static void expandAdvanced(){
		String advanced = LocaleResourceGetter.getLocaleString(TextConstants.Advanced);
		//String advanced = LocaleResourceGetter.getLocaleString(TextConstants.Advanced);
		
		Util.clickPath("//span[text()=\"" + advanced + "\"]/parent::span/preceding-sibling::span/a");
	}
	
	
	//Expand Themes in MapBuilder
	public static void expandThemes(){
		String themes = LocaleResourceGetter.getLocaleString(TextConstants.Themes);
		String WMTS = LocaleResourceGetter.getLocaleString(TextConstants.WMTS);
		
		Util.clickPath("//span[text()=\"" + themes + "\"]/parent::span/preceding-sibling::span/a");
	}
	
	//Expand Themes in MapBuilder
	public static void expandGeometry(){
		String geo = LocaleResourceGetter.getLocaleString(TextConstants.Geometry);
		//String WMTS = LocaleResourceGetter.getLocaleString(TextConstants.WMTS);
		
		Util.clickPath("//span[text()=\"" + geo + "\"]/parent::span/preceding-sibling::span/a");
	}
		
	//Expand BaseMaps in MapBuilder
	public static void expandBaseMaps(){
		String basemaps = LocaleResourceGetter.getLocaleString(TextConstants.BaseMaps);
		//String advanced = LocaleResourceGetter.getLocaleString(TextConstants.Advanced);
		
		Util.clickPath("//span[text()=\"" + basemaps + "\"]/parent::span/preceding-sibling::span/a");
	}
	
	//Expand TileLayer in MapBuilder
	public static void expandTileLayers(){
		String TileLayers = LocaleResourceGetter.getLocaleString(TextConstants.TileLayers);
		//String advanced = LocaleResourceGetter.getLocaleString(TextConstants.Advanced);
		
		Util.clickPath("//span[text()=\"" + TileLayers + "\"]/parent::span/preceding-sibling::span/a");
	}
	
	public static void main(String args[]){
		navigateToMapBuilder();
		//System.out.println("bbb="+bbb);
		
	}
}
