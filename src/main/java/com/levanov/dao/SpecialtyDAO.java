package com.levanov.dao;


import com.levanov.model.Specialty;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialtyDAO extends AbstractDAO {

    public List<Specialty> getAllSpecialtys() {

        List<Specialty> specialtyList = new ArrayList<>();
        getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(" select * from specialty"); // for Security
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Specialty specialty = new Specialty();

                specialty.setId(resultSet.getInt(1));
                specialty.setName(resultSet.getString(2));
                specialty.setFacultyID(resultSet.getInt(3));


                specialtyList.add(specialty);
            } //while
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return specialtyList;
    }

    public Specialty getSpecialtyByName(String specialtyName) {
        Specialty specialty = new Specialty();

        getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from specialty where name=?");
            preparedStatement.setString(1, specialtyName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                specialty.setId(resultSet.getInt(1));
                specialty.setName(resultSet.getString(2));
                specialty.setFacultyID(resultSet.getInt(3));

            }
            //return specialty;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {


            closeConnection();
        }


        return specialty;

    }

    public boolean insertSpecialty(Specialty specialty) {


        boolean itWorked = false;
        getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into specialty (name, faculty_id ) values (?,?)");

            preparedStatement.setString(1, specialty.getName());
            preparedStatement.setInt(2, specialty.getFacultyID());


            itWorked = preparedStatement.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return itWorked;
    }

    public List<Specialty> getSpecialtyByFacultyId(int facultyId) {
        List<Specialty> specialtyList = new ArrayList<>();

        getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from specialty where faculty_id=?");

            preparedStatement.setInt(1, facultyId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Specialty specialty = new Specialty();
                specialty.setId(resultSet.getInt(1));
                specialty.setName(resultSet.getString(2));
                specialty.setFacultyID(resultSet.getInt(3));
                specialtyList.add(specialty);
            }
            //return specialty;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {


            closeConnection();
        }


        return specialtyList;

    }

    public boolean updateSpecialty(Specialty specialty) {
        boolean isSpecialtyUpdate = false;


        getConnection();
        try {
            String SQL_UPDATE = "UPDATE  specialty SET  name = ? WHERE id =? AND faculty_id = ? ";


            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);


            ps.setString(1, specialty.getName());
            ps.setInt(2, specialty.getId());
            ps.setInt(3, specialty.getFacultyID());

            isSpecialtyUpdate = ps.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("SQL Error is =" + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return isSpecialtyUpdate;
    }

    public boolean deletesSpecialtyById(int id) {
        boolean isSpecialtyDeleted = false;
        getConnection();
        try {
            String SQL_INSERT = " DELETE from specialty where id= ? ";

            PreparedStatement ps = connection.prepareStatement(SQL_INSERT);
            ps.setInt(1, id);


            isSpecialtyDeleted = ps.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeConnection();
        }


        return isSpecialtyDeleted;

    }


}
