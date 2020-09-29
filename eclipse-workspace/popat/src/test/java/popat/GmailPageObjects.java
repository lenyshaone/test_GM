package popat;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailPageObjects {
	private WebDriver driver;
	@FindBy(how=How.XPATH, xpath="//input[@id='identifierId']")
	WebElement emailField;
	
	@FindBy(how=How.XPATH, xpath="//*[@id='password']/div[1]/div/div[1]/input")
	WebElement passwordField;
	
	
	@FindBy(how=How.XPATH, xpath="//div[@class='yW']")	 
	List<WebElement> emailThreads;
	
	@FindBy(how=How.XPATH, xpath="//span[@class='gb_bb gbii']")
	WebElement profileLogo;
	
	@FindBy(how=How.XPATH, xpath="//div[@class='T-I T-I-KE L3']")
	WebElement sendLettrs;
	
	@FindBy(how=How.XPATH, xpath="//textarea[@name='to']")
	WebElement toEmail;
	
	@FindBy(how=How.XPATH, xpath="//input[@name='subjectbox']")
	WebElement subjectboxEmail;
	
	@FindBy(how=How.XPATH, xpath="//div[@class='Am Al editable LW-avf tS-tW']")
	WebElement textEmail;
	
	@FindBy(how=How.XPATH, xpath="//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")
	WebElement lettersEmail;
	
	
	
	
	public GmailPageObjects(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void enterEmail(String emailID)
	{
		waitForVisible(driver, emailField);
		Actions actions=new Actions(driver);
		actions.moveToElement(emailField);
		actions.click();
		actions.sendKeys(emailID + Keys.ENTER);
		actions.build().perform();
		System.out.println("Email entered");	
	}
	
	public void enterPassword(String password)
	{
		waitForVisible(driver, passwordField);
		Actions actions=new Actions(driver);
		actions.moveToElement(passwordField);
		actions.click();
		actions.sendKeys(password + Keys.ENTER);
		actions.build().perform();
		System.out.println("Password entered");
	}
	
	public void sendEmail(String surname, String emailID)
	{
		
		int ln=0;
		for (int i = 0; i < emailThreads.size(); i++)
		{
			if (emailThreads.get(i).getText().contains(surname)) 
			{
				ln=ln+1;
			}
		}
		System.out.println("letters:  "+String.valueOf(ln));
		sendLettrs.click();		
		toEmail.sendKeys(emailID+ Keys.ENTER);
		subjectboxEmail.sendKeys("Тестовое задание. <Арсланова>"+ Keys.ENTER);
		textEmail.sendKeys(String.valueOf(ln)+" letters from: "+ surname);
		lettersEmail.click();
		waitForVisible(driver, sendLettrs);
		
}
		
	
		
	
	public void waitForVisible(WebDriver driver, WebElement element) 
	{
		try
		{
			Thread.sleep(1000);
			System.out.println("Waiting for element visibility");
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
