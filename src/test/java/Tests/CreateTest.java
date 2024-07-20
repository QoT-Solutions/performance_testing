package Tests;

import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;
import us.abstracta.jmeter.javadsl.http.DslHttpSampler;

import java.io.IOException;
import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class CreateTest {

    @Test()
    public void testPerformance() throws IOException {
        TestPlanStats stats = testPlan(
                threadGroup(2, 10,
                        httpSampler("http://my.service")
                ),
                //this is just to log details of each request stats
                jtlWriter("target/jtls")
        ).run();
        // Check that there were no errors in the test plan

    }
    @Test
    public void performanceTest() throws IOException {
        String host = "www.qotsolutions.co.za/";
        testPlan(
                threadGroup(1, 1,
                        httpSampler(host)
                                .children(responseAssertion().containsSubstrings("OK"))
                                .method(HTTPConstants.GET)
                ),
            htmlReporter("report"),
            responseAssertion()
//        ).saveAsJmx("test.jmx");
        ).run();
    }

    private DslHttpSampler Sampler(String host, String productName) {
        return httpSampler("https://" + host + "/api/product")
                .post("{\"name\": \"" + productName + "\"}", ContentType.APPLICATION_JSON);
    }
    private DslHttpSampler Get(String host, String productName) {
        return httpSampler("https://" + host + "/api/product")
                .post("{\"name\": \"" + productName + "\"}", ContentType.APPLICATION_JSON);
    }
    private DslHttpSampler getProducts(String host) {
        // Construct the URL for the GET request
        String url = "https://" + host + "/api/products";

        // Return a GET request sampler with the constructed URL
        return httpSampler(HTTPConstants.GET);
    }

    @Test
    public void test_Get() throws Exception {
        testPlan(
                threadGroup(1, 1,
                        httpSampler("https://automationintesting.online/branding")
                                .method(HTTPConstants.GET)
                                .header("host","automationintesting.online")
                                .header("Content-Type","application/json"),
                        resultsTreeVisualizer(),
                        responseAssertion()

                ),
                threadGroup(1, 1,
                        httpSampler("https://automationintesting.online/message")
                                .method(HTTPConstants.POST)
                                .header("host","automationintesting.online")
                                .header("Content-Type","application/json")
                                .body("{\n" +
                                        "    \"name\": \"Lungelo Trevor Shabangu\",\n" +
                                        "    \"email\": \"lungelo.shabangu101@gmail.com\",\n" +
                                        "    \"phone\": \"0817370126\",\n" +
                                        "    \"subject\": \"Exploration: Testing Test Validation\",\n" +
                                        "    \"description\": \"Welcome to Shady Meadows, a delightful Bed & Breakfast nestled in the hills on Newingtonfordburyshire. A place so beautiful you will never want to leave. All our rooms have comfortable beds and we provide breakfast from the locally sourced supermarket. It is a delightful place\"\n" +
                                        "}")
                ),
                resultsTreeVisualizer(),
                responseAssertion()
                ).run();
    }
    @Test
    public void test_Post() throws Exception {
        testPlan(
                threadGroup(1, 1,
                        httpSampler("https://automationintesting.online/message")
                                .method(HTTPConstants.POST)
                               .header("host","automationintesting.online")
                                .header("Content-Type","application/json")
                                .body("{\n" +
                                        "    \"name\": \"Lungelo Trevor Shabangu\",\n" +
                                        "    \"email\": \"lungelo.shabangu101@gmail.com\",\n" +
                                        "    \"phone\": \"0817370126\",\n" +
                                        "    \"subject\": \"Exploration: Testing Test Validation\",\n" +
                                        "    \"description\": \"Welcome to Shady Meadows, a delightful Bed & Breakfast nestled in the hills on Newingtonfordburyshire. A place so beautiful you will never want to leave. All our rooms have comfortable beds and we provide breakfast from the locally sourced supermarket. It is a delightful place\"\n" +
                                        "}")
                ),
                resultsTreeVisualizer(),
                responseAssertion()
                ).run();
    }
}
