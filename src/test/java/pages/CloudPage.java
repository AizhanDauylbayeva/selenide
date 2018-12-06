package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selectors.byXpath;


public class CloudPage {

    public void closePanel() {
        $(By.xpath("//div[@class='b-panel__close__icon']")).waitUntil(Condition.visible, 5000).click();
    }

    public PicturesFolderCloudPage openPicturesFolder() {
       $(byXpath("//div[@data-id='/Pictures']/*[@class='b-thumb__content']")).click();
        return new PicturesFolderCloudPage();
    }

    public void createFolder() {
        $(byXpath("//div[@data-group='create']")).click();
        $(byXpath("//span[contains(string(), 'Папку')]")).click();
        //Selenide.actions().sendKeys(Keys.ENTER);
        $(byXpath("//button[@data-name=\"add\"]")).click();
    }

    public void removeTheNewFolder() {
        $(byXpath("//div[@data-id='/Новая папка']//div[@class='b-checkbox__box']")).click();
        $(byXpath("//span[@class='b-toolbar__btn__text b-toolbar__btn__text_pad' and contains(string(), 'Удалить')]")).click();
        $(byXpath("//button[@data-name='remove']")).click();
        $(byXpath("//div[@class='layer__footer']/button[@data-name='close']")).click();
    }

    public boolean isFolderExist() {
        $$(byXpath("//div[@class='b-filename__text']")).find(byText("Новая папка")).shouldBe(visible);
        return true;
    }
}

//https://www.programcreek.com/java-api-examples/?class=com.codeborne.selenide.SelenideElement&method=findAll