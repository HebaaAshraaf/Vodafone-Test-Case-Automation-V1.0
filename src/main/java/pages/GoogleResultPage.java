package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GoogleResultPage extends page {

    //The name of the locators and url data in properties file
    protected final String page2_xpath = "google.results.page2.xpath";
    protected final String page3_xpath = "google.results.page3.xpath";
    protected final String resultsXpath = "google.results.xpath";

    public GoogleResultPage(WebDriver driver) throws IOException {
        super(driver);
    }

    @Step("Select page number {0} in result pages")
    public void selectPageAfterSearch(String num) {
        if (num.equals("2")) {
            elements.locators.getElementByXpath(page2_xpath, this.driver, prop).click();
        } else if (num.equals("3")) {
            elements.locators.getElementByXpath(page3_xpath, this.driver, prop).click();
        } else {
            System.out.println("Please Enter Valid Page Number");
            driver.close();
        }
    }

    @Step("Calculate the number of results in the page")
    public int getNumberOfResultsByXpath() {
        return elements.locators.getElementsByXpath(resultsXpath, this.driver, prop).size();
    }

    @Step("Waiting for a while to load the page")
    public void waiting() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Step("Scroll down till the footer")
    public void scrollDownTilTheEndOfThePage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //This will scroll to the page ends
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
