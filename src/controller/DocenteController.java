package controller;
import service.DocenteService;

import entity.Docente;
import java.util.List;

import java.util.Scanner;

public class DocenteController {

    Scanner scanner = new Scanner(System.in);
    private DocenteService oDocenteService = new DocenteService();

    public void createDocente(){
        System.out.println("inserisci nome");

        String name = scanner.nextLine();
        System.out.println("inserisci cognome");
        String surname = scanner.nextLine();
        System.out.println("inserisci materia");
        String schoolSubject = scanner.nextLine();

        oDocenteService.createDocente(name,surname,schoolSubject);
    }

    public void readDocente(){
        System.out.println("ecco una lista dei docenti");
        DocenteService oDocenteService =  new DocenteService();
        List<Docente> listaDocenti = oDocenteService.readDocente();
        for(Docente d : listaDocenti){
            System.out.println(d.getId() + " " +d.getName() + " " +d.getSurname() + " " +d.getSchoolSubject());
        }

    }
   /* public List<Docente> readDocenti(){
        System.out.println("ecco una lista dei docenti");
        DocenteService oDocenteService =  new DocenteService();
        List<Docente> listaDocenti = oDocenteService.readDocente();
        for(Docente d : listaDocenti) {
            d
        }

    }*/

    public void updateDocente(){
        System.out.println("scegli un id del docente da modificare");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("aggiorna il nome docente");

        String name = scanner.nextLine();
        System.out.println("aggiorna il cognome docente");
        String surname = scanner.nextLine();
        System.out.println("aggiorna la materia insegnata dal docente");
        String schoolSubject = scanner.nextLine();

        oDocenteService.updateDocente(id,name,surname,schoolSubject);

    }

    public void deleteDocente(){
        System.out.println("scegli un id del docente da eliminare");
        int id = scanner.nextInt();
        scanner.nextLine();
        oDocenteService.deleteDocente(id);
    }
}
