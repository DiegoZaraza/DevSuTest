package test;

import com.github.javafaker.Faker;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utility.ApiUtilities;
import utility.PropertiesRead;

public class BaseClass {
    public static String baseURL;
    public static long timeResponse;
    public static ApiUtilities apiUtilities;
    public static PropertiesRead propertiesRead;
    public static Pet pet;

    @BeforeClass
    public void setupApplication()
    {
        Reporter.log("=====Base Class Setup Started=====", true);
        propertiesRead = new PropertiesRead();
        pet = new Pet(Faker.instance().animal().name(), "available");
        apiUtilities = new ApiUtilities(propertiesRead);
        propertiesRead.getPropertiesFile("config.properties");

        baseURL = propertiesRead.returnProperties("URL");
        timeResponse = Long.parseLong(propertiesRead.returnProperties("TIME_RESPONSE"));
        Reporter.log("=====Base Class Setup Complete=====", true);
    }

    @AfterClass
    public void closeApplication()
    {
        Reporter.log("=====Session End=====", true);
    }
}
