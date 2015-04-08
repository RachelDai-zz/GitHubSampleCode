package oracle.sgt.mapviewer.common.constants;

//import com.oracle.sgt.webdriver.common.EnvConstants;

public class MapViewerEnvConstants {
	//for instance, passed from Ant
	public final static String HOSTNAME = System.getProperty("hostname");
	public final static String PORT = System.getProperty("port");
	public final static String LOGINNAME = System.getProperty("loginname");
	public final static String LOGINPASS = System.getProperty("loginpass");
	
	//for debug
	/*public final static String LOCALE = System.getProperty("locale") == null?"en_US":
		("".equals(System.getProperty("locale"))?"en_US":System.getProperty("locale"));*/
	public final static String LOCALE = "de";
	//public static String URL = "http://" + HOSTNAME + ":" + PORT + "/mapviewer/";
	public static String URL = "http://slc00bnd.us.oracle.com:7001/mapviewer";
	public static String BROWSER = "Firefox";	
	/*public static String BROWSER = !(System.getProperty("browser").contains("Firefox")
			||System.getProperty("browser").contains("Chrome")
			||System.getProperty("browser").contains("IE"))?"Firefox":System.getProperty("browser");*/
	public static String IEDRIVERSERVER = (System.getProperty("iedriverserver")==null || 
			System.getProperty("iedriverserver").equals(""))?"c:\\IEDriverServer.exe":
				System.getProperty("iedriverserver");	
	public static String CHROMEDRIVERSERVER = (System.getProperty("chromedriverserver")==null || 
			"".equals(System.getProperty("chromedriverserver"))) ? 
					"c:\\chromedriverserver.exe" : System.getProperty("chromedriverserver");
	public static String TomcatUrl = "http://" + System.getProperty("tomcatname") + ":" + System.getProperty("tomcatport");
	
	//for testdata, passed from ant
	public final static String PWD = System.getProperty("pwd");
	public final static String DATADIR = System.getProperty("datadir");
	public static String DATAPATH = PWD + System.getProperty("file.separator") + DATADIR ;
	public static String SCREENSHOTSDIR = System.getProperty("screenshotsDir");
	public static String SCREENSHOTSPATH =  SCREENSHOTSDIR ;
	
	
	// for report testresult to trs
	public static String TESTPLANID = System.getProperty("testplanid");
	public static String DEFAULT_REPORTER = "MapViewerAutomation";
	public static String REPORTER = 
			(System.getProperty("reporter")==null ||System.getProperty("reporter").equals(""))?DEFAULT_REPORTER:System.getProperty("reporter");
	
	
	public final static String TESTCASE_START_MSG = "START INVOKING TESTCASE: ";
	public final static String TESTCASE_END_MSG = "END INVOKING TESTCASE: ";
	public final static String SCREENSHOT_SAVED = "Screenshot saved: ";

	
}