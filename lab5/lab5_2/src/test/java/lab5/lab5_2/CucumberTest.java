package lab5.lab5_2;


import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("lab5/lab5_2")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "lab5.lab5_2")
public class CucumberTest {

}