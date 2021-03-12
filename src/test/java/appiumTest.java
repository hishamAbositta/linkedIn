import com.google.common.collect.ImmutableMap;
import io.appium.java_client.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
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
        driver = new AppiumDriver<>(url,cap);
    }
    @Test(priority = 1)
    public void linkedInPostTest()  {
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        MobileElement signIn = driver.findElement(By.id("com.linkedin.android:id/growth_prereg_fragment_login_button"));
        signIn.click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        MobileElement email = driver.findElement(By.id("com.linkedin.android:id/growth_login_join_fragment_email_address"));
        email.clear();
        driver.hideKeyboard();
        email.sendKeys("hishamabusitta0@gmail.com");
        MobileElement password = driver.findElement(By.id("com.linkedin.android:id/growth_login_join_fragment_password"));
        password.clear();
        driver.hideKeyboard();
        password.sendKeys("123789456");

        MobileElement continueBtn = driver.findElement(By.id("com.linkedin.android:id/growth_login_fragment_sign_in"));
        continueBtn.click();
        MobileElement searchBtn = driver.findElement(By.id("com.linkedin.android:id/search_bar"));

        searchBtn.click();
        MobileElement searchBtn2 = driver.findElement(By.id("com.linkedin.android:id/search_bar_edit_text"));
        searchBtn2.clear();
        driver.hideKeyboard();
        searchBtn2.sendKeys("hossam.youssof@samesystem.com");
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "Go"));
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        getPost();

        //scroll
        scrollPage();
        MobileElement likeCount = driver.findElement(By.id("com.linkedin.android:id/feed_conversations_reactions_count"));
        String countBefore =likeCount.getText();
        int counter1 = Integer.valueOf(countBefore);

        //android.widget.ImageButton
        MobileElement likeBtn = driver.findElement(By.id("com.linkedin.android:id/feed_social_actions_like"));
        likeBtn.click();

        driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
        String countAfter =likeCount.getText();
        int counter2 = Integer.valueOf(countAfter);
        Assert.assertEquals(counter1+1,counter2 );
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
       // likeBtn.click();
        MobileElement reactionsList = driver.findElement(By.id("com.linkedin.android:id/reactions_rollup_items"));
        reactionsList.click();
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        likePageRefresh();
    }
    public void scrollPage()
    {
        try {
            driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"));
        } catch (InvalidSelectorException n) {
            // ignore
        }
    }
    public void likePageRefresh()
    {
        try {
            MobileElement viewGroup = driver.findElement(By.xpath("//*[@text='hisham test']"));
            Assert.assertNotNull(viewGroup);
            System.out.println(viewGroup);
        }catch (NoSuchElementException e){
            MobileElement backBtn = driver.findElement(By.className("android.widget.ImageButton"));
            backBtn.click();
            scrollPage();
            MobileElement reactionsList = driver.findElement(By.id("com.linkedin.android:id/reactions_rollup_items"));
            reactionsList.click();
            likePageRefresh();
        }
    }
    public void getPost(){
        try {
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            MobileElement clickOnPost = driver.findElement(By.id("com.linkedin.android:id/search_entity_result_content_summary"));
            clickOnPost.click();
        }catch (NoSuchElementException e){
        getPost();
        }
    }

}
