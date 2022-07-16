package Creat_Chat;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;


public class Test_Creat_Chat {

    @ParameterizedTest
    @CsvFileSource(resources = "/Creat_Chat.csv",numLinesToSkip = 2)

    public void RunTest(String access_token, String user_receive_id, String  code, String message){
        BaseClass.init();
        Response response1 = given().contentType(ContentType.JSON).header("Authorization", "Bearer" + access_token).when().post("/chat/conversation/" + user_receive_id);
        if (response1.getStatusCode() == 302) {
            Response response2 = given().expect().statusCode(200).when().get(response1.getHeader("Location"));
            response2.prettyPrint();
            Response_Creat_Chat rp = response2.as(Response_Creat_Chat.class);
            Assert.assertEquals(code, rp.code);
            Assert.assertEquals(message, rp.message);
        } else {
            response1.prettyPrint();
            Response_Creat_Chat rp = response1.as(Response_Creat_Chat.class);
            Assert.assertEquals(code, rp.code);
            Assert.assertEquals(message, rp.message);
        }
    }
}


class Data{
    public String user_receive_id;
    public String chat_Id;
    public String getUser_receive_id;
    public String created_at;



}

class Response_Creat_Chat{
    public String code;
    public String message;
    public Data data;
}