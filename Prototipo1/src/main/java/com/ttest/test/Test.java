package com.ttest.test;

import com.ttest.testwap.model.Step;
import com.ttest.testwap.repository.UseCaseRepository;
import org.openqa.selenium.WebDriver;

public class Test {

	private WebDriver driver;

	private String useCaseId;

	private List<Step> useCaseSteps;

	private List<TestStep> testSteps = new ArrayList();

	public Test(WebDriver driver, String useCaseId, List<Step> useCaseSteps) {
		this.driver = driver;
		this.useCaseId = useCaseId;
		this.useCaseSteps = useCaseSteps;
	}

    /**
     * Builds the test, i.e. add the testSteps od the Tested Use Case
     */
	public void build() {
        System.out.println("Building Test for Use Case: " + useCaseId);
        for(Step it: useCaseSteps) {
        	Integer actionId = it.action.id;
        	switch(actionId) {
        		case 1:
        			testSteps.add(TestStep(ClickByIdTestAction(it.searchCriteria, driver)));
        			break;
        		case 2:
        			testSteps.add(TestStep(ClickByXpathTestAction(it.searchCriteria, driver)));
        			break;
        		case 3:
        			testSteps.add(TestStep(NavigateTestAction(it.url, driver)));
        			break;
        		case 4:
        			testSteps.add(TestStep(SendKeysTestAction(it.searchCriteria, it.elementValue, driver)));
        			break;
        		case 5:
        			testSteps.add(TestStep(LocateByTextTestAction(it.searchCriteria, driver)));
        			break;
        		case 6:
        			testSteps.add(TestStep(LocateByIdTestAction(it.searchCriteria, driver)));
        			break;
        		case 7:
        			testSteps.add(TestStep(UploadFileTestAction(it.searchCriteria, it.elementValue, driver)));
        			break;
        		case 8:
        			testSteps.add(TestStep(DragAndDropTestAction(it.searchCriteria, it.targetElementId, driver)));
        			break;
        		default:
        			System.out.println("Action not defined for id: " + actionId);
        			break;
        	}
        }

	}

    /**
     * Runs the test, i.e. executes each step the Test
     */
    public Boolean run() {
    	Boolean isValid = Boolean.TRUE;
    	System.out.println("Test Is Running");
    	for(TestStep step: testSteps) {
    		if(!step.execute()) {
    			isValid = Boolean.FALSE;
    			break;
    		}
    	}
    	return isValid;
    }

}