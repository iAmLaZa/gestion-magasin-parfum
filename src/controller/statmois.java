package controller;

import classes.Vente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class statmois implements Initializable {
    String Annees,window,Mois;
    public void showinformation(String m,String a,String w){
        Annees=a;
        Mois=m;
        window=w;
        if(!window.equals("menu")){
            moisvente.setVisible(false);
            annees.setVisible(false);
            valider.setVisible(false);
            Vente v=new Vente();
            try{
                listevente=v.listeventemios(Integer.valueOf(Annees),Integer.valueOf(Mois));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            tablestatmois.setItems(listevente);

            try {
                nbvente.setText(nbvente.getText()+"\n"+v.nbvente(Annees+"-"+Mois+"-%"));
                beneficetxt.setText(beneficetxt.getText()+"\n"+v.bene(Annees+"-"+Mois+"-%"));
                totaletxt.setText(totaletxt.getText()+"\n"+v.Totale(Annees+"-"+Mois+"-%"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }
    @FXML
    Button valider;
    @FXML
    TableView<Vente> tablestatmois;
    @FXML
    TableColumn<Vente, LocalDate> datevente;
    @FXML
    TableColumn<Vente,Double> totale;
    @FXML
    TableColumn<Vente,Double> benefice;

    ObservableList<Vente> listevente;

    @FXML
    Label nbvente;
    @FXML
    Label totaletxt;
    @FXML
    Label beneficetxt;
    @FXML
    ChoiceBox<Integer> moisvente;
    @FXML
    TextField annees;

    public void valider(){
        Vente v=new Vente();
        try{

            listevente=v.listeventemios(Integer.valueOf(annees.getText()),moisvente.getValue());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tablestatmois.setItems(listevente);

        try {
            nbvente.setText("Nombre De Vente :"+"\n"+v.nbvente(annees.getText()+"-"+String.valueOf(moisvente.getValue())+"-%"));
            beneficetxt.setText("Bénéfices :"+"\n"+v.bene(annees.getText()+"-"+String.valueOf(moisvente.getValue())+"-%"));
            totaletxt.setText("Totale :"+"\n"+v.Totale(annees.getText()+"-"+String.valueOf(moisvente.getValue())+"-%"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void infojour() throws IOException {
        int i=tablestatmois.getSelectionModel().getFocusedIndex();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/sample/statjour.fxml"));
        Parent root= loader.load();
        statjour statjour=loader.getController();
        statjour.showinformation(listevente.get(i).getDate().toString(),"mois");
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("stat de jour");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        datevente.setCellValueFactory(new PropertyValueFactory<Vente,LocalDate>("date"));
        totale.setCellValueFactory(new PropertyValueFactory<Vente,Double>("totale"));
        benefice.setCellValueFactory(new PropertyValueFactory<Vente,Double>("benefice"));

        ObservableList l= FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);
        moisvente.setItems(l);

    }
}
