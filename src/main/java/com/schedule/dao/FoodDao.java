package com.schedule.dao;

import com.schedule.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class FoodDao {

    @Autowired
    DataSource dataSource;

    String insertQuery = "Insert into schedule.food_schedule(date, meal_type, meal) " +
            "values (?,?,?);";
    String getQuery = "select * from schedule.food_schedule where date = ?";

    public void insertDataToFoodScheduler(Meal meal) throws SQLException {
        PreparedStatement preparedStmt = dataSource.getConnection().prepareStatement(insertQuery);
        preparedStmt.setDate   (1,meal.getDate());
        preparedStmt.setString (2,meal.getMealType());
        preparedStmt.setString (3,meal.getMeal());

        preparedStmt.execute();

    }

    public List<Meal> getDataForDate(Date date) throws SQLException {
        PreparedStatement preparedStmt = dataSource.getConnection().prepareStatement(getQuery);
        preparedStmt.setDate   (1,date);
        ResultSet resultSet = preparedStmt.executeQuery();
        List<Meal> responseMeal = new ArrayList<>();
        while(resultSet.next()){
            Meal meal = new Meal();
            meal.setDate(resultSet.getDate(1));
            meal.setMealType(resultSet.getString(2));
            meal.setMeal(resultSet.getString(3));
            responseMeal.add(meal);

        }
        return responseMeal;





    }




}
