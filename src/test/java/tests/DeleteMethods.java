package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CustomAnotations.TestDescription;
import utils.ReadersForConfigs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteMethods {



    @TestDescription(def="Given user wants to search with valid id , then UserShould be delete successfully")
    @Test
    public void DeleteSuccess200(){
        RestAssured.baseURI= ReadersForConfigs.getPropertiesValue("base.URI");

        int expected_id=14;
        Response response= given().auth().basic("admin","testPassword").
                header("accept","*/*").
                when().delete("/v1/delete-person/"+expected_id).then().log().body().extract().response();


        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.asString().contains("deleted person with Id:"));




    }

    @TestDescription(def="Given user DELETE a person which already deleted  , then it should be failed")
    @Test
    public void DeleteWithInvalidId404(){
        RestAssured.baseURI= ReadersForConfigs.getPropertiesValue("base.URI");

        int expected_id=14;
        Response response= given().auth().basic("admin","testPassword").
                header("accept","*/*").
                when().delete("/v1/delete-person/"+expected_id).then().log().body().extract().response();


        Assert.assertEquals(response.statusCode(),400);
        Assert.assertTrue(response.asString().contains("deleted person with Id:"));




    }

    @TestDescription(def="Given user wants to search with invalid id , then it should be failed")
    @Test
    public void DeleteWithInvalidIds404(){
        RestAssured.baseURI= ReadersForConfigs.getPropertiesValue("base.URI");

        int expected_id=1400;
        Response response= given().auth().basic("admin","testPassword").
                header("accept","*/*").
                when().delete("/v1/delete-person/"+expected_id).then().log().body().extract().response();


        Assert.assertEquals(response.statusCode(),404);
        Assert.assertTrue(response.asString().equals("Cannot find Person with id: 1400"));



    }


    @TestDescription(def="Given user wants to search with invalid id , then it should be failed")
    @Test
    public void DeleteWithZERO404(){
        RestAssured.baseURI= ReadersForConfigs.getPropertiesValue("base.URI");

        int expected_id=-90;
        Response response= given().auth().basic("admin","testPassword").
                header("accept","*/*").
                when().delete("/v1/delete-person/"+expected_id).then().log().body().extract().response();


        Assert.assertEquals(response.statusCode(),404);
        Assert.assertTrue(response.asString().equals("Cannot find Person with id: -90"));



    }
}
