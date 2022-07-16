package ListCategories;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class Test_ListCategories {
    @Test
    void listCategories(){
        BaseClass.init();
        Response response = given().contentType(ContentType.JSON).when().get("/categories");
        response.prettyPrint();
    }
}

class Response_listCategories{
    public String code;
    public String message;
    public Data data;
}

class Data{
    public ArrayList<Category> categories;
}

class Category{
    public String category_id;
    public String name;
    public String image;
    public String type;
}
