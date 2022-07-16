package Read_news;
import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class Read_news {
    @ParameterizedTest
    @CsvFileSource(resources = "/Read_News.csv",numLinesToSkip = 2)
    public void Test_ReadNews(String access_token,String Id) {
        BaseClass.init();
        Response rp = given().contentType(ContentType.JSON).header("Authorization", "Bearer" + access_token).when().get("/news/read/"+Id);
        rp.prettyPrint();
    }
}
class Response_ReadNews{
    public String code;
    public String message;
    public Data data;
}

class Data{
    public String is_read;
    public String new_id;
    public String content;
    public String user_id;
    public String title;
    public String updated_at;
    public String user_create_name;
}
