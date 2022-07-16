package Info_Item;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class Info_Item {
    @ParameterizedTest
    @CsvFileSource(resources = "/info_item.csv",numLinesToSkip = 2)
    public void info_item(String itemId, String access_token, String code, String message){
        BaseClass.init();
        Response rp = given().contentType(ContentType.JSON).header("Authorization","Bearer"+access_token).when().get("/items/info/"+ itemId);
        if (rp.getStatusCode() == 302) {
            Response rp2 = given().expect().statusCode(200).when().get(rp.getHeader("Location"));
            rp2.prettyPrint();
            Response_info_item response_logout = rp2.as(Response_info_item.class);
            Assert.assertEquals(code, response_logout.code);
            Assert.assertEquals(message, response_logout.message);
        } else {
            rp.prettyPrint();
            Response_info_item response_logout = rp.as(Response_info_item.class);
            Assert.assertEquals(code, response_logout.code);
            Assert.assertEquals(message, response_logout.message);

        }
    }
}

class Response_info_item {
    public String code;
    public String message;
    public Data_3 data;
}

class Data_3 {
    public String name;
    public String series;
    public String description;
    public String starting_price;
    public String brand_id;
    public ArrayList<String> images;
}
