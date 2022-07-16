package Edit_account;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class Test_edit_account {
    @ParameterizedTest
    @CsvFileSource(resources = "/edit_account.csv",numLinesToSkip = 2)
    public void test_edit_account(String access_token,String Email, String address, String name, String phone, String avatar, String code, String message) {
        BaseClass.init();
        User user = new User();
        user.email = Email;
        user.address = address;
        user.name = name;
        user.avatar = avatar;
        user.phone = phone;
        Response response = given().contentType(ContentType.JSON).header("Authorization","Bearer"+access_token).when().body(user).post("/edit");
        response.prettyPrint();
        Response_edit_account rp = response.as(Response_edit_account.class);
        System.out.println(rp.code);
        System.out.println(rp.message);
        System.out.println("Expected:");
        System.out.println(code + "\n" + message);
        System.out.println("Actual:");
        System.out.println(rp.code + "\n" + rp.message);
        Assert.assertEquals(code, rp.code);
        Assert.assertEquals(message, rp.message);
        if (rp.data != null) {
            Assert.assertEquals(Email, rp.data.email);
            Assert.assertEquals(name, rp.data.name);
            Assert.assertEquals(phone, rp.data.phone);
            Assert.assertEquals(address, rp.data.address);
            Assert.assertEquals("2", rp.data.role);
            Assert.assertEquals(avatar, rp.data.avatar);
        }
    }
}
class Response_edit_account{
    public String code;
    public String message;
    public Data data;
}

class Data{
    public String name;
    public String email;
    public String phone;
    public String address;
    public String avatar;
    public String role;

}
class User {
    public String name;
    public String address;
    public String email;
    public String avatar;
    public String phone;
}