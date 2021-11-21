package classes;

import interfaces.Iprod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Produit implements Iprod {

    private static Connection con = BD.connect();

    private String codeprod;
    private double prixachat,prixvente;

    public Produit(String codeprod, double prixachat, double prixvente) {
        this.codeprod = codeprod;
        this.prixachat = prixachat;
        this.prixvente = prixvente;
    }
    public Produit(){};

    //Setter and getter :

    public String getCodeprod() {
        return codeprod;
    }

    public void setCodeprod(String codeprod) {
        this.codeprod = codeprod;
    }

    public double getPrixachat() {
        return prixachat;
    }

    public void setPrixachat(double prixachat) {
        this.prixachat = prixachat;
    }

    public double getPrixvente() {
        return prixvente;
    }

    public void setPrixvente(double prixvente) {
        this.prixvente = prixvente;
    }

    @Override
    public boolean ajouterprod() {
        try{
            PreparedStatement pr=con.prepareStatement("insert into produit values('"+this.getCodeprod()+"','"+this.getPrixachat()+"','"+this.getPrixvente()+"')");
            return pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public ObservableList<Produit> listeprod() throws SQLException {
        ObservableList<Produit> liste= FXCollections.observableArrayList();
        PreparedStatement pr=con.prepareStatement("select * from produit");
        ResultSet rs=pr.executeQuery();
        while(rs.next()){
            liste.add(new Produit(rs.getString("codeprod"),rs.getDouble("prixachat"),rs.getDouble("prixvente")));
        }
        return liste;
    }

    @Override
    public boolean modifieprod() {
        try{
            PreparedStatement pr=con.prepareStatement("update produit set codeprod='"+this.getCodeprod()+
                    "',prixachat="+this.getPrixachat()+
                    ",prixvente="+this.getPrixvente()+" where codeprod='"+this.getCodeprod()+"'");
            return !pr.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean supprod() {
        try{
            PreparedStatement pr=con.prepareStatement("delete from produit where codeprod='"+this.getCodeprod()+"'");
            return !pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Double prixachat(String codeprod) throws SQLException {
        PreparedStatement pr=con.prepareStatement("select prixachat from produit where codeprod='"+codeprod+"'");
        ResultSet rs=pr.executeQuery();
        if(rs.next()){
            return rs.getDouble("prixachat");
        }
        return 0.0;
    }
}
