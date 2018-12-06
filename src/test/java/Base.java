import entity.User;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.CloudPage;
import pages.InboxPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class Base {
    protected InboxPage inbox;
    protected CloudPage cloudPage;
    protected User user = new User("new_account_2018@bk.ru", "password2018");

    @BeforeClass
    public void openInbox() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driverbinaries/chromedriver.exe");
        baseUrl = "http://mail.ru";
        startMaximized = true;
        browser = "chrome";

        open(baseUrl);
        inbox = login(user.getUsername(), user.getPass());
        waitUntilPagesIsLoaded();
    }

    private static InboxPage login(String name, String pass) {
        $(By.id("mailbox:login")).val(name).pressEnter();
        $(By.id("mailbox:password")).val(pass).pressEnter();
        return new InboxPage();
    }

    protected static void waitUntilPagesIsLoaded() {
        $(By.id("PH_user-email")).waitUntil(visible, 20000);
    }

    @AfterClass
    public static void logout() {
        closeWebDriver();
    }
}
