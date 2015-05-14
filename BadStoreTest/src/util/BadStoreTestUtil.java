package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import badstore.TestClass1;

public class BadStoreTestUtil {
	
	public static int threadTimeForSleeping = 1000;

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
		String newElement = "<div id=\"commentText\" style=\"background-color:black; font-family: ‘Lucida Console’, Monaco, monospace; color:white; padding:5px; width:" + ((TestClass1.width/2)-10) + ";\"><h3>"+message+"</h3></div>";
		String modifiedHtml = pageSource+newElement;
		modifiedHtml = modifiedHtml.replaceAll("\\s\\s*", " ");
		modifiedHtml = modifiedHtml.replaceAll("&", "&amp;");
		modifiedHtml = modifiedHtml.replaceAll("'", "\"");
		//modifiedHtml = modifiedHtml.replaceAll("'", "&#39;");
		jsExecutor.executeScript("arguments[0].innerHTML='" + modifiedHtml + "'", element);
	}
	
	public static void injectHtmlElementAttackBlock(WebDriver driver, String elementInfo, String message){
		WebElement element = driver.findElement(By.tagName(elementInfo));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String pageSource = driver.getPageSource();
		//pageSource = pageSource.substring(pageSource.indexOf("<div")+1);
		//pageSource = pageSource.substring(pageSource.indexOf(">")+1,pageSource.lastIndexOf("</div>"));
		String newElement = "<div id=\"commentText\" style=\"background-color:black; font-family: ‘Lucida Console’, Monaco, monospace; color:white; padding:5px; width:" + ((TestClass1.width/2)-10) + ";\"><h3>"+message+"</h3></div>";
		String modifiedHtml = "<body>" + pageSource + newElement + "</body>";
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
	
	public static void disableMouseRightClick() throws Exception{
		try {
			//String[] cmd = {"/home/swap/java_program/d.sh"};
			String[] cmd = {"/badstore/disable_right_click.sh"};
			Process pb = Runtime.getRuntime().exec(cmd);
			String line;
			BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));
			while ((line = input.readLine()) != null) {
			    System.out.println(line);
			}
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void disableInputDevice(String id) throws Exception{
		try {
			//String[] cmd = {"/home/swap/java_program/s.sh",id};
			String[] cmd = {"/badstore/disable_input_device.sh",id};
			Process pb = Runtime.getRuntime().exec(cmd);
			String line;
			BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));
			while ((line = input.readLine()) != null) {
			    System.out.println(line);
			}
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void enableInputDevice(String id) throws Exception{
		try {
			//String[] cmd = {"/home/swap/java_program/d.sh"};
			String[] cmd = {"/badstore/enable_input_device.sh",id};
			Process pb = Runtime.getRuntime().exec(cmd);
			String line;
			BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));
			while ((line = input.readLine()) != null) {
			    System.out.println(line);
			}
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void enableInputDevices() throws Exception{
		String output = executeCommand("xinput list");
		int[] device_ids = extractInputDeviceIds(output);
		for(int id : device_ids){
			enableInputDevice(Integer.toString(id));
		}
	}
	
	public static void disableInputDevices() throws Exception{
		String output = executeCommand("xinput list");
		int[] device_ids = extractInputDeviceIds(output);
		for(int id : device_ids){
			disableInputDevice(Integer.toString(id));
		}
	}
	
	private static String executeCommand(String command){
		StringBuffer output = new StringBuffer();
		Process process;
		try{
			process = Runtime.getRuntime().exec(command);
			process.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return output.toString();
	}
	
	private static int[] extractInputDeviceIds(String output) throws Exception{
		String[] lines = output.split("\n");
		int[] ids = new int[lines.length-1];
		Pattern pattern = Pattern.compile("(.*)id=(\\d+)(.*)");
		for(int i=0; i<lines.length-1; i++){
			if(lines[i] != null && !lines[i].isEmpty()){
				Matcher matcher = pattern.matcher(lines[i]);
				if(matcher.find() && matcher.group(2) != null && !matcher.group(2).isEmpty()){
					ids[i] = Integer.parseInt(matcher.group(2));
				}
			}
		}
		return ids;
	}
	
}
