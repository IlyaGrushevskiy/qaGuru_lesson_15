package tests;

import io.restassured.response.ResponseBody;
import jsonModel.JsonModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

public class TestApi extends BaseTest{

    JsonModel jsonModel = new JsonModel();

    @Test
    public void createTest() {
        String name = "Ilya",
                job = "IT";

        String body = jsonModel.createModel(name, job);

        given().spec(requestSpecification)
                .when().body(body)
                .post("users")
                .then().assertThat()
                .statusCode(201)
                .log().body()
                .body("name", equalTo(name))
                .body("job", equalTo(job))
                .body("id", is(notNullValue()));
    }

    @Test
    public void deleteTest() {
        given().spec(requestSpecification)
                .when()
                .delete("users/2")
                .then().assertThat()
                .statusCode(204);
    }

    @Test
    public void getTest() {
        given().spec(requestSpecification)
                .when()
                .get("uncnown/2")
                .then().assertThat()
                .statusCode(200)
                .log().body()
                .body("data", is(notNullValue()));
    }

    @Test
    public void getTestNegative() {
        given().spec(requestSpecification)
                .when()
                .get("usernotfound/99999")
                .then().assertThat()
                .statusCode(404);
    }

    @Test
    public void updateTest() {
        String name = "Peter",
                job = "Driver";

        String body = jsonModel.createModel(name, job);

        given().spec(requestSpecification)
                .when().body(body)
                .put("users/2")
                .then().assertThat()
                .statusCode(200)
                .log().body()
                .body("name", equalTo(name))
                .body("job", equalTo(job))
                .body("updatedAt", is(notNullValue()));
    }

}
