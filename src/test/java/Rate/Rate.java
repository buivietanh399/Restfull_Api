package Rate;

import Base_Url.BaseClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
public class Rate {
    @ParameterizedTest
    @CsvFileSource(resources = "/rate.csv", numLinesToSkip = 2)
    void Test_rate(String access_token, String star, String content, String image,String auctionID,String code, String message) {
        BaseClass.init();
        Input input =new Input();
        input.content=content;
        input.image =image;
        input.star =star;
        Response response = given().contentType(ContentType.JSON).header("Authorization","Bearer"+access_token).body(input).post("/auctions/rate/"+auctionID);
        response.prettyPrint();
        Response_rate rp = response.as(Response_rate.class);
        Assert.assertEquals(code,rp.code);
        Assert.assertEquals(message,rp.message);
    }
}

class Response_rate {

   public String code;
   public String message;
   public String data;
}

class Input {
    public String star;
    public String content;
    @JsonIgnore
    public String image;
}