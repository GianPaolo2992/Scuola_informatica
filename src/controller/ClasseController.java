package controller;



import entity.Classe;
import entity.Docente;
import entity.Gita;
import service.ClasseService;
import service.DocenteService;
import service.GitaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClasseController {

    Scanner scanner = new Scanner(System.in);
    ClasseService classeService = new ClasseService();
    GitaService gitaService = new GitaService();

    public void createClasse(){
        System.out.println("inserisci la sezione ");
        String sezione = scanner.nextLine();



        System.out.println("ecco una lista dei docenti");
        DocenteService oDocenteService =  new DocenteService();
        List<Docente> listaDocenti = oDocenteService.readDocente();
        for(Docente d : listaDocenti){
            System.out.println(d.getId() + " " +d.getName() + " " +d.getSurname() + " " +d.getSchoolSubject());
        }
        System.out.println("scegli uno dei docenti: ");
        int id_docente = scanner.nextInt();
        scanner.nextLine();
        Docente oDocente = null;
        for (Docente d : listaDocenti){
            if (d.getId() == id_docente){
                 oDocente = d;

                 break;
            }
        }
        //Docente oDocente = listaDocenti.get(id -2);

        classeService.createClasse(id_docente,sezione, oDocente);
    }

    public void readClasse(){

        System.out.println("Ecco la lista delle classi");
        List<Classe> listaClassi = classeService.readClasse();
        int i = 0;
        while(i<listaClassi.size() ){
            System.out.println(listaClassi.get(i).getId()+" "+listaClassi.get(i).getSezione()+" "+listaClassi.get(i).getDocenteId()+" "+ listaClassi.get(i).getDocente().getName());
            i++;
        }
    }


    public void updateClasse(){

        System.out.println("inserisci l' id del corso da aggiornare");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("inserisci la sezione del corso da aggiornare");
        String sezione = scanner.nextLine();

        System.out.println("ecco una lista dei docenti");
        DocenteController docenteController = new DocenteController();
        docenteController.readDocente();

        System.out.println("scegli l' id di un docente da associare alla classe");
        int id_docente = scanner.nextInt();
        scanner.nextLine();



        classeService.updateClasse(id,sezione,id_docente);
    }

    public void deleteClasse(){


        System.out.println("scegli l'id della classe da eliminare");
        int id =scanner.nextInt();
        scanner.nextLine();

        classeService.deleteClasse(id);
    }

    public  void associaClasseAGita() {

        // Visualizzazione della lista dei discenti disponibili
        System.out.println("Ecco la lista delle classi disponibili:");

        List<Classe> listaClasse = classeService.readClasse(); // Assumi che questo metodo esista
        for (Classe c : listaClasse) {
            System.out.println(c.getId() + " - " + c.getSezione() + " " + c.getDocenteName());
        }

        // Selezione del discente
        System.out.println("Inserisci l'ID della classe da associare alla gita:");
        int classeId = Integer.parseInt(scanner.nextLine());
        Classe classeSelezionato = null;
        for (Classe c : listaClasse) {
            if (c.getId() == classeId) {
                classeSelezionato = c;
                break;
            }
        }

        if (classeSelezionato == null) {
            System.out.println("Discente non trovato.");
            return;
        }

        // Visualizzazione della lista dei gite disponibili
        System.out.println("Ecco la lista delle gite disponibili:");

       List<Gita> listaGite = gitaService.readGita();
        //inserire id docente in clsaase gestire docente 0...1---->1 classe
        for (Gita g : listaGite) {
            System.out.println(g.getId() + " - " + g.getDestinazione() + " - " + g.getDataPartenza() + " - " + g.getDataRitorno() + " - " + g.getDocenteId());
        }

        // Inserimento degli ID dei corsi associati
        System.out.println("Inserisci  l'ID della gita assegnata al docente (separati da virgola):");
        int gitaInput =Integer.parseInt(scanner.nextLine());
        Gita gitaSelezionata = null;
        for (Gita g: listaGite) {
            if (g.getId() == gitaInput) {
                gitaSelezionata = g;
                break;

            }
        }
        if (gitaSelezionata == null) {
            System.out.println("Discente non trovato.");
            return;
        }

        // Associazione del discente ai corsi selezionati


            classeSelezionato.aggiungiGita(gitaSelezionata);
            gitaSelezionata.aggiungiClasse(classeSelezionato);
            classeService.associaClasseAGita(  classeSelezionato,gitaSelezionata);



        System.out.println("Classe " + classeSelezionato.getSezione() + " è stato associato alla gita selezionata.");

        System.out.println("Gita " + gitaSelezionata.getDestinazione() + " " + gitaSelezionata.getDataPartenza()  + " " + gitaSelezionata.getDataRitorno() + " è stata associata al docente selezionato.");
    }

   public void giteProgrammate() {
        List<Classe> listaGiteProgrammate = classeService.giteProgrammate();

        for (Classe c : listaGiteProgrammate) {

            System.out.println("Classe ID: " + c.getId() + ", Sezione: " + c.getSezione());
            for (Gita g : c.getListaGite()) {
                System.out.println("  Gita ID: " + g.getId() + ", Destinazione: " + g.getDestinazione()) ;


            }
       }
    }

   /* public void giteProgrammate() {
        List<Classe> listaGiteProgrammate = classeService.giteProgrammate();

        // Debug: stampa la dimensione della lista delle classi
        System.out.println("Numero di classi: " + listaGiteProgrammate.size());

        for (Classe c : listaGiteProgrammate) {
            System.out.println("Classe ID: " + c.getId() + ", Sezione: " + c.getSezione());

            // Debug: stampa la dimensione della lista delle gite per ogni classe
            System.out.println("Numero di gite per la classe " + c.getId() + ": " + c.getListaGite().size());

            for (Gita g : c.getListaGite()) {
                System.out.println("  Gita ID: " + g.getId() + ", Destinazione: " + g.getDestinazione());
            }
        }
    }*/


    public void deleteGitaProgrammata(){

        System.out.println("scegli l'id della gita programmata da eliminare");
        int classeId =scanner.nextInt();
        scanner.nextLine();
       giteProgrammate();
        System.out.println("scegli la destinazione per eliminare il record");
        String destinazione = scanner.nextLine();

        classeService.deleteGitaProgrammata(classeId,destinazione);

    }


}






