package com.esens.automation.bdd.runner;

import com.esens.automation.bdd.utils.OSValidator;
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
                "com.esens.automation.bdd.steps",
        },
        tags = {
                "not @Ignore",
        },
        plugin = {
                "pretty",
                "json:target/cucumber/report.json",
                "html:target/cucumber/html",
        }
)
public class TestRunner {

    private static final String CUCUMBER_REPORT_OUTPUT_DIR = "target/cucumber";
    private static final String CUCUMBER_JSON_REPORT_PATH = CUCUMBER_REPORT_OUTPUT_DIR + "/report.json";

    private static final String TEST_PLATFORM = "Test Platform";

    private static final String BUILD_NUMBER = "0.0.4";
    private static final String PROJECT_NAME = "Sample Tests";

    @BeforeClass
    public static void setUp(){

    }

    @AfterClass
    public static void tearDown(){

        generateReport();
        copyDocs();
    }

    private static void generateReport() {

        File reportOutputDirectory = new File(CUCUMBER_REPORT_OUTPUT_DIR);
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add(CUCUMBER_JSON_REPORT_PATH);

        Configuration configuration = new Configuration(reportOutputDirectory, PROJECT_NAME);
        configuration.setBuildNumber(BUILD_NUMBER);
        configuration.addClassifications(TEST_PLATFORM, OSValidator.OS);

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

    private static void copyDocs() {


    }
}
