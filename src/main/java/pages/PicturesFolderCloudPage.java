package pages;

import utils.WebDriverSingleton;

public class PicturesFolderCloudPage extends AbstractPage {

    public String getTitle() {
        return WebDriverSingleton.getWebDriverInstance().getTitle();
    }

}