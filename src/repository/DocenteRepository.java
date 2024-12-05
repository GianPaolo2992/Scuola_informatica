package repository;

import config.DbConnection;
import entity.Docente;

import java.security.Security;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocenteRepository {



    public void createDocente(Docente oDocente){
        try{
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "INSERT INTO docente (nome, cognome,materia) VALUES(?,?,?)";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, oDocente.getName());
            pstmt.setString(2,oDocente.getSurname());
            pstmt.setString(3,oDocente.getSchoolSubject());
            pstmt.executeUpdate();

        }catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public ArrayList<Docente> readDocente() {
        ArrayList<Docente> listaDocenti = new ArrayList<>();
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "SELECT * FROM docente ORDER BY id asc";
            PreparedStatement pstmt = c.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Docente oDocente = new Docente();
                oDocente.setId(rs.getInt("id"));
                oDocente.setName(rs.getString("nome"));
                oDocente.setSurname(rs.getString("cognome"));
                oDocente.setSchoolSubject(rs.getString("materia"));

                listaDocenti.add(oDocente);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        return listaDocenti;
    }


    public void updateDocente(Docente oDocente){
       try{
           Connection c = DbConnection.openConnection();
           System.out.println("Connessione Riuscita");
           String query = "UPDATE docente SET nome= ?,cognome = ?, materia = ? WHERE id = ?";
           PreparedStatement pstmt = c.prepareStatement(query);
           pstmt.setString(1,oDocente.getName());
           pstmt.setString(2,oDocente.getSurname());
           pstmt.setString(3,oDocente.getSchoolSubject());
           pstmt.setInt(4,oDocente.getId());
           pstmt.executeUpdate();
           pstmt.close();
           System.out.println("model.dao.Docente aggiornato");
       }catch (ClassNotFoundException | SQLException e){
           System.err.println(e.getMessage());
           System.exit(0);

       }

    }

    public void deleteDocente(Docente oDocente){
        try{
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "DELETE FROM docente WHERE id = ? ";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1,oDocente.getId());
            pstmt.executeUpdate();
            System.out.println("model.dao.Docente eliminato");
        }catch(ClassNotFoundException|SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }

}




