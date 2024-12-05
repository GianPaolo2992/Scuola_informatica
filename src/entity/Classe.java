package entity;

import java.util.ArrayList;
import java.util.List;

public class Classe {
    private int id;
    private String sezione;
    private Docente docente;
    private List<Gita> listaGite;


    public Classe(){
        this.listaGite = new ArrayList<>();
    }

    public List<Gita> getListaGite() {
        return listaGite;
    }
    public void aggiungiGita(Gita oGita){
        if (!listaGite.contains(oGita)){
            listaGite.add(oGita);

        }
    }

    public void setListaGite(List<Gita> listaGite) {
        this.listaGite = listaGite;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setSezione(String sezione){
        this.sezione = sezione;
    }
    public String getSezione(){
        return sezione;
    }

    public void setDocente(Docente docente){
        this.docente = docente;
    }

    public String getDocenteName(){
        return docente.getName();
    }
    public int getDocenteId(){
        return docente.getId();
    }
    public void setDocenteId(int id){
        this.docente = new Docente();
        docente.setId(id);
    }


    public Docente getDocente(){
        return docente;
    }


}
