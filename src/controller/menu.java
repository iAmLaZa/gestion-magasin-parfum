package controller;

import classes.LivRetrait;
import classes.Produit;
import classes.Vente;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotResult;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import manipulation.outils;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


public class menu  implements Initializable {
    int i;
    @FXML
    Button menubtnproduit;
    @FXML
    Button menubtncaisse;
    @FXML
    Button menubtnfournisseur;
    @FXML
    Button menubtnstat;
    @FXML
    Button logout;

    @FXML
    AnchorPane anchorprod;

    @FXML
    AnchorPane anchorfournisseur;

    @FXML
    AnchorPane anchorstat;

    @FXML
    AnchorPane anchorcaisse;
    public void menuprod(){
        anchorprod.setVisible(true);
        anchorfournisseur.setVisible(false);
        anchorcaisse.setVisible(false);
        anchorstat.setVisible(false);
    }
    public void logout(){
        Stage stage=(Stage) logout.getScene().getWindow();
        stage.close();
    }

    @FXML
    TableView<Produit> tableprod;
    @FXML
    TableColumn<Produit, String> codeprod;
    @FXML
    TableColumn<Produit, Double> prixachat;
    @FXML
    TableColumn<Produit, Double> prixvente;

    ObservableList<Produit> listeprod;

    @FXML
    TextField search;

    public void searchprod() {
        outils.searchglobale(search, listeprod, tableprod);
    }
    public void addprod() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/sample/addprod.fxml"));
        Parent root= loader.load();

        addprod addprod=loader.getController();
        addprod.showinformation(listeprod);
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("Ajouter un produit");

        stage.show();
    }

    public void infoprod() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/sample/addprod.fxml"));
        Parent root= loader.load();
         i=tableprod.getSelectionModel().getFocusedIndex();
        addprod addprod=loader.getController();
        addprod.updateinformation(listeprod,i);
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("modifie/supprimer un produit");

        stage.show();
    }

    public void menucaisse(){
        anchorprod.setVisible(false);
        anchorfournisseur.setVisible(false);
        anchorcaisse.setVisible(true);
        anchorstat.setVisible(false);

    }

    @FXML
    TableView<Produit> tablecaisseprod;
    @FXML
    TableColumn<Produit,String> codeprodcaisse;
    @FXML
    TableColumn<Produit,Double> prixprodcaisse;
    @FXML
    TextField searchprodcaisse;
    public void searchprodcaisse() {
        outils.searchglobale(searchprodcaisse, listeprod, tablecaisseprod);
    }



    @FXML
    TextField choixprix;
    @FXML
    TextField choixqte;
    String prod;

    public void infoprodcaisse(){
        i=tablecaisseprod.getSelectionModel().getFocusedIndex();
        prod=listeprod.get(i).getCodeprod();
        choixprix.setText(String.valueOf(listeprod.get(i).getPrixvente()));
        choixqte.setText(String.valueOf(1));
    }

    @FXML
    TableView<Vente> tablevente;
    @FXML
    TableColumn<Vente,LocalDate> datevente;
    @FXML
    TableColumn<Vente,String> codeprodvente;
    @FXML
    TableColumn<Vente,Double> prixventevente;
    @FXML
    TableColumn<Vente,Integer> qtevente;
    @FXML
    TableColumn<Vente,Double> totalevente;

    ObservableList<Vente> listevente;


    public void ajouterventecaisse() throws SQLException {
        Produit p=new Produit();
        Vente v=new Vente();
        v.setCodeprod(prod);
        v.setPrix(Double.valueOf(choixprix.getText()));
        v.setQte(Integer.valueOf(choixqte.getText()));
        Double totale=v.getPrix()*v.getQte();
        v.setTotale(totale);
        v.setBenefice(totale-(p.prixachat(prod)*v.getQte()));
        System.out.println(totale-(p.prixachat(prod)*v.getQte()));
        v.ajoutervente();
        listevente.add(v);
        choixqte.setText("");
        choixprix.setText("");
    }


    public void menufournisseur(){
        anchorprod.setVisible(false);
        anchorfournisseur.setVisible(true);
        anchorcaisse.setVisible(false);
        anchorstat.setVisible(false);
    }
    @FXML
    TextField searchliv;
    public void searchliv(){
        outils.searchglobale(searchliv,listeliv,tableliv);
    }
    @FXML
    TextArea note;
    @FXML
    TextField prixlivtxt;
    @FXML
    DatePicker datelivtxt;
    @FXML
    TableView<LivRetrait> tableliv;
    @FXML
    TableColumn<LivRetrait, LocalDate> dateliv;
    @FXML
    TableColumn<LivRetrait,Double> prixliv;
    @FXML
    TableColumn<LivRetrait,String> noteliv;

    ObservableList<LivRetrait> listeliv;

    public void ajouterliv(){
        LivRetrait l=new LivRetrait();
        l.setDate(datelivtxt.getValue());
        l.setNote(note.getText());
        l.setPrix(Double.valueOf(prixlivtxt.getText()));
        l.ajouterliv();
        listeliv.add(l);
        note.setText("");
        prixlivtxt.setText("");
        datelivtxt.setValue(LocalDate.now());

    }

    @FXML
    Button Modifieliv;
    @FXML
    Button Ajouterliv;
    @FXML
    Button supliv;
    @FXML
    Label labelajouterliv;

    public void infoliv(){
         i=tableliv.getSelectionModel().getFocusedIndex();
         datelivtxt.setValue(listeliv.get(i).getDate());
         note.setText(listeliv.get(i).getNote());
         prixlivtxt.setText(String.valueOf(listeliv.get(i).getPrix()));
         Modifieliv.setVisible(true);
         Ajouterliv.setVisible(false);
         supliv.setVisible(true);
         labelajouterliv.setVisible(false);
    }
    int idliv;
    public void Modifieliv(){
        idliv=listeliv.get(i).getId();
        LivRetrait l=new LivRetrait(idliv,datelivtxt.getValue(),Double.valueOf(prixlivtxt.getText()),note.getText());
        listeliv.set(i,l);
        l.modifieliv();
        Ajouterliv.setVisible(true);
        Modifieliv.setVisible(false);
        supliv.setVisible(false);
        note.setText("");
        prixlivtxt.setText("");
        datelivtxt.setValue(LocalDate.now());
        labelajouterliv.setVisible(true);
    }

    public void supliv(){
        idliv=listeliv.get(i).getId();
        LivRetrait l=new LivRetrait();
        l.supliv(idliv);
        listeliv.remove(i);
        Ajouterliv.setVisible(true);
        Modifieliv.setVisible(false);
        supliv.setVisible(false);
        note.setText("");
        prixlivtxt.setText("");
        datelivtxt.setValue(LocalDate.now());
        labelajouterliv.setVisible(true);
    }



    public void menustat(){
        anchorprod.setVisible(false);
        anchorfournisseur.setVisible(false);
        anchorcaisse.setVisible(false);
        anchorstat.setVisible(true);

    }

    @FXML
    Label nbvente;
    @FXML
    Label totaletxt;
    @FXML
    Label beneficetxt;



    public void parjour() throws IOException {

        FXMLLoader loader=new FXMLLoader(getClass().getResource("/sample/statjour.fxml"));
        Parent root= loader.load();
        statjour statjour=loader.getController();
        statjour.showinformation("0000-00-00","menu");
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("stat de jour");
        stage.show();
    }
    public void parmois() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/sample/statmois.fxml"));
        Parent root= loader.load();
        statmois statmois=loader.getController();
        statmois.showinformation("null","null","menu");
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("stat de jour");
        stage.show();

    }
    public void parannees() throws IOException {

        FXMLLoader loader=new FXMLLoader(getClass().getResource("/sample/statannees.fxml"));
        Parent root= loader.load();
        statannees statannees=loader.getController();
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("stat d'Années");
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        codeprod.setCellValueFactory(new PropertyValueFactory<Produit, String>("codeprod"));
        prixachat.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prixachat"));
        prixvente.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prixvente"));


        codeprodcaisse.setCellValueFactory(new PropertyValueFactory<Produit, String>("codeprod"));
        prixprodcaisse.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prixvente"));
        Produit p = new Produit();
        try {
            listeprod = p.listeprod();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tableprod.setItems(listeprod);
        tablecaisseprod.setItems(listeprod);



        note.setPrefColumnCount(20);
        note.setWrapText(true);

        dateliv.setCellValueFactory(new PropertyValueFactory<LivRetrait,LocalDate>("date"));
        prixliv.setCellValueFactory(new PropertyValueFactory<LivRetrait,Double>("prix"));
        noteliv.setCellValueFactory(new PropertyValueFactory<LivRetrait,String>("note"));



        LivRetrait livRetrait=new LivRetrait();
        try{
            listeliv=livRetrait.listeliv();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tableliv.setItems(listeliv);
        datelivtxt.setValue(LocalDate.now());


        datevente.setCellValueFactory(new PropertyValueFactory<Vente,LocalDate>("date"));
        codeprodvente.setCellValueFactory(new PropertyValueFactory<Vente,String>("codeprod"));
        qtevente.setCellValueFactory(new PropertyValueFactory<Vente,Integer>("qte"));
        prixventevente.setCellValueFactory(new PropertyValueFactory<Vente,Double>("prix"));
        totalevente.setCellValueFactory(new PropertyValueFactory<Vente,Double>("totale"));

        Vente v=new Vente();
        try{
            listevente=v.listevente(LocalDate.now());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tablevente.setItems(listevente);


        try {
            nbvente.setText(nbvente.getText()+"\n"+v.nbvente("%-%-%"));
            beneficetxt.setText(beneficetxt.getText()+"\n"+v.bene("%-%-%"));
            totaletxt.setText(totaletxt.getText()+"\n"+v.Totale("%-%-%"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




    }
}