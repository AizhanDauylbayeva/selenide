package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CloudPage extends AbstractPage {
    private Actions action = new Actions(driver);

    @FindBy(xpath = "//div[@data-id='/Pictures']/*[@class='b-thumb__content']")
    private WebElement picturesFolder;

    @FindBy(xpath = "//div[@class='b-panel__close__icon']")
    private WebElement closepanel;

    @FindBy(xpath = "//div[@data-group='create']")
    private WebElement create;

    @FindBy(xpath = "//span[contains(string(), 'Папку')]")
    private WebElement createFolder;

    @FindBy(xpath = "//div[@class='b-filename__text']")
    private List<WebElement> fileNameList;

    @FindBy(xpath = "//div[@data-id='/Новая папка']//div[@class='b-checkbox__box']")
    private WebElement newFolderCheckbox;

    @FindBy(xpath = "//span[@class='b-toolbar__btn__text b-toolbar__btn__text_pad' and contains(string(), 'Удалить')]")
    private WebElement remove;

    @FindBy(xpath = "//button[@data-name='remove']")
    private WebElement removeButton;

    @FindBy(xpath = "//div[@class='layer__footer']/button[@data-name='close']")
    private WebElement okClose;

    public void closePanel() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", closepanel);
    }

    private List<WebElement> getFileNameList() {
        return fileNameList;
    }

    public CloudPage moveMouseToPicturesFolder() {
        waitForElementEnabled(picturesFolder);
        action.moveToElement(picturesFolder).perform();
        return this;
    }

    public PicturesFolderCloudPage doubleClick() {
        action.doubleClick().perform();
        return new PicturesFolderCloudPage();
    }

    public void createFolder() {
        action
                .click(create)
                .click(createFolder)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
    }

    public void removeTheNewFolder() {
        action
                .click(newFolderCheckbox)
                .click(remove)
                .click(removeButton)
                .click(okClose)
                .build()
                .perform();
    }

    public boolean isFolderExist() {
        List<WebElement> list = getFileNameList();
        boolean isExist = false;
        for (WebElement filename : list) {
            if (isExist = filename.getText().equals("Новая папка")) {
                highlightElement(filename);
                break;
            }
        }
        return isExist;
    }
}
