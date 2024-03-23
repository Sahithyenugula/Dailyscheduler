package com.schedule.service;


import com.schedule.dao.FoodDao;
import com.schedule.model.Item;
import com.schedule.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FoodScheduleService {

    @Autowired
    private FoodDao foodDao;
    //method name : insertMealData
    //return type String;
    //parameter datatype = Meal variable name:meal

    public String insertMealData(final Meal meal) throws SQLException {
            foodDao.insertDataToFoodScheduler(meal);
        return("SUCESSFULLY INSERTED TO FOOD_SCHEDULER");
    }
    //method name insertItemData
    //return type String;
    //parameter datatype = Item, variable = item
    public String insertItemData(final Item item) {
        foodDao.insertDataToGrocery(item);
        return("SUCESSFULLY INSERTED TO GROCERY");
    }
    //method name : updateMealData
    //return type String;
    //parameter datatype = Meal, variable = meal
    public String updateMealData(final Meal meal) throws SQLException {
        foodDao.updateDataToFoodScheduler(meal);
        return ("SUCESSFULLY UPDATED TO FOODSCHEDULER");
    }
    //method name getMealData
    //return type List<Meal>
    //parameter datatype String variable = inputdate

    public List<Meal> getMealData(final String inputdate) throws SQLException {
        Date date = java.sql.Date.valueOf(inputdate);
        List<Meal> mealsData = new ArrayList<>();
        mealsData = foodDao.getDataForDate(date);
        return mealsData;
    }
   public String updateItemData(final Item quantity) throws SQLException {
        foodDao.updateDataToGrocery(quantity);
        return ("SUCESSFULLY DONE");
   }
   public List<Item> getGroceryData() throws SQLException {
        List<Item> items = new ArrayList<>();
        items = foodDao.getDataForGrocery();
        return items;
   }
   public String deleteGrocery() throws SQLException{
        foodDao.deleteDataFromGrocery();
        return ("DONE");

   }
}
