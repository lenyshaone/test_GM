package popat;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;



public class Tests {
	
	WebDriver driver;
	int len;
	
	@BeforeClass
	public void setup()
	{		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		driver.get("https://gmail.com/");
		driver.manage().window().maximize();		
	}
	
	@Test(priority=1)
	public void logoPresens()
	{		
		WebElement username = driver.findElement(By.xpath("//input[@type='email']"));
		username.sendKeys("testgmln@gmail.com");		 		  
	    driver.findElement(By.id("identifierNext")).click();		  		  
	}
	
	@Test(priority=2)
	public void loginTest()
	{
		WebElement pwd = driver.findElement(By.xpath("//input[@name='password']"));		
		pwd.sendKeys("smbtestgm");		  
		driver.findElement(By.id("passwordNext")).click();
			
	}
	
	@Test(priority=3)
	public void registrationTest() throws InterruptedException
	{
		String name2find = "Елена Арсланова";			 
		len = driver.findElements(By.cssSelector("div.afn [name='"+name2find+"']")).size();				 
		driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']")).click();
		WebElement to = driver.findElement(By.xpath("//textarea[@name='to']"));			  
		to.sendKeys("testgmln@gmail.com");
	    WebElement sub = driver.findElement(By.xpath("//input[@name='subjectbox']"));
		sub.sendKeys("Тестовое задание. <Арсланова>");
		WebElement txt = driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf tS-tW']"));
		txt.sendKeys(String.valueOf(len)+" letters from: "+ name2find);
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();
		Thread.sleep(2000);					 			 						  
	}
		
	@AfterClass
	public void tesrDown() 
	{			
	    driver.quit();												
	}
}
