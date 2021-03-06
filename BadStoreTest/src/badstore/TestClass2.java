package badstore;

import com.thoughtworks.selenium.*;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import util.BadStoreTestUtil;
import static org.testng.Assert.*;
import gui.StartScreen;

import java.awt.Toolkit;
import java.util.regex.Pattern;

public class TestClass2{
	
	public String baseUrl = "http://99.99.101.30";
	public WebDriver driver = new FirefoxDriver();
	private Selenium selenium = null;
	
	//information
	private String userEmail = "swap@localhost.net";
	private String userPassword = "swap";
	private String hackerEmail = "hacker@localhost.net";
	private String hackerPassword = "hacker";
	//color
	private String userHighlightColor = "yellow";
	private String userTextColor = "green";
	private String hackerHighlightColor = "yellow";
	private String hackerTextColor = "red";
	//waiting time information
	private String waitForPageLoading = "100000"; //milliseconds
	private int threadTimeForSleeping = BadStoreTestUtil.threadTimeForSleeping; //milliseconds
	public static int threadParallelWait = 2000;
	//width and height
	private double width;
	private double height;

	@BeforeSuite
	public void beforeSuite() throws Exception{
		System.out.println(System.getProperty("user.dir"));
		//get screen width and height
		java.awt.Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		width = screen.getWidth();
		height = screen.getHeight();
		System.out.println(width+" x "+height);
		//adjust browser position
		driver.manage().window().setPosition(new Point((int)width/2,0));
		driver.manage().window().setSize(new Dimension((int)width/2,(int)height));
		//open selenium
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		selenium.open("/");
		BadStoreTestUtil.zoomOutIn(driver, "zoomin", 5);
	}
	
	@BeforeTest
	public void beforeTest() throws Exception{
		//need to set position again for Linux Platform
		//**driver.manage().window().setPosition(new Point((int)width/2,0));
		//register user
		BadStoreTestUtil.injectHtmlElement(driver, "body", "User creates his/her account on badstore");
		userSignup(userEmail, userEmail, userPassword, userHighlightColor, userTextColor);
		//register hacker
		BadStoreTestUtil.injectHtmlElement(driver, "body", "Hacker creates his/her account on badstore");
		userSignup(hackerEmail, hackerEmail, hackerPassword, hackerHighlightColor, hackerTextColor);
	}
	
	@Test
	public void sqlInjectionAttackTestOne() throws Exception {
		try {
			//open home page
			BadStoreTestUtil.injectHtmlElement(driver, "body", "TEST 1 ==> Login with SQL Injection");
			openHomepage(userHighlightColor, userTextColor);
			//user login
			BadStoreTestUtil.injectHtmlElement(driver, "body", "TEST 1 > STEP 1 ==> User login with username and password");
			userLogin(userEmail, userPassword, userHighlightColor, userTextColor);
			//user logout
			BadStoreTestUtil.injectHtmlElement(driver, "body", "TEST 1 > STEP 2 ==> user logout !!!!!");
			userLogout(userHighlightColor, userTextColor);
			//hacker login to users account through sql injection
			BadStoreTestUtil.injectHtmlElement(driver, "body", "TEST 1 > STEP 3 ==> Hacker hacks user account with sql injection");
			String sqlInjection = userEmail+"' OR '1'='1";
			userLogin(sqlInjection, "", hackerHighlightColor, hackerTextColor);
			//to check whether WAF catch attack or not
			if(driver.findElement(By.linkText("Home")) == null){
				//WAF catch attack
				BadStoreTestUtil.injectHtmlElementAttackBlock(driver, "div", "Attack Failed");
				throw new Exception();
			}else{
				//WAF didn't catch attack
			}
			//open home page
			BadStoreTestUtil.injectHtmlElement(driver, "body", "TEST 1 > STEP 4 ==> Hacker gets access to user account without password");
			openHomepage(hackerHighlightColor, hackerTextColor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			while(TestClass1.trackTest[1]==(byte)0){
				//wait here for another thread
				Thread.sleep(threadParallelWait);
			}
		}
	}
	
	@Test
	public void xssAttackTestOne() throws Exception {
		try {
			//open home page
			BadStoreTestUtil.injectHtmlElement(driver, "body", "TEST 2 ==> Login with cookie stealing");
			openHomepage(userHighlightColor, userTextColor);
			//hacker login
			BadStoreTestUtil.injectHtmlElement(driver, "body", "TEST 2 > STEP 1 ==> Hacker login to his/her account");
			userLogin(hackerEmail, hackerPassword, hackerHighlightColor, hackerTextColor);
			//hacker post comment with xss script
			BadStoreTestUtil.injectHtmlElement(driver, "body", "TEST 2 > STEP 2 ==> Hacker inject xss with cookie stealing code");
			String xssComment = "<h3 id=\"stolen_cookie\" style=\"color:red\"><script>document.write(document.cookie)</script></h3>";
			userPostComment(hackerEmail, hackerEmail, xssComment, hackerHighlightColor, hackerTextColor);
			//to check whether WAF catch attack or not
			if(driver.findElement(By.linkText("Home")) == null){
				//WAF catch attack
				BadStoreTestUtil.injectHtmlElementAttackBlock(driver, "div", "Attack Failed");
				throw new Exception();
			}else{
				//WAF didn't catch attack
			}
			//user login
			BadStoreTestUtil.injectHtmlElement(driver, "body", "TEST 2 > STEP 3 ==> User login to his/her account");
			userLogin(userEmail, userPassword, userHighlightColor, userTextColor);
			//user post comment
			BadStoreTestUtil.injectHtmlElement(driver, "body", "TEST 2 > STEP 4 ==> User post comment");
			String comment = "Great Product !!!!";
			userPostComment(userEmail, userEmail, comment, userHighlightColor, userTextColor);
			//hacker somehow managed to send user cookie to himself either by hosting service, email etc...
			BadStoreTestUtil.injectHtmlElement(driver, "body", "TEST 2 > STEP 5 ==> After visiting comments page, user cookie gets stolen");
			String stolen_cookie = BadStoreTestUtil.getElementValue(driver, "stolen_cookie");
			System.out.println("Stolen Cookie "+stolen_cookie);
			stolen_cookie = stolen_cookie.substring(stolen_cookie.indexOf("SSOid=")+6);
			System.out.println("Stolen Cookie SSOid="+stolen_cookie);
			//logout
			BadStoreTestUtil.injectHtmlElement(driver, "body", "TEST 2 > STEP 6 ==> Meanwhile, Hacker gets user cookie");
			userLogout(userHighlightColor, userTextColor);
			//manipulate cookie by hacker
			BadStoreTestUtil.injectHtmlElement(driver, "body", "TEST 2 > STEP 7 ==> Hacker creates fake cookie same as user cookie and  Hacker visits website and hacker gets user account access");
			addManipulatedCookie("SSOid",stolen_cookie);
			//open home page
			openHomepage(hackerHighlightColor, hackerTextColor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			while(TestClass1.trackTest[2]==(byte)0){
				//wait here for another thread
				Thread.sleep(threadParallelWait);
			}
		}
		
	}
	
	private void userLogin(String email, String password, String highlightColor, String textColor) throws Exception{
		BadStoreTestUtil.highlightElementByLinkText(driver, "Login / Register",highlightColor,textColor);
		selenium.click("link=Login / Register");
		selenium.waitForPageToLoad(waitForPageLoading);
		Thread.sleep(threadTimeForSleeping);
		BadStoreTestUtil.highlightElementByName(driver, "email",highlightColor,textColor);
		selenium.type("name=email", email);
		Thread.sleep(threadTimeForSleeping);
		BadStoreTestUtil.highlightElementByName(driver, "passwd",highlightColor,textColor);
		selenium.type("name=passwd", password);
		Thread.sleep(threadTimeForSleeping);
		BadStoreTestUtil.highlightElementByName(driver, "Login",highlightColor,textColor);
		selenium.click("name=Login");
		Thread.sleep(threadTimeForSleeping);
		selenium.waitForPageToLoad(waitForPageLoading);
	}
	
	private void userPostComment(String name, String email, String comments, String highlightColor, String textColor)throws Exception{
		BadStoreTestUtil.highlightElementByLinkText(driver, "Sign Our Guestbook",highlightColor,textColor);
		selenium.click("link=Sign Our Guestbook");
		selenium.waitForPageToLoad(waitForPageLoading);
		Thread.sleep(threadTimeForSleeping);
		BadStoreTestUtil.highlightElementByName(driver, "name",highlightColor,textColor);
		selenium.type("name=name", name);
		Thread.sleep(threadTimeForSleeping);
		BadStoreTestUtil.highlightElementByName(driver, "email",highlightColor,textColor);
		selenium.type("name=email", email);
		Thread.sleep(threadTimeForSleeping);
		BadStoreTestUtil.highlightElementByName(driver, "comments",highlightColor,textColor);
		selenium.type("name=comments", comments);
		Thread.sleep(threadTimeForSleeping);
		BadStoreTestUtil.highlightElementByXPath(driver, "(//input[@type='submit'])",highlightColor,textColor);
		selenium.click("css=input[type=\"submit\"]");
		selenium.waitForPageToLoad(waitForPageLoading);
		Thread.sleep(threadTimeForSleeping);
	}
	
	private void userSignup(String fullname, String email, String password, String highlightColor, String textColor)throws Exception{
		BadStoreTestUtil.highlightElementByLinkText(driver, "Login / Register",highlightColor,textColor);
		selenium.click("link=Login / Register");
		BadStoreTestUtil.highlightElementByName(driver, "fullname",highlightColor,textColor);
		selenium.type("name=fullname", fullname);
		Thread.sleep(threadTimeForSleeping);
		BadStoreTestUtil.highlightElementByXPath(driver, "(//input[@name='email'])[2]",highlightColor,textColor);
		selenium.type("css=p > input[name=\"email\"]", email);
		Thread.sleep(threadTimeForSleeping);
		BadStoreTestUtil.highlightElementByXPath(driver, "(//input[@name='passwd'])[2]",highlightColor,textColor);
		selenium.type("xpath=(//input[@name='passwd'])[2]", password);
		Thread.sleep(threadTimeForSleeping);
		BadStoreTestUtil.highlightElementByName(driver, "Register",highlightColor,textColor);
		selenium.click("name=Register");
		Thread.sleep(threadTimeForSleeping);
		selenium.waitForPageToLoad(waitForPageLoading);
	}
	
	private void userLogout(String highlightColor, String textColor) throws Exception{
		BadStoreTestUtil.highlightElementByName(driver, "bsheader",highlightColor,textColor);
		selenium.deleteAllVisibleCookies();
		Thread.sleep(threadTimeForSleeping);
		openHomepage(highlightColor, textColor);
		Thread.sleep(threadTimeForSleeping);
		BadStoreTestUtil.highlightElementByName(driver, "bsheader",highlightColor,textColor);
		Thread.sleep(threadTimeForSleeping);
	}
	
	private void addManipulatedCookie(String cookieName, String cookieValue) throws Exception{
		Thread.sleep(threadTimeForSleeping);
		selenium.deleteAllVisibleCookies();
		Cookie compramised_Cookie= new Cookie(cookieName, cookieValue);
		driver.manage().addCookie(compramised_Cookie);
	}
	
	private void openHomepage(String highlightColor, String textColor) throws Exception{
		Thread.sleep(threadTimeForSleeping);
		BadStoreTestUtil.highlightElementByLinkText(driver, "Home",highlightColor,textColor);
		selenium.click("link=Home");
		selenium.waitForPageToLoad(waitForPageLoading);
		Thread.sleep(threadTimeForSleeping);
		BadStoreTestUtil.highlightElementByName(driver, "bsheader",highlightColor,textColor);
		Thread.sleep(threadTimeForSleeping);
	}
	
	private void mockError()throws Exception{
		selenium.click("link=mockerror");
	}
	
	@AfterSuite
	public void afterSuite() throws Exception{
		//enable mouse
		BadStoreTestUtil.enableInputDevices();
		selenium.close();
	}
}
