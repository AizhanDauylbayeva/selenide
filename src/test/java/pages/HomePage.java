package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {

    @FindBy(how = How.ID, using ="mailbox:login")
    private SelenideElement username;

    @FindBy(id = "mailbox:password")
    private SelenideElement password;

    @FindBy(xpath = "//*[@id='mailbox:submit']/input")
    private SelenideElement signInButton;

    @FindBy(xpath = "//*[@id='mailbox:domain']/option[4]")
    private SelenideElement domain;

    public InboxPage login(String name, String pass) {
        username.val(name).pressEnter();
        password.val(pass).pressEnter();
        return new InboxPage();
    }
}
