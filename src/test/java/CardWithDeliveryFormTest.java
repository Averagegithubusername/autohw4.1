import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CardWithDeliveryFormTest {

    @Test
    void shouldSendForm() {
//        Configuration.headless = true;
        Configuration.holdBrowserOpen = true;
        LocalDate date = LocalDate.now();
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Южно-Сахалинск");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(date.plusDays(5).format(DateTimeFormatter.ofPattern("dd MM yyyy")));
        $("[data-test-id='name'] input").setValue("Жан-Поль Бельмондо");
        $("[data-test-id='phone'] input").setValue("12345678910");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Запланировать")).click();
//        $("[data-test-id='replan-notification'] button").shouldBe(Condition.appear).click();
        $("[data-test-id='success-notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
