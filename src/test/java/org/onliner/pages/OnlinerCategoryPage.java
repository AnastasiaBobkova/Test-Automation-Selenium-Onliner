package org.onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class OnlinerCategoryPage extends OnlinerBasePage {
    private static String pageLocator = "//h1[@class='catalog-navigation__title'][contains(text(),'%s')]";

    private final String catalogNavigationItemLocator =
            "//span[@class='catalog-navigation-classifier__item-title'][contains(.,'%s')]";
    private final String catalogAsideListItemLocator =
            "//div[@class='catalog-navigation-list__aside-item'][contains(.,'%s')]";
    private final String catalogAsideListDropdownItemLocator =
            "//a[@class='catalog-navigation-list__dropdown-item'][contains(.,'%s')]";

    private WebElement catalogNavigationItem;
    private WebElement catalogAsideListItem;
    private WebElement catalogAsideListDropdownItem;

    public OnlinerCategoryPage(String pageLocatorValue) {
        super(By.xpath(String.format(pageLocator, pageLocatorValue)));
    }

    public OnlinerCategoryPage selectCatalogNavigationItem(String selectedCatalogNavigationItem) {
        catalogNavigationItem = browser.getDriver().findElement(By.xpath(String.format(catalogNavigationItemLocator,selectedCatalogNavigationItem)));
        catalogNavigationItem.click();
        return this;
    }

    public OnlinerProductsPage moveToAndSelectCatalogAsideListDropdownItem(String selectedCatalogAsideListItem, String selectedCatalogAsideListDropdownItem) {
        catalogAsideListItem = browser.getDriver().findElement(By.xpath(String.format(catalogAsideListItemLocator,selectedCatalogAsideListItem)));
        catalogAsideListDropdownItem = browser.getDriver().findElement(By.xpath(String.format(catalogAsideListDropdownItemLocator,selectedCatalogAsideListDropdownItem)));
        Actions action = new Actions(browser.getDriver());
        action.moveToElement(catalogAsideListItem).click().moveToElement(catalogAsideListDropdownItem).click().perform();
        return new OnlinerProductsPage(selectedCatalogAsideListDropdownItem);
    }
}
