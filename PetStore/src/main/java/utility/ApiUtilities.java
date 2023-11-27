package utility;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import test.Pet;

import static io.restassured.RestAssured.given;

public class ApiUtilities {
    private final PropertiesRead propertiesRead;
    public ApiUtilities(PropertiesRead propertiesRead){
        this.propertiesRead = propertiesRead;
    }

    /**
     * Method that contains base to send post request rest assured
     *
     * @param bodyRequest
     * @return Response
     */
    public Response sendPostRequest(Pet pet, String URL){
        return  given()
                .header("Content-type", "application/json")
                .and()
                .body(pet, ObjectMapperType.GSON)
                .when()
                .post(URL)
                .then()
                .extract().response();
    }

    /**
     *
     * @param URL
     * @return
     */
    public Response sendGetRequest(String URL, String param, Pet pet){

        return given().headers("Content-Type", "application/json").
                param(param, param.equals("id")?pet.getId():pet.getStatus()).
                when().
                get(URL).
                then().
                extract().
                response();
    }

    public Response sendPutRequest(String URL, Pet pet) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(pet, ObjectMapperType.GSON)
                .when()
                .put(URL)
                .then()
                .extract().response();
    }
    /**
     *
     * @param response
     * @param fieldName
     * @return
     */
    public String extractValueBody(Response response, String fieldName) {
        return response.body().jsonPath().get(fieldName).toString();
    }
}
