package DetailsAuctions;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class Test_DetailsAuctions {
    @ParameterizedTest
    @CsvFileSource(resources = "/detailsAuctions.csv", numLinesToSkip = 2)
    void detailsAuctions(String access_token, String id, String code, String message){
        BaseClass.init();
        Response response = given().contentType(ContentType.JSON).when().get("/auctions/detail/"+id);
        response.prettyPrint();
        Response_detailsAuctions rp = response.as(Response_detailsAuctions.class);
        System.out.println("Expected:");
        System.out.println(code + "\n" + message);
        System.out.println("Actual:");
        System.out.println(rp.code + "\n" + rp.message);
        Assertions.assertEquals(code, rp.code);
        Assertions.assertEquals(message, rp.message);
    }
}

class Response_detailsAuctions{
    public String code;
    public String message;
    public Data_detailsAuctions data;
}
