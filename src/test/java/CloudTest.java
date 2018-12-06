import org.testng.Assert;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import pages.PicturesFolderCloudPage;

public class CloudTest extends Base{

/*
    @Test(description = "Login test")
    public void loginTest() {
        inbox.assertUserSignedIn();
    }
*/

    @Test
    public void createFolder() {
        cloudPage = inbox.openCloudPage();
        cloudPage.closePanel();
        cloudPage.createFolder();
        Selenide.refresh();
        Assert.assertTrue(cloudPage.isFolderExist(), "The folder doesn't created");
    }

    @Test(dependsOnMethods = "createFolder")
    public void removeTheFolder() {
        cloudPage.removeTheNewFolder();
        Assert.assertFalse(cloudPage.isFolderExist(), "The folder doesn't removed");
    }

    @Test(dependsOnMethods = "removeTheFolder")
    public void doubleClickTest() {
        PicturesFolderCloudPage picturesFolderPage = cloudPage.openPicturesFolder();
        Assert.assertEquals(picturesFolderPage.getTitle(), "Pictures / Облако Mail.Ru", "The title is wrong");
    }

}
