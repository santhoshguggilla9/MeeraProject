package amazontest;


	import org.openqa.selenium.By;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterSuite;
	import org.testng.annotations.BeforeSuite;
	import org.testng.annotations.Test;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class SearchForProduct {
		WebDriver driver;
		@BeforeSuite
		public void setup()
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		@Test(priority=1)
		public void openApp() throws Exception
		{
			driver.get("https://www.amazon.in/?&ext_vrnc=hi&tag=googhydrabk1-21&ref=pd_sl_7hz2t19t5c_e&adgrpid=58355126069&hvpone=&hvptwo=&hvadid=610644601173&hvpos=&hvnetw=g&hvrand=201308671182816415&hvqmt=e&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9302561&hvtargid=kwd-10573980&hydadcr=14453_2316415");
		    Thread.sleep(10000);
		}
		@Test(priority=2)
		public void searchProduct()
		{
			WebElement searchBox=driver.findElement(By.id("twotabsearchtextbox"));
			searchBox.sendKeys("Puma sneakers for men",Keys.ENTER);
			WebElement puma=driver.findElement(By.xpath("(//span[@class=\"a-size-base-plus a-color-base\"])[1]"));
			String text=puma.getText();
			Assert.assertTrue(text.equals("Puma"));
		}
		@AfterSuite
		public void teardown()
		{
			driver.close();
		}


}
