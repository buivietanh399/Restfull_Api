package Get_List_Bids;

import Base_Url.BaseClass;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class Test_Get_List_Bids {
    @ParameterizedTest
    @CsvFileSource(resources = "/get_list_bids.csv", numLinesToSkip = 2)

    public void RunTest(String access_token, String index, String count, String auctionId, int code, String message) {
        BaseClass.init();
        Data Input = new Data();
        Input.index = index;
        Input.count = count;
        Response response = given().contentType(ContentType.JSON).header("Authorization", "Bearer" + access_token).when().body(Input).get("/bids/" + auctionId);
        response.prettyPrint();
//        Response_Get_list_Bids rp = response.as(Response_Get_list_Bids.class);
//        Assertions.assertEquals(code, rp.code);
//        Assertions.assertEquals(message, rp.message);
    }

}
class Bids {
    public String user_name;
    public String user_avatar;
    public String price;
    public String update_at;

}

class Data {

    public String index;
    public String count;
    public Bids bids;
    public String total;
}

class Response_Get_list_Bids {
    public int code;
    public String message;
    public Data data;
}

// test_notFail