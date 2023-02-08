package com.geekster.todo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@RestController
public class JokesController  {

    @GetMapping("/joke")
    public ResponseEntity<String> getJoke(@RequestParam String dateOfBirth) {

        if(validDateOfBirth(dateOfBirth)) {
            int age = getAge(dateOfBirth);
            return new ResponseEntity<>("Hey! this is a valid date and your age is- " + age, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Hey! this is not valid date", HttpStatus.BAD_REQUEST);
        }
    }

    public int getAge(String dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String myDate = LocalDate.parse(dateOfBirth, formatter).format(formatter2);
        LocalDate dob = LocalDate.parse(myDate);
        LocalDate curDate = LocalDate.now();
        return Period.between(dob, curDate).getYears();
    }

    private boolean validDateOfBirth(String dateOfBirth) {

        String regex = "^(1[0-2]|0[1-9])-(3[01]"
                + "|[12][0-9]|0[1-9])-[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((CharSequence)dateOfBirth);
        return matcher.matches();

    }
}
