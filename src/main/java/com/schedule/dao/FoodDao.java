package com.schedule.dao;

import com.schedule.model.Item;
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

    String insertQuery = "Insert into schedule.food_schedule(date, meal_type, meal) values (?,?,?);";
    String insertGrocery = "Insert into schedule.grocery(item, quantity) values (?,?)";

    String getQuery = "select * from schedule.food_schedule where date = ?";

    String updateFoodSchedule ="UPDATE schedule.food_schedule SET meal = ? WHERE meal_type = ? and date = ?";

    String updateGrocery = "UPDATE schedule.grocery SET quantity = ? WHERE item = ?";

    String getGrocery = "select * from schedule.grocery";

    String deleteall = "DELETE FROM schedule.grocery";

    public void insertDataToFoodScheduler(Meal meal) throws SQLException {
        PreparedStatement preparedStmt = dataSource.getConnection().prepareStatement(insertQuery);
        preparedStmt.setDate   (1,meal.getDate());
        preparedStmt.setString (2,meal.getMealType());
        preparedStmt.setString (3,meal.getMeal());

        preparedStmt.execute();
    }

    public void updateDataToFoodScheduler(Meal meal) throws SQLException {
        PreparedStatement preparedStmt = dataSource.getConnection().prepareStatement(updateFoodSchedule);
        preparedStmt.setString(1,meal.getMeal());
        preparedStmt.setString(2, meal.getMealType());
        preparedStmt.setDate(3,meal.getDate());

        preparedStmt.execute();
    }
    public void updateDataToGrocery(Item quantity) throws SQLException {
        PreparedStatement preparedStmt = dataSource.getConnection().prepareStatement(updateGrocery);
        preparedStmt.setInt(1, quantity.getQuantity());
        preparedStmt.setString(2, quantity.getItemName());

        preparedStmt.execute();
    }
    public void insertDataToGrocery(Item item) {

        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(insertGrocery);
            preparedStatement.setString (1, item.getItemName());
            preparedStatement.setInt(2,item.getQuantity());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    public List<Item> getDataForGrocery() throws SQLException {
        PreparedStatement ps = dataSource.getConnection().prepareStatement(getGrocery);
        ResultSet rs = ps.executeQuery();
        List<Item> responseItem = new ArrayList<>();
        while(rs.next()){
            Item item = new Item();
            item.setItemName(rs.getString(1));
            item.setQuantity(rs.getInt(2));
            responseItem.add(item);
        }
        return responseItem;
    }
    public void deleteDataFromGrocery() throws SQLException {
        PreparedStatement ps = dataSource.getConnection().prepareStatement(deleteall);
        ps.execute();
        }
    }




