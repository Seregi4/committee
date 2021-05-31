package com.levanov.dao;


import com.levanov.model.NumberOfSeats;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NumberOfSeatsDAO extends AbstractDAO {
    public List<NumberOfSeats> getNumberOfSeats() {
        List<NumberOfSeats> numberOfSeatsList = new ArrayList<>();
        getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from number_of_seats");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                NumberOfSeats numberOfSeats = new NumberOfSeats();

                numberOfSeats.setId(resultSet.getInt(1));
                numberOfSeats.setSpecialtyID(resultSet.getInt(2));
                numberOfSeats.setCount(resultSet.getInt(3));
                numberOfSeatsList.add(numberOfSeats);

            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return numberOfSeatsList;
    }

    public NumberOfSeats getNumberOfSeatsById(int id) {
        NumberOfSeats numberOfSeats = new NumberOfSeats();
        getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from number_of_seats where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                numberOfSeats.setId(resultSet.getInt(1));
                numberOfSeats.setSpecialtyID(resultSet.getInt(2));
                numberOfSeats.setCount(resultSet.getInt(3));

            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return numberOfSeats;
    }

    public NumberOfSeats getNumberOfSeatsBySpecialtyId(int id) {
        NumberOfSeats numberOfSeats = new NumberOfSeats();
        getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from number_of_seats where specialty_id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                numberOfSeats.setId(resultSet.getInt(1));
                numberOfSeats.setSpecialtyID(resultSet.getInt(2));
                numberOfSeats.setCount(resultSet.getInt(3));

            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return numberOfSeats;
    }

    public boolean insertNumberOfSeats(NumberOfSeats numberOfSeats) {


        boolean itWorked = false;
        getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into discipline_score (specialty_id, count ) values (?,?)");

            preparedStatement.setInt(1, numberOfSeats.getSpecialtyID());
            preparedStatement.setInt(2, numberOfSeats.getCount());


            itWorked = preparedStatement.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return itWorked;
    }
}
