package model;

public class Teacher {
    
    private String id;
    private TypeOfId idType;
    private String name;
    private String lastName;
    private String email;
    private Course dictatedCourse;
    
    public Teacher(String id, TypeOfId idType, String name, String lastName, String email, Course dictatedcourse) {
        this.id = id;
        this.idType = idType;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.dictatedCourse = dictatedCourse;
    }

    public Course getDictatedCourses() {
        return dictatedCourse;
    }
    public void setDictatedCourses(Course dictatedCourse) {
        this.dictatedCourse = dictatedCourse;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public TypeOfId getIdType() {
        return idType;
    }
    public void setIdType(TypeOfId idType) {
        this.idType = idType;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Id:" + id + "\n"+
        "Nombre:" + name  + " " + lastName + "\n"+ 
        "Email:" + email  + "\n"+ 
        "Cursos Dictados:" + dictatedCourse + "\n";
    }

}
