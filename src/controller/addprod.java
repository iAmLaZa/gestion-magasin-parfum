package controller;

import classes.Produit;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class addprod   {
    ObservableList<Produit> liste;
    Produit prod=new Produit();
    int i=-1;
    public void showinformation(ObservableList<Produit> p){
        liste=p;
        ajouterproduit.setVisible(true);
        Ajouter.setVisible(true);
        Terminer.setVisible(true);
        modifie.setVisible(false);
        supp.setVisible(false);

    }

    public void updateinformation(ObservableList<Produit> p,int j){
        liste=p;
        i=j;
        prod=p.get(i);
        nom.setText(p.get(i).getCodeprod());
        prixvente.setText(String.valueOf(p.get(i).getPrixvente()));
        prixachat.setText(String.valueOf(p.get(i).getPrixachat()));
        ajouterproduit.setVisible(false);
        Ajouter.setVisible(false);
        Terminer.setVisible(false);
        modifie.setVisible(true);
        supp.setVisible(true);
    }

    @FXML
    TextField nom;
    @FXML
    TextField prixachat;
    @FXML
    TextField prixvente;
    @FXML
    Button Ajouter;
    @FXML
    Button modifie;
    @FXML
    Button supp;
    @FXML
    Label ajouterproduit;



     public void ajouter(){
         Produit p=new Produit(nom.getText(),Integer.valueOf(prixachat.getText()),Integer.valueOf(prixvente.getText()));
         p.ajouterprod();
         liste.add(p);
         nom.setText("");
         prixvente.setText("");
         prixachat.setText("");

     }
    @FXML
    Button Terminer;
     public void Terminer(){
         Stage stage=(Stage) Terminer.getScene().getWindow();
         stage.close();
     }

     public void supp(){
        prod.supprod();
        liste.remove(i);
         Stage stage=(Stage) supp.getScene().getWindow();
         stage.close();
     }
     public void modifie(){
         prod.setCodeprod(nom.getText());
         prod.setPrixachat(Double.valueOf(prixachat.getText()));
         prod.setPrixvente(Double.valueOf(prixvente.getText()));
         prod.modifieprod();
         liste.set(i,prod);
         Stage stage=(Stage) modifie.getScene().getWindow();
         stage.close();
     }



}
