package oracle.sgt.mapviewer.page;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import junit.framework.Assert;
import oracle.sgt.mapviewer.common.constants.MapViewerEnvConstants;
import oracle.sgt.mapviewer.common.constants.TextConstants;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.oracle.sgt.webdriver.common.VerificationException;
import com.oracle.sgt.webdriver.util.LocaleResourceGetter;
import com.oracle.sgt.webdriver.util.TRSReportUtil;
import com.oracle.sgt.webdriver.util.TestDataReader;

public class Util extends BasePage{
	//Check xpath which contains translation
	public static boolean checkItemInPage(String strPath) {
		boolean result = false;
		WebElement we = null;
		try {
			we = driver.findElement(By.xpath(strPath));	
			if ( we.isDisplayed() || we.isEnabled()) {
				result = true;
			}
		}catch (Exception e){
			e.getStackTrace();
			result = false;
		} 
		checkPoints("Check element already displayed in page", result);
		sleepBySecs(3);
		return result;
	}
	
	
	//Get Random String
	public static String getRandomName(int length){
		//for 10 admin languages, length should be less than 15
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("zh_TW", "華話國話屬藏語語聲調漢語系統種");
		map.put("zh_CN", "再加上一套记录和编排所有改变的");
		map.put("pt_BR", "âæãÝÞßðñòóøùûþÿ");
		map.put("ko", "마크업언어를통해쉽게내용을추가");
		map.put("ja", "を規定」していないが、法令その");
		map.put("it", "ÚÛÜùÏÐÑÒÓÔÕÖ×ØÙ");
		map.put("es", "§¦¶óéáíúčëñüåäö");
		map.put("en", "#&*abmefghlyz~q");
		map.put("de", "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎ");
		map.put("fr", "çèŒœàĉôîŸê'ïð`^");
		
		Random random = new Random();
		StringBuffer buf = new StringBuffer();
		Iterator iter = map.keySet().iterator();
		String str = null;
		while (iter.hasNext()) {
			String key = iter.next().toString();
			if (key.equals(MapViewerEnvConstants.LOCALE) ) {  
				str = map.get(key).toString();
			}
		}
		for (int i=0; i < length; i++) {
			int num = random.nextInt(15);
			buf.append(str.charAt(num));
		}
		return buf.toString();
	}
	
	
	//Click Path and wait for the specific element to show up
	public static void clickPath(String xpathString, String xpathWaitElement){
		try { 
			WebElement we = driver.findElement(By.xpath(xpathString));
			if (we.isDisplayed()){
				we.click();
			}
			else 
				System.out.println("clickPath - Element" + xpathString + " not displayed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("clickPath - Element" + xpathString + " not found.");
		}
		waitElement(xpathWaitElement, 8);
	}
	
	
	//Click Path and wait for 2 seconds
	public static void clickPath(String xpathString){
		try {
			WebElement we = driver.findElement(By.xpath(xpathString));
			if (we.isDisplayed()){
				we.click();
			}
			else 
				System.out.println("clickPath - Element" + xpathString + " not displayed.");
			sleepBySecs(2);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("clickPath - Element" + xpathString + " not found.");
		}
	}
	
	//Right Click Path and wait for 2 seconds
	public static void rightClickPath(String xpathString){
		try {
			WebElement we = driver.findElement(By.xpath(xpathString));
			if (we.isDisplayed()){
				BasePage.rightClick(we);
			}
			else 
				System.out.println("rightClickPath - Element" + xpathString + " not displayed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("rightClickPath - Element" + xpathString + " not found.");
		}
		sleepBySecs(2);
	}
	
	//Right Click Path and wait for the specific element to show up
	public static void rightClickPath(String xpathString, String xpathWaitElement){
		try { 
			WebElement we = driver.findElement(By.xpath(xpathString));
			if (we.isDisplayed()){
				BasePage.rightClick(we);
			}
			else 
				System.out.println("rightClickPath - Element" + xpathString + " not displayed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("rightClickPath - Element" + xpathString + " not found.");
		}
		waitElement(xpathWaitElement, 8);
	}
	
	//Clear and Send keys
	public static void sendKeys(String xpath, String keys){
		try {
			WebElement we = driver.findElement(By.xpath(xpath));
			if (we.isDisplayed()) {
				we.clear();
				we.sendKeys(keys);				
			} else {
				System.out.println("sendKeys - Element" + xpath + " not displayed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sendKeys - Element" + xpath + " not found.");
		}
		sleepBySecs(2);
	}
	
	//Wait until a particular element showed up for a particular time
	public static void waitElement(String xpathString, int intSec){
		//sleepBySecs(2);
		int n = 1;
		while (n < intSec) {
			WebElement we = null;
			try {
				we = driver.findElement(By.xpath(xpathString));
				if (we.isDisplayed()) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (n == intSec - 1) {
					System.out.println("waitElement - " + xpathString + ". xpath didn't show up.");
				}
				n = n + 1;
				sleepBySecs(1);
			}
		}
	}
	
	public static void main(String args[]) {
		String aa = getRandomName(6);
		System.out.println(aa);
	}

}
