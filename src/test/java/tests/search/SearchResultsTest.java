package tests.search;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchSessionException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import readers.JSONReader;
import tests.base.BaseTest;
import pages.*;
import org.testng.annotations.Test;
import utils.reports.AllureListener;

import java.io.IOException;

import static org.testng.Assert.*;

//this class extends from base test class and the test scenario is to test if
//the number of Search Results in page 2 == page 3
@Listeners({AllureListener.class})
@Epic("Automation Tests")
@Feature("Search Tests")
public class SearchResultsTest extends BaseTest{

    @DataProvider
    public Object[][] jsonData(){
        return JSONReader.JsonReader();
    }
    @Test(dataProvider = "jsonData", description = "Check Results number in both pages should be the same")
    @Description("Test Description: Check if the results number in both pages is same")
    public void checkSearchResultsNumbersInBothPagesTest(String searchData, String fPage, String sPage) {
        try{
            this.searchData = searchData;
            GoogleResultPage ResultPage = googleHomePage.makeSearchByName(searchData);
            ResultPage.waiting();
            ResultPage.scrollDownTilTheEndOfThePage();
            ResultPage.selectPageAfterSearch(fPage);
            int firstPageResults = ResultPage.getNumberOfResultsByXpath();
            ResultPage.waiting();
            ResultPage.scrollDownTilTheEndOfThePage();
            ResultPage.selectPageAfterSearch(sPage);
            int secondPageResults = ResultPage.getNumberOfResultsByXpath();
            assertSame(firstPageResults,secondPageResults, "The Search Results number in not the same in "+
                    fPage+", and "+sPage);
            System.out.println(firstPageResults+", "+secondPageResults);
        } catch (InvalidSelectorException e) {
            exceptions.ExceptionsMessages.InvalidSelectorMsg(e);
        } catch (NoSuchElementException e){
            exceptions.ExceptionsMessages.NoSuchElementMsg(e);
        } catch (ElementNotVisibleException e){
            exceptions.ExceptionsMessages.ElementNotVisibleMsg(e);
        } catch (NoSuchSessionException e){
            exceptions.ExceptionsMessages.NoSuchSessionMsg(e);
        } catch (NullPointerException e){
            exceptions.ExceptionsMessages.NullPointerMsg(e);
        } catch (IOException e) {
            exceptions.ExceptionsMessages.IOMsg(e);
        }
    }
}
