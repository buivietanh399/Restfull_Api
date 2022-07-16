package Edit_item;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class Edit_item {
    @ParameterizedTest
    @CsvFileSource(resources = "/Edit_item.csv", numLinesToSkip = 2)
    void run_Test(String access_token, String Id, String name, String Starting_price, String brand_id, String description, String series, String images1, String images2, String images3, String images4, String images5, String code, String message) {
        BaseClass.init();
        Data input = new Data();
        input.name = name;
        input.starting_price = Starting_price;
        input.brand_id = brand_id;
        input.description = description;
        input.series = series;
        input.images = new ArrayList<String>();
        if (images1 != null)
            input.images.add(images1);
        if (images2 != null)
            input.images.add(images2);
        if (images3 != null)
            input.images.add(images3);
        if (images4 != null)
            input.images.add(images4);
        if (images5 != null)
            input.images.add(images5);
        Response response1 = given().contentType(ContentType.JSON).header("Authorization", "Bearer" + access_token).when().body(input).post("/items/edit/" + Id);
        if (response1.getStatusCode() == 302) {
            Response response2 = given().expect().statusCode(200).when().get(response1.getHeader("Location"));
            response2.prettyPrint();
            Response_create rp = response2.as(Response_create.class);
            Assert.assertEquals(code, rp.code);
            Assert.assertEquals(message, rp.message);
        } else {
            response1.prettyPrint();
            Response_create rp = response1.as(Response_create.class);
            Assert.assertEquals(code, rp.code);
            Assert.assertEquals(message, rp.message);
        }
    }
}

class Response_create {
    public String code;
    public String message;
    public Data data;
}

class Data {
    public String brand_id;
    public String series;
    public String name;
    public String description;
    public ArrayList<String> images;
    public String starting_price;
}
