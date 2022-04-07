import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class ApiTests {

    public String today;
    public String inOneMonth;
    public String oneMonthAgo;
    public String twoMonthsAgo;

    @Test
    public void test_01_statusCodeAndMinPrice(){
        today = calculateToday();
        inOneMonth = calculateInOneMonth();
                                     // change to ZERO_TICKETS / ONE_TICKET / TWO_TICKETS
        String endPoint = AppStrings.BASE_URL + AppStrings.SIX_TICKETS + AppStrings.FROM + today + AppStrings.TO + inOneMonth + AppStrings.PARAM;
        var response = given().header(AppStrings.HEADER_KEY, AppStrings.HEADER_VALUE).when().get(endPoint).
                then().
                assertThat().statusCode(200).
                body("response.minPrice",everyItem(CoreMatchers.notNullValue()));
    }

    @Test
    public void test_02_statusCodeWithoutHeader(){
        today = calculateToday();
        inOneMonth = calculateInOneMonth();
                                     // change to ZERO_TICKETS / ONE_TICKET / TWO_TICKETS
        String endPoint = AppStrings.BASE_URL + AppStrings.SIX_TICKETS + AppStrings.FROM + today + AppStrings.TO + inOneMonth + AppStrings.PARAM;
        var response = given().when().get(endPoint).
                then().
                assertThat().statusCode(404).
                body("context.errors.message[0]",equalTo("Product not found"));
    }

    @Test
    public void test_03_statusCodeWithDateInThePast(){
        oneMonthAgo = calculateOneMonthAgo();
        twoMonthsAgo = calculateTwoMonthsAgo();
                                    // change to ZERO_TICKETS / ONE_TICKET / TWO_TICKETS
        String endPoint = AppStrings.BASE_URL + AppStrings.SIX_TICKETS + AppStrings.FROM + twoMonthsAgo + AppStrings.TO + oneMonthAgo + AppStrings.PARAM;
        var response = given().when().get(endPoint).
                then().
                assertThat().statusCode(400).
                body("context.errors.message[0]",equalTo("start date should not be in the past"));
    }



    public String calculateToday(){
        LocalDate today = LocalDate.now();
        String todayDate = today.toString().replace("-","");
        return todayDate;
    }

    public String calculateInOneMonth(){
        LocalDate today = LocalDate.now();
        LocalDate todayPlusOneMonth = today.plusMonths(1);
        String inOneMonthDate = todayPlusOneMonth.toString().replace("-","");
        return inOneMonthDate;
    }

    public String calculateOneMonthAgo(){
        LocalDate today = LocalDate.now();
        LocalDate oneMonthAgo = today.minusMonths(1);
        String OneMonthAgoDate = oneMonthAgo.toString().replace("-","");
        return OneMonthAgoDate;
    }

    public String calculateTwoMonthsAgo(){
        LocalDate today = LocalDate.now();
        LocalDate twoMonthsAgo = today.minusMonths(2);
        String twoMonthAgoDate = twoMonthsAgo.toString().replace("-","");
        return twoMonthAgoDate;
    }
}
