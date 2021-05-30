package framework;

import org.openqa.selenium.By;

public abstract class BasePage {
    protected static Browser browser = Browser.getInstance();
    protected By pageLocator;

    public BasePage(final By locator) {
        init(locator);
        assertIsOpen();
    }

    private void init(final By locator) {
        pageLocator = locator;
    }

    public void assertIsOpen() {
        assert browser.getDriver().findElement(pageLocator).isDisplayed();
    }

    public String getPageTitle() {
        return browser.getDriver().getTitle();
    }
}
