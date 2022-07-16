package Change_pass;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class Change_pass {
    @ParameterizedTest
    @CsvFileSource(resources = "/change_pass.csv",numLinesToSkip = 2)
    void Test_change_pass(String access_token, String old_pass, String new_pass, String re_pass, String code, String message,String data) {
        BaseClass.init();
        Input input = new Input();
        input.setNew_pass(new_pass)  ;
        input.setOld_pass(old_pass)  ;
        input.setRe_pass(re_pass)  ;
        Response response = given().contentType(ContentType.JSON).header("Authorization","Bearer"+access_token).when().body(input).post("/changepass");
        response.prettyPrint();
        Response_change_pass rp = response.as(Response_change_pass.class);

        System.out.println("Expected:");
        System.out.println(code + "\n" + message);
        System.out.println("Actual:");
        System.out.println(rp.code + "\n" + rp.message);
        Assert.assertEquals(code, rp.code);
        Assert.assertEquals(message, rp.message);
    }
}
class Input{
    String old_pass;
    String new_pass;
    String re_pass;
    public String getOld_pass() {
        return old_pass;
    }

    public void setOld_pass(String old_pass) {
        this.old_pass = old_pass;
    }

    public String getNew_pass() {
        return new_pass;
    }

    public void setNew_pass(String new_pass) {
        this.new_pass = new_pass;
    }

    public String getRe_pass() {
        return re_pass;
    }

    public void setRe_pass(String re_pass) {
        this.re_pass = re_pass;
    }
}
class Response_change_pass{
   public String code;
   public String message;
   public String data;
}
