package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Gita {
    private int id;
    private String destinazione;
    private LocalDate dataPartenza;
    private LocalDate dataRitorno;
    private Docente docente;
    private List<Classe> listaClassi;

    public Gita(){
        this.listaClassi = new ArrayList<>();
    }

    public List<Classe> getListaClassi() {
        return listaClassi;
    }

    public void setListaClassi(List<Classe> listaClassi) {
        this.listaClassi = listaClassi;
    }

    public void aggiungiClasse(Classe oClasse){
        if (!listaClassi.contains(oClasse)){
            listaClassi.add(oClasse);

        }
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setDestinazione(String destinazione){
        this.destinazione = destinazione;
    }
    public String getDestinazione(){
        return destinazione;
    }

    public void setDataPartenza(LocalDate dataPartenza){
        this.dataPartenza = dataPartenza;
    }

    public  LocalDate getDataPartenza(){
        return dataPartenza;
    }

    public void setDataRitorno(LocalDate dataRitorno){
        this.dataRitorno = dataRitorno;
    }

    public  LocalDate getDataRitorno(){
        return dataRitorno;
    }

    public int getDocenteId(){
        return docente.getId();
    }
    public Docente getDocente(){
        return docente;
    }
    public void setDocente(Docente docente){
        this.docente = docente;
    }

    public void setDocenteId(int id){
        this.docente = new Docente();
        docente.setId(id);
    }


}
