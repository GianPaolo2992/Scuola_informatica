package service;

import entity.Docente;
import entity.Gita;
import repository.GitaRepository;

import java.time.LocalDate;
import java.util.List;

public class GitaService {
    private GitaRepository gitaRepository = new GitaRepository();
    private Gita oGita = new Gita();

    public void createGita(String destinazione, LocalDate dataPartenza, LocalDate dataRitorno, Docente oDocente) {
        oGita.setDestinazione(destinazione);
        oGita.setDataPartenza(dataPartenza);
        oGita.setDataRitorno(dataRitorno);
        oGita.setDocente(oDocente);

        gitaRepository.createGita(oGita);

    }

    public List<Gita> readGita(){
        return gitaRepository.readGite();
    }
    public void updateGita(int id,String destinazione,LocalDate dataPartenza,LocalDate dataRitorno,int id_docente){
        oGita.setId(id);
        oGita.setDestinazione(destinazione);
        oGita.setDataPartenza(dataPartenza);
        oGita.setDataRitorno(dataRitorno);
        oGita.setDocenteId(id_docente);
        gitaRepository.updateGite(oGita);
    }

    public  void deleteGita(int id){
        oGita.setId(id);
        gitaRepository.deleteGite(oGita);
    }

}

