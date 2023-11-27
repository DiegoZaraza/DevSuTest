package test;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.ApiUtilities;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class PetService extends BaseClass{
    private Response response;

    @Test(description = "Add pet to store", priority=1)
    public void AddNewPet(){
        sendAddNewPet();
        pet.setId(Long.parseLong(BaseClass.apiUtilities.extractValueBody(getResponse(), "id")));

    }

    @Test(description = "Search Pet for ID", priority=2)
    public void GetNewPet(){
        getNewPet();
        System.out.println("ID : " + pet.getId());
    }

    @Test(description = "Update name and status", priority=3)
    public void UpdatePet() {
        pet.setName(Faker.instance().animal().name());
        pet.setStatus("sold");
        updateNewPet();
        pet.setStatus(BaseClass.apiUtilities.extractValueBody(getResponse(), "status"));
        pet.setName(BaseClass.apiUtilities.extractValueBody(getResponse(), "name"));
        System.out.println("ID : " + pet.getId());
        System.out.println("Name : " + pet.getName());
        System.out.println("Status : " + pet.getStatus());

    }

    @Test(description = "Search pet for Status", priority=4)
    public void GetPetForStatus() {
        findByStatus();

        System.out.println(BaseClass.apiUtilities.extractValueBody(getResponse(), "status"));
        System.out.println(BaseClass.apiUtilities.extractValueBody(getResponse(), "id"));
    }


    public void sendAddNewPet(){
        response = apiUtilities.sendPostRequest(pet, returnURL());
    }

    public void getNewPet(){
        response = apiUtilities.sendGetRequest(returnURL(), "id", pet);
    }

    public void updateNewPet(){
        response = apiUtilities.sendPutRequest(returnURL(), pet);
    }

    public void findByStatus(){
        response = apiUtilities.sendGetRequest(returnURL()+ "/findByStatus", "status", pet);
    }
    public String returnURL() {
        return baseURL + "pet";
    }
    public Response getResponse() {
        return response;
    }

}
