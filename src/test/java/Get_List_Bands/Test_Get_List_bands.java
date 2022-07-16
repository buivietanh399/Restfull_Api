package Get_List_Bands;

import Base_Url.BaseClass;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class Test_Get_List_bands {
    @ParameterizedTest
    @CsvFileSource(resources = "/get_list_brands.csv", numLinesToSkip = 2)

    public void RunTest() {
        BaseClass.init();
        Data Input = new Data();

        Response response = given().contentType(ContentType.JSON).when().body(Input).get("/brands" );
        response.prettyPrint();
//        Response_Get_List_Brands rp = response.as(Response_Get_List_Brands.class);
//        Assertions.assertEquals(code, rp.code);
//        Assertions.assertEquals(message, rp.message);
    }
}


class Data{
    public Bands b;
}

class Bands{
    public String brand_id;
    public String name;
    public String brand_info;
}
class Response_Get_List_Brands{
    public String message;
    public int code;
    public Data data;
}

// Test notFail