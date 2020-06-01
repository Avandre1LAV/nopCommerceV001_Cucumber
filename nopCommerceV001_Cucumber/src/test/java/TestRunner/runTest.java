package TestRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features=".//Features/", 
			//		{".//Features/Login.feature",".//Features/Customers.feature"},
				  glue= "stepDefinition",
				  dryRun=false,
				  monochrome=true,
				  plugin= {"pretty", "html:test-output"})
			//	  tags= {"@sanity,@regression"})
public class runTest {

}
