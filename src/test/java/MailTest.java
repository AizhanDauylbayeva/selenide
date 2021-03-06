import entity.Mail;
import entity.User;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.codeborne.selenide.Selenide;
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
        inbox = new HomePage().login(user.getUsername(), user.getPass());
        inbox.assertUserSignedIn();
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
        Selenide.refresh();
        Assert.assertTrue(draftsFolderPage.isSavedMailExist(mail), "The draft content isn't the same as in mail");
    }

    @Test(dependsOnMethods = "testContent")
    public void testSecondMail() {
        Assert.assertTrue(draftsFolderPage.isSavedMailExist(secondMail), "The second mail isn't found");
    }

    @Test(dependsOnMethods = "testSecondMail")
    public void sendMailTest() {
        draftsFolderPage.openMail(mail);
        Selenide.refresh();
        newMailPage.sendMail();
        draftsFolderPage.openDraftsFolder();
        Selenide.refresh();
        Assert.assertFalse(draftsFolderPage.isSavedMailExist(mail), "The mail didn't disappear from 'Drafts' folder");
    }

    @Test(dependsOnMethods = "sendMailTest")
    public void sentFolderTest() {
        draftsFolderPage.openSentFolder();
        Selenide.refresh();
        Assert.assertTrue(sentPage.isSentMailExist(mail), "The sent letter isn't in 'Sent' folder");
    }

}
