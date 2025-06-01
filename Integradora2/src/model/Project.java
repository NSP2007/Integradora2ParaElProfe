package model;

import java.util.ArrayList;

public class Project {
    
    private ArrayList<String> beneficiaryCompanies;
    private String period;
    private String projectName;
    private TypeOfProject projectType;
    private ArrayList<String> keywords;
    private String description;
    private String projectLink;
    private Result[] results;
    private Course relatedCourse;
    private String id;
    
    public Project(ArrayList<String> beneficiaryCompanies, String period, String projectName, TypeOfProject projectType,
            ArrayList<String> keywords, String description, String projectLink, Course relatedCourse) {
        this.results = new Result[3];
        this.beneficiaryCompanies = beneficiaryCompanies;
        this.period = period;
        this.projectName = projectName;
        this.projectType = projectType;
        this.keywords = keywords;
        this.description = description;
        this.projectLink = projectLink;
        this.relatedCourse = relatedCourse;
        this.id=generateId();
    }
    
    public String getId() {
        return id;
    }
    public String generateId() {
        return hashCode()+"";
    }

    public Course getRelatedCourse() {
        return relatedCourse;
    }
    public void setRelatedCourse(Course relatedCourse) {
        this.relatedCourse = relatedCourse;
    }

    public ArrayList<String> getBeneficiaryCompanies() {
        return beneficiaryCompanies;
    }
    public void setBeneficiaryCompanies(ArrayList<String> beneficiaryCompanies) {
        this.beneficiaryCompanies = beneficiaryCompanies;
    }

    public String getPeriod() {
        return period;
    }
    public void setPeriod(String period) {
        this.period = period;
    }

    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public TypeOfProject getProjectType() {
        return projectType;
    }
    public void setProjectType(TypeOfProject projectType) {
        this.projectType = projectType;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }
    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectLink() {
        return projectLink;
    }
    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public Result[] getResults() {
        return results;
    }
    public void setResults(Result[] results) {
        this.results = results;
    }
    public void addResult(Result result){
        for (int i = 0; i < results.length; i++) {
            if(results[i]==null){
                results[i]=result;
            }
        }
    }
    public String showResults(){

        String list = "";

        for (int i = 0; i < results.length; i++) {
            list += "["+(i+1)+"]"+results[i]+"\n";
        }

        return list+"\n";
    }
}