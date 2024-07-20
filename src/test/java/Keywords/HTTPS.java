package Keywords;

import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import org.testng.annotations.Test;
import us.abstracta.jmeter.javadsl.http.DslHttpSampler;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class HTTPS {

    private DslHttpSampler post(String host, String productName) {
        return httpSampler("https://" + host + "/api/product")
                .post("{\"name\": \"" + productName + "\"}", ContentType.APPLICATION_JSON);
    }
    private DslHttpSampler get(String host, String productName) {
        return httpSampler("https://" + host + "/api/product")
                .post("{\"name\": \"" + productName + "\"}", ContentType.APPLICATION_JSON);
    }

    @Test
    public void test_Get() throws Exception {
        testPlan(
                threadGroup(1, 1,
                        httpSampler("https://automationintesting.online/branding")
                                .method(HTTPConstants.GET)
                                .header("host","automationintesting.online")
                                .header("Content-Type","application/json")

                ),
                resultsTreeVisualizer(),
                responseAssertion()
        ).run();
    }
}
