package OnlineTestExam;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchSuggestion {

	public static WebDriver driver;
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "F:\\NARESH WORKSPACE\\WorkspaceSelenium\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Thread.sleep(1000);
		driver.get("https://en.wikipedia.org/wiki/Selenium");
		driver.findElement(By.id("searchInput")).sendKeys("pluto");
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='suggestions']//div[@class='suggestions-results']//a/descendant::div[@class='suggestions-result']"));
		System.out.println("Total list size is : "+list.size());
		
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).getText());
			if(list.get(i).getText().contains("Plutonium")) {
				list.get(i).click();
				break;
				
			}
		}
		
		driver.quit();

	}

}
