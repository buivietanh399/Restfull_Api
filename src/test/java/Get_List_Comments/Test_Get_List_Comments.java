package Get_List_Comments;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class Test_Get_List_Comments {
    @ParameterizedTest
    @CsvFileSource(resources = "/get_list_comment.csv", numLinesToSkip = 2)

    public void RunTest(String access_token, String index, String count, String auctionId, int code, String message) {
        BaseClass.init();
        Data Input = new Data();
        Input.index = index;
        Input.count = count;
        Response response = given().contentType(ContentType.JSON).header("Authorization", "Bearer" + access_token).when().body(Input).get("/comments/" + auctionId);
        response.prettyPrint();
//        Response_Get_list_Comment rp = response.as(Response_Get_list_Comment.class);
//        Assert.assertEquals(code, rp.code);
//        Assert.assertEquals(message, rp.message);
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

class Response_Get_list_Comment {
    public int code;
    public String message;
    public Data data;
}

// Test_NotFail