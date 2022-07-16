package Delete_Auction;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class Delete_Auction {

    @ParameterizedTest
    @CsvFileSource(resources = "/delete_auction.csv",numLinesToSkip = 2)

    public void delete_auction(String auctionId, String access_token, String code, String message){
        BaseClass.init();
        Response rp = given().contentType(ContentType.JSON).header("Authorization","Bearer"+access_token).when().post("/auctions/deleteAuction/" + auctionId);
        
        //Response_delete_auction rp = response.as(Response_delete_auction.class);
        //Assertions.assertEquals(code, rp.code);
        //Assertions.assertEquals(message, rp.message);
        if (rp.getStatusCode() == 302) {
            Response rp2 = given().expect().statusCode(200).when().get(rp.getHeader("Location"));
            rp2.prettyPrint();
            Response_delete_auction response_logout = rp2.as(Response_delete_auction.class);
            Assert.assertEquals(code, response_logout.code);
            Assert.assertEquals(message, response_logout.message);
        } else {
            rp.prettyPrint();
            Response_delete_auction response_logout = rp.as(Response_delete_auction.class);
            Assert.assertEquals(code, response_logout.code);
            Assert.assertEquals(message, response_logout.message);

        }
    }


}

class Response_delete_auction{
    public String code;
    public String message;
    public String data;
}