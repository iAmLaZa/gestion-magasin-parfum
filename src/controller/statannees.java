package controller;

import classes.Vente;
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
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class statannees  implements Initializable {

    @FXML
    Button valider;
    @FXML
    TableView<Vente> tablestatannees;
    @FXML
    TableColumn<Vente, String> datevente;
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
    TextField annees;

    public void valider(){
        Vente v=new Vente();
        try{

            listevente=v.listeventeannees(Integer.valueOf(annees.getText()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tablestatannees.setItems(listevente);

        try {
            nbvente.setText("Nombre De Vente :"+"\n"+v.nbvente(annees.getText()+"-%-%"));
            beneficetxt.setText("Bénéfices :"+"\n"+v.bene(annees.getText()+"-%-%"));
            totaletxt.setText("Totale :"+"\n"+v.Totale(annees.getText()+"-%-%"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void infomonth() throws IOException {
        int i=tablestatannees.getSelectionModel().getFocusedIndex();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/sample/statmois.fxml"));
        Parent root= loader.load();
        statmois statmois=loader.getController();
        statmois.showinformation(String.valueOf(listevente.get(i).getDate().getMonthValue()), annees.getText(), "annees");
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("stat de mois");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datevente.setCellValueFactory(new PropertyValueFactory<Vente,String>("month"));
        totale.setCellValueFactory(new PropertyValueFactory<Vente,Double>("totale"));
        benefice.setCellValueFactory(new PropertyValueFactory<Vente,Double>("benefice"));
    }
}
