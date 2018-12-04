import entity.Mail;
import entity.User;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.WebDriverSingleton;
import pages.*;

public class MailTest {
    private InboxPage inbox;
    private Mail mail = new Mail("ayzhan7797@mail.ru", "test(module 4.2)", "Hello!");
    private Mail secondMail = new Mail("<Не указано>", "<Без темы>", "-- Name LastName");
    private User user = new User("new_account_2018", "password2018");
    private DraftsFolderPage draftsFolderPage;
    private CreateNewMailPage newMailPage;
    private SentFolderPage sentPage;

    @BeforeClass
    private void init() {
        inbox = new InboxPage();
        newMailPage = new CreateNewMailPage();
        draftsFolderPage = new DraftsFolderPage();
        sentPage = new SentFolderPage();
    }

    @Test(description = "Login test")
    public void loginTest() {
        inbox = new HomePage().open().fillUsername(user.getUsername()).fillPassword(user.getPass()).chooseDomain().signIn();
        Assert.assertTrue(inbox.isUserSignedIn(), "Authentication failed");
    }

    @Test(dependsOnMethods = "loginTest")
    public void saveNewMailTest() {
        inbox.openWriteNewMail();
        newMailPage.fillAddressee(mail.getAddressee());
        newMailPage.fillSubject(mail.getSubject());
        newMailPage.fillBody(mail.getBody());
        newMailPage.saveDraft();
        Assert.assertTrue(newMailPage.isMailSaved(), "The mail is not saved");
    }

    @Test(dependsOnMethods = "saveNewMailTest")
    public void testContent() {
        newMailPage.openDraftsFolder();
        WebDriverSingleton.getWebDriverInstance().navigate().refresh();
        Assert.assertTrue(draftsFolderPage.isSavedMailExist(mail), "The draft content isn't the same as in mail");
    }

    @Test(dependsOnMethods = "testContent")
    public void testSecondMail() {
        Assert.assertTrue(draftsFolderPage.isSavedMailExist(secondMail), "The second mail isn't found");
    }

    @Test(dependsOnMethods = "testSecondMail")
    public void sendMailTest() {
        draftsFolderPage.openMail(mail);
        WebDriverSingleton.getWebDriverInstance().navigate().refresh();
        newMailPage.sendMail();
        draftsFolderPage.openDraftsFolder();
        WebDriverSingleton.getWebDriverInstance().navigate().refresh();
        Assert.assertFalse(draftsFolderPage.isSavedMailExist(mail), "The mail didn't disappear from 'Drafts' folder");
    }

    @Test(dependsOnMethods = "sendMailTest")
    public void sentFolderTest() {
        draftsFolderPage.openSentFolder();
        WebDriverSingleton.getWebDriverInstance().navigate().refresh();
        Assert.assertTrue(sentPage.isSentMailExist(mail), "The sent letter isn't in 'Sent' folder");
    }

    @AfterClass(description = "closePanel browser")
    public void kill(){
        WebDriverSingleton.kill();
    }
}
