package GetNews;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class Test_GetNews {
    @ParameterizedTest
    @CsvFileSource(resources = "/getNews.csv", numLinesToSkip = 2)
    void getNews(String access_token, String index, String count, String code, String message){
        BaseClass.init();
        Input input = new Input();
        input.setIndex(index);
        input.setCount(count);
        Response response = given().contentType(ContentType.JSON).header("Authorization","Bearer"+access_token).when().body(input).get("/news");
        response.prettyPrint();
        Response_getNews rp = response.as(Response_getNews.class);
        System.out.println("Expected:");
        System.out.println(code + "\n" + message);
        System.out.println("Actual:");
        System.out.println(rp.code + "\n" + rp.message);
        Assertions.assertEquals(code, rp.code);
        Assertions.assertEquals(message, rp.message);
    }
}

class Input{
    String index;
    String count;
    public String getIndex(){
        return index;
    }
    public void setIndex(String index){
        this.index = index;
    }
    public String getCount(){
        return count;
    }
    public void setCount(String count){
        this.count = count;
    }
}

class Response_getNews{
    public String code;
    public String message;
    public Data data;
}

class Data{
    public ArrayList<Data_News> news;
    public String total;
}

class Data_News{
    public String user;
    public String new_id;
    public String title;
    public String content;
    public String updated_at;
    public String is_read;
}
