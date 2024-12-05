package view;

import controller.ClasseController;
import controller.DocenteController;
import controller.GitaController;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        int entityChoice;

        // Scegli se lavorare con Docente o Discente
        System.out.println("Scegli l'entità: ");
        System.out.println("1. Docente");
        System.out.println("2. Classe");
        System.out.println("3. Gita");
        System.out.println("4. Gite Programmate ");


        entityChoice = scanner.nextInt();
        do {
            if (entityChoice == 1) {
                System.out.println("Classe Docente");
                System.out.println("***Menu***");
                System.out.println("1. Crea un nuovo docente");
                System.out.println("2. lista docenti");
                System.out.println("3. aggiorna docente");
                System.out.println("4. elimina docente");
                System.out.println("9. Exit");
                System.out.print("Inserisci la tua scelta: ");
                choice = scanner.nextInt();
                DocenteController docenteController = new DocenteController();

                switch (choice) {
                    case 1:
                        docenteController.createDocente();
                        break;
                    case 2:
                        docenteController.readDocente();
                        break;
                    case 3:
                        docenteController.readDocente();
                        docenteController.updateDocente();
                        break;
                    case 4:
                        docenteController.readDocente();
                        docenteController.deleteDocente();
                    case 9:
                        System.out.println("Exiting");
                        break;
                    default:
                        System.out.println("Scelta errata. Scegliere un numero da 1 a 4 o 9");

                }
            }
            else if (entityChoice == 2) {
                System.out.println("Classe Classe");
                System.out.println("***Menu***");
                System.out.println("1. Crea una nuova classe");
                System.out.println("2. lista classe e docente");
                System.out.println("3. aggiorna classe e docente");
                System.out.println("4. elimina classe");
                System.out.println("9. Exit");
                System.out.print("Inserisci la tua scelta: ");
                choice = scanner.nextInt();
                ClasseController classeController = new ClasseController();

                switch (choice) {
                    case 1:
                        classeController.createClasse();
                        break;
                    case 2:
                        classeController.readClasse();
                        break;
                    case 3:
                        classeController.readClasse();
                        classeController.updateClasse();
                        break;
                    case 4:
                        classeController.readClasse();
                        classeController.deleteClasse();
                        break;

                    case 9:
                        System.out.println("Exiting");
                        break;
                    default:
                        System.out.println("Scelta errata. Scegliere un numero da 1 a 4 o 9");

                }
            }
            else if (entityChoice == 3) {
                System.out.println("Classe Gita");
                System.out.println("***Menu***");
                System.out.println("1. Crea una nuova Gita");
                System.out.println("2. lista classe e Gita");
                System.out.println("3. aggiorna Gita e assegna un nuovo docente");
                System.out.println("4. elimina Gita");
                System.out.println("9. Exit");
                System.out.print("Inserisci la tua scelta: ");
                choice = scanner.nextInt();
                GitaController gitaController = new GitaController();

                switch (choice) {
                    case 1:
                        gitaController.createGita();
                        break;
                   case 2:
                       gitaController.readGite();
                        break;
                  case 3:
                      gitaController.readGite();
                        gitaController.updateGita();
                        break;
                      case 4:

                        gitaController.readGite();
                        gitaController.deleteGita();
                        break;

                    case 9:
                        System.out.println("Exiting");
                        break;
                    default:
                        System.out.println("Scelta errata. Scegliere un numero da 1 a 4 o 9");

                }
            }
            else if (entityChoice == 4) {

                System.out.println("***Menu***");
                System.out.println("1. Associa una classe a gita");
                System.out.println("2. lista classe e Gita");
                /*System.out.println("3. aggiorna Gita e assegna un nuovo docente");*/
                System.out.println("3. elimina Gita programmata");
                System.out.println("9. Exit");
                System.out.print("Inserisci la tua scelta: ");
                choice = scanner.nextInt();
                ClasseController classeController = new ClasseController();

                switch (choice) {
                    case 1:
                        classeController.associaClasseAGita();
                        break;
                    case 2:
                        classeController.giteProgrammate();
                        break;
                    case 3:
                        classeController.giteProgrammate();
                        classeController.deleteGitaProgrammata();
                        break;
                    //case 4:

                        //gitaController.readGite();
                        //gitaController.deleteGita();
                       //break;

                    case 9:
                        System.out.println("Exiting");
                        break;
                    default:
                        System.out.println("Scelta errata. Scegliere un numero da 1 a 4 o 9");

                }
            }
        } while (choice != 9);
        scanner.close();
    }

}