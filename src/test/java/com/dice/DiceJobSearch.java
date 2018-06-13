package com.dice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {
	public static void main(String[] args) {
		
		//set up chrome driver path
		WebDriverManager.chromedriver().setup();
		
		//invoke selenium webdriver
		WebDriver driver = new ChromeDriver();
		
		//fullscreeen
		driver.manage().window().maximize();
		
		//set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Step1: Launch browser and navigate to https://dice.com
		//Expected:dice home page should be displayed.
		
		String url ="https://dice.com";
		driver.get(url);
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com";
		
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Step Pass. Dice Home Page successfully loaded");
		}else {
			System.out.println("Step Fail. Dice Home Page did not load successfully");
			throw new RuntimeException("Step Fail. Dice Home Page did not load successfully");
		}
		
		String keyword ="javaScript developer";
		driver.findElement(By.name("q")).clear();
		driver.findElement(By.name("q")).sendKeys(keyword);
			
		String location ="22102";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.findElement(By.id("findTechJobs")).click();
		
		String count=driver.findElement(By.id("posiCountId")).getText();
        System.out.println(count);
        
        //ensure count is more than 0
        int countResult=Integer.parseInt(count.replace(",",""));
        if(countResult>0) {
        	System.out.println("Keyword : "+keyword+ " search returned "+countResult+" results in "+location);
        }else {
        	System.out.println("Step Fail : Keyword : "+keyword+ " search returned "+countResult+" results in "+location);
        }
        
        driver.close();
        System.out.println("Test Completed - "+ LocalDateTime.now());
        
		
		
		
	}

}
