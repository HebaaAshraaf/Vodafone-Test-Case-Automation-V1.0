package utils.reports;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.base.BaseTest;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class AllureListener extends BaseTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        if( driver instanceof WebDriver){
            System.out.println("Screenshot captured for test case: "+ result.getName());
            saveScreenshot(driver);
        }
        saveTextLog(result.getName()+" failed and screenshot taken!");
        utils.takescreenshot.TakeScreenshot.takeScreenShotAfterFailure(driver,result.getName(),browserName,searchData);
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        //Set Allure Test Environment
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("User Name", System.getProperty("user.name"))
                        .put("Platform", System.getProperty("os.name")+" "+System.getProperty("os.arch"))
                        .build(),"testoutput/reports/Results/");
    }

    @Override
    public void onFinish(ITestContext context) {

    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot (WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog (String message){
        return message;
    }
}
