package framework;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public final class Browser {
    private static final String IMPLICITLY_WAIT = "implicitlyWait";
    private static final String DEFAULT_PAGE_LOAD_TIMEOUT = "defaultPageLoadTimeout";
    private static final String DEFAULT_CONDITION_TIMEOUT = "defaultConditionTimeout";

    static final String PROPERTIES_FILE_PATH = "src/test/resources/configs/Configuration.properties";

    private static Browser instance;
    private static WebDriver driver;
    public static ConfigFileReader configs;

    private static String implicitlyWait;
    private static String timeoutForPageLoad;
    private static String timeoutForCondition;

    public static Browser getInstance() {
        if (instance == null) {
            initProperties();
            try {
                System.setProperty("webdriver.chrome.driver", Browser.configs.getProperty("chromeDriverPath"));
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(Long.parseLong(configs.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
            instance = new Browser();
        }
        return instance;
    }

    public void exit() {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            instance = null;
        }
    }

    public String getImplicitlyWait() {
        return implicitlyWait;
    }

    public String getTimeoutForPageLoad() {
        return timeoutForPageLoad;
    }

    public String getTimeoutForCondition() {
        return timeoutForCondition;
    }

    public static void initProperties() {
        configs = new ConfigFileReader(PROPERTIES_FILE_PATH);
        implicitlyWait = configs.getProperty(IMPLICITLY_WAIT);
        timeoutForPageLoad = configs.getProperty(DEFAULT_PAGE_LOAD_TIMEOUT);
        timeoutForCondition = configs.getProperty(DEFAULT_CONDITION_TIMEOUT);
    }

    public void windowMaximize() {
        try {
            driver.manage().window().maximize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigate(final String url) {
        driver.navigate().to(url);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
