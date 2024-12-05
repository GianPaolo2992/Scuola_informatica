package repository;

import config.DbConnection;
import entity.Classe;
import entity.Docente;
import entity.Gita;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ClasseRepository {

    public void createClasse(Classe oClasse) {
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "INSERT INTO classe(sezione,id_docente) VALUES (?,?)";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, oClasse.getSezione());
            pstmt.setInt(2, oClasse.getDocenteId());
            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }

    public List<Classe> readClasse() {
        ArrayList<Classe> listaClassi = new ArrayList<>();
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "SELECT * FROM classe c JOIN " +
                    "docente d ON c.id_docente = d.id";
            PreparedStatement pstmt = c.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Classe oClasse = new Classe();
                oClasse.setId(rs.getInt("id"));
                oClasse.setSezione(rs.getString("sezione"));

                Docente oDocente = new Docente();
                oDocente.setId(rs.getInt("id_docente"));
                oDocente.setName(rs.getString("nome"));
                oDocente.setSurname(rs.getString("cognome"));

                oDocente.setSchoolSubject(rs.getString("materia"));

                oClasse.setDocente(oDocente);
                listaClassi.add(oClasse);
            }

            rs.close();
            pstmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        return listaClassi;
    }

    public void updateClasse(Classe oClasse) {
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");

            String query = "UPDATE classe SET sezione = ?,id_docente = ? WHERE id =?";

            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, oClasse.getSezione());
            pstmt.setInt(2, oClasse.getDocenteId());
            pstmt.setInt(3, oClasse.getId());
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("model.dao.Classe aggiornato");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public void deleteClasse(Classe oClasse) {
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "DELETE FROM classe c  WHERE c.id = ?";

            PreparedStatement pstmt = c.prepareStatement(query);

            pstmt.setInt(1, oClasse.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public void associaClasseAGita( Classe oClasse,Gita oGita) {

        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "INSERT INTO destinazione(id_classe,id_gita) VALUES(?,?)";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1, oClasse.getId());
            pstmt.setInt(2, oGita.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    /*public List<Classe> giteProgrammate() {
        List<Classe> listaGiteProgrammate = new ArrayList<>();
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "SELECT g.*,c.sezione,c.id FROM destinazione d " +
                    "JOIN gita g ON g.id = d.id_gita " +
                    "JOIN classe c ON c.id = d.id_classe " ;
            PreparedStatement pstmt = c.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Gita oGita = new Gita();
                oGita.setId(rs.getInt("id"));
                oGita.setDestinazione(rs.getString("destinazione"));
                Classe oClasse = new Classe();
                oClasse.setId(rs.getInt("id"));
                oClasse.setSezione(rs.getString("sezione"));


                oGita.aggiungiClasse(oClasse);
                oClasse.aggiungiGita(oGita);
                listaGiteProgrammate.add(oClasse);

            }

        } catch (ClassNotFoundException | SQLException e) {

        }
        return listaGiteProgrammate;
    }*/
    public List<Classe> giteProgrammate() {
        List<Classe> listaGiteProgrammate = new ArrayList<>();
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "SELECT d.id AS destinazione_id, g.id AS gita_id, g.destinazione, c.id AS classe_id, c.sezione " +
                    "FROM destinazione d " +
                    "JOIN gita g ON g.id = d.id_gita " +
                    "JOIN classe c ON c.id = d.id_classe";
            PreparedStatement pstmt = c.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Gita oGita = new Gita();
                oGita.setId(rs.getInt("gita_id"));
                oGita.setDestinazione(rs.getString("destinazione"));

                Classe oClasse = new Classe();
                oClasse.setId(rs.getInt("classe_id"));
                oClasse.setSezione(rs.getString("sezione"));

                // Associa la classe alla gita
                oGita.aggiungiClasse(oClasse);
                // Associa la gita alla classe
                oClasse.aggiungiGita(oGita);

                listaGiteProgrammate.add(oClasse);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return listaGiteProgrammate;
    }


    public void deleteClasseGita(int classeId, String destinazione) {
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "DELETE FROM destinazione " +
                    "WHERE id IN (" +
                    "SELECT d.id " +
                    "FROM destinazione d " +
                    "JOIN classe c ON c.id = d.id_classe " +
                    "JOIN gita g ON g.id = d.id_gita " +
                    "WHERE c.id = ? AND g.destinazione = ?)";

            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1, classeId);
            pstmt.setString(2, destinazione);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Record eliminato con successo.");
            } else {
                System.out.println("Nessun record trovato con i criteri specificati.");
            }

            pstmt.close();
            c.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }


}
