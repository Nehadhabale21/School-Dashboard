package com.school.dashboard.dto;

public class EducationalDetailRequest {
    private String degree;
    private String institution;
    private String yearOfPassing;
    private String gradeOrPercentage;

    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }
    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }
    public String getYearOfPassing() { return yearOfPassing; }
    public void setYearOfPassing(String yearOfPassing) { this.yearOfPassing = yearOfPassing; }
    public String getGradeOrPercentage() { return gradeOrPercentage; }
    public void setGradeOrPercentage(String gradeOrPercentage) { this.gradeOrPercentage = gradeOrPercentage; }
}
