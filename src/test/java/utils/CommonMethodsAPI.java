package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.StatusLine;

public class CommonMethodsAPI {

    public static JsonPath jsonPath;
    public static StatusLine statusLine;



    public static int getStatus(Response response){
        if (statusLine== null){
            return -1;
        }

        int statusCode=response.statusCode();
        return statusCode;
    }

    public static String getResponseKeyValue(String response_body,String response_key){

        jsonPath=new JsonPath(response_body);
        String key_value=jsonPath.get(response_key);

        return key_value;
    }

    public static String getStatusMessage(Response response){
        if(statusLine==null){
            return null;
        }

       return response.statusLine();

    }
}
