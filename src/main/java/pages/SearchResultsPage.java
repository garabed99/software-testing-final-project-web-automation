package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static constants.locators.SearchResultsPageConstants.*;

public class SearchResultsPage {
    private WebDriver driver;
    private By firstResult = By.xpath(FIRST_RESULT);
    private By resultNotFound = By.xpath(RESULTS_NOT_FOUND);
    private By entireSearchResult = By.className(ENTIRE_SEARCH_RESULTS);
    private String validSearchKeyword = VALID_SEARCH_KEYWORD;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

//    public List<WebElement> getSearchedResults() {
////        List<WebElement> searchList = driver.findElements(entireSearchResult);
//        List<WebElement> results = new ArrayList<WebElement>(10);
//        for(int i = 1; i <= 10; i++) {
//            String temp = "//*[@id=\"content\"]/article/div[2]/ul/li["+i+"]";
//            String temp1 = "//*[@id=\"content\"]/article/div[2]/ul/li[1]";
//            boolean j = temp.equals(temp1);
//            System.out.println(j);
//            System.out.println(temp);
//            WebElement searchListItem = driver.findElement(By.xpath(temp1));
//            System.out.println(searchListItem);
//
//            results.add(searchListItem);
//        }
//        System.out.println(results);
//        return results;
//    }
    public List<WebElement> getSearchedResults() {
            List<WebElement> searchList = driver.findElements(entireSearchResult);
            return searchList;
    }
    public int numberOfRelevantAnswers() {
        int count = 0;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        for(WebElement answer : getSearchedResults()) {
            if(answer.getText().contains(validSearchKeyword)) count++;
        }
        System.out.println(count);
        return count;
    }

    public SelectedSearchResultPage getFirstSearchedResult() {
        WebElement result = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(firstResult));
        result.click();
        return new SelectedSearchResultPage(driver);
    }

    public String getResultNotFound() {

        return driver.findElement(resultNotFound).getText();
    }

    public SelectedSearchResultPage goToSpecificResultPage(int num) {
        getSearchedResults().get(num).click();
        return new SelectedSearchResultPage(driver);
    }

}
