package com.ortizmiguelangel.integration;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectDirectories("src/test/resources/features")
@SelectClasspathResource("com/ortizmiguelangel/integration")
@ConfigurationParameters({
        @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.ortizmiguelangel.integration")
})
public class RunCucumberTests {
}
