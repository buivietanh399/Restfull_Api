package TotalLikes;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class Test_TotalLikes {
    @ParameterizedTest
    @CsvFileSource(resources = "/totalLikes.csv", numLinesToSkip = 2)
    void totalLikes(String access_token, String id, String code, String message){
        BaseClass.init();
        Response response = given().contentType(ContentType.JSON).when().get("/totalLikes/"+id);
        response.prettyPrint();
        Response_totalLikes rp = response.as(Response_totalLikes.class);
        System.out.println("Expected:");
        System.out.println(code + "\n" + message);
        System.out.println("Actual:");
        System.out.println(rp.code + "\n" + rp.message);
        Assertions.assertEquals(code, rp.code);
        Assertions.assertEquals(message, rp.message);
    }
}

class Response_totalLikes{
    public String code;
    public String message;
    public Data data;
}

class Data{
    public String auction_id;
    public String total_liked;
}
