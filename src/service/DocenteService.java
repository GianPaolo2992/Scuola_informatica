package service;
import entity.Docente;
import repository.DocenteRepository;

import java.util.List;

public class DocenteService {
    private DocenteRepository docenteRepository = new DocenteRepository();
    Docente oDocente = new Docente();

    public void createDocente(String name,String surname,String schoolSubject){
        oDocente.setName(name);
        oDocente.setSurname(surname);
        oDocente.setSchoolSubject(schoolSubject);
        docenteRepository.createDocente(oDocente);
    }

    public List<Docente> readDocente( ){
        return docenteRepository.readDocente();
    }

    public void updateDocente(int id, String name, String surname, String schoolSubject){
        oDocente.setId(id);
        oDocente.setName(name);
        oDocente.setSurname(surname);
        oDocente.setSchoolSubject(schoolSubject);
        docenteRepository.updateDocente(oDocente);
    }

    public void deleteDocente(int id){
        oDocente.setId(id);
        docenteRepository.deleteDocente(oDocente);
    }
}
