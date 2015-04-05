package util;

import org.apache.commons.lang3.StringEscapeUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BadStoreTestUtil {

	public static void highlightElementById(WebDriver driver, String elementID, String borderColor, String textColor) {
		WebElement element = driver.findElement(By.id(elementID));
		BadStoreTestUtil.highlightHtmlElement(driver, element, borderColor, textColor);
	}
	
	public static void highlightElementByName(WebDriver driver, String elementName, String borderColor, String textColor) {
		WebElement element = driver.findElement(By.name(elementName));
		BadStoreTestUtil.highlightHtmlElement(driver, element, borderColor, textColor);
	}
	
	
	public static void highlightElementByLinkText(WebDriver driver, String linkText, String borderColor, String textColor) {
		WebElement element = driver.findElement(By.linkText(linkText));
		BadStoreTestUtil.highlightHtmlElement(driver, element, borderColor, textColor);
	}
	
	public static void highlightElementByXPath(WebDriver driver, String xPath, String borderColor, String textColor) {
		WebElement element = driver.findElement(By.xpath(xPath));
		BadStoreTestUtil.highlightHtmlElement(driver, element, borderColor, textColor);
	}
	
	private static void highlightHtmlElement(WebDriver driver, WebElement element, String borderColor, String textColor){
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);",
				element, "color:"+ textColor +"; border: 5px solid "+borderColor+";");
		//driver.findElement(By.id(elementID)).clear();
		//driver.findElement(By.id(elementID)).sendKeys("");
	}
	
	public static String getElementValue(WebDriver driver,String elementID){
		return driver.findElement(By.id(elementID)).getText();
	}
	
	/*public static void injectHtmlElement(WebDriver driver, String elementInfo, String message){
		WebElement element = driver.findElement(By.tagName(elementInfo));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String pageSource = driver.getPageSource();
		pageSource = pageSource.substring(pageSource.indexOf("<body")+1);
		pageSource = pageSource.substring(pageSource.indexOf(">")+1,pageSource.indexOf("</body>"));
		String newElement = "<div id=\"commentText\" style=\"background-color:black; color:white; padding:5px; width:750px;\"><h2>"+message+"</h2></div>";
		String modifiedHtml = pageSource+newElement;
		modifiedHtml = modifiedHtml.replaceAll("\\s\\s*", " ");
		System.out.println(modifiedHtml);
		System.out.println("===========================================");
		modifiedHtml = modifiedHtml.replaceAll("&", "&amp;");
		System.out.println(modifiedHtml);
		System.out.println("===========================================");
		modifiedHtml = modifiedHtml.replaceAll("'", "\"");
		//modifiedHtml = modifiedHtml.replaceAll("'", "&#39;");
		System.out.println(modifiedHtml);
		System.out.println("===========================================");
		jsExecutor.executeScript("arguments[0].innerHTML='" + modifiedHtml + "'", element);
	}*/
	
	public static void injectHtmlElement(WebDriver driver, String elementInfo, String message){
		WebElement element = driver.findElement(By.tagName(elementInfo));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String pageSource = driver.getPageSource();
		pageSource = pageSource.substring(pageSource.indexOf("<body")+1);
		pageSource = pageSource.substring(pageSource.indexOf(">")+1,pageSource.lastIndexOf("</body>"));
		String newElement = "<div id=\"commentText\" style=\"background-color:black; font-family: ‘Lucida Console’, Monaco, monospace; color:white; padding:5px; width:750px;\"><h3>"+message+"</h3></div>";
		String modifiedHtml = pageSource+newElement;
		modifiedHtml = modifiedHtml.replaceAll("\\s\\s*", " ");
		modifiedHtml = modifiedHtml.replaceAll("&", "&amp;");
		modifiedHtml = modifiedHtml.replaceAll("'", "\"");
		//modifiedHtml = modifiedHtml.replaceAll("'", "&#39;");
		jsExecutor.executeScript("arguments[0].innerHTML='" + modifiedHtml + "'", element);
	}
	
	public static void zoomOutIn(WebDriver driver, String zoomOption, int zoomLevel){
		WebElement html = driver.findElement(By.tagName("html"));
		if("zoomout".equalsIgnoreCase(zoomOption)){
			for(int i=0; i<zoomLevel; i++){
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
			}
		}else{
			if("zoomin".equalsIgnoreCase(zoomOption)){
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			}
		}
	}
	
}
