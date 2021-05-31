package com.levanov.service;


import com.levanov.model.Faculty;

public class BriefDescription {

    public String getBrief(Faculty faculty) {
        String[] mass = faculty.getDescription().split(".!?");
        return mass[0] + " " + mass[1];

    }
}