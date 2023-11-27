package test;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetService extends BaseClass{
    private Response response;

    @Test(description = "Add pet to store", priority=1)
    public void AddNewPet(){
        sendAddNewPet();
        pet.setId(Long.parseLong(BaseClass.apiUtilities.extractValueBody(getResponse(), "id")));

        Assert.assertEquals(200, response.statusCode());
        Assert.assertTrue(response.getBody().asString().length() > 0);
        Assert.assertTrue(response.time() < 2000);

    }

    @Test(description = "Search Pet for ID", priority=2)
    public void GetNewPet(){
        getNewPet();
        System.out.println(response.getBody().asString());
        Assert.assertEquals(Long.parseLong(BaseClass.apiUtilities.extractValueBody(getResponse(), "id")), pet.getId());
        Assert.assertEquals(BaseClass.apiUtilities.extractValueBody(getResponse(), "name"), pet.getName());
        Assert.assertEquals(BaseClass.apiUtilities.extractValueBody(getResponse(), "status"), pet.getStatus());
        Assert.assertEquals(200, response.statusCode());
        Assert.assertTrue(response.getBody().asString().length() > 0);
        Assert.assertTrue(response.time() < 2000);
    }

    @Test(description = "Update name and status", priority=3)
    public void UpdatePet() {
        pet.setName(Faker.instance().animal().name());
        pet.setStatus("sold");
        updateNewPet();
        pet.setStatus(BaseClass.apiUtilities.extractValueBody(getResponse(), "status"));
        pet.setName(BaseClass.apiUtilities.extractValueBody(getResponse(), "name"));
        Assert.assertEquals(Long.parseLong(BaseClass.apiUtilities.extractValueBody(getResponse(), "id")), pet.getId());
        Assert.assertEquals(BaseClass.apiUtilities.extractValueBody(getResponse(), "name"), pet.getName());
        Assert.assertEquals(BaseClass.apiUtilities.extractValueBody(getResponse(), "status"), pet.getStatus());
        Assert.assertEquals(200, response.statusCode());
        Assert.assertTrue(response.getBody().asString().length() > 0);
        Assert.assertTrue(response.time() < 2000);

    }

    @Test(description = "Search pet for Status", priority=4)
    public void GetPetForStatus() {
        findByStatus();


    }


    public void sendAddNewPet(){
        response = apiUtilities.sendPostRequest(pet, returnURL());
    }

    public void getNewPet(){
        response = apiUtilities.sendGetRequestById(returnURL(), "id", pet);
    }

    public void updateNewPet(){
        response = apiUtilities.sendPutRequest(returnURL(), pet);
    }

    public void findByStatus(){
        response = apiUtilities.sendGetRequestByStatus(returnURL()+ "/findByStatus", "status", pet);
    }
    public String returnURL() {
        return baseURL + "pet";
    }
    public Response getResponse() {
        return response;
    }

}
