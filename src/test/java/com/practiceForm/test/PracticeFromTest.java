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
//        Configuration.browser= "firefox";
        Configuration.baseUrl="https://demoqa.com";
//        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void practiceFromTest() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        $("#firstName").setValue(PracticeFormData.FIRST_NAME);
        $("#lastName").setValue(PracticeFormData.LAST_NAME);
        $("#userEmail").setValue(PracticeFormData.EMAIL);
        $("#genterWrapper").find(byText(PracticeFormData.GENDER)).click();
        $("#userNumber").setValue(PracticeFormData.PHONE_NUMBER);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(PracticeFormData.DATE_OF_BIRTH_MONTH);
        $(".react-datepicker__year-select").selectOption(PracticeFormData.DATE_OF_BIRTH_YEAR);
        $(".react-datepicker__day--0" + PracticeFormData.DATE_OF_BIRTH_DAY + "").click();
        $("#subjectsInput").setValue(PracticeFormData.SUBJECT).pressEnter();
        $("#hobbiesWrapper").find(byText(PracticeFormData.HOBBIES)).click();
        $("#uploadPicture").uploadFile(new File(PracticeFormData.PICTURE_PATH + PracticeFormData.PICTURE_NAME));
        $("#currentAddress").setValue(PracticeFormData.CURRENT_ADDRESS);
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").find(byText(PracticeFormData.STATE)).click();
        $("#city").click();
        $("#stateCity-wrapper").find(byText(PracticeFormData.CITY)).click();
        $("#submit").click();

        //Проверка заполенных данных в мадальной форме
        $(".modal-content").shouldBe(visible).shouldHave(Condition.text(PracticeFormData.MODAL_FORM_TITLE));
        $(".modal-body").shouldHave(
                text(PracticeFormData.FIRST_NAME + " " + PracticeFormData.LAST_NAME),
                text(PracticeFormData.EMAIL),
                text(PracticeFormData.GENDER),
                text(PracticeFormData.PHONE_NUMBER),
                text(PracticeFormData.DATE_OF_BIRTH_DAY + " " + PracticeFormData.DATE_OF_BIRTH_MONTH + "," + PracticeFormData.DATE_OF_BIRTH_YEAR),
                text(PracticeFormData.SUBJECT),
                text(PracticeFormData.HOBBIES),
                text(PracticeFormData.PICTURE_NAME),
                text(PracticeFormData.CURRENT_ADDRESS),
                text(PracticeFormData.STATE+" "+ PracticeFormData.CITY)
        );
    }
}
