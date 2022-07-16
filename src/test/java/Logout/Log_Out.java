package Logout;
import Base_Url.BaseClass;
import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;

public class Log_Out {
    @ParameterizedTest
    @CsvFileSource(resources = "/logout.csv",numLinesToSkip =2 )
    public void Test_logout(String access_token,String code,String message){
        BaseClass.init();
        SessionFilter sessionFilter = new SessionFilter();
        Response rp = given().contentType("application/json").header("Authorization","Bearer"+access_token).when().post("/logout");
        if(rp.getStatusCode()==302) {
           Response rp2 = given().expect().statusCode(200).when().get(rp.getHeader("Location"));
            rp2.prettyPrint();
            Response_logout response_logout = rp2.as(Response_logout.class);
            Assert.assertEquals(code, response_logout.code);
            Assert.assertEquals(message, response_logout.message);
        }
        else {
            rp.prettyPrint();
            Response_logout response_logout = rp.as(Response_logout.class);
            Assert.assertEquals(code, response_logout.code);
            Assert.assertEquals(message, response_logout.message);
        }
    }
}
class Response_logout{
   public String message;
   public String code;
   public String data;

}


