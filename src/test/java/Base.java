import entity.User;
import org.testng.annotations.BeforeClass;
import pages.CloudPage;
import pages.HomePage;
import pages.InboxPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Configuration.*;

public class Base {
    protected InboxPage inbox;
    protected CloudPage cloudPage;
    protected User user = new User("new_account_2018@bk.ru", "password2018");

    @BeforeClass
    public void openInbox() {
        timeout = 10000;
        baseUrl = "http://gmail.com";
        startMaximized = false;
        browser = "chrome";
        browserPosition = "890x10";
        browserSize = "780x950";

        open("/");
        inbox = new HomePage().openMailRu().login(user.getUsername(), user.getPass());
    }

}
