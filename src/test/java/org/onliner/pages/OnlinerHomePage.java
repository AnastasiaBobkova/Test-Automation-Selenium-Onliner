package org.onliner.pages;

import org.openqa.selenium.By;

public class OnlinerHomePage extends OnlinerBasePage {
    private static String pageLocator = "onliner_logo";

    public OnlinerHomePage() {
        super(By.className(pageLocator));
    }
}
