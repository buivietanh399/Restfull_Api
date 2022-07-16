package ContactUs;

import Base_Url.BaseClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class Test_ContactUs {
    @ParameterizedTest
    @CsvFileSource(resources = "/contactUs.csv", numLinesToSkip = 2)
    void contactUs(String access_token, String name, String phone, String email, String content, String file, String report_type, String code, String message){
        BaseClass.init();
        Data input = new Data();
        input.name = name;
        input.phone = phone;
        input.email = email;
        input.content = content;
        input.file = file;
        input.report_type = report_type;
        Response response = given().contentType(ContentType.JSON).when().body(input).post("/contactUs");
        response.prettyPrint();
        Response_contactUs rp = response.as(Response_contactUs.class);
        System.out.println("Expected:");
        System.out.println(code + "\n" + message);
        System.out.println("Actual:");
        System.out.println(rp.code + "\n" + rp.message);
        Assertions.assertEquals(code, rp.code);
        Assertions.assertEquals(message, rp.message);
    }
}

class Response_contactUs{
    public String code;
    public String message;
    public Data data;
}

class Data{
    public String name;
    public String phone;
    public String email;
    public String content;
    @JsonIgnore
    public String file;
    public String report_type;
}