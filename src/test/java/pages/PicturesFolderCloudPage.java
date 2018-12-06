package pages;

import com.codeborne.selenide.Selenide;

public class PicturesFolderCloudPage{

    public String getTitle() {
        return Selenide.title();
    }

}