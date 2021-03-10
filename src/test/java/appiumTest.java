import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
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
        driver = new AndroidDriver<MobileElement>(url,cap);
    }
    @Test(priority = 1)
    public static void openLinkedIn()  {
        MobileElement signIn = driver.findElement(By.id("com.linkedin.android:id/growth_prereg_fragment_login_button"));
        signIn.click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        MobileElement email = driver.findElement(By.id("com.linkedin.android:id/growth_login_join_fragment_email_address"));
        email.clear();
        driver.hideKeyboard();
        email.sendKeys("hishamabositta@gmail.com");
        MobileElement password = driver.findElement(By.id("com.linkedin.android:id/growth_login_join_fragment_password"));
        password.clear();
        driver.hideKeyboard();
        password.sendKeys("3218458h");

        MobileElement continueBtn = driver.findElement(By.id("com.linkedin.android:id/growth_login_fragment_sign_in"));
        continueBtn.click();



    }

}
