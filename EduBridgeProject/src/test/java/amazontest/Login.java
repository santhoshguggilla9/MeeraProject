package amazontest;

	import java.io.File;
	import java.io.IOException;

	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.By;
	import org.openqa.selenium.NoSuchElementException;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeSuite;
	import org.testng.annotations.Test;

	import io.github.bonigarcia.wdm.WebDriverManager;
	import net.sourceforge.tess4j.ITesseract;
	import net.sourceforge.tess4j.Tesseract;
	import net.sourceforge.tess4j.TesseractException;
	public class Login {
		WebDriver driver;
		
		@BeforeSuite
		public void setup()
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		@Test(priority=1)
		public void openApp()
		{
			driver.get("https://www.amazon.in/-/hi/ap/signin?openid.pape.max_auth_age=3600&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fspr%2Freturns%2Fgift&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=amzn_psr_desktop_in&openid.mode=checkid_setup&language=en_IN&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
		}
		@Test(priority=2)
		public void handleCaptcha() throws IOException, TesseractException, InterruptedException
		{
			try
			{
			WebElement imageElement=driver.findElement(By.xpath("//div[@class='a-row a-text-center']/child::img"));
		    File src=imageElement.getScreenshotAs(OutputType.FILE);
		    File dst=new File("C:\\Users\\lenovo\\eclipse-workspace\\EduBridgeProject\\CaptchaImages\\amazoncaptcha.png");
		    FileUtils.copyFile(src, dst);
		    Thread.sleep(3000);
		    ITesseract it=new Tesseract();
		    String captcha=it.doOCR(dst);
		    WebElement enterCaptcha=driver.findElement(By.id("captchacharacters"));
		    enterCaptcha.sendKeys(captcha);
		    System.out.println(captcha);
		    Thread.sleep(6000);
		    WebElement continueShopping=driver.findElement(By.xpath("//button[text()='Continue shopping']"));
		    continueShopping.click();
			}
			catch(NoSuchElementException e)
			{
				System.out.println("Captcha not required");
			}
		}
		@Test(priority=3)
		public void login()
		{
			WebElement username=driver.findElement(By.id("ap_email"));
			username.sendKeys("jagabattulaudaykumar@gmail.com");
			WebElement password=driver.findElement(By.id("ap_password"));
			password.sendKeys("$$$$4444$$$$amazon");
			WebElement signInBtn=driver.findElement(By.id("signInSubmit"));
			signInBtn.click();
			WebElement signIn=driver.findElement(By.id("nav-link-accountList-nav-line-1"));
			String text=signIn.getText();
	        Assert.assertFalse(text.equals("Hello, sign in"));
			
		}
		
		@AfterTest
		public void teardown()
		{
			driver.close();
		}
		


}
