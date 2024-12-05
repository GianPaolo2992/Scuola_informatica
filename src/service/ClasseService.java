package service;

import entity.Classe;
import entity.Docente;
import entity.Gita;
import repository.ClasseRepository;

import java.util.List;

public class ClasseService {

    private ClasseRepository classeRepository = new ClasseRepository();
    Classe oClasse = new Classe();
    Gita oGita = new Gita();

    public void createClasse(int id,String sezione, Docente oDocente){

        oClasse.setId(id);
        oClasse.setSezione(sezione);
        oClasse.setDocente(oDocente);
        classeRepository.createClasse(oClasse);

    }

    public List<Classe> readClasse(){


        return classeRepository.readClasse();

    }

    public void updateClasse(int id,String sezione,int id_docente){

        oClasse.setId(id);
        oClasse.setSezione(sezione);
        oClasse.setDocenteId(id_docente);

        classeRepository.updateClasse(oClasse);
    }

    public void deleteClasse(int id){
        oClasse.setId(id);
        classeRepository.deleteClasse(oClasse);
    }

    public void associaClasseAGita(Classe oClasse, Gita oGita){
        classeRepository.associaClasseAGita(oClasse,oGita);
    }
    public List<Classe> giteProgrammate(){
        return classeRepository.giteProgrammate();
    }
    public void deleteGitaProgrammata(int classeId, String destinazione){
        oClasse.setId(classeId);
        oGita.setDestinazione(destinazione);
        classeRepository.deleteClasseGita(classeId,destinazione);


    }

}
