package oracle.sgt.mapviewer.junit;

	/**
	 * @author: ruijia.dai@oracle.com
	 */

import oracle.sgt.mapviewer.common.constants.MapViewerEnvConstants;
import oracle.sgt.mapviewer.common.constants.TextConstants;
import oracle.sgt.mapviewer.page.BasePage;
import oracle.sgt.mapviewer.page.Navigation;
import oracle.sgt.mapviewer.page.Util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.oracle.sgt.junit.ext.BlockClassRunnerExt;
import com.oracle.sgt.junit.ext.PreTest;
import com.oracle.sgt.webdriver.common.TRSTestCase;
import com.oracle.sgt.webdriver.common.VerificationException;
import com.oracle.sgt.webdriver.util.LocaleResourceGetter;
import com.oracle.sgt.webdriver.util.TRSReportUtil;

import junit.framework.Assert;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = BlockClassRunnerExt.class)
public class Admin{
	static String strTileName = "";
	static String gif = "";
				
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Navigation.accessMapViewer(MapViewerEnvConstants.URL, MapViewerEnvConstants.LOGINNAME,MapViewerEnvConstants.LOGINPASS);
		//Navigation.accessMapViewer("http://slc00bnd.us.oracle.com:7001/mapviewer", "weblogic", "welcome1");
		Navigation.navigateToAdmin();
		strTileName = Util.getRandomName(6).toUpperCase();
		gif = LocaleResourceGetter.getLocaleString(TextConstants.GIF);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Navigation.logOutMapViewer();
	}

	@Before
	public void setUp() throws Exception {	
	
	}

	@After
	public void tearDown() throws Exception {
		
	}

	
	@Test
	@PreTest("NA")
	@TRSTestCase(id="200775", 
	 checkPoints={"Check element already displayed in page: check translation on UI"}) 
	public void test01CheckConfiguration () {
		String save_restart = LocaleResourceGetter.getLocaleString(TextConstants.Saverestart);
		String editconfigxml = LocaleResourceGetter.getLocaleString(TextConstants.Editconfigxml);
		
		Navigation.navigateToConfiguration();
		boolean result1 = Util.checkItemInPage("//span[text()=\"" + editconfigxml + "\"]");
		boolean result2 = Util.checkItemInPage("//button[text()=\"" + save_restart + "\"]");
		boolean result = result1 && result2;
		if(result)
			TRSReportUtil.report2TRS("200775", TRSReportUtil.RESULT_PASSED);
		else 
			TRSReportUtil.report2TRS("200775", TRSReportUtil.RESULT_FAILED);
		Assert.assertTrue(result);
	}
	
	@Test
	@PreTest("200775")
	@TRSTestCase(id="200776", 
	 checkPoints={"Check element already displayed in page: check translation on UI"}) 
	public void test02DatabaseConnection () {		 
		String refresh = LocaleResourceGetter.getLocaleString(TextConstants.Refresh);
		String maxconn = LocaleResourceGetter.getLocaleString(TextConstants.Maxconnection);
		String purge = LocaleResourceGetter.getLocaleString(TextConstants.PurgeDB);
		String jdbcurl = LocaleResourceGetter.getLocaleString(TextConstants.JDBCUrl);
		
		Navigation.navigateToDatasource();
		boolean result1 = Util.checkItemInPage("//button[text()=\"" + refresh + "\"]");
		boolean result2 = Util.checkItemInPage("//button[text()=\"" + purge + "\"]");
		boolean result3 = Util.checkItemInPage("//div[text()=\"" + maxconn + "\"]");
		boolean result4 = Util.checkItemInPage("//div[text()=\"" + jdbcurl + "\"]");
		boolean result = result1 && result2 && result3 && result4;
		if(result)
			TRSReportUtil.report2TRS("200776", TRSReportUtil.RESULT_PASSED);
		else 
			TRSReportUtil.report2TRS("200776", TRSReportUtil.RESULT_FAILED);
		Assert.assertTrue(result);
	}
		
	@Test
	@PreTest("200775")
	@TRSTestCase(id="200780", 
	 checkPoints={"Check element already displayed in page: check translation on UI"}) 
	public void test03GeometryCache () {		 
		String geocachetile = LocaleResourceGetter.getLocaleString(TextConstants.Geocachetitle);
		String purgeselected = LocaleResourceGetter.getLocaleString(TextConstants.Purgeselectedtheme);
		String purgeall = LocaleResourceGetter.getLocaleString(TextConstants.Purgealltheme);
		String theme = LocaleResourceGetter.getLocaleString(TextConstants.Geotheme);
		String source = LocaleResourceGetter.getLocaleString(TextConstants.Geosource);
		String allcachedeleted = LocaleResourceGetter.getLocaleString(TextConstants.AllCacheDeleted).split(":")[0].toString();
		
		Navigation.navigateToGeometryCache();
		boolean result1 = Util.checkItemInPage("//button[text()=\"" + purgeselected + "\"]");
		boolean result2 = Util.checkItemInPage("//button[text()=\"" + purgeall + "\"]");
		boolean result3 = Util.checkItemInPage("//label[text()=\"" + theme + "\"]");
		boolean result4 = Util.checkItemInPage("//label[text()=\"" + source + "\"]");
		boolean result5 = Util.checkItemInPage("//h2[text()=\"" + geocachetile + "\"]");
		boolean result_translation = result1 && result2 && result3 && result4 && result5;
		//Choose datasource
		Util.clickPath("//select[contains(@id,'SrcSelect')]/option[@value='0']", 
				"//select[contains(@id,'Src')]/ancestor::table/following-sibling::table//select/option");
		//Purge all themes
		Util.clickPath("//button[text()=\"" + purgeall + "\"]", "//div[contains(text(), allcachedeleted)]");
		//Close dialog
		Util.clickPath("//a[contains(@id, 'close')]");
		boolean result_clear = Util.checkItemInPage("//span/b[text()='0 KB']");
		boolean result = result_clear && result_translation;
		if(result)
			TRSReportUtil.report2TRS("200780", TRSReportUtil.RESULT_PASSED);
		else 
			TRSReportUtil.report2TRS("200780", TRSReportUtil.RESULT_FAILED);
		Assert.assertTrue(result);
	}
	
	@Test
	@PreTest("200775")
	@TRSTestCase(id="200781", 
	 checkPoints={"Check element already displayed in page: check translation on UI"}) 
	public void test04CreateTileLayer () {		 
		String btncontinue = LocaleResourceGetter.getLocaleString(TextConstants.Continue);
		String createtiletitle = LocaleResourceGetter.getLocaleString(TextConstants.Createtiletitle);
		String tilename = LocaleResourceGetter.getLocaleString(TextConstants.Createtilename);
		String tilestorage = LocaleResourceGetter.getLocaleString(TextConstants.Createtilestorage);
		String btnsubmit = LocaleResourceGetter.getLocaleString(TextConstants.Submit);
		String tip1 = LocaleResourceGetter.getLocaleString(TextConstants.Createtiletip1);
		String datasource = LocaleResourceGetter.getLocaleString(TextConstants.Createtiledb);
		String tip2 = LocaleResourceGetter.getLocaleString(TextConstants.Createtiletip2);
		String createtileheight = LocaleResourceGetter.getLocaleString(TextConstants.Createtileheight);
		String createtileminscale = LocaleResourceGetter.getLocaleString(TextConstants.Createtileminscale);
		String advanced = LocaleResourceGetter.getLocaleString(TextConstants.Advanced);
		String dpi = LocaleResourceGetter.getLocaleString(TextConstants.DPI);
		String mpu = LocaleResourceGetter.getLocaleString(TextConstants.MPU);
		String tilecreated = LocaleResourceGetter.getLocaleString(TextConstants.Createtilecreated);
		
		Navigation.navigateToCreateTileLayer();
		Util.clickPath("//button[text()=\""+ btncontinue + "\"]", "//td[text()=\"" + createtiletitle + "\"]");
		
		String xpathTileName =  "//label[text()=\"" + tilename + "\"]/parent::td/following-sibling::td/input";
		Util.clickPath(xpathTileName);
		//Check the translation of tip
		boolean btip1 = Util.checkItemInPage("//div[text()=\"" + tip1 + "\"]");
		//Fill in tile name
		Util.sendKeys(xpathTileName, strTileName);
		//Choose data source
		Util.clickPath("//label[text()=\"" + datasource + "\"]/parent::td/following-sibling::td//option[@value='0']");
		//Check translation of tip
		boolean btip2 = Util.checkItemInPage("//div[text()=\"" + tip2 + "\"]");
		
		//Check the translation of current page
		boolean blabel1 = Util.checkItemInPage("//label[text()=\"" + createtileheight + "\"]");
		boolean blabel2 = Util.checkItemInPage("//label[text()=\"" + createtileminscale + "\"]");
		boolean blabel5 = Util.checkItemInPage("//label[text()=\"" + tilestorage + "\"]");
		//Click advanced to unfold
		Util.clickPath("//td[text()=\"" + advanced + "\"]/preceding-sibling::td/a");
		boolean blabel3 = Util.checkItemInPage("//label[text()=\"" + dpi + "\"]");
		boolean blabel4 = Util.checkItemInPage("//label[text()=\"" + mpu + "\"]");
		
		//Click submit to finish
		Util.clickPath("//button[text()=\"" + btnsubmit + "\"]", "//div[text()=\"" + tilecreated + "\"]");
		//Close the pop up dialog and check the new created tile layer already in it
		Util.clickPath("//a[contains(@id, 'close')]", "//td[text()=\"" + strTileName.toUpperCase() + "\"]");
	
		boolean btip = btip1 && btip2;
		boolean blabel = blabel1 && blabel2 && blabel3 && blabel4 && blabel5;
		
		if(btip && blabel)
			TRSReportUtil.report2TRS("200781", TRSReportUtil.RESULT_PASSED);
		else 
			TRSReportUtil.report2TRS("200781", TRSReportUtil.RESULT_FAILED);
		Assert.assertTrue(btip && blabel);
	}
	
	@Test
	@PreTest("NA")
	@TRSTestCase(id="200805", 
	 checkPoints={"Check element already displayed in page: Expecting return false"}) 
	public void test05CheckNewlyCreatedTileLayerInMapBuilder() {
		String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
		String refreshwarn = LocaleResourceGetter.getLocaleString(TextConstants.RefreshWarn);
		String ButtonYes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
		
		Navigation.navigateToMapBuilder();
		Navigation.expandTileLayers();
		BasePage.sleepBySecs(5);

		Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
		Util.clickPath("//td[text()=\"" + refreshwarn + "\"]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
		//Check created tile layer already in page
		boolean result = Util.checkItemInPage("//span[text()=\"" + strTileName + "\"]");	
		//Unfold tile layers
		Navigation.expandTileLayers();
		if(!result)
			TRSReportUtil.report2TRS("200805", TRSReportUtil.RESULT_FAILED);
		else 
			TRSReportUtil.report2TRS("200805", TRSReportUtil.RESULT_PASSED);
		Assert.assertTrue(result);
	}
	
	@Test
	@PreTest("200781")
	@TRSTestCase(id="200782", 
	 checkPoints={"Check element already displayed in page: check translation on UI"}) 
	public void test06EditTileLayer() {		 
		String edittile = LocaleResourceGetter.getLocaleString(TextConstants.Edittiledetail);
		String maxcache = LocaleResourceGetter.getLocaleString(TextConstants.Maxcache);
		String zoomdef = LocaleResourceGetter.getLocaleString(TextConstants.Zoomdef);
		String coordinate = LocaleResourceGetter.getLocaleString(TextConstants.Coordinate);
		String copyrighttext = LocaleResourceGetter.getLocaleString(TextConstants.Copyrighttext);
		String srid = LocaleResourceGetter.getLocaleString(TextConstants.SRID);
		String minx = LocaleResourceGetter.getLocaleString(TextConstants.MinX);
		String tileformat = LocaleResourceGetter.getLocaleString(TextConstants.Tileformat);
		String btnsubmit = LocaleResourceGetter.getLocaleString(TextConstants.Submit);
		
		Navigation.navigateToAdmin();
		Navigation.navigateToManageTileLayer();
		Util.clickPath("//td[text()=\"" + strTileName.toUpperCase() + "\"]");
		Util.clickPath("//button[text()=\"" + edittile + "\"]", "//label[text()=\"" + maxcache + "\"]");
		//Change tile format
		Util.clickPath("//label[text()=\"" + tileformat + "\"]/parent::td/following-sibling::td//option[text()=\"" + gif + "\"]");
		//Check translations
		boolean result1 = Util.checkItemInPage("//td[text()=\"" + zoomdef + "\"]");
		boolean result2 = Util.checkItemInPage("//label[text()=\"" + copyrighttext + "\"]");
		//Unfold Coordinate
		Util.clickPath("//td[text()=\"" + coordinate + "\"]/preceding-sibling::td/a", "//label[text()=\"" + srid + "\"]");
		boolean result3 = Util.checkItemInPage("//label[text()=\"" + minx + "\"]");
		
		//Click submit to finish
		Util.clickPath("//button[text()=\"" + btnsubmit + "\"]"); 
		
		boolean result = result1 && result2 && result3;
		
		if(result)
			TRSReportUtil.report2TRS("200782", TRSReportUtil.RESULT_PASSED);
		else 
			TRSReportUtil.report2TRS("200782", TRSReportUtil.RESULT_FAILED);
		Assert.assertTrue(result);
	}
	
	@Test
	@PreTest("NA")
	@TRSTestCase(id="200806", 
	 checkPoints={"Check element already displayed in page: Expecting return false"}) 
	public void test07CheckEditedTileLayerInMapBuilder() {		 
		String open = LocaleResourceGetter.getLocaleString(TextConstants.Open);
		String deletewarn = LocaleResourceGetter.getLocaleString(TextConstants.DeleteStyleWarn);
		String warnString = deletewarn.substring(0, deletewarn.indexOf("{"));
		if (warnString.equals("")) {
			warnString = deletewarn.substring(deletewarn.indexOf("}")+1);
		}
		String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
		String refreshwarn = LocaleResourceGetter.getLocaleString(TextConstants.RefreshWarn);
		String ButtonYes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
		
		Navigation.navigateToMapBuilder();
		Navigation.expandTileLayers();
		BasePage.sleepBySecs(5);
		Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
		Util.clickPath("//td[text()=\"" + refreshwarn + "\"]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
		Util.rightClickPath("//span[text()=\"" + strTileName + "\"]");
		Util.clickPath("//td[text()=\"" + open + "\"]");
		//Check modified string already in page
		boolean result = Util.checkItemInPage("//textarea[contains(text(),\"" + gif + "\")]");
		//Close modify dialog
		Util.clickPath("//a[text()=\"" + strTileName + "\"]/parent::div/following-sibling::div//a");
		
		//Unfold tile layers
		Navigation.expandTileLayers();
		if(result)
			TRSReportUtil.report2TRS("200806", TRSReportUtil.RESULT_PASSED);
		else 
			TRSReportUtil.report2TRS("200806", TRSReportUtil.RESULT_FAILED);
		Assert.assertTrue(result);
	}
	
	
	@Test
	@PreTest("200781")
	@TRSTestCase(id="200783", 
	 checkPoints={"Check element already displayed in page: check translation on UI"}) 
	public void test08ViewTileLayer () {		 
		String viewtilelayer = LocaleResourceGetter.getLocaleString(TextConstants.Viewtilelayer);
		String viewarea = LocaleResourceGetter.getLocaleString(TextConstants.Viewarea);
		String tileoperation = LocaleResourceGetter.getLocaleString(TextConstants.Tileoperation);
		String viewdraw = LocaleResourceGetter.getLocaleString(TextConstants.Viewdraw);
		String centerX = LocaleResourceGetter.getLocaleString(TextConstants.CenterX);
		String showmap = LocaleResourceGetter.getLocaleString(TextConstants.Showmap);
		String btnReturn = LocaleResourceGetter.getLocaleString(TextConstants.Return);
		
		Navigation.navigateToAdmin();
		Navigation.navigateToManageTileLayer();
		Util.clickPath("//td[text()=\"" + strTileName.toUpperCase() + "\"]");
		Util.clickPath("//button[text()=\"" + viewtilelayer + "\"]", "//label[text()=\"" + viewarea + "\"]");
		//Check translations
		boolean result1 = Util.checkItemInPage("//label[text()=\"" + tileoperation + "\"]");
		boolean result2 = Util.checkItemInPage("//label[text()=\"" + viewdraw + "\"]");
		boolean result3 = Util.checkItemInPage("//label[text()=\"" + centerX + "\"]");
		
		//Click show map
		Util.clickPath("//button[text()=\"" + showmap  + "\"]");
		BasePage.sleepBySecs(3);
		BasePage.saveScreenShot("200783_test06ViewTileLayer_ShowMap");
		//Click return button
		Util.clickPath("//button[text()=\"" + btnReturn + "\"]");
		
		boolean result = result1 && result2 && result3;
		if(result)
			TRSReportUtil.report2TRS("200783", TRSReportUtil.RESULT_PASSED);
		else 
			TRSReportUtil.report2TRS("200783", TRSReportUtil.RESULT_FAILED);
		Assert.assertTrue(result);
	}
	
	@Test
	@PreTest("200781")
	@TRSTestCase(id="200784", 
	 checkPoints={"Check element already displayed in page: check translation on UI"}) 
	public void test09TileLayerOnlineOffline() {		 
		String offline = LocaleResourceGetter.getLocaleString(TextConstants.Offline);
		String offlineInfo = LocaleResourceGetter.getLocaleString(TextConstants.OfflineInfo);
		String strOfflineInfo = offlineInfo.substring(0, offlineInfo.indexOf("["));
		String online = LocaleResourceGetter.getLocaleString(TextConstants.Online);
		String onlineInfo = LocaleResourceGetter.getLocaleString(TextConstants.OnlineInfo);
		String strOnlineInfo = onlineInfo.substring(0, onlineInfo.indexOf("["));
		
		Navigation.navigateToManageTileLayer();
		Util.clickPath("//td[text()=\"" + strTileName.toUpperCase() + "\"]");
		//Offline operation
		Util.clickPath("//button[text()=\"" + offline + "\"]");
		boolean result1 = Util.checkItemInPage("//div[contains(text(),\"" + strOfflineInfo + "\")]");
		Util.clickPath("//a[contains(@id, 'close')]");
		Util.clickPath("//td[text()=\"" + strTileName.toUpperCase() + "\"]");
		//Online operation
		Util.clickPath("//button[text()=\"" + online + "\"]");
		boolean result2 = Util.checkItemInPage("//div[contains(text(),\"" + strOnlineInfo + "\")]");
		Util.clickPath("//a[contains(@id, 'close')]");
		
		boolean result = result1 && result2;
		if(result)
			TRSReportUtil.report2TRS("200784", TRSReportUtil.RESULT_PASSED);
		else 
			TRSReportUtil.report2TRS("200784", TRSReportUtil.RESULT_FAILED);
		Assert.assertTrue(result);
	}
	
	@Test
	@PreTest("200784")
	@TRSTestCase(id="200785", 
	 checkPoints={"Check element already displayed in page: Expecting one result in false"}) 
	public void test10DeleteTileLayer() {		 
		String delete = LocaleResourceGetter.getLocaleString(TextConstants.Delete);
		String deletewarn = LocaleResourceGetter.getLocaleString(TextConstants.Deletewarn);
		String warn = LocaleResourceGetter.getLocaleString(TextConstants.Warn);
		String tiledeleted = LocaleResourceGetter.getLocaleString(TextConstants.Tiledeleted).split(" - ")[0].toString();
		String refresh = LocaleResourceGetter.getLocaleString(TextConstants.Refresh);
		String ok = LocaleResourceGetter.getLocaleString(TextConstants.OK);
		//In es, OK button translated into Aceptar, not in UpperCase all
		String btnOK = null;
		if (ok.length() == 2 ) {
			btnOK = ok.toUpperCase();
		} else {
			btnOK = ok;
		}
		
		Navigation.navigateToManageTileLayer();
		Util.clickPath("//td[text()=\"" + strTileName.toUpperCase() + "\"]");
		Util.clickPath("//button[text()=\"" + delete + "\"]");
		boolean result1 = Util.checkItemInPage("//label[contains(text(),\"" + deletewarn + "\")]");
		Util.clickPath("//div[text()=\"" + warn + "\"]/ancestor::tr/following-sibling::tr//span[text()=\"" + btnOK +"\"]");
		boolean result2 = Util.checkItemInPage("//div[contains(text(),\"" + tiledeleted + "\")]");
		Util.clickPath("//div[contains(@id,'cancel')]//span[text()=\"" + btnOK + "\"]");
		//Refresh
		Util.clickPath("//button[text()=\"" + refresh + "\"]");
		boolean result3 = Util.checkItemInPage("//td[text()=\"" + strTileName.toUpperCase() + "\"]");
		
		boolean result = result1 && result2 && (!result3);
		if(result)
			TRSReportUtil.report2TRS("200785", TRSReportUtil.RESULT_PASSED);
		else 
			TRSReportUtil.report2TRS("200785", TRSReportUtil.RESULT_FAILED);
		Assert.assertTrue(result);
	}
	
	@Test
	@PreTest("200775")
	@TRSTestCase(id="200786", 
	 checkPoints={"Check element already displayed in page: check translation on UI"}) 
	public void test11CheckMonitoring() {		 
		String checkJDBC = LocaleResourceGetter.getLocaleString(TextConstants.CheckJDBC);
		String checktheme = LocaleResourceGetter.getLocaleString(TextConstants.Checktheme);
		String resettheme = LocaleResourceGetter.getLocaleString(TextConstants.Resettheme);
		String restartmv = LocaleResourceGetter.getLocaleString(TextConstants.Restartmv);
			
		Navigation.navigateToMonitoring();
		boolean result1 = Util.checkItemInPage("//span[text()=\"" + checkJDBC + "\"]");
		boolean result2 = Util.checkItemInPage("//span[text()=\"" + checktheme + "\"]");
		boolean result3 = Util.checkItemInPage("//span[text()=\"" + resettheme + "\"]");
		boolean result4 = Util.checkItemInPage("//span[text()=\"" + restartmv + "\"]");
		boolean result = result1 && result2 && result3 && result4;
		if(result)
			TRSReportUtil.report2TRS("200786", TRSReportUtil.RESULT_PASSED);
		else 
			TRSReportUtil.report2TRS("200786", TRSReportUtil.RESULT_FAILED);
		Assert.assertTrue(result);
	}
	
	@Test
	@PreTest("200775")
	@TRSTestCase(id="200787", 
	 checkPoints={"Check element already displayed in page: check translation on UI"}) 
	public void test12CheckViewLogs() {		 
		String fileLoc = LocaleResourceGetter.getLocaleString(TextConstants.Filelocation);
		String logs = LocaleResourceGetter.getLocaleString(TextConstants.Logs);
		String logNum = LocaleResourceGetter.getLocaleString(TextConstants.Lognumber);
		
		Navigation.navigateToViewLogs();
		boolean result1 = Util.checkItemInPage("//label[text()=\"" + logs + "\"]");
		boolean result2 = Util.checkItemInPage("//label[text()=\"" + fileLoc + "\"]");
		boolean result3 = Util.checkItemInPage("//label[text()=\"" + logNum + "\"]");
		boolean result = result1 && result2 && result3;
		if(result)
			TRSReportUtil.report2TRS("200787", TRSReportUtil.RESULT_PASSED);
		else 
			TRSReportUtil.report2TRS("200787", TRSReportUtil.RESULT_FAILED);
		Assert.assertTrue(result);
	}
	
	
	@Test
	@PreTest("200775")
	@TRSTestCase(id="209073", 
	 checkPoints={"Check element already displayed in page: check translation on UI"}) 
	public void test13CheckHome() {		 
		String home1 = LocaleResourceGetter.getLocaleString(TextConstants.Home1);
		String home2 = LocaleResourceGetter.getLocaleString(TextConstants.Home2);
		String home3 = LocaleResourceGetter.getLocaleString(TextConstants.Home3);
		
		Navigation.navigateToHome();
		boolean result1 = Util.checkItemInPage("//div[text()=\"" + home1 + "\"]");
		boolean result2 = Util.checkItemInPage("//div[text()=\"" + home2 + "\"]");
		boolean result3 = Util.checkItemInPage("//div[text()=\"" + home3 + "\"]");
		boolean result = result1 && result2 && result3;
		if(result)
			TRSReportUtil.report2TRS("209073", TRSReportUtil.RESULT_PASSED);
		else 
			TRSReportUtil.report2TRS("209073", TRSReportUtil.RESULT_FAILED);
		Assert.assertTrue(result);
	}
	
	@Test
	@PreTest("200775")
	@TRSTestCase(id="200789", 
	 checkPoints={"Check element already displayed in page: check translation on UI"}) 
	public void test14CheckAbout() {		 
		String version = LocaleResourceGetter.getLocaleString(TextConstants.Version);
		
		Navigation.navigateToAbout();
		boolean result = Util.checkItemInPage("//label[text()=\"" + version + "\"]");
		if(result)
			TRSReportUtil.report2TRS("200789", TRSReportUtil.RESULT_PASSED);
		else 
			TRSReportUtil.report2TRS("200789", TRSReportUtil.RESULT_FAILED);
		Assert.assertTrue(result);
	}
	
	@Test
	@PreTest("200775")
	@TRSTestCase(id="200788", 
	 checkPoints={"Check element already displayed in page: check translation on UI"}) 
	public void test15CheckMetadata() {		 
		String Listtheme = LocaleResourceGetter.getLocaleString(TextConstants.Listtheme);
		String Listthemeinbase = LocaleResourceGetter.getLocaleString(TextConstants.Listthemeinbase);
		String Liststyle = LocaleResourceGetter.getLocaleString(TextConstants.Liststyle);
		String Listtile = LocaleResourceGetter.getLocaleString(TextConstants.ExistingTile);
		String Listbasemap = LocaleResourceGetter.getLocaleString(TextConstants.Listbasemap);
		
		
		Navigation.navigateToMetadata();
		boolean result1 = Util.checkItemInPage("//h2[text()=\"" + Listtheme + "\"]");
		boolean result2 = Util.checkItemInPage("//h2[text()=\"" + Listthemeinbase + "\"]");
		boolean result3 = Util.checkItemInPage("//h2[text()=\"" + Liststyle + "\"]");
		boolean result4 = Util.checkItemInPage("//h2[text()=\"" + Listtile + "\"]");
		boolean result5 = Util.checkItemInPage("//h2[text()=\"" + Listbasemap + "\"]");
		boolean result = result1 && result2 && result3 && result4 && result5;
		if(result)
			TRSReportUtil.report2TRS("200788", TRSReportUtil.RESULT_PASSED);
		else 
			TRSReportUtil.report2TRS("200788", TRSReportUtil.RESULT_FAILED);
		Assert.assertTrue(result);
	}

	
	public static void main(String[] args){		
	}
}
		
