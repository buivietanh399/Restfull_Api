package Get_list_auctions_by_status;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static io.restassured.RestAssured.given;

public class Auctions_by_status {
    @ParameterizedTest
    @CsvFileSource(resources = "/auctions_by_status.csv",numLinesToSkip = 2)
    public void Get_list_auctions_by_status(String status,String index,String count){
        BaseClass.init();
        Input input = new Input();
        input.setIndex(index);
        input.setCount(count);
        Response response = given().contentType(ContentType.JSON).when().body(input).get("/auctions/listAuctionsByStatus/"+status);
        response.prettyPrint();
    }
}
class Input{
    String index;
    String count;
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }


}
