package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class GoogleHomePage extends page {

    //The name of the locators and url data in properties file
    private final String searchBarName = "google.home.searchBar.name";
    //The path of google url data in properties file
    private final String googleURL = "google.home.url";

    public GoogleHomePage(WebDriver driver) throws IOException {
        super(driver);
        driver.get(readers.PropReader.read(googleURL,prop));
    }

    @Step("Search about: {0}")
    public GoogleResultPage makeSearchByName(String data) throws IOException {
        elements.locators.getElementByName(searchBarName, driver, prop).sendKeys(data, Keys.ENTER);
        return new GoogleResultPage(driver);
    }
}
