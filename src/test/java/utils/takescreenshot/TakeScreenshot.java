package utils.takescreenshot;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public interface TakeScreenshot {
    static void takeScreenShotAfterFailure(WebDriver driver, String resultname, String browserName
            , String searchData) {
        //as a file
        TakesScreenshot camera = ((TakesScreenshot) driver);
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        File Dest = new File("testoutput/screenshots/" + browserName.toUpperCase() + "_" +
                searchData + "_" + resultname + java.time.LocalDate.now() + ".jpeg");
        try {
            Files.copy(screenshot, Dest);
        } catch (IOException e) {
            exceptions.ExceptionsMessages.IOMsg(e);
        }
    }
}
