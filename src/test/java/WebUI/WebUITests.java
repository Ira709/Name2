package WebUI;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class WebUITests {
    String userLogin = "standard_user";
    String password = "secret_sauce";

    @BeforeAll
    public static void setUp() {
        open("https://www.saucedemo.com/");
    }

    @Test
    public void LoginTest() {
        $(".login_logo").shouldHave(text("Swag Labs"));

        $("#user-name").setValue(userLogin);

        $("#password").setValue(password);

        $("#login-button").click();

        $(".title").shouldBe(visible).shouldHave(text("Products"));

        sleep(3000);
    }

    @Test
    public void AddToCart() {
        SelenideElement item = $x("//div[@class = 'inventory_item'][1]");
        item.shouldBe(visible);

        SelenideElement itemName = $("#item_4_title_link");
        itemName.$(".inventory_item_name").shouldHave(text("Sauce Labs Backpack"));

        item.$("#add-to-cart-sauce-labs-backpack").click();

        item.$("#remove-sauce-labs-backpack").shouldBe(visible);

        SelenideElement shoppingCart = $(".shopping_cart_link");
        shoppingCart.$("data-test='shopping-cart-badge'").shouldBe(visible).shouldHave(text("1"));

        SelenideElement filter = $(".product_sort_container");
        filter.click();

        filter.selectOption("Price (low to high)");

        $(".active_option").shouldHave(text("Price (low to high)"));
    }
}
