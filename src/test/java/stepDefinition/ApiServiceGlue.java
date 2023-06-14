package stepDefinition;

import com.accolite.util.GlobalData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApiServiceGlue {

    RequestSpecification request;
    Response response;


    @Given("^I have base url \"([^\"]*)\"$")
    public void i_have_base_url(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        Thread.sleep(3000);
        System.out.println("url: " + arg1);
        RestAssured.baseURI = arg1;
        request = given();
    }

    @Given("^I set headers \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iSetHeadersTo(String headerKey, String headerValue) throws Throwable {
        request.header(headerKey, headerValue);
    }

    @When("I make a GET call {string}")
    public void iMakeAGETCall(final String endpoint) {
        System.out.println(endpoint);
//        System.out.println("status code: " + given().when().get(endpoint).getStatusCode());
        response = request.when().get(endpoint);
    }

    @And("I set params {string} to {string}")
    public void iSetParamsTo(final String key,
                             final String value) {
        request = request.param(key, value);
    }

    @Then("verify status code {string}")
    public void verifyStatusCode(final String statusCode) {
        System.out.println(response.statusCode());
        assertThat("status code not matched", response.statusCode(), equalTo(Integer.valueOf(statusCode)));
    }

    @When("I make a POST call {string}")
    public void iMakeAPOSTCall(final String endpoint) {
        response = request.when().log().all().post(endpoint);
    }

    @And("I extract response to json")
    public void iExtractResponse() {
        response.prettyPrint();
        String string = response.body().toString();
        JsonPath jsonPath = new JsonPath(response.asString());
        GlobalData.getInstance().setEnvironmentData("JSON_PATH", new JsonPath(response.asString()));
    }

    @Then("I extract json path value {string}")
    public void verifyJsonPath(final String jsonPath) {
        JsonPath json_path = (JsonPath) GlobalData.getInstance().getEnvironmentData("JSON_PATH");
        Object object = json_path.get(jsonPath);
        System.out.println(object.toString());
        GlobalData.getInstance().setEnvironmentData("JSON_VALUE", object);
    }

    @Then("I get value as {string}")
    public void iGetValueAs(final String expValue) {
        Object json_value = GlobalData.getInstance().getEnvironmentData("JSON_VALUE");
        assertThat("value not matching", String.valueOf(json_value), equalTo(expValue));
    }

    @And("I prepare body")
    public void iPrepareBody(final DataTable dataTable) {
        Map<String, String> tableData = dataTable.asMap(String.class, String.class);
        JSONObject bodyParams = new JSONObject();
        tableData.forEach((key, value) -> bodyParams.put(key, value));
        request.body(bodyParams.toString());
    }
}
