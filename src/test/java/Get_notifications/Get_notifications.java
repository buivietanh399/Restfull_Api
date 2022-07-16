package Get_notifications;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static io.restassured.RestAssured.given;

public class Get_notifications {
    @ParameterizedTest
    @CsvFileSource(resources = "/Get_notifications.csv", numLinesToSkip = 2)
    public void testGet_notifications(String access_token, String index, String account) {
        BaseClass.init();
        Response response = given().contentType(ContentType.JSON).header("Authorization", "Bearer"+access_token).when().get("/notifications");
        response.prettyPrint();
    }
}

class Response_get_notifications {
    public int code;
    public String massage;
    public Data data;
}
class Data {
   public String [] denys = {"tile","start_date","end_date","reasons","auction_id","updated_at","type","is_read"};
   public String total_not_read;
   public String total;
}