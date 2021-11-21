package controller;

import classes.Vente;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class statjour implements Initializable {
     String date,window;
    public void showinformation(String d,String w){
        date=d;
        window=w;
        if(window=="menu")
            choixdate.setVisible(true);
        else {
            choixdate.setVisible(false);
            Vente v=new Vente();
            try{
                listevente=v.listevente(LocalDate.parse(date));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            tablestatjour.setItems(listevente);

            try {
                nbvente.setText(nbvente.getText()+"\n"+v.nbvente(date));
                beneficetxt.setText(beneficetxt.getText()+"\n"+v.bene(date));
                totaletxt.setText(totaletxt.getText()+"\n"+v.Totale(date));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }
    @FXML
    DatePicker choixdate;
    @FXML
    TableView<Vente> tablestatjour;
    @FXML
    TableColumn<Vente, LocalDate> datevente;
    @FXML
    TableColumn<Vente,String> codeprod;
    @FXML
    TableColumn<Vente,Double> prix;
    @FXML
    TableColumn<Vente,Integer> qte;
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

    public void choixdate(){
        Vente v=new Vente();
        try{
            listevente=v.listevente(choixdate.getValue());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tablestatjour.setItems(listevente);

        try {
            nbvente.setText("Nombre De Vente :"+"\n"+v.nbvente(choixdate.getValue().toString()));
            beneficetxt.setText("Bénéfices :"+"\n"+v.bene(choixdate.getValue().toString()));
            totaletxt.setText("Totale :"+"\n"+v.Totale(choixdate.getValue().toString()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        datevente.setCellValueFactory(new PropertyValueFactory<Vente,LocalDate>("date"));
        codeprod.setCellValueFactory(new PropertyValueFactory<Vente,String>("codeprod"));
        qte.setCellValueFactory(new PropertyValueFactory<Vente,Integer>("qte"));
        prix.setCellValueFactory(new PropertyValueFactory<Vente,Double>("prix"));
        totale.setCellValueFactory(new PropertyValueFactory<Vente,Double>("totale"));
        benefice.setCellValueFactory(new PropertyValueFactory<Vente,Double>("benefice"));




    }
}
