package com.levanov.dao;

import com.levanov.model.SchoolCertificate;
import com.levanov.model.Specialty;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchoolCertificateDAO extends AbstractDAO {

    public List<SchoolCertificate> getAllSchoolCertificate() throws SQLException {

        List<SchoolCertificate> schoolCertificateList = new ArrayList<>();
        getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from school_certificate");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                SchoolCertificate schoolCertificate = new SchoolCertificate();

                schoolCertificate.setId(resultSet.getInt(1));
                schoolCertificate.setAverageScore(resultSet.getInt(2));
                schoolCertificate.setUserAccountID(resultSet.getInt(3));

                schoolCertificateList.add(schoolCertificate);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return schoolCertificateList;
    }

    public SchoolCertificate getSchoolCertificateById(int certificateId) {

        SchoolCertificate schoolCertificate = new SchoolCertificate();
        getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from school_certificate where id = ? ");
            preparedStatement.setInt(1, certificateId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                schoolCertificate.setId(resultSet.getInt(1));
                schoolCertificate.setAverageScore(resultSet.getInt(2));
                schoolCertificate.setUserAccountID(resultSet.getInt(3));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            closeConnection();
        }

        return schoolCertificate;
    }

    public SchoolCertificate getSchoolCertificateByUserId(int userId) {

        SchoolCertificate schoolCertificate = new SchoolCertificate();
        getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from school_certificate where user_account_id = ? ");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                schoolCertificate.setId(resultSet.getInt(1));
                schoolCertificate.setAverageScore(resultSet.getInt(2));
                schoolCertificate.setUserAccountID(resultSet.getInt(3));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            closeConnection();
        }

        return schoolCertificate;
    }

    public boolean insertSchoolCertificate(SchoolCertificate schoolCertificate) {


        boolean itWorked = false;
        getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into school_certificate (average_score, user_account_id ) values (?,?)");

            preparedStatement.setInt(1, schoolCertificate.getAverageScore());
            preparedStatement.setInt(2, schoolCertificate.getUserAccountID());


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
