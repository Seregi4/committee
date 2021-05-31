package com.levanov.model;

public class DisciplineScore {

    private int id;
    private int examinationDisciplinesId;
    private int score;
    private int userQuestionnaireId;

    public DisciplineScore() {
    }

    public DisciplineScore(int examinationDisciplinesId, int score, int userQuestionnaireId) {
        this.examinationDisciplinesId = examinationDisciplinesId;
        this.score = score;
        this.userQuestionnaireId = userQuestionnaireId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExaminationDisciplinesId() {
        return examinationDisciplinesId;
    }

    public void setExaminationDisciplinesId(int examinationDisciplinesId) {
        this.examinationDisciplinesId = examinationDisciplinesId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getUserQuestionnaireId() {
        return userQuestionnaireId;
    }

    public void setUserQuestionnaireId(int userQuestionnaireId) {
        this.userQuestionnaireId = userQuestionnaireId;
    }
}
