package popat;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;



public class Tests {
	
	WebDriver driver;
	String nodeUrl;

	
	@BeforeClass
	public void setup() throws MalformedURLException
	{	nodeUrl="http://192.168.0.151:24589/wd/hub";
		DesiredCapabilities cap =DesiredCapabilities.chrome();
		cap.setBrowserName("chrome");
		cap.setPlatform(Platform.WINDOWS);
		
		driver = new RemoteWebDriver(new URL(nodeUrl), cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://gmail.com/");
		String currenTitle = driver.getTitle();
		String expectedTitle = "Gmail";
		Assert.assertEquals(currenTitle, expectedTitle);
	
	}
	
	@Test(priority=1, description="Check sending message")
	@Description("Check sending messag.......")
	@Epic("EPIC001")
	@Feature("Feature1: Send email")
	@Story("Story: Email send")
	@Step("Verification of sending Email")
	@Severity(SeverityLevel.BLOCKER)
	public void clickEmail()
	{
		
		GmailPageObjects gp=PageFactory.initElements(driver, GmailPageObjects.class);
		gp.enterEmail("testgmln@gmail.com");//Replace with your email id
		gp.enterPassword("smbtestgm");//Replace with your password
		gp.sendEmail("Елена Арсланова","testgmln@gmail.com");//Replace with email subject you want to click

	}
	
	@AfterClass
	public void tesrDown() 
	{			
	    driver.quit();												
	}
}
