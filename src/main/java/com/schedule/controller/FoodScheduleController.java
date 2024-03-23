package com.schedule.controller;


import com.schedule.model.Meal;
import com.schedule.model.Item;
import com.schedule.service.FoodScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.sql.Date;
import java.sql.SQLException;

@RestController
@RequestMapping("/scheduler")
public class FoodScheduleController {

    @Autowired
    private FoodScheduleService foodScheduleService;

    @PostMapping("/insert/grocery")
    private String insertGroceryData(@RequestBody Item item) {
        return foodScheduleService.insertItemData(item);
    }
    @PostMapping("/insert/food")
    private String insertFoodData(@RequestBody Meal meal) throws SQLException {
        return foodScheduleService.insertMealData(meal);
    }

    @PutMapping("/update/food")
    private String updateFoodData(@RequestBody Meal meal) throws SQLException {
        return foodScheduleService.updateMealData(meal);
    }
    @PutMapping("/update/grocery")
    private String updateGroceryData(@RequestBody Item quantity) throws SQLException{
        return foodScheduleService.updateItemData(quantity);
    }
    @GetMapping(value = "/select/food")
    private List<Meal> getFooddata(@RequestParam String date)throws SQLException {
        return foodScheduleService.getMealData(date);
    }
    @GetMapping("/select/items")
    private List<Item> getgrocerydata() throws SQLException {
        return foodScheduleService.getGroceryData();
    }
    @DeleteMapping("/delete/grocery")
    private String deleteGrocery() throws SQLException {
        return foodScheduleService.deleteGrocery();
    }
}
