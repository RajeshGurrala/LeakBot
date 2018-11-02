import com.HomeSure.support.WebModel;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.minidev.json.JSONObject;
import org.junit.Assert;


import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

public class accountDetails_stepDefs {

    WebModel webModel=new WebModel();
    String randomFirstName;
    String randomLastName;
    @cucumber.api.java.en.Given("^I perform a GET request for the account details with valid credentials$")
    public void iPerformAGETRequestForTheAccountDetailsWithValidCredentials() {
        webModel.getRestServices().executeGetRequest("token", "b4444924ec456699e23024f2881282dd72419d30", "account_id", "2032", "Read/");

    }

    @Then("^I should be able to receive that particular customer details$")
    public void iShouldBeAbleToReceiveThatParticularCustomerDetails() {
        List<Map<String, String>> allKeys = JsonPath.parse(webModel.getRestServices().response.asString()).read("$");
        for (Map<String, String> requiredKey : allKeys) {
            assertThat(requiredKey, hasKey("mobile_number"));
            assertThat(requiredKey, hasKey("home_tel_number"));
            assertThat(requiredKey, hasKey("last_name"));
            assertThat(requiredKey, hasKey("username"));
            assertThat(requiredKey, hasKey("sms_allowed"));
            assertThat(requiredKey, hasKey("email_allowed"));
            assertThat(requiredKey, hasKey("mail_allowed"));
            assertThat(requiredKey, hasKey("first_name"));

        }
    }
    @And("^I should be able to assert response code (\\d+)$")
    public void iShouldBeAbleToAssertResponseCode(int expectedCode) {
        assertThat(webModel.getRestServices().response.statusCode(), is(expectedCode));
    }




    @Given("^I perform a GET request for the account details with invalid token$")
    public void iPerformAGETRequestForTheAccountDetailsWithInvalidToken() {
        webModel.getRestServices().executeGetRequest("token", "b4444924ec456699e23024f2881282dd72419d30", "account_id", "0000", "Read/");


    }

    @Then("^I should get an error message with description station that the token is invalid$")
    public void iShouldGetAnErrorMessageWithDescriptionStationThatTheTokenIsInvalid() {
        Assert.assertEquals(webModel.getRestServices().response.getBody().path("description"), ("ACCOUNT_ID is not valid"));


    }

    @Given("^I perform a PATCH request on particular account details by providing valid token and account_id$")
    public void iPerformAPATCHRequestOnParticularAccountDetailsByProvidingValidTokenAndAccount_id()  {
        JSONObject patchAccountDetails = webModel.getUtils().getPayload("accountDetails");
        randomFirstName = webModel.getUtils().randomName();
        randomLastName=webModel.getUtils().randomName();
        patchAccountDetails.replace("first_name", randomFirstName);
        patchAccountDetails.replace("last_name", randomLastName);
        webModel.getRestServices().executePATCHRequest(patchAccountDetails, "/Update");
    }


    @Then("^I should be able to update the details$")
    public void iShouldBeAbleToUpdateTheDetails() {
        assertThat(webModel.getRestServices().response.statusCode(), is(201));

    }

    @And("^I should be able to assert the updating by a GET request$")
    public void iShouldBeAbleToAssertTheUpdatingByAGETRequest() {
        webModel.getRestServices().executeGetRequest("token", "b4444924ec456699e23024f2881282dd72419d30", "account_id", "2032", "Read/");
        Assert.assertEquals(webModel.getRestServices().response.getBody().path("first_name"), (randomFirstName));
        Assert.assertEquals(webModel.getRestServices().response.getBody().path("last_name"), (randomLastName));



    }

    @Given("^I perform a PATCH request on particular account details by providing invalid token$")
    public void iPerformAPATCHRequestOnParticularAccountDetailsByProvidingInvalidToken()  {
        JSONObject patchAccountDetails = webModel.getUtils().getPayload("accountDetails");
        randomFirstName = webModel.getUtils().randomName();
        randomLastName=webModel.getUtils().randomName();
        patchAccountDetails.replace("token", "abcdef123456");
        patchAccountDetails.replace("first_name", randomFirstName);
        patchAccountDetails.replace("last_name", randomLastName);
        webModel.getRestServices().executePATCHRequest(patchAccountDetails, "/Update");

    }

    @Then("^I should receive an error description on the invalidity of the token provided$")
    public void iShouldReceiveAnErrorDescriptionOnTheInvalidityOfTheTokenProvided()  {
        Assert.assertEquals(webModel.getRestServices().response.getBody().path("description"), ("TOKEN is not valid"));


    }

    @And("^i should be able to verify that none of the details are updated$")
    public void iShouldBeAbleToVerifyThatNoneOfTheDetailsAreUpdated()  {
        webModel.getRestServices().executeGetRequest("token", "b4444924ec456699e23024f2881282dd72419d30", "account_id", "2032", "Read/");
        Assert.assertNotEquals(webModel.getRestServices().response.getBody().path("first_name"), (randomFirstName));
        Assert.assertNotEquals(webModel.getRestServices().response.getBody().path("last_name"), (randomLastName));
    }


}