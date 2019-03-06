package com.ttest.test;

/**
 * Class for defining and TestStep it an test
 * @property testAction Which is to be executed
 */
public class TestStep {

	private TestAction testAction;

	public TestStep(TestAction testAction) {
		this.testAction = testAction;
	}
	
	/**
    * Executes the testAction
    * @return Whether the testAction was successful or not
    */
	public Boolean execute() {
		return testAction.execute();
	}

}