package com.schedule.service;


import com.schedule.dao.FoodDao;
import com.schedule.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Component
public class FoodScheduleService {

    @Autowired
    private FoodDao foodDao;

    public String insertMealData(final Meal meal) throws SQLException {

            foodDao.insertDataToFoodScheduler(meal);

        return("SUCESSFULLY INSERTED TO DATABASE");
    }

    public List<Meal> getMealData(final String inputdate) throws SQLException {
        Date date = java.sql.Date.valueOf(inputdate);
        return foodDao.getDataForDate(date);


    }

}
