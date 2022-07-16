package AcceptBid;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class Test_AcceptBid {
    @ParameterizedTest
    @CsvFileSource(resources = "/acceptBid.csv", numLinesToSkip = 2)
    void acceptBid(String access_token, String id, String selling_info, String code, String message){
        BaseClass.init();
        Input input = new Input();
        input.selling_info = selling_info;
        Response response = given().contentType(ContentType.JSON).header("Authorization","Bearer"+access_token).when().body(input).post("/accept/"+id);
        response.prettyPrint();
        Response_acceptBid rp = response.as(Response_acceptBid.class);
        System.out.println("Expected:");
        System.out.println(code + "\n" + message);
        System.out.println("Actual:");
        System.out.println(rp.code + "\n" + rp.message);
        Assertions.assertEquals(code, rp.code);
        Assertions.assertEquals(message, rp.message);
    }
}

class Input{
    public String selling_info;
}

class Response_acceptBid{
    public String code;
    public String message;
    public Data_acceptBid data;
}
