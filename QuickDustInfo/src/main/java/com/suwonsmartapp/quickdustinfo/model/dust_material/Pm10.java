package com.suwonsmartapp.quickdustinfo.model.dust_material;

public class Pm10 {

    private String grade;
    private String value;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pm10{" +
                "grade='" + grade + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}