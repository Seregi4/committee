package com.levanov.dao;

import com.levanov.model.SchoolCertificate;
import com.levanov.model.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatementDAO extends AbstractDAO {

    public Statement getStatement() {
        Statement statement = new Statement();
        getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from statement");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                statement.setId(resultSet.getInt(1));
                statement.setAverageScore(resultSet.getInt(2));
                statement.setEnrolled(resultSet.getInt(3));


            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return statement;

    }

    public Statement getStatementById(int id) {
        Statement statement = new Statement();
        getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from statement where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                statement.setId(resultSet.getInt(1));
                statement.setAverageScore(resultSet.getInt(2));
                statement.setEnrolled(resultSet.getInt(3));


            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return statement;
    }

    public Statement getStatementByAverageScore(int score) {
        Statement statement = new Statement();
        getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from statement where average_score = ?");
            preparedStatement.setInt(1, score);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                statement.setId(resultSet.getInt(1));
                statement.setAverageScore(resultSet.getInt(2));
                statement.setEnrolled(resultSet.getInt(3));


            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return statement;
    }

    public boolean insertStatement(Statement statement) {


        boolean itWorked = false;
        getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into statement (average_score, enrolled ) values (?,?)");

            preparedStatement.setInt(1, statement.getAverageScore());
            preparedStatement.setInt(2, statement.getEnrolled());


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
