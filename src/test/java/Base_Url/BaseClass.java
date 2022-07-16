package Base_Url;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;

public class BaseClass {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "https://auction-app3.herokuapp.com/api/";
    }
}
