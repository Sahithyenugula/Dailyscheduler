package com.schedule.controller;


import com.schedule.model.Meal;
import com.schedule.service.FoodScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.sql.Date;
import java.sql.SQLException;

@RestController
@RequestMapping("/scheduler")
public class FoodScheduleController {

    @Autowired
    private FoodScheduleService foodScheduleService;

    @PostMapping("/insert/food")
    private String insertFoodData(@RequestBody Meal meal) throws SQLException {

        return foodScheduleService.insertMealData(meal);
    }

    @GetMapping(value = "/select/food")
    private List<Meal> getFooddata(@RequestParam String date)throws SQLException {

        return foodScheduleService.getMealData(date);


    }


}
