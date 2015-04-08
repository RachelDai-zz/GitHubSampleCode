package oracle.sgt.mapviewer.page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.oracle.sgt.log4j.ext.ExtLogger;
import com.oracle.sgt.webdriver.common.FatwireFFDriver;
import com.oracle.sgt.webdriver.common.FatwireIEDriver;
import com.oracle.sgt.webdriver.common.FatwireChromeDriver;




public class BasePage {

//	public static FatwireFFDriver driver = FatwireFFDriver.getDriverInstance();
	

//	public static FatwireChromeDriver driver = FatwireChromeDriver.getDriverInstance();
	//public static RemoteWebDriver driver = System.getProperty("browser").equals("IE")?FatwireIEDriver.getDriverInstance()
		//	:System.getProperty("browser").equals("Chrome")?FatwireChromeDriver.getDriverInstance():FatwireFFDriver.getDriverInstance();
	public static RemoteWebDriver driver = 	FatwireFFDriver.getDriverInstance();
		
	private static ExtLogger log = ExtLogger.getLogger(BasePage.class.getName());

	public static void sleepBySecs(int i) {

		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			// TODO do nothing 
			// e.printStackTrace();
		}

	}
	

	public static String handleAlertWindows() {

		String alertMessage = "";

		try {

			Alert alert = driver.switchTo().alert();

			alertMessage = alert.getText();

			alert.accept();

		}

		catch (NoAlertPresentException e) {

			track("Execution done, No Alert windows popup!");

		}

		return alertMessage;
	}

	public static boolean alertPopsup(String msg) {
		boolean b = false;
		Alert alert = driver.switchTo().alert();
		if (alert != null) {
			b = true;
			if (!"".equals(msg) && !alert.getText().contains(msg)) {
				b = false;
			}
			alert.accept();
		}
		driver.switchTo().defaultContent();
		return b;
	}

	public static void checkPoints(String key, boolean status) {
		// saveScreenShot(key);
		log.checkPoints(key, status);
	}

	public static void track(String msg) {
		log.track(msg);
	}

	public static void info(String msg) {
		log.info(msg);
	}

	public static void error(String msg) {
		log.error(msg);
	}

	public static void saveScreenShot(String name) {
		
			if(System.getProperty("browser").equals("IE")){
				
				((FatwireIEDriver)driver).saveScreenShot(name);
				
			}else{
				
				if(System.getProperty("browser").equals("Chrome"))
					
					((FatwireChromeDriver)driver).saveScreenShot(name);
				
				else
					
					((FatwireFFDriver)driver).saveScreenShot(name);
				
			}
	}
	
	public static void dragAndDrop(RemoteWebElement source,RemoteWebElement target){
		

	}
	public static void dragAndDrop(WebElement source,int x,int y){
		
	}
	public static void rightClick(WebElement we){
		we.click();
		Actions actions = new Actions(driver);
		Action action = actions.contextClick(we).build();
		action.perform();

	}

	public static void doubleClick(WebElement myElement){
		Actions action = new Actions(driver);
		action.doubleClick(myElement);
		action.perform();

	}
	

}
