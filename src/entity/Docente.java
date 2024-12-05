package entity;

public class Docente {
    private int id;

    private String name;
    private String surname;

    private String schoolSubject;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void  setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void  setSurname(String surname){
        this.surname = surname;
    }

    public String getSurname(){
        return surname;
    }

    public void setSchoolSubject(String schoolSubject ){
        this.schoolSubject = schoolSubject;
    }

    public String getSchoolSubject(){
        return schoolSubject;
    }
}
