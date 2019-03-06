package com.ttest.test;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Interface for defining an testAction
*/
public interface TestAction {
    
    /**
     * @return Whether the testAction Was Successful or Not
    */
    public Boolean excute();

}

/**
 * Class that defines an testAction for drag a WebElement by it's id @see [org.openqa.selenium.WebElement]
 * and drop it to a target WebElement
 * @property elementId Identifier of the html element that is to be dragged
 * @property targetId Identifier of the html element where the source element is to be dropped
 */
public class DragAndDropTestAction implements TestAction {

    private String elementId;

    private String targetId;

    private WebDriver driver;

    public DragAndDropTestAction(String elementId, String targetId, WebDriver driver) {
        this.elementId = elementId;
        this.targetId = targetId;
        this.driver = driver;
    }

    /**
     * Locates the element by it's id using xpath and drags it to the target
     * @return Whether the element was found and clicked or not
    */
    @Override
    public Boolean execute() {
        Boolean passed = Boolean.TRUE;
        waitForPageToLoad(driver);
        try {
            
        } catch (TimeoutException ex) {
            passed = Boolean.FALSE;
            System.out.println("Cannot locate elemnt with id: " + elementId);
        }
    }

}


class DragAndDropTestAction(private var elementId: String,
                                    private var targetId: String,
                                    val driver: WebDriver) : TestAction {
        override fun execute(): Boolean {
        var passed = true
        // Outer try: Element
        waitForPageToLoad(driver)
        try {
            val element = WebDriverWait(driver, 10)
                    .until(ExpectedConditions
                            .presenceOfElementLocated(By
                                    .xpath("//*[contains(@id, '$elementId')]")))
            println("Located element with id: '$elementId'")
            WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element))
            val previousLocation = element.location
            // Inner try target
            try {
                val target = WebDriverWait(driver, 10)
                        .until(ExpectedConditions
                                .presenceOfElementLocated(By
                                        .xpath("//*[contains(@id, '$targetId')]")))
//                WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(target))
                println("Located element with id: '$targetId'")
                // Drag and Drop
                Actions(driver).dragAndDrop(element, target).perform()
                val currentLocation = element.location
                if (previousLocation != currentLocation) {
                    println("Dragged element with id: '$elementId' to element with id '$targetId'")
                } else {
                    passed = false
                    println("Element with id: '$elementId' couldn't be draggedAndDropped to element with id '$targetId'")
                }
            } catch (ex: TimeoutException) {
                passed = false
                println("Cannot Locate element with id: '$targetId'")
            }
        } catch (ex: TimeoutException) {
            passed = false
            println("Cannot Locate element with id: '$elementId'")
        }
        return passed
    }

}


/**
 * Class that defines an testAction for clicking a WebElement by it's id @see [org.openqa.selenium.WebElement]
 * @property elementId Identifier of the html element that is to be located
 */
class ClickByIdTestAction(private var elementId: String, val driver: WebDriver) : TestAction {
    /**
     * Locates the element by it's id using xpath and Clicks it
     * @return Whether the element was found and clicked or not
     */
    override fun execute(): Boolean {
        var passed = true
        waitForPageToLoad(driver)
        try {
            val element = WebDriverWait(driver, 10)
                    .until(ExpectedConditions
                            .presenceOfElementLocated(By
                                    .xpath("//*[contains(@id, '$elementId')]")))
            Thread.sleep(500)
            element.click()
            println("Clicked element with id: '$elementId'")
        } catch (ex: TimeoutException) {
            passed = false
            println("Cannot Locate element with id: '$elementId'")
        }
        return passed
    }

}


/**
 * Class that defines an testAction for clicking a WebElement via Xpath @see [org.openqa.selenium.WebElement]
 * @property xpath xpath of the html element for a click testAction
 */
class ClickByXpathTestAction(private var xpath: String, val driver: WebDriver) : TestAction {
    /**
     * 'Locates' the element via xpath's id and 'Clicks it'
     * @return Whether the element was found and clicked or not
     */
    override fun execute(): Boolean {
        var passed = true
        waitForPageToLoad(driver)
        try {
            val element = WebDriverWait(driver, 10)
                    .until(ExpectedConditions
                            .presenceOfElementLocated(By
                                    .xpath(xpath)))
//            WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element))
            element.click()
            println("Clicked element with xpath: '$xpath'")
        } catch (ex: TimeoutException) {
            passed = false
            println("Cannot Locate element with xpath: '$xpath'")
        }
        return passed
    }

}

/**
 * Class that defines an testAction for navigating to an URL
 * @property url for navigation purposes
 */
class NavigateTestAction(private val url: String, val driver: WebDriver) : TestAction {
    /**
     * Navigates to the specified URL
     * @return Whether the step was successful or not
     */
    override fun execute(): Boolean {
        // Handle Http connection
        driver.navigate().to(url)
        println("Connected to $url")
        return true
    }
}

/**
 * Class that defines an testAction for writing data to an html input
 * @property elementId Identifier of the input for a 'Write' testAction
 * @property data Which is to be written in the element
 */
class SendKeysTestAction(private val elementId: String, private val data: String, val driver: WebDriver) : TestAction {
    /**
     * 'Locates' the input via xpath's id and Writes the data in it
     * @property elementId Identifier of the input element for a Write testAction
     * @return Whether the step was successful or not
     */
    override fun execute(): Boolean {
        var passed = true
        waitForPageToLoad(driver)
        try {
            val element = WebDriverWait(driver, 10)
                    .until(ExpectedConditions
                            .presenceOfElementLocated(By
                                    .xpath("//input[contains(@id, '$elementId')]")))
//            WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element))
            try {
                // Erases the previous data
                element.clear()
                // Writes the new data
                element.sendKeys(data)
                println("'$data' was written in element with id: '$elementId'")
            } catch (e: ElementNotInteractableException) {
                println("Can't write '$data' in element: '$elementId'")
                passed = false
            }
        } catch (ex: TimeoutException) {
            println("Cannot Write '$data' in element with id: '$elementId'")
            passed = false
        }
        return passed
    }
}

/**
 * Class that defines an testAction for locating an element by it's text (e.g. <h1>Foo Bar Baz</h1>)
 * @property text Text contained by the html element
 */
class LocateByTextTestAction(private val text: String, val driver: WebDriver) : TestAction {
    /**
     * Locates the element via it's contained text
     * @return Whether the element was found or not
     */
    override fun execute(): Boolean {
        var passed = true
        waitForPageToLoad(driver)
        try {
            val element = WebDriverWait(driver, 10)
                    .until(ExpectedConditions
                            .presenceOfElementLocated(By
                                    .xpath("//*[contains(text(), '$text')]")))
//            WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element))
            println("Located element with text: '$text'")
        } catch (ex: TimeoutException) {
            println("Cannot Locate element with text: '$text'")
            passed = false
        }
        return passed
    }
}

/**
 * Class that defines an testAction for locating an element by it's id
 * @property elementId Identifier of the ui element for a Write testAction
 */
class LocateByIdTestAction(private val elementId: String, val driver: WebDriver) : TestAction {
    /**
     * Locates the element via xpath's id
     * @return Whether it was found or not
     */
    override fun execute(): Boolean {
        var passed = true
        waitForPageToLoad(driver)
        try {
            val element = WebDriverWait(driver, 10)
                    .until(ExpectedConditions
                            .presenceOfElementLocated(By
                                    .xpath("//*[contains(@id, '$elementId')]")))
            WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element))
            println("Located element with id: '$elementId'")
        } catch (ex: TimeoutException) {
            println("Cannot Locate element with id: '$elementId'")
            passed = false
        }
        return passed
    }
}


/**
 * Class that defines an testAction for uploading a file
 * @property elementId Identifier of the input type file element for an Upload testAction
 */
class UploadFileTestAction(private val elementId: String, private val filePath: String, val driver: WebDriver) : TestAction {
    /**
     * Locates the input type file via it's xpath id
     * @return Whether it was found or not
     */
    override fun execute(): Boolean {
        var passed = true
        waitForPageToLoad(driver)
        try {
            val element = WebDriverWait(driver, 10)
                    .until(ExpectedConditions
                            .presenceOfElementLocated(By
                                    .xpath("//*[contains(@id, '$elementId')]")))
//            WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element))
            element.sendKeys(filePath)
            println("File: '$filePath' was uploaded from element with id: '$elementId'")
        } catch (ex: TimeoutException) {
            println("Cannot Locate input type file: '$elementId'")
            passed = false
        }
        return passed
    }
}

fun waitForPageToLoad(driver: WebDriver) {
    do {
        val js = driver as JavascriptExecutor
        val pageLoadStatus = js.executeScript("return document.readyState") as String
        print(".")
    } while (pageLoadStatus != "complete")
    println()
    println("Page Loaded.")
}