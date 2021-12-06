package tests;

import HELPERPOJO.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommonMethodsAPI;
import utils.CustomAnotations.TestDescription;
import utils.ReadersForConfigs;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.*;

public class PostMethods {






    @TestDescription(def = "Given User have full body, User should be able create a perosn in databse")
    @Test
    public void createPersonSuccessfully201() throws Exception{
        RestAssured.baseURI= ReadersForConfigs.getPropertiesValue("base.URI");

        Person person1=new Person("Evindar","Pre","1235556677");
        ObjectMapper objectMapper=new ObjectMapper();
        String personbody=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(person1);


        Response response= given().auth().basic("admin","testPassword").
                header("Content-Type","application/json").
                body(personbody).
                when().post("/v1/post-person").then().extract().response();

        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(person1.getFirstName(),"Evindar");
        Assert.assertEquals(person1.getLastName(),"Pre");
    }

    @TestDescription(def = "Given User wants create a person with existing id , Then it should be failed")
    @Test
    public void failedWithExistingId() throws Exception{
        RestAssured.baseURI= ReadersForConfigs.getPropertiesValue("base.URI");

        Person person1=new Person(2,"Evindar","Pre","1235556677");
        ObjectMapper objectMapper=new ObjectMapper();
        String personbody=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(person1);


        Response response= given().auth().basic("admin","testPassword").
                header("Content-Type","application/json").
                body(personbody).
                when().post("/v1/post-person").then().log().body().extract().response();

        Assert.assertEquals(response.statusCode(),400);

    }

    @Test
    public void missingFirstNameInBody400() throws Exception{
        RestAssured.baseURI= ReadersForConfigs.getPropertiesValue("base.URI");

        Person person1=new Person("","Pre","1235556677");
        ObjectMapper objectMapper=new ObjectMapper();
        String personbody=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(person1);


        Response response= given().auth().basic("admin","testPassword").
                header("Content-Type","application/json").
                body(personbody).
                when().post("/v1/post-person").then().extract().response();

        Assert.assertEquals(response.statusCode(),400);

    }

    @Test
    public void missingLastNameInBody400() throws Exception{
        RestAssured.baseURI= ReadersForConfigs.getPropertiesValue("base.URI");

        Person person1=new Person();
        person1.setLastName("DC");
        person1.setId(20);
        person1.setPhoneNumber("1222223344");
        ObjectMapper objectMapper=new ObjectMapper();
        String personbody=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(person1);


        Response response= given().auth().basic("admin","testPassword").
                header("Content-Type","application/json").
                body(personbody).
                when().post(ReadersForConfigs.getPropertiesValue("createPerson")).then().extract().response();

        Assert.assertEquals(response.statusCode(),201);

    }
    @TestDescription(def = "IF user wants to create a person with 12 digits,")
    @Test
    public void createPersonwith11DIGITSerror400() throws Exception{
        RestAssured.baseURI= ReadersForConfigs.getPropertiesValue("base.URI");

        Person person1=new Person(24,"Evindar","Pre","12355566770000");
        ObjectMapper objectMapper=new ObjectMapper();
        String personbody=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(person1);


        Response response= given().auth().basic("admin","testPassword").
                header("Content-Type","application/json").
                body(personbody).
                when().post("/v1/post-person").then().log().body().log().status().extract().response();

        Assert.assertEquals(response.statusCode(),400);





    }





}
