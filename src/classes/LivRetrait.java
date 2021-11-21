package classes;

import interfaces.Iliv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class LivRetrait implements Iliv {

    private static Connection con = BD.connect();

    private int id;
    private LocalDate date;
    private double prix;
    private String note;

    public LivRetrait(int id, LocalDate date, double prix, String note) {
        this.id = id;
        this.date = date;
        this.prix = prix;
        this.note = note;
    }

    public LivRetrait() {
    }

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        LivRetrait.con = con;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean ajouterliv() {
        try{
            PreparedStatement pr=con.prepareStatement("insert into LivRetrait (dateliv,prix,note) values ('"+ this.getDate() +"',"+this.getPrix()+",'"+this.getNote()+"')");
            return pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public ObservableList<LivRetrait> listeliv() throws SQLException {
        ObservableList<LivRetrait> liste= FXCollections.observableArrayList();
        PreparedStatement pr=con.prepareStatement("select * from LivRetrait");
        ResultSet rs=pr.executeQuery();
        while(rs.next()){
            liste.add(new LivRetrait(rs.getInt("idliv"),rs.getDate("dateliv").toLocalDate(),rs.getDouble("prix"),rs.getString("note")));
        }
        return liste;
    }

    @Override
    public boolean modifieliv() {
        try{
            PreparedStatement pr=con.prepareStatement("update LivRetrait set dateliv='"+this.getDate()+"',prix="+this.getPrix()+
                    ",note='"+
                    this.getNote()+"' where idliv="+this.getId());
            return !pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean supliv(int id) {
        try{
            PreparedStatement pr=con.prepareStatement("delete from LivRetrait where idliv="+id);
            return !pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}


