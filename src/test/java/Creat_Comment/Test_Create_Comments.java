package Creat_Comment;

import Base_Url.BaseClass;
;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class Test_Create_Comments {

    @ParameterizedTest
    @CsvFileSource(resources = "/Creat_Comment.csv",numLinesToSkip = 2)

    public void RunTest(String access_token, String content, String comment_last_id,String auctionId, int code, String message){
        BaseClass.init();
        Data Input = new Data();
        Input.content = content;
        Input.comment_last_id = comment_last_id;
        Response response = given().contentType(ContentType.JSON).header("Authorization","Bearer"+access_token).when().body(Input).post("/comments/create/" + auctionId);
        response.prettyPrint();
//        Response_Create_Comment rp = response.as(Response_Create_Comment.class);
//        Assertions.assertEquals(code, rp.code);
//        Assertions.assertEquals(message, rp.message);
    }
}

class Response_Create_Comment{
    public int code;
    public String message;
    public Data data;
}
class Data{

    public String comment_last_id;
    @JsonIgnore
    public String auction_id;
    @JsonIgnore
    public String user_id;
    public String content;
    @JsonIgnore
    public String update_at;
    @JsonIgnore
    public String total;
}