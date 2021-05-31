package com.levanov.dao;

import com.levanov.model.DisciplineScore;
import com.levanov.model.SchoolCertificate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplineScoreDAO extends AbstractDAO {
    public List<DisciplineScore> getDisciplineScoreList() {
        List<DisciplineScore> disciplineScoreList = new ArrayList<>();
        getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from discipline_score");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                DisciplineScore disciplineScore = new DisciplineScore();

                disciplineScore.setId(resultSet.getInt(1));
                disciplineScore.setExaminationDisciplinesId(resultSet.getInt(2));
                disciplineScore.setScore(resultSet.getInt(3));
                disciplineScore.setUserQuestionnaireId(resultSet.getInt(4));

                disciplineScoreList.add(disciplineScore);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return disciplineScoreList;
    }

    public List<DisciplineScore> getDisciplineScoreListById(int id) {

        List<DisciplineScore> disciplineScoreList = new ArrayList<>();
        getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from discipline_score where id = ? ");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DisciplineScore disciplineScore = new DisciplineScore();

                disciplineScore.setId(resultSet.getInt(1));
                disciplineScore.setExaminationDisciplinesId(resultSet.getInt(2));
                disciplineScore.setScore(resultSet.getInt(3));
                disciplineScore.setUserQuestionnaireId(resultSet.getInt(4));

                disciplineScoreList.add(disciplineScore);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            closeConnection();
        }

        return disciplineScoreList;
    }

    public List<DisciplineScore> getDisciplineScoreListByExamenationDisciplinID(int id) {

        List<DisciplineScore> disciplineScoreList = new ArrayList<>();
        getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from discipline_score where examination_disciplines_id = ? ");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DisciplineScore disciplineScore = new DisciplineScore();

                disciplineScore.setId(resultSet.getInt(1));
                disciplineScore.setExaminationDisciplinesId(resultSet.getInt(2));
                disciplineScore.setScore(resultSet.getInt(3));
                disciplineScore.setUserQuestionnaireId(resultSet.getInt(4));

                disciplineScoreList.add(disciplineScore);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            closeConnection();
        }

        return disciplineScoreList;
    }

    public List<DisciplineScore> getDisciplineScoreListUserQuestionnaireID(int id) {

        List<DisciplineScore> disciplineScoreList = new ArrayList<>();
        getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from discipline_score where user_questionnaire_id = ? ");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DisciplineScore disciplineScore = new DisciplineScore();

                disciplineScore.setId(resultSet.getInt(1));
                disciplineScore.setExaminationDisciplinesId(resultSet.getInt(2));
                disciplineScore.setScore(resultSet.getInt(3));
                disciplineScore.setUserQuestionnaireId(resultSet.getInt(4));

                disciplineScoreList.add(disciplineScore);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            closeConnection();
        }

        return disciplineScoreList;
    }

    public boolean insertDisceplineScore(DisciplineScore disciplineScore) {


        boolean itWorked = false;
        getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into discipline_score (examination_disciplines_id, score,user_questionnaire_id ) values (?,?,?)");

            preparedStatement.setInt(1, disciplineScore.getExaminationDisciplinesId());
            preparedStatement.setInt(2, disciplineScore.getScore());
            preparedStatement.setInt(3, disciplineScore.getUserQuestionnaireId());


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
