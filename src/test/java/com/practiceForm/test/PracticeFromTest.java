package com.practiceForm.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFromTest {

    @BeforeAll
    public static void setUpMain(){
        Configuration.browserSize = "1920x1080";
        Configuration.browser= "firefox";
        Configuration.baseUrl="https://demoqa.com";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void practiceFromTest() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        $("#firstName").val(Strings.FIRST_NAME);
        $("#lastName").val(Strings.LAST_NAME);
        $("#userEmail").val(Strings.EMAIL);
        $("#genterWrapper").find(byText(Strings.GENDER)).click();
        $("#userNumber").val(Strings.PHONE_NUMBER);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(Strings.DATE_OF_BIRTH_MONTH);
        $(".react-datepicker__year-select").selectOption(Strings.DATE_OF_BIRTH_YEAR);
        $(".react-datepicker__day--0" + Strings.DATE_OF_BIRTH_DAY + "").click();
        $("#subjectsInput").val(Strings.SUBJECT).pressEnter();
        $("#hobbiesWrapper").find(byText(Strings.HOBBIES)).click();
        $("#uploadPicture").uploadFile(new File(Strings.PICTURE_PATH + Strings.PICTURE_NAME));
        $("#currentAddress").val(Strings.CURRENT_ADDRESS);
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").find(byText(Strings.STATE)).click();
        $("#city").click();
        $("#stateCity-wrapper").find(byText(Strings.CITY)).click();
        $("#submit").click();

        //Проверка заполенных данных в мадальной форме
        $(".modal-content").shouldBe(visible).shouldHave(Condition.text(Strings.MODAL_FORM_TITLE));
        $(".modal-body").shouldHave(
                text(Strings.FIRST_NAME + " " + Strings.LAST_NAME),
                text(Strings.EMAIL),
                text(Strings.GENDER),
                text(Strings.PHONE_NUMBER),
                text(Strings.DATE_OF_BIRTH_DAY + " " + Strings.DATE_OF_BIRTH_MONTH + "," + Strings.DATE_OF_BIRTH_YEAR),
                text(Strings.SUBJECT),
                text(Strings.HOBBIES),
                text(Strings.PICTURE_NAME),
                text(Strings.CURRENT_ADDRESS),
                text(Strings.STATE+" "+Strings.CITY));
    }
}
