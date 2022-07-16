package Search;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static io.restassured.RestAssured.given;

public class Search {
    @ParameterizedTest
    @CsvFileSource(resources = "/Search.csv",numLinesToSkip = 2)
    public void test_search(String type, String key) {
        BaseClass.init();
        Input input = new Input();
        input.setType(type);
        input.setKey(key);
        Response response = given().contentType(ContentType.JSON).when().body(input).get("/search");
        response.prettyPrint();
    }
}

class Input {
    String type;
    String key;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
}
