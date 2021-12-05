package tests;

import HELPERPOJO.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CustomAnotations.TestDescription;
import utils.ReadersForConfigs;
import static org.hamcrest.Matchers.equalTo;


import static io.restassured.RestAssured.given;

public class GetMethods {


    @TestDescription(def="Given user wants to search with valid id , then results should be shown")
    @Test
    public void getPersonSuccess200(){
    RestAssured.baseURI= ReadersForConfigs.getPropertiesValue("base.URI");

    int expected_id=4;
  String expected_name="Willis";


    Response response= given().auth().basic("admin","testPassword").
            header("Content-Type","application/json").
            when().get("/v1/get-person/"+expected_id).then().log().body().extract().response();


    response.then().body("firstName",equalTo(expected_name));
    Assert.assertEquals(response.statusCode(),200);




  }

    @TestDescription(def="Given user wants to search with INVALID id , then SHOULD SEE 404 status code")
    @Test
    public void InvalidId404Error(){
        RestAssured.baseURI= ReadersForConfigs.getPropertiesValue("base.URI");

        int expected_id=400;



        Response response= given().auth().basic("admin","testPassword").
                header("Content-Type","application/json").
                when().get("/v1/get-person/"+expected_id).then().log().body().extract().response();


        Assert.assertEquals(response.statusCode(),404);



    }


    @TestDescription(def="Given user wants to search with Id=0 , then SHOULD SEE 404 status code and Cannot find Person with id:0 ")
    @Test
    public void IdEquals0_404Error(){
        RestAssured.baseURI= ReadersForConfigs.getPropertiesValue("base.URI");

        int expected_id=0;

        Response response= given().auth().basic("admin","testPassword").
                header("Content-Type","application/json").
                when().get("/v1/get-person/"+expected_id).then().log().body().extract().response();
        Assert.assertEquals(response.statusCode(),404);

    }

    @TestDescription(def="Given user wants to search a person with name ")
    @Test
    public void withString_400BadRequest(){
        RestAssured.baseURI= ReadersForConfigs.getPropertiesValue("base.URI");

        String id ="Evindar";

        Response response= given().auth().basic("admin","testPassword").
                header("Content-Type","application/json").
                when().get("/v1/get-person/"+id).then().log().body().extract().response();

        Assert.assertEquals(response.statusCode(),400);

        response.then().body("error",equalTo("Bad Request"));

    }

}
