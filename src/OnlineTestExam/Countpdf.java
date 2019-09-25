package OnlineTestExam;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Countpdf {
	public static WebDriver driver;
	public static void main(String[] args) throws NullPointerException {
		
		System.setProperty("webdriver.chrome.driver", "F:\\NARESH WORKSPACE\\WorkspaceSelenium\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Thread.sleep(1000);
		driver.get("https://en.wikipedia.org/wiki/Selenium");
		//6.Count the number of pdf links in “References“
		//WebElement element = driver.findElement(By.xpath("//div[@class='reflist columns references-column-width']"));
		//element.getLocation();
		//int width = element.getSize().getWidth();
		//int height = element.getSize().getHeight();
		//int x = element.getLocation().getX();
		//int y = element.getLocation().getY();
		//String S3 = element.getAttribute("innerHTML");
		//System.out.println(S3);
		//String s2= driver.findElement(By.xpath("//div[@class='reflist columns references-column-width']")).getText();	
		//List<WebElement> list = driver.findElements(By.xpath("//div[@class='reflist columns references-column-width']/parent::div//ol[@class='references']//li[//a[@href='http://www-d0.fnal.gov/hardware/cal/lvps_info/engineering/elementmagn.pdf']]/following-sibling::li"));
		List<WebElement> list = driver.findElement(By.xpath("//div[@class='reflist columns references-column-width']")).findElements(By.xpath("//a[contains(@href,'.pdf')]"));
		list.size();
		for(int i=0;i<list.size();i++) {
			String pdf = list.get(i).getText();
			String elementhref = list.get(i).getAttribute("href");
			System.out.println((i+1)+". pdf name is :"+pdf);
			System.out.println((i+1)+". pdf link is :"+elementhref);
			System.out.println("*********************************************");
			
		}
		System.out.println(list.size());
				driver.quit();
	}

}
