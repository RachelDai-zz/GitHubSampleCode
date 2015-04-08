package oracle.sgt.mapviewer.junit;

/**
 * @author: ruijia.dai@oracle.com
 */


import junit.framework.Assert;
import oracle.sgt.mapviewer.common.constants.MapViewerEnvConstants;
import oracle.sgt.mapviewer.common.constants.TextConstants;
import oracle.sgt.mapviewer.page.BasePage;
import oracle.sgt.mapviewer.page.Navigation;
import oracle.sgt.mapviewer.page.Util;
import oracle.sgt.mapviewer.junit.Admin;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.oracle.sgt.junit.ext.BlockClassRunnerExt;
import com.oracle.sgt.junit.ext.PreTest;
import com.oracle.sgt.webdriver.common.TRSTestCase;
import com.oracle.sgt.webdriver.util.LocaleResourceGetter;
import com.oracle.sgt.webdriver.util.TRSReportUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = BlockClassRunnerExt.class)
public class MapBuilder {
//To avoid drag scroll bar operation, name should begin with A
static String strAreaName = "AA";
static String strColorName = "AA";
static String strLineName = "AA";
static String strTextName = "AA";
static String strMarkerName = "AA";
static String strAdvancedName = "AA";
static String strThemeName = "AA";
static String strBaseMapName = "AA";
			
@BeforeClass
public static void setUpBeforeClass() throws Exception {
	//Navigation.accessMapViewer(MapViewerEnvConstants.URL, MapViewerEnvConstants.LOGINNAME,MapViewerEnvConstants.LOGINPASS);
	Navigation.accessMapViewer("http://slc00bnd.us.oracle.com:7001/mapviewer", "weblogic", "welcome1");
	Navigation.navigateToMapBuilder();
	strAreaName += Util.getRandomName(6).toUpperCase();
	strColorName += Util.getRandomName(6).toUpperCase();
	strLineName += Util.getRandomName(6).toUpperCase();
	strTextName += Util.getRandomName(6).toUpperCase();
	strMarkerName += Util.getRandomName(6).toUpperCase();
	strAdvancedName += Util.getRandomName(6).toUpperCase();
	strThemeName += Util.getRandomName(6).toUpperCase();
	strBaseMapName += Util.getRandomName(6).toUpperCase();
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
@TRSTestCase(id="200791", 
 checkPoints={"Check element already displayed in page: check translation on UI"}) 
public void test01CreateNewArea() {		 
	String create = LocaleResourceGetter.getLocaleString(TextConstants.Create);
	String styleidentification = LocaleResourceGetter.getLocaleString(TextConstants.StyleIdentification);
	String area = LocaleResourceGetter.getLocaleString(TextConstants.Area);
	String name = LocaleResourceGetter.getLocaleString(TextConstants.Name);
	String desc = LocaleResourceGetter.getLocaleString(TextConstants.Desc);
	String refresh = LocaleResourceGetter.getLocaleString(TextConstants.RefreshPreview);
	String patten = LocaleResourceGetter.getLocaleString(TextConstants.PattenType);
	String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
	String refreshwarn = LocaleResourceGetter.getLocaleString(TextConstants.RefreshWarn);
	String yes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
	
	Navigation.navigateToMapBuilder();
	Navigation.expandStyles();
	Navigation.expandArea();
	Util.clickPath("//span[text()=\"" + area + "\"]");
	Util.rightClickPath("//span[text()=\"" + area + "\"]");
	Util.clickPath("//td[text()=\"" + create + "\"]", "//h1[text()=\"" + styleidentification + "\"]");
	String strAreaDesc = Util.getRandomName(10);
	Util.sendKeys("//label[text()=\"" + name + "\"]/parent::td/following-sibling::td/input", strAreaName);
	//Before click saveall, need click somewhere to make saveall enabled
	Util.clickPath("//h1[text()=\"" + styleidentification + "\"]");
	Util.sendKeys("//label[text()='" + desc + "']/parent::td/following-sibling::td/input", strAreaDesc);
	//Before click saveall, need click somewhere to make saveall enabled
	Util.clickPath("//h1[text()=\"" + styleidentification + "\"]");
	Util.clickPath("//img[contains(@src,'saveall.png')]");
	boolean result1 = Util.checkItemInPage("//button[text()=\"" + refresh + "\"]");
	boolean result2 = Util.checkItemInPage("//label[text()=\"" + patten + "\"]");
	
	//Close create panel
	Util.clickPath("//a[text()=\"" + strAreaName + "\"]/parent::div/following-sibling::div//a");
	Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
	Util.clickPath("//td[text()=\"" + refreshwarn + "\"]/parent::tr/following-sibling::tr//span[text()=\"" + yes + "\"]");
	boolean result3 = Util.checkItemInPage("//span[text()=\"" + strAreaName + "\"]");
	boolean result = result1 && result2 && result3;
	if(result)
		TRSReportUtil.report2TRS("200791", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("200791", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(result);
}

@Test
@PreTest("NA")
@TRSTestCase(id="200792", 
 checkPoints={"Check element already displayed in page: Expecting return false"}) 
public void test02DeleteArea() {		 
	String delete = LocaleResourceGetter.getLocaleString(TextConstants.Delete);
	String deletewarn = LocaleResourceGetter.getLocaleString(TextConstants.DeleteStyleWarn);
	String warnString = deletewarn.substring(0, deletewarn.indexOf("{"));
	if (warnString.equals("") ) {
		warnString = deletewarn.substring(deletewarn.indexOf("}")+1);
	}
	String ButtonYes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
	String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
	String refreshwarn = LocaleResourceGetter.getLocaleString(TextConstants.RefreshWarn);
	
	//Navigation.navigateToMapBuilder();
	//Navigation.expandStyles();
	//Navigation.expandArea();
	BasePage.sleepBySecs(5);
	Util.rightClickPath("//span[text()=\"" + strAreaName + "\"]");
	Util.clickPath("//td[text()=\"" + delete + "\"]");
	Util.clickPath("//td[contains(text(), \"" + warnString + "\")]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
	//Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
	//Util.clickPath("//td[text()=\"" + refreshwarn + "\"]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
	boolean result = Util.checkItemInPage("//span[text()=\"" + strAreaName + "\"]");
	//Unfold Area
	Navigation.expandArea();
	if(!result)
		TRSReportUtil.report2TRS("200792", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("200792", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(!result);
}

@Test
@PreTest("NA")
@TRSTestCase(id="202403", 
 checkPoints={"Check element already displayed in page: check translation on UI"}) 
public void test03CreateColor() {		 
	String create = LocaleResourceGetter.getLocaleString(TextConstants.Create);
	String styleidentification = LocaleResourceGetter.getLocaleString(TextConstants.StyleIdentification);
	String color = LocaleResourceGetter.getLocaleString(TextConstants.Color);
	String name = LocaleResourceGetter.getLocaleString(TextConstants.Name);
	String desc = LocaleResourceGetter.getLocaleString(TextConstants.Desc);
	String refresh = LocaleResourceGetter.getLocaleString(TextConstants.RefreshPreview);
	String fillcolors = LocaleResourceGetter.getLocaleString(TextConstants.FillColor);
	String strokecolor = LocaleResourceGetter.getLocaleString(TextConstants.StrokeColor);
	String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
	String yes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
	
	//Navigation.navigateToMapBuilder();
	//Navigation.expandStyles();
	Navigation.expandColor();
	//Collapse pane
	Util.clickPath("//a/img[contains(@src, 'splittervr.png')]");
	//Begin case
	Util.clickPath("//span[text()=\"" + color + "\"]");
	Util.rightClickPath("//span[text()=\"" + color + "\"]");
	Util.clickPath("//td[text()=\"" + create + "\"]", "//h1[text()=\"" + styleidentification + "\"]");
	String strColorDesc = Util.getRandomName(10);
	Util.sendKeys("//label[text()='" + name + "']/parent::td/following-sibling::td/input", strColorName);
	//Before click saveall, need click somewhere to make saveall enabled
	Util.clickPath("//h1[text()=\"" + styleidentification + "\"]");
	Util.sendKeys("//label[text()='" + desc + "']/parent::td/following-sibling::td/input", strColorDesc);
	//Before click saveall, need click somewhere to make saveall enabled
	Util.clickPath("//h1[text()=\"" + styleidentification + "\"]");
	Util.clickPath("//img[contains(@src,'saveall.png')]");
	boolean result1 = Util.checkItemInPage("//button[text()=\"" + refresh + "\"]");
	boolean result2 = Util.checkItemInPage("//a[text()=\"" + fillcolors + "\"]");
	boolean result4 = Util.checkItemInPage("//a[text()=\"" + strokecolor + "\"]");
	//Close create panel
	Util.clickPath("//a[text()=\"" + strColorName + "\"]/parent::div/following-sibling::div//a");
	//Refresh data to make name in descending order
	Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
	Util.clickPath("//div[text()=\"" + refreshdata + "\"]/ancestor::tr/following-sibling::tr//span[text()=\"" + yes + "\"]");
	//Util.clickPath("//td[text()=\"" + refreshwarn + "\"]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
	boolean result3 = Util.checkItemInPage("//span[text()=\"" + strColorName + "\"]");
	boolean result = result1 && result2 && result3 && result4;
	if(result)
		TRSReportUtil.report2TRS("202403", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("202403", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(result);
}

@Test
@PreTest("NA")
@TRSTestCase(id="200792", 
 checkPoints={"Check element already displayed in page: Expecting return false"}) 
public void test04DeleteColor() {		 
	String delete = LocaleResourceGetter.getLocaleString(TextConstants.Delete);
	String deletewarn = LocaleResourceGetter.getLocaleString(TextConstants.DeleteStyleWarn);
	String warnString = deletewarn.substring(0, deletewarn.indexOf("{"));
	
	if (warnString.equals("") ) {
		warnString = deletewarn.substring(deletewarn.indexOf("}")+1);
	}
	String ButtonYes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
	String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
	String refreshwarn = LocaleResourceGetter.getLocaleString(TextConstants.RefreshWarn);
	
	//Navigation.navigateToMapBuilder();
	//Navigation.expandStyles();
	//Navigation.expandColor();
	BasePage.sleepBySecs(5);
	Util.rightClickPath("//span[text()=\"" + strColorName + "\"]");
	Util.clickPath("//td[text()=\"" + delete + "\"]");
	Util.clickPath("//td[contains(text(), \"" + warnString + "\")]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
	Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
	Util.clickPath("//td[text()=\"" + refreshwarn + "\"]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
	boolean result = Util.checkItemInPage("//span[text()=\"" + strColorName + "\"]");
	//Unfold Color
	Navigation.expandColor();
	if(!result)
		TRSReportUtil.report2TRS("200792", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("200792", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(!result);
}

@Test
@PreTest("NA")
@TRSTestCase(id="202404", 
 checkPoints={"Check element already displayed in page: check translation on UI"}) 
public void test05CreateLine() {		 
	String create = LocaleResourceGetter.getLocaleString(TextConstants.Create);
	String styleidentification = LocaleResourceGetter.getLocaleString(TextConstants.StyleIdentification);
	String line = LocaleResourceGetter.getLocaleString(TextConstants.Line);
	String name = LocaleResourceGetter.getLocaleString(TextConstants.Name);
	String desc = LocaleResourceGetter.getLocaleString(TextConstants.Desc);
	String refresh = LocaleResourceGetter.getLocaleString(TextConstants.RefreshPreview);
	String wingline = LocaleResourceGetter.getLocaleString(TextConstants.WingLine);
	String centerline = LocaleResourceGetter.getLocaleString(TextConstants.CenterLine);
	String markerpattern = LocaleResourceGetter.getLocaleString(TextConstants.MarkerPattern);
	String overallline = LocaleResourceGetter.getLocaleString(TextConstants.OverallLine);
	String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
	String yes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
	
	//Navigation.navigateToMapBuilder();
	//Navigation.expandStyles();
	Navigation.expandLine();
	Util.clickPath("//span[text()=\"" + line + "\"]");
	Util.rightClickPath("//span[text()=\"" + line + "\"]");
	Util.clickPath("//td[text()=\"" + create + "\"]", "//h1[text()=\"" + styleidentification + "\"]");
	String strLineDesc = Util.getRandomName(10);
	Util.sendKeys("//label[text()='" + name + "']/parent::td/following-sibling::td/input", strLineName);
	//Before click saveall, need click somewhere to make saveall enabled
	Util.clickPath("//h1[text()=\"" + styleidentification + "\"]");
	Util.sendKeys("//label[text()='" + desc + "']/parent::td/following-sibling::td/input", strLineDesc);
	//Before click saveall, need click somewhere to make saveall enabled
	Util.clickPath("//h1[text()=\"" + styleidentification + "\"]");
	Util.clickPath("//img[contains(@src,'saveall.png')]");
	boolean result1 = Util.checkItemInPage("//button[text()=\"" + refresh + "\"]");
	boolean result2 = Util.checkItemInPage("//a[text()=\"" + overallline + "\"]");
	boolean result3 = Util.checkItemInPage("//a[text()=\"" + wingline + "\"]");
	boolean result4 = Util.checkItemInPage("//a[text()=\"" + centerline + "\"]");
	boolean result5 = Util.checkItemInPage("//a[text()=\"" + markerpattern + "\"]");
	//Close create panel
	Util.clickPath("//a[text()=\"" + strLineName + "\"]/parent::div/following-sibling::div//a");
	//Refresh data to make name in descending order
	Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
	Util.clickPath("//div[text()=\"" + refreshdata + "\"]/ancestor::tr/following-sibling::tr//span[text()=\"" + yes + "\"]");
	boolean result6 = Util.checkItemInPage("//span[text()=\"" + strLineName + "\"]");
	boolean result = result1 && result2 && result3 && result4 && result5 && result6;
	if(result)
		TRSReportUtil.report2TRS("202404", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("202404", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(result);
}

@Test
@PreTest("NA")
@TRSTestCase(id="200792", 
 checkPoints={"Check element already displayed in page: Expecting return false"}) 
public void test06DeleteLine() {		 
	String delete = LocaleResourceGetter.getLocaleString(TextConstants.Delete);
	String deletewarn = LocaleResourceGetter.getLocaleString(TextConstants.DeleteStyleWarn);
	String warnString = deletewarn.substring(0, deletewarn.indexOf("{"));
	if (warnString.equals("")) {
		warnString = deletewarn.substring(deletewarn.indexOf("}")+1);
	}
	String ButtonYes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
	String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
	String refreshwarn = LocaleResourceGetter.getLocaleString(TextConstants.RefreshWarn);
	
	//Navigation.navigateToMapBuilder();
	//Navigation.expandStyles();
	//Navigation.expandLine();
	BasePage.sleepBySecs(5);
	Util.rightClickPath("//span[text()=\"" + strLineName + "\"]");
	Util.clickPath("//td[text()=\"" + delete + "\"]");
	Util.clickPath("//td[contains(text(), \"" + warnString + "\")]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
	Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
	Util.clickPath("//td[text()=\"" + refreshwarn + "\"]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
	boolean result = Util.checkItemInPage("//span[text()=\"" + strLineName + "\"]");
	//Unfold Line
	Navigation.expandLine();
	if(!result)
		TRSReportUtil.report2TRS("200792", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("200792", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(!result);
}

@Test
@PreTest("NA")
@TRSTestCase(id="202405", 
 checkPoints={"Check element already displayed in page: check translation on UI"}) 
public void test07CreateText() {		 
	String create = LocaleResourceGetter.getLocaleString(TextConstants.Create);
	String styleidentification = LocaleResourceGetter.getLocaleString(TextConstants.StyleIdentification);
	String text = LocaleResourceGetter.getLocaleString(TextConstants.Text);
	String name = LocaleResourceGetter.getLocaleString(TextConstants.Name);
	String desc = LocaleResourceGetter.getLocaleString(TextConstants.Desc);
	String refresh = LocaleResourceGetter.getLocaleString(TextConstants.RefreshPreview);
	String sticky = LocaleResourceGetter.getLocaleString(TextConstants.Sticky);
	String space = LocaleResourceGetter.getLocaleString(TextConstants.LetterSpace);
	String font = LocaleResourceGetter.getLocaleString(TextConstants.Font);
	String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
	String yes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
	
	/* Navigation.navigateToMapBuilder();
	Navigation.expandStyles(); */
	Navigation.expandText();
	Util.clickPath("//span[text()=\"" + text + "\"]");
	Util.rightClickPath("//span[text()=\"" + text + "\"]");
	Util.clickPath("//td[text()=\"" + create + "\"]", "//h1[text()=\"" + styleidentification + "\"]");
	String strDesc = Util.getRandomName(10);
	Util.sendKeys("//label[text()='" + name + "']/parent::td/following-sibling::td/input", strTextName);
	//Before click saveall, need click somewhere to make saveall enabled
	Util.clickPath("//h1[text()=\"" + styleidentification + "\"]");
	Util.sendKeys("//label[text()='" + desc + "']/parent::td/following-sibling::td/input", strDesc);
	//Before click saveall, need click somewhere to make saveall enabled
	Util.clickPath("//h1[text()=\"" + styleidentification + "\"]");
	Util.clickPath("//img[contains(@src,'saveall.png')]");
	boolean result1 = Util.checkItemInPage("//button[text()=\"" + refresh + "\"]");
	boolean result2 = Util.checkItemInPage("//a[text()=\"" + sticky + "\"]");
	boolean result3 = Util.checkItemInPage("//a[text()=\"" + font + "\"]");
	boolean result4 = Util.checkItemInPage("//label[text()=\"" + space + "\"]");
	//Close create panel
	Util.clickPath("//a[text()=\"" + strTextName + "\"]/parent::div/following-sibling::div//a");
	//Refresh data to make name in descending order
	Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
	Util.clickPath("//div[text()=\"" + refreshdata + "\"]/ancestor::tr/following-sibling::tr//span[text()=\"" + yes + "\"]");
	boolean result6 = Util.checkItemInPage("//span[text()=\"" + strTextName + "\"]");
	boolean result = result1 && result2 && result3 && result4 && result6;
	
	if(result)
		TRSReportUtil.report2TRS("202405", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("202405", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(result);
}


@Test
@PreTest("NA")
@TRSTestCase(id="200792", 
 checkPoints={"Check element already displayed in page: Expecting return false"}) 
public void test08DeleteText() {		 
	String delete = LocaleResourceGetter.getLocaleString(TextConstants.Delete);
	String deletewarn = LocaleResourceGetter.getLocaleString(TextConstants.DeleteStyleWarn);
	String warnString = deletewarn.substring(0, deletewarn.indexOf("{"));
	if (warnString.equals("")) {
		warnString = deletewarn.substring(deletewarn.indexOf("}")+1);
	}
	String ButtonYes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
	String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
	String refreshwarn = LocaleResourceGetter.getLocaleString(TextConstants.RefreshWarn);
	
	//Navigation.navigateToMapBuilder();
	//Navigation.expandStyles();
	//Navigation.expandText();
	BasePage.sleepBySecs(5);
	Util.rightClickPath("//span[text()=\"" + strTextName + "\"]");
	Util.clickPath("//td[text()=\"" + delete + "\"]");
	Util.clickPath("//td[contains(text(), \"" + warnString + "\")]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
	Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
	Util.clickPath("//td[text()=\"" + refreshwarn + "\"]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
	boolean result = Util.checkItemInPage("//span[text()=\"" + strTextName + "\"]");
	//Unfold Text
	Navigation.expandText();
	if(!result)
		TRSReportUtil.report2TRS("200792", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("200792", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(!result);
}

@Test
@PreTest("NA")
@TRSTestCase(id="201474", 
 checkPoints={"Check element already displayed in page: check translation on UI"}) 
public void test09CreateMarker() {		 
	String create = LocaleResourceGetter.getLocaleString(TextConstants.Create);
	String styleidentification = LocaleResourceGetter.getLocaleString(TextConstants.StyleIdentification);
	String marker = LocaleResourceGetter.getLocaleString(TextConstants.Marker);
	String name = LocaleResourceGetter.getLocaleString(TextConstants.Name);
	String desc = LocaleResourceGetter.getLocaleString(TextConstants.Desc);
	String refresh = LocaleResourceGetter.getLocaleString(TextConstants.RefreshPreview);
	String markertype = LocaleResourceGetter.getLocaleString(TextConstants.MarkerType);
	String markersize = LocaleResourceGetter.getLocaleString(TextConstants.MarkerSize);
	String coordinate = LocaleResourceGetter.getLocaleString(TextConstants.Coordinates);
	String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
	String yes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
	
 	//Navigation.navigateToMapBuilder();
	//Navigation.expandStyles();
	Navigation.expandMarker();
	Util.clickPath("//span[text()=\"" + marker + "\"]");
	Util.rightClickPath("//span[text()=\"" + marker + "\"]");
	Util.clickPath("//td[text()=\"" + create + "\"]", "//h1[text()=\"" + styleidentification + "\"]");
	String strDesc = Util.getRandomName(10);
	Util.sendKeys("//label[text()='" + name + "']/parent::td/following-sibling::td/input", strMarkerName);
	//Before click saveall, need click somewhere to make saveall enabled
	Util.clickPath("//h1[text()=\"" + styleidentification + "\"]");
	Util.sendKeys("//label[text()='" + desc + "']/parent::td/following-sibling::td/input", strDesc);
	//Before click saveall, need click somewhere to make saveall enabled
	Util.clickPath("//h1[text()=\"" + styleidentification + "\"]");
	Util.clickPath("//img[contains(@src,'saveall.png')]");
	boolean result1 = Util.checkItemInPage("//button[text()=\"" + refresh + "\"]");
	boolean result2 = Util.checkItemInPage("//a[text()=\"" + markertype + "\"]");
	boolean result3 = Util.checkItemInPage("//a[text()=\"" + markersize + "\"]");
	boolean result4 = Util.checkItemInPage("//label[text()=\"" + coordinate + "\"]");
	//Close create panel
	Util.clickPath("//a[text()=\"" + strMarkerName + "\"]/parent::div/following-sibling::div//a");
	//Refresh data to make name in descending order
	Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
	Util.clickPath("//div[text()=\"" + refreshdata + "\"]/ancestor::tr/following-sibling::tr//span[text()=\"" + yes + "\"]");
	boolean result6 = Util.checkItemInPage("//span[text()=\"" + strMarkerName + "\"]");
	boolean result = result1 && result2 && result3 && result4 && result6;
	
	if(result)
		TRSReportUtil.report2TRS("201474", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("201474", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(result);
}

@Test
@PreTest("NA")
@TRSTestCase(id="200792", 
 checkPoints={"Check element already displayed in page: Expecting return false"}) 
public void test10DeleteMarker() {		 
	String delete = LocaleResourceGetter.getLocaleString(TextConstants.Delete);
	String deletewarn = LocaleResourceGetter.getLocaleString(TextConstants.DeleteStyleWarn);
	String warnString = deletewarn.substring(0, deletewarn.indexOf("{"));
	if (warnString.equals("")) {
		warnString = deletewarn.substring(deletewarn.indexOf("}")+1);
	}
	String ButtonYes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
	String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
	String refreshwarn = LocaleResourceGetter.getLocaleString(TextConstants.RefreshWarn);
	
	//Navigation.navigateToMapBuilder();
	//Navigation.expandStyles();
	//Navigation.expandMarker();
	BasePage.sleepBySecs(5);
	Util.rightClickPath("//span[text()=\"" + strMarkerName + "\"]");
	Util.clickPath("//td[text()=\"" + delete + "\"]");
	Util.clickPath("//td[contains(text(), \"" + warnString + "\")]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
	Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
	Util.clickPath("//td[text()=\"" + refreshwarn + "\"]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
	boolean result = Util.checkItemInPage("//span[text()=\"" + strMarkerName + "\"]");
	//Unfold Marker
	Navigation.expandMarker();
	if(!result)
		TRSReportUtil.report2TRS("200792", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("200792", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(!result);
}

@Test
@PreTest("NA")
@TRSTestCase(id="201473", 
 checkPoints={"Check element already displayed in page: check translation on UI"}) 
public void test11CreateAdvanced() {		 
	String create = LocaleResourceGetter.getLocaleString(TextConstants.Create);
	String styleidentification = LocaleResourceGetter.getLocaleString(TextConstants.StyleIdentification);
	String advanced = LocaleResourceGetter.getLocaleString(TextConstants.Advanced);
	String buckettype = LocaleResourceGetter.getLocaleString(TextConstants.BucketType);
	String OK = LocaleResourceGetter.getLocaleString(TextConstants.OK).toUpperCase();
	String name = LocaleResourceGetter.getLocaleString(TextConstants.Name);
	String desc = LocaleResourceGetter.getLocaleString(TextConstants.Desc);
	String refresh = LocaleResourceGetter.getLocaleString(TextConstants.RefreshPreview);
	String BucketInformation = LocaleResourceGetter.getLocaleString(TextConstants.BucketInformation);
	String Button_render_style = LocaleResourceGetter.getLocaleString(TextConstants.RenderStyle);
	String HighValue = LocaleResourceGetter.getLocaleString(TextConstants.HighValue);
	String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
	String yes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
	
 	//Navigation.navigateToMapBuilder();
	//Navigation.expandStyles();
	Navigation.expandAdvanced();
	Util.clickPath("//span[text()=\"" + advanced + "\"]");
	Util.rightClickPath("//span[text()=\"" + advanced + "\"]");
	Util.clickPath("//td[text()=\"" + create + "\"]");
	Util.clickPath("//label[text()=\"" + buckettype + "\"]/ancestor::tr/following-sibling"
			+ "::tr//span[text()=\"" + OK + "\"]", "//h1[text()=\"" + styleidentification + "\"]");
	String strDesc = Util.getRandomName(10);
	Util.sendKeys("//label[text()='" + name + "']/parent::td/following-sibling::td/input", strAdvancedName);
	//Before click saveall, need click somewhere to make saveall enabled
	Util.clickPath("//h1[text()=\"" + styleidentification + "\"]");
	Util.sendKeys("//label[text()='" + desc + "']/parent::td/following-sibling::td/input", strDesc);
	//Before click saveall, need click somewhere to make saveall enabled
	Util.clickPath("//h1[text()=\"" + styleidentification + "\"]");
	Util.clickPath("//img[contains(@src,'saveall.png')]");
	
	boolean result2 = Util.checkItemInPage("//a[text()=\"" + BucketInformation + "\"]");
	boolean result3 = Util.checkItemInPage("//span[text()=\"" + Button_render_style + "\"]");
	boolean result4 = Util.checkItemInPage("//label[text()=\"" + HighValue + "\"]");
	//Close create panel
	Util.clickPath("//a[text()=\"" + strAdvancedName + "\"]/parent::div/following-sibling::div//a");
	//Refresh data to make name in descending order
	Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
	Util.clickPath("//div[text()=\"" + refreshdata + "\"]/ancestor::tr/following-sibling::tr//span[text()=\"" + yes + "\"]");
	boolean result6 = Util.checkItemInPage("//span[text()=\"" + strAdvancedName + "\"]");
	boolean result = result2 && result3 && result4 && result6;
	
	if(result)
		TRSReportUtil.report2TRS("201473", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("201473", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(result);
}

@Test
@PreTest("NA")
@TRSTestCase(id="200792", 
 checkPoints={"Check element already displayed in page: Expecting return false"}) 
public void test12DeleteAdvanced() {		 
	String delete = LocaleResourceGetter.getLocaleString(TextConstants.Delete);
	String deletewarn = LocaleResourceGetter.getLocaleString(TextConstants.DeleteStyleWarn);
	String warnString = deletewarn.substring(0, deletewarn.indexOf("{"));
	if (warnString.equals("")) {
		warnString = deletewarn.substring(deletewarn.indexOf("}")+1);
	}
	String ButtonYes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
	String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
	String refreshwarn = LocaleResourceGetter.getLocaleString(TextConstants.RefreshWarn);
	
	//Navigation.navigateToMapBuilder();
	//Navigation.expandStyles();
	//Navigation.expandAdvanced();
	BasePage.sleepBySecs(5);
	Util.rightClickPath("//span[text()=\"" + strAdvancedName + "\"]");
	Util.clickPath("//td[text()=\"" + delete + "\"]");
	Util.clickPath("//td[contains(text(), \"" + warnString + "\")]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
	Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
	Util.clickPath("//td[text()=\"" + refreshwarn + "\"]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
	boolean result = Util.checkItemInPage("//span[text()=\"" + strAdvancedName + "\"]");
	
	//Unfold Advanced and Styles
	Navigation.expandAdvanced();
	Navigation.expandStyles();
	if(!result)
		TRSReportUtil.report2TRS("200792", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("200792", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(!result);
}


@Test
@PreTest("NA")
@TRSTestCase(id="200796", 
 checkPoints={"Check element already displayed in page: check translation on UI"}) 
public void test13CreateNewTheme() {		 
	String create = LocaleResourceGetter.getLocaleString(TextConstants.Create);
	String geometry = LocaleResourceGetter.getLocaleString(TextConstants.Geometry);
	String next = LocaleResourceGetter.getLocaleString(TextConstants.Next);
	String Renderstyle = LocaleResourceGetter.getLocaleString(TextConstants.Renderstyle);
	String Attributes = LocaleResourceGetter.getLocaleString(TextConstants.Attributes);
	String QueryCondition = LocaleResourceGetter.getLocaleString(TextConstants.QueryCondition);
	String LabelFunction = LocaleResourceGetter.getLocaleString(TextConstants.LabelFunction);
	String finish = LocaleResourceGetter.getLocaleString(TextConstants.Finish);
	String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
	String yes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
	String desc = LocaleResourceGetter.getLocaleString(TextConstants.Desc);
 	
	Navigation.expandThemes();
	Navigation.expandGeometry();
	Util.clickPath("//span[text()=\"" + geometry + "\"]");
	Util.rightClickPath("//span[text()=\"" + geometry + "\"]");
	Util.clickPath("//td[text()=\"" + create + "\"]");
	//Input name and description
	Util.sendKeys("//td/input[@value='themename']", strThemeName);
	String strDesc = Util.getRandomName(10);
	Util.sendKeys("//label[text()=\"" + desc + "\"]/parent::td/following-sibling::td//input", strDesc);
	BasePage.sleepBySecs(3);
	//Click next button
	Util.clickPath("//button[text()=\"" + next + "\"]");
	//Check translation of this UI
	boolean result1 = Util.checkItemInPage("//label[text()=\"" + Renderstyle + "\"]");
	boolean result2 = Util.checkItemInPage("//label[text()=\"" + Attributes + "\"]");
	//Click next button
    Util.clickPath("//button[text()=\"" + next + "\"]");
    //Check translation of this UI
  	boolean result3 = Util.checkItemInPage("//label[text()=\"" + LabelFunction + "\"]");
    //Click next button
    Util.clickPath("//button[text()=\"" + next + "\"]");
    //Check translation of this UI
  	boolean result4 = Util.checkItemInPage("//label[text()=\"" + QueryCondition + "\"]");
  	//Click next button and finish
    Util.clickPath("//button[text()=\"" + next + "\"]");
    Util.clickPath("//button[text()=\"" + finish + "\"]");
    //Refresh data to make name in descending order
	Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
	Util.clickPath("//div[text()=\"" + refreshdata + "\"]/ancestor::tr/following-sibling::tr//span[text()=\"" + yes + "\"]");
	boolean result5 = Util.checkItemInPage("//span[text()=\"" + strThemeName + "\"]");
	boolean result = result1 &&result2 && result3 && result4 && result5;
	
	if(result)
		TRSReportUtil.report2TRS("200796", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("200796", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(result);
}

@Test
@PreTest("NA")
@TRSTestCase(id="200797", 
 checkPoints={"Check element already displayed in page: Check translation on UI"}) 
public void test14ManageTheme() {		 
	String open = LocaleResourceGetter.getLocaleString(TextConstants.Open);
	String deletewarn = LocaleResourceGetter.getLocaleString(TextConstants.DeleteStyleWarn);
	String warnString = deletewarn.substring(0, deletewarn.indexOf("{"));
	if (warnString.equals("")) {
		warnString = deletewarn.substring(deletewarn.indexOf("}")+1);
	}
	String desc = LocaleResourceGetter.getLocaleString(TextConstants.Desc).trim();
	String ThemeIdentify = LocaleResourceGetter.getLocaleString(TextConstants.ThemeIdentify);
	
	
	String modifiedDesc = Util.getRandomName(6).toUpperCase();
	BasePage.sleepBySecs(5);
	Util.rightClickPath("//span[text()=\"" + strThemeName + "\"]");
	Util.clickPath("//td[text()=\"" + open + "\"]");
	BasePage.sleepBySecs(3);
	Util.sendKeys("//label[contains(text(),\"" + desc + "\")]/parent::td/following-sibling::td//input", modifiedDesc);
	//Before click saveall, need click somewhere to make saveall enabled
	Util.clickPath("//h1[text()=\"" + ThemeIdentify + "\"]");
	Util.clickPath("//img[contains(@src,'saveall.png')]");
	//Check modified string already in page
	boolean result = Util.checkItemInPage("//input[@value=\"" + modifiedDesc + "\"]");
	//Close modify dialog
	Util.clickPath("//a[text()=\"" + strThemeName + "\"]/parent::div/following-sibling::div//a");
	//Unfold Geometry and Themes
	Navigation.expandGeometry();
	BasePage.sleepBySecs(3);
	Navigation.expandThemes();
	if(result)
		TRSReportUtil.report2TRS("200797", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("200797", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(result);
}


@Test
@PreTest("NA")
@TRSTestCase(id="200801", 
 checkPoints={"Check element already displayed in page: check translation on UI"}) 
public void test15CreateBaseMap() {		 
	String create = LocaleResourceGetter.getLocaleString(TextConstants.Create);
	String basemap = LocaleResourceGetter.getLocaleString(TextConstants.BaseMaps);
	String next = LocaleResourceGetter.getLocaleString(TextConstants.Next);
	String BasemapParameters  = LocaleResourceGetter.getLocaleString(TextConstants.BasemapParameters);
	String MinRenderStyle = LocaleResourceGetter.getLocaleString(TextConstants.MinRenderStyle);
	String SelectThemes = LocaleResourceGetter.getLocaleString(TextConstants.SelectThemes);
	String Geometry = LocaleResourceGetter.getLocaleString(TextConstants.Geometry);
	String finish = LocaleResourceGetter.getLocaleString(TextConstants.Finish);
	String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
	String yes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
	String desc = LocaleResourceGetter.getLocaleString(TextConstants.Desc);
 	String BasemapThemes = LocaleResourceGetter.getLocaleString(TextConstants.BasemapThemes);
 	String ok = LocaleResourceGetter.getLocaleString(TextConstants.OK).toUpperCase();
 	
 	Navigation.expandBaseMaps();
	Util.clickPath("//span[text()=\"" + basemap + "\"]");
	Util.rightClickPath("//span[text()=\"" + basemap + "\"]");
	Util.clickPath("//td[text()=\"" + create + "\"]");
	//Input name and description
	Util.sendKeys("//td/input[@value='mapname']", strBaseMapName);
	//String strDesc = Util.getRandomName(10);
	//Util.sendKeys("//label[text()=\"" + desc + "\"]/parent::td/following-sibling::td//input", strDesc);
	BasePage.sleepBySecs(3);
	//Click next button
	Util.clickPath("//button[text()=\"" + next + "\"]");
	//Check translation of this UI
	boolean result1 = Util.checkItemInPage("//a[text()=\"" + BasemapParameters + "\"]");
	//Add theme to this base map
  	Util.clickPath("//img[contains(@src,'attributeadd')]");
  	Util.clickPath("//div[text()=\"" + SelectThemes + "\"]/ancestor::tr/following-sibling::tr"
  			+ "//span[text()=\"" + Geometry + "\"]/parent::span/preceding-sibling::span/a");
  	//Select theme created by test13
  	Util.clickPath("//span[text()=\"" + strThemeName + "\"]");
  	Util.clickPath("//div[text()=\"" + SelectThemes + "\"]/ancestor::tr/following-sibling::tr"
  			+ "//span[text()=\"" + ok + "\"]");
    //Check translation of this UI
  	boolean result3 = Util.checkItemInPage("//div[text()=\"" + MinRenderStyle + "\"]");
  	//Click next button
    Util.clickPath("//button[text()=\"" + next + "\"]");
    //Check translation of this UI
  	boolean result4 = Util.checkItemInPage("//a[text()=\"" + BasemapThemes + "\"]");
  	//Click button finish
    Util.clickPath("//button[text()=\"" + finish + "\"]");
    //Refresh data to make name in descending order
	Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
	Util.clickPath("//div[text()=\"" + refreshdata + "\"]/ancestor::tr/following-sibling::tr//span[text()=\"" + yes + "\"]");
	boolean result5 = Util.checkItemInPage("//span[text()=\"" + strBaseMapName + "\"]");
	boolean result = result1 && result3 && result4 && result5;
	
	if(result)
		TRSReportUtil.report2TRS("200801", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("200801", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(result);
}

@Test
@PreTest("NA")
@TRSTestCase(id="200802", 
 checkPoints={"Check element already displayed in page: check translation on UI"}) 
public void test16ManageBaseMap() {		 
	String open = LocaleResourceGetter.getLocaleString(TextConstants.Open);
	String deletewarn = LocaleResourceGetter.getLocaleString(TextConstants.DeleteStyleWarn);
	String warnString = deletewarn.substring(0, deletewarn.indexOf("{"));
	if (warnString.equals("")) {
		warnString = deletewarn.substring(deletewarn.indexOf("}")+1);
	}
	String desc = LocaleResourceGetter.getLocaleString(TextConstants.Desc).trim();
	String BaseMapIdentify = LocaleResourceGetter.getLocaleString(TextConstants.BasemapIdentification);
	
	String modifiedDesc = Util.getRandomName(6).toUpperCase();
	BasePage.sleepBySecs(5);
	Util.rightClickPath("//span[text()=\"" + strBaseMapName + "\"]");
	Util.clickPath("//td[text()=\"" + open + "\"]");
	BasePage.sleepBySecs(3);
	Util.sendKeys("//label[contains(text(),\"" + desc + "\")]/parent::td/following-sibling::td//input", modifiedDesc);
	//Before click saveall, need click somewhere to make saveall enabled
	Util.clickPath("//h1[text()=\"" + BaseMapIdentify + "\"]");
	Util.clickPath("//img[contains(@src,'saveall.png')]");
	//Check modified string already in page
	boolean result = Util.checkItemInPage("//input[@value=\"" + modifiedDesc + "\"]");
	//Close modify dialog
	Util.clickPath("//a[text()=\"" + strBaseMapName + "\"]/parent::div/following-sibling::div//a");

	if(result)
		TRSReportUtil.report2TRS("200802", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("200802", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(result);
}


@Test
@PreTest("NA")
@TRSTestCase(id="200792", 
 checkPoints={"Check element already displayed in page: Expecting return false"}) 
public void test17DeleteBaseMap() {		 
	String delete = LocaleResourceGetter.getLocaleString(TextConstants.Delete);
	String deletewarn = LocaleResourceGetter.getLocaleString(TextConstants.DeleteBasemapQuestion);
	String warnString = deletewarn.substring(0, deletewarn.indexOf("{"));
	if (warnString.equals("")) {
		warnString = deletewarn.substring(deletewarn.indexOf("}")+1);
	}
	String ButtonYes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
	String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
	String refreshwarn = LocaleResourceGetter.getLocaleString(TextConstants.RefreshWarn);


	BasePage.sleepBySecs(5);
	Util.rightClickPath("//span[text()=\"" + strBaseMapName + "\"]");
	Util.clickPath("//td[text()=\"" + delete + "\"]");
	Util.clickPath("//td[contains(text(), \"" + warnString + "\")]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
	Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
	Util.clickPath("//td[text()=\"" + refreshwarn + "\"]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
	boolean result = Util.checkItemInPage("//span[text()=\"" + strBaseMapName + "\"]");
	
	//Unfold Base map
	Navigation.expandBaseMaps();
	if(!result)
		TRSReportUtil.report2TRS("200792", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("200792", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(!result);
}

@Test
@PreTest("NA")
@TRSTestCase(id="200792", 
checkPoints={"Check element already displayed in page: Expecting return false"}) 
public void test18DeleteTheme() {		 
	String delete = LocaleResourceGetter.getLocaleString(TextConstants.Delete);
	String deletewarn = LocaleResourceGetter.getLocaleString(TextConstants.DeleteThemeQuestion);
	String warnString = deletewarn.substring(0, deletewarn.indexOf("{"));
	if (warnString.equals("")) {
		warnString = deletewarn.substring(deletewarn.indexOf("}")+1);
	}
	String ButtonYes = LocaleResourceGetter.getLocaleString(TextConstants.ButtonYes);
	String refreshdata = LocaleResourceGetter.getLocaleString(TextConstants.RefreshData);
	String refreshwarn = LocaleResourceGetter.getLocaleString(TextConstants.RefreshWarn);
	
	Navigation.expandThemes();
	Navigation.expandGeometry();
	BasePage.sleepBySecs(5);
	Util.rightClickPath("//span[text()=\"" + strThemeName + "\"]");
	Util.clickPath("//td[text()=\"" + delete + "\"]");
	Util.clickPath("//td[contains(text(), \"" + warnString + "\")]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
	Util.clickPath("//button[@title=\"" + refreshdata + "\"]");
	Util.clickPath("//td[text()=\"" + refreshwarn + "\"]/parent::tr/following-sibling::tr//span[text()=\"" + ButtonYes + "\"]");
	boolean result = Util.checkItemInPage("//span[text()=\"" + strThemeName + "\"]");
	
	//Unfold Geometry and Themes
	Navigation.expandGeometry();
	BasePage.sleepBySecs(3);
	Navigation.expandThemes();
	if(!result)
		TRSReportUtil.report2TRS("200792", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("200792", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(!result);
}

@Test
@PreTest("NA")
@TRSTestCase(id="200808", 
 checkPoints={"Check element already displayed in page: check translation on UI"}) 
public void test19CheckTileLayer() {		 
	String open = LocaleResourceGetter.getLocaleString(TextConstants.Open);
	String EditorPanel = LocaleResourceGetter.getLocaleString(TextConstants.EditorPanels);
	
	Navigation.expandTileLayers();
	BasePage.sleepBySecs(5);
	Util.rightClickPath("//span[text()='CUSTOMER_MAP']");
	Util.clickPath("//td[text()=\"" + open + "\"]");
	//Check modified string already in page
	boolean result = Util.checkItemInPage("//h1[text()=\"" + EditorPanel + "\"]");
	BasePage.saveScreenShot("200808_test19CheckTileLayer_XML");
	//Close modify dialog
	Util.clickPath("//a[text()='CUSTOMER_MAP']/parent::div/following-sibling::div//a");
		
	if(result)
		TRSReportUtil.report2TRS("200808", TRSReportUtil.RESULT_PASSED);
	else 
		TRSReportUtil.report2TRS("200808", TRSReportUtil.RESULT_FAILED);
	Assert.assertTrue(result);
}



public void main(String[] args){
	
}

}
