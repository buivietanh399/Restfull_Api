package Login;
import Base_Url.BaseClass;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static io.restassured.RestAssured.given;

public class Test_login  {
    @ParameterizedTest
    @CsvFileSource(resources = "/login.csv",numLinesToSkip = 1)
    public void login_1(String Email, String Password,String code,String message) {
        BaseClass.init();
        User user = new User();
        user.email=Email;
        user.password=Password;
        Response response = given().contentType(ContentType.JSON).when().body(user).post("/login");
        response.prettyPrint();
        Response_login rp = response.as(Response_login.class);
        System.out.println("Expected:");
        System.out.println(code + "\n" + message);
        System.out.println("Actual:");
        System.out.println(rp.code + "\n" + rp.message);
        Assert.assertEquals(code,rp.code);
        Assert.assertEquals(message,rp.message);
    }
}
    class Response_login {
        public String code;
        public String message;
        @JsonProperty("data")
        public JsonNode data;

        public String getMessage() {
            return message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
class User{
    public String password;
    public String email;
}
