package Slider;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static io.restassured.RestAssured.given;

public class Slider {
    @ParameterizedTest
    @CsvFileSource(resources = "/Slider.csv",numLinesToSkip = 2)
    public void testSlider(String access_token) {
        BaseClass.init();
        Response response = given().contentType(ContentType.JSON).header("Authorization","Bearer"+access_token).when().get("/slider");
        response.prettyPrint();
    }
}
class Response_Slider {
    public int code;
    public String message;
    public Data data;
}
class Data {
    public String image;
    public String slider_id;
}
