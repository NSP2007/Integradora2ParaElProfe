package model;

import java.util.ArrayList;

public class Course {
    
    private String code;
    private String name;
    private String description;
    private int credits;
    private ArrayList<Teacher> teachers;
    
    public Course(String name, String description, int credits) {
        this.teachers = new ArrayList<>();
        this.code = generateCode();
        this.name = name;
        this.description = description;
        this.credits = credits;
    }

    public String getCode() {
        return code;
    }
    public String generateCode() {
        return hashCode()+"";
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }
    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Codigo:" + code + "\n"+
        "Nombre:" + name  + "\n"+ 
        "Descripcion:" + description  + "\n"+ 
        "Creditos:" + credits + "\n";
    }

}
