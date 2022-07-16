package Create_Auction;


import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class Create_Auction {
    @ParameterizedTest
    @CsvFileSource(resources = "/create_auction.csv", numLinesToSkip = 2)
    public void create_auction(String access_token, String category_id, String start_date, String end_date, String title_ni, String code, String message) {
        BaseClass.init();
        Input input = new Input();
        input.category_id = category_id;
        input.start_date = start_date;
        input.end_date = end_date;
        input.title_ni = title_ni;
        Response rp = given().contentType(ContentType.JSON).header("Authorization", "Bearer" + access_token).when().body(input).post("/auctions/create");
        if (rp.getStatusCode() == 302) {
            Response rp2 = given().expect().statusCode(200).when().get(rp.getHeader("Location"));
            rp2.prettyPrint();
            Response_create_auction response_logout = rp2.as(Response_create_auction.class);
            Assert.assertEquals(code, response_logout.code);
            Assert.assertEquals(message, response_logout.message);
        } else {
            rp.prettyPrint();
            Response_create_auction response_logout = rp.as(Response_create_auction.class);
            Assert.assertEquals(code, response_logout.code);
            Assert.assertEquals(message, response_logout.message);

        }
    }
}

class Response_create_auction {
    public String code;
    public String message;
    public Data_1 data;
}

class Data_1 {
    public String category_id;
    public String start_date;
    public String end_date;
    public String title;
    public String auction_id;
    public String status;
    public String reason;
    public String created_at;
    public String updated_at;
    public String deleted_at;
    public String selling_user_id;


}
class Input
{
    public String category_id;
    public String start_date;
    public String end_date;
    public String title_ni;
}

