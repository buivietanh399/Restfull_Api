package Signup;

import Base_Url.BaseClass;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class Test_Signup {
    @ParameterizedTest
    @CsvFileSource(resources = "/signup.csv", numLinesToSkip = 2)
    public void Test_signup(String Email, String Password, String re_pass, String address, String name, String phone, String avatar, String code, String message) {
        BaseClass.init();
        User user = new User();
        user.email = Email;
        user.password = Password;
        user.address = address;
        user.re_pass = re_pass;
        user.name = name;
        user.avatar = avatar;
        user.phone = phone;
        Response_signup rp = given().contentType(ContentType.JSON).when().body(user).post("/signup").as(Response_signup.class);
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
            if (avatar == null)
                Assert.assertEquals("https://res.cloudinary.com/daqvhmyif/image/upload/v1650429693/wtatjbj7jhpueicdrg6n.jpg", rp.data.avatar);
            else Assert.assertEquals(avatar, rp.data.avatar);
        }
    }
}

class Response_signup {
    public String code;
    public String message;
    public Data data;
}

class Data {
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
    public String password;
    public String re_pass;
    public String phone;
}