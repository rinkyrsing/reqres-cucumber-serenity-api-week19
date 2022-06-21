package in.reqres.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.reqres.reqresinfo.UsersSteps;
import in.reqres.utilis.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

public class UsreSteps {

    static String name = "Amy" + TestUtils.getRandomValue();
    static String job = "Tester" + TestUtils.getRandomValue();
    static String email = "amysmith@gmail.com";
    static String password = "smith123";
    static String usersId;
    static ValidatableResponse response;

    @Steps
    UsersSteps usersSteps;

    @Given("^I am on home page of the application$")
    public void iAmOnHomePageOfTheApplication() {
        usersSteps.loginUser(email, password);
    }

    @When("^I send POST request to the the application using a valid payload to create a User$")
    public void iSendPOSTRequestToTheTheApplicationUsingAValidPayloadToCreateAUser() {
        response = usersSteps.createAllUsers(name, job);
    }

    @And("^I get status code (\\d+)$")
    public void iGetStatusCode(int code) {
        response.assertThat().statusCode(code);
    }

    @Then("^I fetch the ID of newly created user$")
    public void iFetchTheIDOfNewlyCreatedUser() {
        usersId = response.log().all().extract().path("id");
        System.out.println(usersId);
    }

    @When("^I send GET request to the application to read newly created user$")
    public void iSendGETRequestToTheApplicationToReadNewlyCreatedUser() {
        HashMap<String, ?> userMap = usersSteps.getProductInfoByName(usersId);
        Assert.assertThat(userMap, hasValue(name));

    }

    @Then("^I verify in the response if it has newly created user name$")
    public void iVerifyInTheResponseIfItHasNewlyCreatedUserName() {
        HashMap<String, ?> userMap = usersSteps.getProductInfoByName(usersId);
        Assert.assertThat(userMap, hasValue(name));
        System.out.println(usersId);
    }

    @When("^I send PUT request to the application to update newly created user$")
    public void iSendPUTRequestToTheApplicationToUpdateNewlyCreatedUser() {
        name = name + "_updatedbyPut";
        response = usersSteps.usersUpdateByPut(usersId, name, job);
        response.log().all().statusCode(200);
    }

    @Then("^I verify in the response if it has newly created user is updated$")
    public void iVerifyInTheResponseIfItHasNewlyCreatedUserIsUpdated() {
        HashMap<String, ?> userMap = usersSteps.getProductInfoByName(usersId);
        Assert.assertThat(userMap, hasValue(name));
    }

    @When("^I send PATCH request to the application to update newly created user$")
    public void iSendPATCHRequestToTheApplicationToUpdateNewlyCreatedUser() {
        name = name + "_updatedbyPatch";
        response = usersSteps.usersUpdateByPatch(usersId, name, job);
        response.log().all().statusCode(200);
    }

    @When("^I send DELETE request to the application to delete newly created user$")
    public void iSendDELETERequestToTheApplicationToDeleteNewlyCreatedUser() {
        response = usersSteps.deleteUsersId(usersId);
        response.log().all().statusCode(204);
    }

    @Then("^I verify if newly created user is deleted$")
    public void iVerifyIfNewlyCreatedUserIsDeleted() {
        response = usersSteps.deleteUsersId(usersId);
        response.log().all().statusCode(404);
    }

    @When("^I send GET request to the the application to read all users$")
    public void iSendGETRequestToTheTheApplicationToReadAllUsers() {
        response = usersSteps.creatingAllPageTwoUsers();
    }

    @And("^I verify if page is (\\d+)$")
    public void iVerifyIfPageIs(int page) {
        response = usersSteps.creatingAllPageTwoUsers();
        int actual_page = response.log().all().extract().path("page");
        Assert.assertEquals(page, actual_page);
    }

    @And("^I verify if per_page is (\\d+)$")
    public void iVerifyIfPer_pageIs(int per_page) {
        response = usersSteps.creatingAllPageTwoUsers();
        int actual_per_page = response.log().all().extract().path("per_page");
        Assert.assertEquals(per_page, actual_per_page);
    }

    @And("^I verify if second data's id is (\\d+)$")
    public void iVerifyIfSecondDataSIdIs(int expectedText) {
        response = usersSteps.creatingAllPageTwoUsers();
        int data = response.log().all().extract().path("data[1].id");
        Assert.assertEquals(expectedText, data);
    }

    @And("^I verify if forth data's first_name is Byron$")
    public void iVerifyIfForthDataSFirst_nameIsByron() {
        response = usersSteps.creatingAllPageTwoUsers();
        String firstname = response.log().all().extract().path("data[3].first_name");
        Assert.assertEquals("Byron", firstname);
    }

    @And("^I verify if list of data is (\\d+)$")
    public void iVerifyIfListOfDataIs(int expected) {
        response = usersSteps.creatingAllPageTwoUsers();
        List<?> listOfData= response.log().all().extract().path("data");
        Assert.assertEquals(expected, listOfData.size());
    }

    @And("^I verify if sixth data's avatar is \"([^\"]*)\"$")
    public void iVerifyIfSixthDataSAvatarIs(String expectedText) {
        response = usersSteps.creatingAllPageTwoUsers();
        String imageUrl = response.log().all().extract().path("data[5].avatar");
        Assert.assertEquals(expectedText, imageUrl);

    }

    @And("^I verify if support\\.url is \"([^\"]*)\"$")
    public void iVerifyIfSupportUrlIs(String expectedText) {
        response = usersSteps.creatingAllPageTwoUsers();
        String supportHeading = response.log().all().extract().path("support.url");
        Assert.assertEquals(expectedText, supportHeading);
    }

    @And("^I verify if support\\.text is \"([^\"]*)\"$")
    public void iVerifyIfSupportTextIs(String expectedText) {
        response = usersSteps.creatingAllPageTwoUsers();
        String supportText = response.log().all().extract().path("support.text");
        Assert.assertEquals(expectedText, supportText);
    }


}
