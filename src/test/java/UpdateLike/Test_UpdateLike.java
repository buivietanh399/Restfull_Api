package UpdateLike;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class Test_UpdateLike {
    @ParameterizedTest
    @CsvFileSource(resources = "/updateLike.csv", numLinesToSkip = 2)
    void updateLike(String access_token, String id, String code, String message){
        BaseClass.init();
        Response response = given().contentType(ContentType.JSON).header("Authorization","Bearer"+access_token).when().post("/updateLike/"+id);
        response.prettyPrint();
        Response_updateLike rp = response.as(Response_updateLike.class);
        System.out.println("Expected:");
        System.out.println(code + "\n" + message);
        System.out.println("Actual:");
        System.out.println(rp.code + "\n" + rp.message);
        Assertions.assertEquals(code, rp.code);
        Assertions.assertEquals(message, rp.message);
    }
}

class Response_updateLike{
    public String code;
    public String message;
    public Data data;
}

class Data{
    public String user_id;
    public String auction_id;
    public String is_liked;
}


