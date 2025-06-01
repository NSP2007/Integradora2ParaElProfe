package model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import exceptions.RegisterErrorException;
import model.Teacher;

public class Controller {
    
    private ArrayList<Course> courses;
    private ArrayList<Teacher> teachers;
    private ArrayList<Project> projects;

    public Controller(){

        courses = new ArrayList<>();
        teachers = new ArrayList<>();
        projects = new ArrayList<>();
        testData();
    }

     public void saveToFile() throws FileNotFoundException, IOException {

        File CoursesDataBase = new File("data\\Courses.txt");
        CoursesDataBase.createNewFile();
        File ProjectsDataBase = new File("data\\Projects.txt");
        ProjectsDataBase.createNewFile();
        File TeachersDataBase = new File("data\\Teachers.txt");
        TeachersDataBase.createNewFile();

        ObjectOutputStream course = new ObjectOutputStream(new FileOutputStream(CoursesDataBase));
        ObjectOutputStream project = new ObjectOutputStream(new FileOutputStream(ProjectsDataBase));
        ObjectOutputStream teacher = new ObjectOutputStream(new FileOutputStream(TeachersDataBase));

        for (Course c : courses) {
            course.writeObject(c);
        }
        for (Project p : projects) {
            project.writeObject(p);
        }
        for (Teacher t : teachers) {
            teacher.writeObject(t);
        }

        course.flush();
        course.close();
        project.flush();
        project.close();
        teacher.flush();
        teacher.close();

    }

    
    public void loadFromFile() throws ClassNotFoundException, IOException, EOFException {

        File CoursesDataBase = new File("data\\Courses.txt");
        File ProjectsDataBase = new File("data\\Projects.txt");
        File TeachersDataBase = new File("data\\Teachers.txt");

        ObjectInputStream course = new ObjectInputStream(new FileInputStream(CoursesDataBase));
        ObjectInputStream project = new ObjectInputStream(new FileInputStream(ProjectsDataBase));
        ObjectInputStream teacher = new ObjectInputStream(new FileInputStream(TeachersDataBase));

        Course temp = null;
        Project temp1 = null;
        Teacher temp2 = null;

        while ((temp = (Course) course.readObject()) != null) {
            courses.add(temp);
        }
        while ((temp1 = (Project) project.readObject()) != null) {
            projects.add(temp1);
        }
        while ((temp2 = (Teacher) teacher.readObject()) != null) {
            teachers.add(temp2);
        }

        course.close();
        project.close();
        teacher.close();

    }

    public void testData(){
        Course newCourse = new Course("APO", "Algoritmos y Programacion", 3);
        courses.add(newCourse);
        teachers.add(new Teacher("1234567890", TypeOfId.CEDULA_DE_CIUDADANIA, "Juan", "Ito", "JuanitoPro@gmail.com", newCourse));
        projects.add(new Project(null, "2025-2", "ProyectoFinal", TypeOfProject.PROYECTO_FINAL, null, "Proyecto final de semestre", "https://saman.icesi.edu.co/", newCourse));
    }

    public void registerRepository(int projectId,String group, TypeOfFase fase, String link, int documents,int score){
        projects.get(projectId).addResult(new Result(group,new Repository(fase,link, documents),score));
    }

    public void registerDocument(int projectId,String group, TypeOfFase fase, String link,int score){
        projects.get(projectId).addResult(new Result(group,new Document(fase,link),score));
    }

    public boolean projectRegister(ArrayList<String> beneficiaryCompanies, String period, String projectName, TypeOfProject projectType,
    ArrayList<String> keywords, String description, String projectLink, Course relatedCourse) throws RegisterErrorException{

        boolean validation = false;

        Project newProject = new Project(beneficiaryCompanies,period,projectName,projectType,keywords,description,projectLink,relatedCourse);
        boolean add = projects.add(newProject);

        if(!add){
            throw new RegisterErrorException();
        }else{
            validation = true;
        }

        return validation;
    }

    public boolean teacherRegister(String id, int idType, String name, String lastName, String email, Course dictatedCourse) throws RegisterErrorException{

        boolean validation = false;

        Teacher newTeacher = new Teacher(id, calculateTypeOfId(idType), name, lastName, email, dictatedCourse);
        boolean add = teachers.add(newTeacher);

        if(!add){
            throw new RegisterErrorException();
        }else{
            validation = true;
        }

        return validation;
    }

    public boolean courseRegister(String name, String description,int credits) throws RegisterErrorException{
        
        boolean validation = false;

        Course newCourse = new Course(name, description, credits);
        boolean add = courses.add(newCourse);

        if(!add){
            throw new RegisterErrorException();
        }else{
            validation = true;
        }

        return validation;
    }

    public String showCourses(){

        String coursesList = "";

        for (int i = 0; i < courses.size(); i++) {
            coursesList += "["+i+"]"+courses.get(i).getCode()+"|"+courses.get(i).getName()+"\n";
        }

        return coursesList+"\n";
    }

    public String showProjects(){
        String list ="";

        for (int i = 0; i < projects.size(); i++) {
            list += "["+i+"]"+projects.get(i).getId()+"|"+projects.get(i).getProjectName()+"\n";
            
        }

        return list;
    }

    public String showProjectsWithNoResults(){
        
        String list="";
        for(Project project : projects) {
            if(project.getResults().length == 0){
                list += project.getId()+"|"+project.getProjectName();
            }
        }
        return list;
    }

    public String showProjectsById(String id){

        String projectsList = "";

        for (int i = 0; i < projects.size(); i++) {
            if(id == projects.get(i).getId()){
                projectsList += "["+i+"]"+projects.get(i).getId()+"|"+projects.get(i).getProjectName()+"|"+projects.get(i).showResults()+"\n";
            }
        }

        return projectsList+"\n";
    }

    public String showIdTypes(){

        String list = "";
        TypeOfId[] idTypes = TypeOfId.values();

        for (int i = 0; i < idTypes.length; i++) {
            list += "["+(i+1)+"]"+idTypes[i]+"\n";
        }

        return list+"\n";
    }

    public String showFaseTypes(){

        String list = "";
        TypeOfFase[] faseTypes = TypeOfFase.values();

        for (int i = 0; i < faseTypes.length; i++) {
            list += "["+(i+1)+"]"+faseTypes[i]+"\n";
        }

        return list+"\n";
    }

    public TypeOfFase calculateFaseType(int option){

         TypeOfFase temp = TypeOfFase.ANALISIS_DE_REQUERIMIENTOS;
        switch (option) {
            case 1:
                temp = TypeOfFase.ANALISIS_DE_REQUERIMIENTOS;
                break;
            case 2:
                temp = TypeOfFase.CONSTRUCCION;
                break;
            case 3:
                temp = TypeOfFase.DESPLIEGUE;
            case 4:
                temp = TypeOfFase.DISENO;
                break;
            case 5:
                temp = TypeOfFase.PRUEBAS;
            default:
                temp = TypeOfFase.ANALISIS_DE_REQUERIMIENTOS;
                break;
        }

        return temp;

    }

    public String showProjectTypes(){

        String list = "";
        TypeOfProject[] projectTypes = TypeOfProject.values();

        for (int i = 0; i < projectTypes.length; i++) {
            list += "["+(i+1)+"]"+projectTypes[i]+"\n";
        }

        return list+"\n";
    }

    public TypeOfProject calculateProjectType(int option){

         TypeOfProject temp = TypeOfProject.PROYECTO_DE_CURSO;
        switch (option) {
            case 1:
                temp = TypeOfProject.PROYECTO_DE_CURSO;
                break;
            case 2:
                temp = TypeOfProject.PROYECTO_FINAL;
                break;
            case 3:
                temp = TypeOfProject.TAREA_INTEGRADORA;
            default:
                temp = TypeOfProject.PROYECTO_DE_CURSO;
                break;
        }

        return temp;

    }

    public Course calculateCourse(int option){

        Course course = courses.get(option-1);

        return course;
    }

    public TypeOfId calculateTypeOfId(int option){

         TypeOfId temp = TypeOfId.CEDULA_DE_CIUDADANIA;
        switch (option) {
            case 1:
                temp = TypeOfId.CEDULA_DE_CIUDADANIA;
                break;
            case 2:
                temp = TypeOfId.CEDULA_DE_EXTRANJERIA;
                break;
            case 3:
                temp = TypeOfId.PASAPORTE;
            default:
                temp = TypeOfId.CEDULA_DE_CIUDADANIA;
                break;
        }

        return temp;

    }



}
