package pages;

import bonus.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(id = "mailbox:login")
    private WebElement username;

    @FindBy(id = "mailbox:password")
    private WebElement password;

    @FindBy(xpath = "//*[@id='mailbox:submit']/input")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@id='mailbox:domain']/option[4]")
    private WebElement domain;

    public HomePage open() {
        Logger.info("Going to URL: " + "https://mail.ru");
        driver.get("https://mail.ru");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public HomePage fillUsername(String login) {
        username.sendKeys(login);
        return this;
    }

    public HomePage fillPassword(String pass) {
        password.sendKeys(pass);
        return this;
    }

    public HomePage chooseDomain() {
        domain.click();
        return this;
    }

    public InboxPage signIn() {
        signInButton.click();
        return new InboxPage();
    }
}
