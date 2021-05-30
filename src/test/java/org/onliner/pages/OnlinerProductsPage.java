package org.onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class OnlinerProductsPage extends OnlinerBasePage {
    private static String pageLocator = "//h1[@class='schema-header__title'][contains(text(),'%s')]";

    private final String checkboxFilterLocator =
            "//div[@class='schema-filter__label'][contains(.,'%s')]/following-sibling::div//span[contains(text(),'%s')]";
    private final String inputFilterLocator =
            "//div[@class='schema-filter__label'][contains(.,'%s')]/following-sibling::div//input[@placeholder='%s']";
    private final String filterButtonLocator =
            "//div[@class='schema-filter-button__inner-container']";
    private final String productPriceLocator =
            "//div[@class='schema-product__price']/a/span";
    private final String productTitleLocator =
            "//div[@class='schema-product__title']";
    private final String productDescriptionLocator =
            "//div[@class='schema-product__description']";

    private WebElement filter;
    public OnlinerProductsPage(String pageLocatorValue) {
        super(By.xpath(String.format(pageLocator, pageLocatorValue)));
    }


    public OnlinerProductsPage checkboxFilter (String filterType, String filterValue) {
        filter = browser.getDriver().findElement(By.xpath(String.format(checkboxFilterLocator, filterType, filterValue)));
        JavascriptExecutor js = (JavascriptExecutor) browser.getDriver();
        js.executeScript("arguments[0].scrollIntoView();",filter);
        WebDriverWait wait = new WebDriverWait(browser.getDriver(), Long.parseLong(browser.getTimeoutForCondition()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(filterButtonLocator)));
        filter.click();
        return this;
    }

    public OnlinerProductsPage inputFilter (String filterType, String filterValue, String filterInputValue) {
        filter = browser.getDriver().findElement(By.xpath(String.format(inputFilterLocator, filterType, filterValue)));
        JavascriptExecutor js = (JavascriptExecutor) browser.getDriver();
        js.executeScript("arguments[0].scrollIntoView();",filter);
        WebDriverWait wait = new WebDriverWait(browser.getDriver(), Long.parseLong(browser.getTimeoutForCondition()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(filterButtonLocator)));
        filter.sendKeys(filterInputValue);
        return this;
    }

    public boolean isEachProductTitleContainsFilterValue(String filterValue) {
        List<WebElement> productsTitles = browser.getDriver().findElements(By.xpath(productTitleLocator));
        for (WebElement element : productsTitles) {
            if(!element.getText().contains(filterValue)){
                return false;
            };
        }
        return true;
    }

    public boolean isEachProductPriceMatchesFilterValue(String filterValue) {
        List<WebElement> productsPrices = browser.getDriver().findElements(By.xpath(productPriceLocator));
        for (WebElement element : productsPrices) {
            Double price = Double.parseDouble(element.getText().replaceAll(" р.","").replace(',','.'));
            if(!(price <= Double.parseDouble(filterValue))){
                return false;
            };
        }
        return true;
    }

    public boolean isEachProductDescriptionContainsFilterValue(String filterValue){
        List<WebElement> productsDescriptions = browser.getDriver().findElements(By.xpath(productDescriptionLocator));
        for (WebElement element : productsDescriptions) {
            if(!element.getText().contains(filterValue)){
                return false;
            };
        }
        return true;
    }

    public boolean isEachProductDescriptionContainsFilterValueInRange(String filterValueStartRange, String filterValueEndRange){
        List<WebElement> productsDescriptions = browser.getDriver().findElements(By.xpath(productDescriptionLocator));
        for (WebElement element : productsDescriptions) {
            Double diagonal = Double.parseDouble(element.getText().substring(0,2));
            if(!(diagonal >= Double.parseDouble(filterValueStartRange) && diagonal <= Double.parseDouble(filterValueEndRange))){
                return false;
            };
        }
        return true;
    }
}

