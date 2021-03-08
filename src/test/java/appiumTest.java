import io.appium.java_client.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class appiumTest {
    static AppiumDriver<MobileElement> driver;
    @BeforeClass
    public void setup()  throws MalformedURLException{
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName","android");
        cap.setCapability("udid","5367c4d8");
        cap.setCapability("platformName","Android");
        cap.setCapability("platformVersion","9");
        cap.setCapability("appPackage","com.linkedin.android");
        cap.setCapability("appActivity","com.linkedin.android.authenticator.LaunchActivity");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AppiumDriver(url,cap);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);;
    }
    @Test(priority = 1)
    public static void openLinkedIn()  {
        MobileElement signIn = driver.findElement(By.xpath("//android.widget.Button[@text='Sign in']"));
        signIn.click();



    }

}
