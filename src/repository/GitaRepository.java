package repository;

import config.DbConnection;
import entity.Gita;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class GitaRepository {

    public void createGita(Gita oGita) {
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "INSERT INTO gita (destinazione,datapartenza,dataritorno,id_docente) " +
                    " VALUES(?,?,?,?)";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, oGita.getDestinazione());
            pstmt.setDate(2, java.sql.Date.valueOf(oGita.getDataPartenza()));
            pstmt.setDate(3, java.sql.Date.valueOf(oGita.getDataRitorno()));
            pstmt.setInt(4, oGita.getDocenteId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (ClassNotFoundException | SQLException e) {

            System.err.println(e.getMessage());
            System.exit(0);


        }
    }

    public List<Gita> readGite() {
        List<Gita> listaGite = new ArrayList<>();
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "SELECT g.* FROM gita g JOIN docente d ON d.id = g.id_docente ";
            PreparedStatement pstmt = c.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Gita oGita = new Gita();
                oGita.setId(rs.getInt("id"));
                oGita.setDestinazione(rs.getString("destinazione"));
                oGita.setDataPartenza(rs.getDate("datapartenza").toLocalDate());
                oGita.setDataRitorno(rs.getDate("dataritorno").toLocalDate());
                oGita.setDocenteId(rs.getInt("id_docente"));

                listaGite.add(oGita);
            }
            pstmt.close();

        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return listaGite;

    }

    public void updateGite(Gita oGita){
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "UPDATE gita SET destinazione = ? , datapartenza = ?, dataritorno = ?, id_docente = ? WHERE id = ?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1,oGita.getDestinazione());
            pstmt.setDate(2,java.sql.Date.valueOf(oGita.getDataPartenza()));
            pstmt.setDate(3,java.sql.Date.valueOf(oGita.getDataRitorno()));
            pstmt.setInt(4,oGita.getDocenteId());
            pstmt.setInt(5,oGita.getId());
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("model.dao.Docente aggiornato");

        }catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }


    }

    public void deleteGite(Gita oGita){

        try{
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "DELETE FROM gita WHERE id = ? ";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1,oGita.getId());
            pstmt.executeUpdate();
            pstmt.close();

        }catch(ClassNotFoundException|SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);

        }

    }
}
