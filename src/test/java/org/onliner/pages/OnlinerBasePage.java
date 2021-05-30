package org.onliner.pages;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OnlinerBasePage extends BasePage {
    private static String pageLocator = "onliner_logo";

    private static String navigationItemLocator = "//ul[@class='b-main-navigation']//span[text() = '%s']";
    private WebElement navigationItem;

    public OnlinerBasePage() {
        super(By.className(pageLocator));
    }

    public OnlinerBasePage(final By locator) {
        super(locator);
    }

    public OnlinerCategoryPage selectNavigationItem(String selectedNavigationItem) {
        navigationItem = browser.getDriver().findElement(By.xpath(String.format(navigationItemLocator, selectedNavigationItem)));
        navigationItem.click();
        return new OnlinerCategoryPage(selectedNavigationItem);
    }
}
