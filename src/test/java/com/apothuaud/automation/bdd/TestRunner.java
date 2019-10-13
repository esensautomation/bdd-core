package com.apothuaud.automation.bdd;

import com.apothuaud.automation.bdd.utils.OSValidator;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features",
        },
        glue = {
                "com.apothuaud.automation.bdd.steps",
        },
        tags = {
                "not @Ignore",
        },
        plugin = {
                "pretty",
                "json:target/report.json",
        }
)
public class TestRunner {

    private static final String CUCUMBER_REPORT_OUTPUT_DIR = "target";
    private static final String CUCUMBER_JSON_REPORT_PATH = CUCUMBER_REPORT_OUTPUT_DIR + "/report.json";

    private static final String AUT = "AUTs";
    private static final String TEST_PLATFORM = "Test Platform";
    private static final String TEST_FRAMEWORKS = "Test Frameworks";
    private static final String TEST_BROWSER = "Test Browsers";

    private static final String BUILD_NUMBER = "0.0.2";
    private static final String PROJECT_NAME = "Automation Tests";
    private static final String AUT_VALUE = "Jsonplaceholder, Spotify v1, TheInternet, CURA Healthcare";
    private static final String TEST_FRAMEWORKS_VALUE = "RestAssured, Selenium";
    private static final String TEST_BROWSER_VALUE = "Chrome";

    @BeforeClass
    public static void setUp(){

    }

    @AfterClass
    public static void generateReport() {

        File reportOutputDirectory = new File(CUCUMBER_REPORT_OUTPUT_DIR);
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add(CUCUMBER_JSON_REPORT_PATH);

        Configuration configuration = new Configuration(reportOutputDirectory, PROJECT_NAME);
        configuration.setBuildNumber(BUILD_NUMBER);
        configuration.addClassifications(AUT, AUT_VALUE);
        configuration.addClassifications(TEST_PLATFORM, OSValidator.OS);
        configuration.addClassifications(TEST_FRAMEWORKS, TEST_FRAMEWORKS_VALUE);
        configuration.addClassifications(TEST_BROWSER, TEST_BROWSER_VALUE);


        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
