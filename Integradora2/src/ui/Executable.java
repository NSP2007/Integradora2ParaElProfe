package ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import exceptions.RegisterErrorException;
import model.Controller;

public class Executable{

    private Scanner reader;
    private Controller controller;

    public static void main(String[] args) {
        Executable exe = new Executable();
        exe.menu();
    }

    public Executable(){
        initializer();
    }

    public void initializer(){
        reader = new Scanner(System.in);
        controller = new Controller();
    }

    /**
     * Descripcion: Muestra el menu de opciones del sistema
     */
    public void menu(){

        try{
        int option = 0;
        do {
            System.out.println("\n---Sistema de Gestion de Proyectos---");
            System.out.println("Elija una opcion: ");
            System.out.println("[1] Registrar Curso");
            System.out.println("[2] Registrar Profesor");
            System.out.println("[3] Registrar Proyecto");
            System.out.println("[4] Registrar Resultado");
            System.out.println("[5] Buscar Proyecto");
            System.out.println("[0] Salir");

            option = reader.nextInt();
            switch (option) {
                case 1:     
                    courseRegister();              
                    break;
                case 2:   
                    registerTeacher();
                    break;
                case 3:   
                    registerProject();
                    break;
                case 4:
                    registerResult();
                    break;
                case 5: 
                    searchProjects();
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
                }
            } while (option != 0);    
        }catch(InputMismatchException e){
            System.out.println("Entrada invalida");
        }
    }

    /**
     * Descripcion: El metodo courseResgister registra los cursos en el sistema
     */
    public void courseRegister(){
        boolean validation=true;
        String courseName="",courseDescription="";
        int courseCredits=0;
        
        System.out.println("---Registrar un nuevo Curso---");
        reader.nextLine();

        while (validation) {
            System.out.println("Escriba el nombre del Curso:");
            courseName=reader.nextLine();

            if (courseName.trim() != null) {
                validation=false;
            }else{
                System.out.println("Escribe por favor el nombre del curso");
                validation=true;
            } 
        }

        validation=true;
        while (validation) {
            System.out.println("Escriba la descripcion del Curso:");
            courseDescription=reader.nextLine();

            if (courseDescription.trim() != null) {
                validation=false;                
            }else{
                System.out.println("Escribe por favor la decripcion del curso");
                validation=true;
            } 
        }

        validation=true;
        while (validation) {
            System.out.println("Digita el numero de creditos");
            try {
                courseCredits=reader.nextInt();
                validation=false;
            }catch(InputMismatchException e) {
                System.out.println("Por favor digite un valor numerico");
            }            
        }

        try {
            if(controller.courseRegister(courseName, courseDescription, courseCredits)){
                System.out.println("Registro exitoso");
            }    
        } catch (RegisterErrorException e) {
            e.getMessage();
        }
    }

    /**
     * Descripcion: El metodo registerTeacher registrar profesores en el sistema
     */
    public void registerTeacher(){

        boolean validation=true;
        String id="",name="",lastName="",email="";
        int idType=0,coursesOption = 0;
        
        System.out.println("---Registrar un nuevo Maestr@---");
        reader.nextLine();

        while (validation) {
            System.out.println("Escriba el numero de documento:");
            id=reader.nextLine();

            if (id.trim() != null) {
                validation=false;
            }else{
                System.out.println("Escribe por favor el numero de documento");
                validation=true;
            } 
        }

        validation=true;
        while (validation) {
            System.out.println("Elija el tipo de documento:");
            try {
                controller.showIdTypes();
                do{
                idType=reader.nextInt();
                }while(idType != 1 && idType != 2 && idType != 3);
                controller.calculateTypeOfId(idType);
                validation=false;
            }catch(InputMismatchException e) {
                System.out.println("Por favor digite un valor numerico");
            }            
        }

        validation=true;
        while (validation) {
            System.out.println("Escriba el primer nombre:");
            name=reader.nextLine();

            if (name.trim() != null) {
                validation=false;                
            }else{
                System.out.println("Escribe por favor el nombre");
                validation=true;
            } 
        }

        validation=true;
        while (validation) {
            System.out.println("Escriba el apellido:");
            lastName=reader.nextLine();

            if (lastName.trim() != null) {
                validation=false;                
            }else{
                System.out.println("Escribe por favor el apellido");
                validation=true;
            } 
        }

        validation=true;
        while (validation) {
            System.out.println("Escriba el email:");
            email=reader.nextLine();

            if (email.trim() != null && email.contains("@")) {
                validation=false;                
            }else{
                System.out.println("Escribe por favor el email");
                validation=true;
            } 
        }

        validation=true;
        while (validation) {
            System.out.println("Elija el curso que dicta:");
            if(controller.showCourses().trim().length()==0){
                System.out.println("No hay cursos registrados");
                validation=false;
            }else{
                try {
                    controller.showCourses();
                    coursesOption=reader.nextInt();
                    controller.calculateCourse(coursesOption);
                    validation=false;
                }catch(InputMismatchException e) {
                    System.out.println("Por favor digite un valor numerico");
                }catch(IndexOutOfBoundsException e){
                    System.out.println("No es una opcion posible");
                }
            }
        }

        try {
            controller.teacherRegister(id, idType, name, lastName, email, controller.calculateCourse(coursesOption));    
        } catch (RegisterErrorException e) {
            e.getMessage();
        }
        
    }

    /**
     * Descripcion: el metodo registerProject regitra proyectos en el sistema
     */
    public void registerProject(){
        
        boolean validation=true;
        String name ="",description="",link="",period="";
        ArrayList<String> companies = new ArrayList<>(),keywords = new ArrayList<>();
        int course=0,projectType=0;

        System.out.println("---Registro de Proyecto---");

        while (validation) {
            System.out.println("Escriba el nombre:");
            name=reader.nextLine();

            if (name.trim() != null) {
                validation=false;                
            }else{
                System.out.println("Escribe por favor el nombre del proyecto");
                validation=true;
            } 
        }

        validation=true;
        while (validation) {
            System.out.println("Escriba la descripcion:");
            description=reader.nextLine();

            if (description.trim() != null) {
                validation=false;                
            }else{
                System.out.println("Escribe por favor una descripcion");
                validation=true;
            } 
        }

        validation=true;
        while (validation) {
            System.out.println("Ingrese el link:");
            link=reader.nextLine();

            if (link.trim() != null) {
                validation=false;                
            }else{
                System.out.println("Ingrese por favor el link del enunciado");
                validation=true;
            } 
        }

        validation=true;
        while (validation) {
            int year=0,semester=0;
            System.out.println("Ingrese el ano:");
            try {
                year=reader.nextInt();
            }catch(InputMismatchException e) {
                System.out.println("Por favor digite un valor numerico");
            }
            System.out.println("ingrese el periodo [1,2]");
            try {
                do{
                    semester=reader.nextInt();
                }while(semester != 1 && semester != 2);
            }catch(InputMismatchException e) {
                System.out.println("Por favor digite un valor numerico");
            }

            if(String.valueOf(year).trim().length() == 4){
                validation=false;
                period = String.valueOf(year) + "-" +String.valueOf(semester);
            }else{
                System.out.println("El ano es invalido");
            }
        }

        validation=true;
        int temp=0;
        String company="";
        while (validation) {
            
            System.out.println("Ingrese el numero de empresas beneficiarias:");
            try{
                temp=reader.nextInt();
                validation = false;
            }catch(InputMismatchException e){
                System.out.println("Ingrese un valor numerico");
            }
        }

        for (int i = 0; i < temp; i++) {
            boolean valid = true;
            while(valid){
                System.out.println("Ingrese el nombre de la compania:");
                company=reader.nextLine();
                if (company.trim() != null) {
                    companies.add(company);
                    valid=false;
                }else{
                    System.out.println("Escriba por favor el nombre de la compania");
                    validation=true;                    
                }
            }
        }

        validation=true;
        int temp1=0;
        String keyword="";
        while (validation) {
            
            System.out.println("Ingrese el numero de palabras clave:");
            try{
                temp1=reader.nextInt();
                validation = false;
            }catch(InputMismatchException e){
                System.out.println("Ingrese un valor numerico");
            }
        }

        for (int i = 0; i < temp1; i++) {
            boolean valid = true;
            while(valid){
                System.out.println("Ingrese la palabra clave:");
                keyword=reader.nextLine();
                if (keyword.trim() != null) {
                    keywords.add(keyword);
                    valid=false;
                }else{
                    System.out.println("Escriba por favor una palabra clave");
                    validation=true;                    
                }
            }
        }

        validation=true;
        while (validation) {
            System.out.println("Elija el curso al que se asocia:");
            try {
                controller.showCourses();
                course=reader.nextInt();
                controller.calculateCourse(course);
                validation=false;
            }catch(InputMismatchException e) {
                System.out.println("Por favor digite un valor numerico");
            }catch(IndexOutOfBoundsException e){
                System.out.println("No es una opcion posible");
            }
        }

        validation=true;
        while (validation) {
            System.out.println("Elija el curso que dicta:");
            try {
                controller.showProjectTypes();
                projectType=reader.nextInt();
                controller.calculateProjectType(projectType);
                validation=false;
            }catch(InputMismatchException e) {
                System.out.println("Por favor digite un valor numerico");
            }catch(IndexOutOfBoundsException e){
                System.out.println("No es una opcion posible");
            }
        }

        try {
            controller.projectRegister(companies, period, name, controller.calculateProjectType(projectType), keywords,description,link, controller.calculateCourse(course));    
        } catch (RegisterErrorException e) {
            e.getMessage();
        }
             
    }

    /**
     * Descripcion: El metodo registerResults registra resultados en el sistema dependiendo del tipo
     */
    public void registerResult(){

        boolean valid = true;
        int option = 0;
        while(valid){
            try {
                System.out.println("Elija el tipo de entregable [1] Repositorio [2] Documento");
                option = reader.nextInt();        
            } catch (InputMismatchException e) {
                System.out.println("Por favor escriba un valor numerico");
            }

        if(option == 1){
            registerRepository();
            valid = false;
        }else if(option == 2){
            registerDocument();
            valid = false;
        }else{
            System.out.println("Escoja una de las opciones");
        }
        
        }

    }
    /**
     * Descripcion: El metodo registerRepository registra resultados de tipo repositorio
     */
    public void registerRepository(){
        boolean validation = true;
        String group="",link="";
        int documents=0,fase=0,score=0,project=0;
        while (validation) {
            System.out.println("Escriba el numero de grupo:");
            int grupo = 0;
            try {
                grupo = reader.nextInt();
                group = "G"+String.valueOf(grupo);
                validation=false;
            }catch(InputMismatchException e) {
                System.out.println("Por favor digite un valor numerico");
            }            
        }

        while (validation) {
            System.out.println("Escriba el numero de documentos:");
            try {
                documents=reader.nextInt();
                validation=false;
            }catch(InputMismatchException e) {
                System.out.println("Por favor digite un valor numerico");
            }            
        }

        validation=true;
        while (validation) {
            System.out.println("Ingrese el link:");
            link=reader.nextLine();
            if (link.trim() != null) {
                validation=false;                
            }else{
                System.out.println("Ingrese el ink por favor");
                validation=true;
            } 
        }

        while (validation) {
            System.out.println("Escoja la fase del ciclo de vida:");
            try {
                controller.showFaseTypes();
                fase=reader.nextInt();
                controller.calculateFaseType(fase);
                validation=false;
            }catch(InputMismatchException e) {
                System.out.println("Por favor digite un valor numerico");
            }            
        }

        while (validation) {
            System.out.println("Escoja el proyecto asociado:");
            try {
                controller.showProjectsById(link);
                project=reader.nextInt();
                validation=false;
            }catch(InputMismatchException e) {
                System.out.println("Por favor digite un valor numerico");
            }            
        }

        while (validation) {
            System.out.println("Escriba la nota de la entrega:");
            try {
                controller.showProjects();
                score=reader.nextInt();
                validation=false;
            }catch(InputMismatchException e) {
                System.out.println("Por favor digite un valor numerico");
            }            
        }

        controller.registerRepository(project,group, controller.calculateFaseType(fase),link, documents,score);
    }

    /**
     * Descripcion: El metodo registerDocument registra entregrables de tipo documento
     */
    public void registerDocument(){
        boolean validation = true;
        String group="",link="";
        int fase=0,score=0,project=0;
        while (validation) {
            System.out.println("Escriba el numero de grupo:");
            int grupo = 0;
            try {
                grupo = reader.nextInt();
                group = "G"+String.valueOf(grupo);
                validation=false;
            }catch(InputMismatchException e) {
                System.out.println("Por favor digite un valor numerico");
            }            
        }

        validation=true;
        while (validation) {
            System.out.println("Ingrese el link:");
            link=reader.nextLine();
            if (link.trim() != null) {
                validation=false;                
            }else{
                System.out.println("Ingrese el ink por favor");
                validation=true;
            } 
        }

        while (validation) {
            System.out.println("Escoja la fase del ciclo de vida:");
            try {
                controller.showFaseTypes();
                fase=reader.nextInt();
                controller.calculateFaseType(fase);
                validation=false;
            }catch(InputMismatchException e) {
                System.out.println("Por favor digite un valor numerico");
            }            
        }

        while (validation) {
            System.out.println("Escoja el proyecto asociado:");
            try {
                controller.showProjectsById(link);
                project=reader.nextInt();
                validation=false;
            }catch(InputMismatchException e) {
                System.out.println("Por favor digite un valor numerico");
            }            
        }

        while (validation) {
            System.out.println("Escriba la nota de la entrega:");
            try {
                controller.showProjects();
                score=reader.nextInt();
                validation=false;
            }catch(InputMismatchException e) {
                System.out.println("Por favor digite un valor numerico");
            }            
        }

        controller.registerDocument(project,group, controller.calculateFaseType(fase),link,score);
    }
    /**
     * Descripcion: El metodo searchProjects le pregunta al usuario como quiere buscar los proyetos
     */
    public void searchProjects(){
        System.out.println("Desea buscar [1] Por ID o [2] Por resultados no asignados");
        try {
            int option = reader.nextInt();
            if(option == 1){
                searchById();
            }else if(option == 2){
                searchByNoResults();
            }else{  
                System.out.println("Por favor escoja una opcion");
            }    
        } catch (InputMismatchException e) {
            System.out.println("Por favor escriba una valor numerico");
        }
        
        
    }

    /**
     * Descripcion: El metodo searchById le pregunta al usuario el id del proyecto y lo muestra
     */
    public void searchById(){

        System.out.println("Escriba el id del proyecto que busca");
        String id = reader.nextLine();
        System.out.println(controller.showProjectsById(id));

    }

    /**
     * Descripcion: El metodo searchByNoResults muestra los proyectos sin resultados asignados
     */
    public void searchByNoResults(){
        System.out.println(controller.showProjectsWithNoResults());
    }

}

