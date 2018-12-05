package pages;

import utils.WebDriverSingleton;

public class PicturesFolderCloudPage{

    public String getTitle() {
        return WebDriverSingleton.getWebDriverInstance().getTitle();
    }

}