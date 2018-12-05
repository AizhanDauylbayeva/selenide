import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.PicturesFolderCloudPage;
import utils.WebDriverSingleton;

public class CloudTest extends Base{

    @Test(description = "Login test")
    public void loginTest() {
        inbox.assertUserSignedIn();
    }

    @Test(dependsOnMethods = "loginTest")
    public void createFolder() {
        cloudPage = inbox.openCloudPage();
        cloudPage.closePanel();
        cloudPage.createFolder();
        WebDriverSingleton.getWebDriverInstance().navigate().refresh();
        Assert.assertTrue(cloudPage.isFolderExist(), "The folder doesn't created");
    }

    @Test(dependsOnMethods = "createFolder")
    public void removeTheFolder() {
        cloudPage.removeTheNewFolder();
        Assert.assertFalse(cloudPage.isFolderExist(), "The folder doesn't removed");
    }

    @Test(dependsOnMethods = "removeTheFolder")
    public void doubleClickTest() {
        PicturesFolderCloudPage picturesFolderPage = cloudPage.moveMouseToPicturesFolder().doubleClick();
        Assert.assertEquals(picturesFolderPage.getTitle(), "Pictures / Облако Mail.Ru", "The title is wrong");
    }

    @AfterClass(description = "closePanel browser")
    public void kill() {
        WebDriverSingleton.kill();
    }
}
