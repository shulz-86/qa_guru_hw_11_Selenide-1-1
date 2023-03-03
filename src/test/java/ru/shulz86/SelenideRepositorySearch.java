package ru.shulz86;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SelenideRepositorySearch {
    @Test
    void shouldFindSelenideRepositoryAtTheTop() {

        step("открыть браузер на странице гитхаб", () -> {
            open("https://github.com/");
        });
        step("ввести в поле поиска \"selenide\" и нажать на enter", () -> {
            $(".header-search-input").setValue("selenide").pressEnter();
        });
        step("в списке найденных репозиториев кликнуть на первый", () -> {
            $$("ul.repo-list li").first().$("a").click();
        });
        step("перейти в раздел Wiki проекта", () -> {
            $("#wiki-tab").click();
        });
        step("проверка: в списке страниц (Pages) есть страница SoftAssertions", () -> {
            $(".wiki-more-pages-link").find(byText("Show 2 more pages…")).click();
            $("div.Box--condensed").shouldHave(text("SoftAssertions"));
        });
        step("открыть страницу SoftAssertions", () -> {
            $("div.Box--condensed").find(byText("SoftAssertions")).click();
        });
        step("проверить что внутри есть пример кода для JUnit5", () -> {
            $("#wiki-body").shouldHave(text("3. Using JUnit5 extend test class:"));
        });
    }
}
