package Creat_bid;

import Base_Url.BaseClass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static io.restassured.RestAssured.given;


public class Test_Creat_Bid {

    @ParameterizedTest
    @CsvFileSource(resources = "/creat_bids.csv",numLinesToSkip = 2)

    public void RunTest(String access_token, String price, String bid_last_id,String auctionId, int code, String message){
        BaseClass.init();
        Data Input = new Data();
        Input.price = price;
        Input.bid_last_id = bid_last_id;
        Response response = given().contentType(ContentType.JSON).header("Authorization","Bearer"+access_token).when().body(Input).post("/bids/create/" + auctionId);
        response.prettyPrint();
        Response_Creat_Bid rp = response.as(Response_Creat_Bid.class);
        Assertions.assertEquals(code, rp.code);
        Assertions.assertEquals(message, rp.message);
    }

}
class Response_Creat_Bid{
    public int code;
    public String message;
    public Data data;
}
class Data{
    public String bid_last_id;
    public String price;

    @JsonIgnore
    public String auction_id;

    @JsonIgnore
    public String user_id;
    @JsonIgnore
    public String content;
    @JsonIgnore
    public String update_at;
    @JsonIgnore
    public String total;

}

// Fail 5 test
/*
+) Test 1 : Expected :1008
    Actual   :1001
+) Test 2 : Expected :1000
    Actual   :1001
+) Test 7 : Expected :price: 7006
Actual   :price: 7014
+) Test 10 : Expected :1000
Actual   :1008
+) Test 12 : Expected :1000
Actual   :1001
 */