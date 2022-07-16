package Get_list_rates;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static io.restassured.RestAssured.given;
public class Get_list_rates {
    @ParameterizedTest
    @CsvFileSource(resources = "/Get_list_rate.csv",numLinesToSkip = 2)
    void Test_getlist_rate(String index, String count,String auctionsID){
        BaseClass.init();
        Input input = new Input();
        input.count = count;
        input.index = index;
        Response response = given().contentType(ContentType.JSON).when().body(input).get("/rates/"+auctionsID);
        response.prettyPrint();
    }
}
class Input{
    public String index;
    public  String count;
}
