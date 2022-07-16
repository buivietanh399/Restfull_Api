package Read_notifications;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static io.restassured.RestAssured.given;

public class Read_notifications {
    @ParameterizedTest
    @CsvFileSource(resources = "/Read_notifications.csv", numLinesToSkip = 2)
    public void testRead_notifications(String access_token, String DenyId){
        BaseClass.init();
        Response response = given().contentType(ContentType.JSON).header("Authorization","Bearer"+access_token).when().get("/notifications/read/"+DenyId);
        response.prettyPrint();
    }
}
class Response_Read_notifications {
    public int code;
    public String message;
    public Data data;
}
class Data {
    public String is_read;
    public String auction_id;
    public String type;
    public Auctions auctions;
    public Category category;
    public Items items;
    public String total_not_read;
    public String total;
}
class Auctions{
    public String title;
    public String start_date;
    public String end_date;
    public String status;
    public String updated_at;
    public String reason;
}
class Category{
    public String name;
}
class Items{
    public String name;
    public String brand;
    public String series;
    public String description;
    public String starting_price;
    public String mainImage;
    public String images;
}
