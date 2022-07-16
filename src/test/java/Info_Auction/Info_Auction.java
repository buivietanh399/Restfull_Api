package Info_Auction;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static io.restassured.RestAssured.given;

public class Info_Auction {
    @ParameterizedTest
    @CsvFileSource(resources = "/info_auction.csv", numLinesToSkip = 2)
    public void info_auction(String auctionId, String access_token, String code, String message) {
        BaseClass.init();
        Response rp = given().contentType(ContentType.JSON).header("Authorization", "Bearer" + access_token).when().get("/auctions/info/" + auctionId);
        if (rp.getStatusCode() == 302) {
            Response rp2 = given().expect().statusCode(200).when().get(rp.getHeader("Location"));
            rp2.prettyPrint();
            Response_info_auction response_logout = rp2.as(Response_info_auction.class);
            Assert.assertEquals(code, response_logout.code);
            Assert.assertEquals(message, response_logout.message);
        } else {
            rp.prettyPrint();
            Response_info_auction response_logout = rp.as(Response_info_auction.class);
            Assert.assertEquals(code, response_logout.code);
            Assert.assertEquals(message, response_logout.message);

        }
    }
}
class Response_info_auction {
    public String code;
    public String message;
    public Data_6 data;
    }
    class Data_6 {
        public String category_id;
        public String start_date;
        public String end_date;
        public String title;
    }

