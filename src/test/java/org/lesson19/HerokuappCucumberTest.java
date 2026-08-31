package org.lesson19;

import io.cucumber.core.options.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/lesson19")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@smoke")

public class HerokuappCucumberTest {
}