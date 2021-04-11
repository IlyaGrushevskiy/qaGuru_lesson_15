package jsonModel;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JsonModel {

    public String createModel(String name, String job) {
        String jsonString = ("{" +
                "\"name\": \"" + name + "\"," +
                "\"job\": \"" + job + "\"" +
                "}");
        return jsonString;
    }


}
