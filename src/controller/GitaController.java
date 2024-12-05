package controller;

import entity.Docente;
import entity.Gita;
import service.DocenteService;
import service.GitaService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GitaController {
    private GitaService gitaService = new GitaService();
    private DocenteController docenteController = new DocenteController();
    private DocenteService docenteService = new DocenteService();
Scanner scanner = new Scanner(System.in);
    public void createGita(){
        System.out.println("inserisci la destinazione della gita");
        String destinazione = scanner.nextLine();

        System.out.println("scegli una data di partenza (formato dd-MM-yyyy) ");
        String dataPartenzaInput = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate dataPartenza = LocalDate.parse(dataPartenzaInput,formatter);

        System.out.println("scegli una data di ritorno (formato dd-MM-yyyy) ");

        String dataRitornoInput = scanner.nextLine();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate dataRitorno = LocalDate.parse(dataRitornoInput,format);
        docenteController.readDocente();
       List<Docente> listaDocenti =  docenteService.readDocente();

        System.out.println("scegli un docente da assegnare alla gita ");
         int id_docente = scanner.nextInt();
        scanner.nextLine();
        Docente oDocente = null;
        for (Docente d : listaDocenti){
            if (d.getId() == id_docente){
              oDocente = d;
                break;
            }

        }
        gitaService.createGita(destinazione,dataPartenza,dataRitorno,oDocente);



    }

    public void readGite(){
        System.out.println("ecco la lista delle gite");
        List<Gita> listaGite = gitaService.readGita();

        int i = 0;
        while(i<listaGite.size()){
            System.out.println(listaGite.get(i).getId() +" "+
                               listaGite.get(i).getDestinazione() +" "+
                                listaGite.get(i).getDataPartenza() +" "+
                               listaGite.get(i).getDataRitorno() +" "+
                               listaGite.get(i).getDocenteId());
            i++;
        }
    }

    public void updateGita(){

        GitaController gitaController = new GitaController();
        gitaController.readGite();

        System.out.println("inserisci l' id del corso da aggiornare");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("inserisci la destinazione della da aggiornare");
        String destinazione = scanner.nextLine();

        System.out.println("inserisci la data di inizio del corso (formato: dd-MM-yyyy)");
        String dataPartenzaInput = scanner.nextLine(); // Specificare il formato della data
        DateTimeFormatter formatpartenza = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate dataPartenza = LocalDate.parse(dataPartenzaInput, formatpartenza);

        System.out.println("inserisci la data di inizio del corso (formato: dd-MM-yyyy)");
        String dataRitornoInput = scanner.nextLine(); // Specificare il formato della data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate dataRitorno = LocalDate.parse(dataRitornoInput, formatter);
        System.out.println("ecco una lista dei docenti");

        System.out.println("ecco una lista dei docenti");
        DocenteController  docenteController = new  DocenteController();
        docenteController.readDocente();

        System.out.println("scegli l' id di un docente da associare alla classe");
        int id_docente = scanner.nextInt();
        scanner.nextLine();



        gitaService.updateGita(id,destinazione,dataPartenza,dataRitorno,id_docente);
    }

    public void deleteGita(){
        System.out.println("scegli l'id della gita da eliminare");
        int id =scanner.nextInt();
        scanner.nextLine();

        gitaService.deleteGita(id);
    }
}
