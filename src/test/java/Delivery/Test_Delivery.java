package Delivery;

import Base_Url.BaseClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class Test_Delivery {

    @ParameterizedTest
    @CsvFileSource(resources = "/Delivery.csv",numLinesToSkip = 2)

    public void RunTest(String access_token,String auctionId, String code, String message){
        BaseClass.init();

        Response response = given().contentType(ContentType.JSON).header("Authorization","Bearer"+access_token).when().post("/auctions/updateDelivery/" + auctionId);
        response.prettyPrint();
        Response_Delivery rp = response.as(Response_Delivery.class);
        Assertions.assertEquals(code, rp.code);
        Assertions.assertEquals(message, rp.message);
    }

}

class Response_Delivery{
    public String code;
    public String message;
    @JsonIgnore
    public String  data;
}
// Fail_5test
/*
+)Test  2 3 4 Không có data
+) Test 5 in ra max HTML
 */