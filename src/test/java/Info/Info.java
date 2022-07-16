package Info;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static io.restassured.RestAssured.given;

public class Info {
    @ParameterizedTest
    @CsvFileSource(resources = "/info.csv",numLinesToSkip = 2)
    public void Info(String access_token, String code, String message){
        BaseClass.init();
        Response response = given().contentType(ContentType.JSON).header("Authorization","Bearer"+access_token).when().get("/info");
        response.prettyPrint();
    }
}

class Response_info {
    public String code;
    public String message;
    //public Data_5 data;
}


