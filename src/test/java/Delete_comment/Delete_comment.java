package Delete_comment;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static io.restassured.RestAssured.given;

public class Delete_comment {
    @ParameterizedTest
    @CsvFileSource(resources = "/Delete_comment.csv", numLinesToSkip = 2)
    public void testDeleteComment(String access_token, String Id) {
        BaseClass.init();
        Response response = given().contentType(ContentType.JSON).header("Authorization","Bearer"+access_token).when().get("/comments/"+Id);
        response.prettyPrint();
    }
}
class Response_DeleteComment {
    public String code;
    public String message;
    public String data;
}
