package org.onliner.tests;

import framework.Browser;
import org.onliner.pages.OnlinerHomePage;
import org.onliner.pages.OnlinerProductsPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestOnlinerFilter {
    private static Browser browser = Browser.getInstance();

    @BeforeTest
    public void setup(){
        browser.windowMaximize();
        browser.navigate(browser.configs.getProperty("URL"));
    }

    @Parameters({"filterValueProducer", "filterInputValueMaxPrice",
            "filterValueResolution","filterValueMinDiagonal",
            "filterValueMaxDiagonal"
    })
    @Test
    public void checkProductsPageFilters(String filterValueProducer,
                                         String filterInputValueMaxPrice,
                                         String filterValueResolution,
                                         String filterValueMinDiagonal,
                                         String filterValueMaxDiagonal){
        OnlinerHomePage homePage = new OnlinerHomePage();
        OnlinerProductsPage productsPage = homePage
                .selectNavigationItem("Каталог")
                .selectCatalogNavigationItem("Электроника")
                .moveToAndSelectCatalogAsideListDropdownItem("Телевидение", "Телевизор")
                .checkboxFilter("Производитель",filterValueProducer)
                .inputFilter("цена","до", filterInputValueMaxPrice)
                .checkboxFilter("Разрешение",filterValueResolution)
                .checkboxFilter("Диагональ",filterValueMinDiagonal)
                .checkboxFilter("Диагональ",filterValueMaxDiagonal);

        SoftAssert softAssert = new SoftAssert();
        String ERROR_MSG_TITLE_NOT_MATCH = "Not each product title contains selected producer: %s";
        String ERROR_MSG_PRICE_NOT_MATCH_RANGE = "Not each product price is within selected range: до %s";
        String ERROR_MSG_DESCRIPTION_NOT_MATCH = "Not each product description contains filtered value: %s";
        String ERROR_MSG_DESCRIPTION_NOT_MATCH_RANGE = "Not each product description contains value within selected range: %s %s";

        softAssert.assertTrue(productsPage.isEachProductTitleContainsFilterValue(filterValueProducer),
                String.format(ERROR_MSG_TITLE_NOT_MATCH,filterValueProducer));
        softAssert.assertTrue(productsPage.isEachProductPriceMatchesFilterValue(filterInputValueMaxPrice),
                String.format(ERROR_MSG_PRICE_NOT_MATCH_RANGE,filterInputValueMaxPrice));
        softAssert.assertTrue(productsPage.isEachProductDescriptionContainsFilterValue(filterValueResolution),
                String.format(ERROR_MSG_DESCRIPTION_NOT_MATCH,filterValueResolution));
        softAssert.assertTrue(productsPage.isEachProductDescriptionContainsFilterValueInRange(filterValueMinDiagonal,filterValueMaxDiagonal),
                String.format(ERROR_MSG_DESCRIPTION_NOT_MATCH_RANGE,filterValueMinDiagonal,filterValueMaxDiagonal));
        softAssert.assertAll();
    }

    @AfterTest
    public void tearDown(){
        browser.exit();
    }
}
