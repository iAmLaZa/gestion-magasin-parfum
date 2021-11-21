package manipulation;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class outils {
    public outils() {
    }

    public static void showconfirmationmessage(String titre, String message){
        Alert a=new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle(titre);
        a.setContentText(message);

        a.showAndWait();
    }

    public static void showerroronmessage(String titre,String message){
        Alert a=new Alert(Alert.AlertType.ERROR);
        a.setTitle(titre);
        a.setContentText(message);

        a.showAndWait();
    }

    private  void loadwindow(ActionEvent event, String title, String url)throws IOException{


        FXMLLoader loader=new FXMLLoader(getClass().getResource(url));
        Parent root= loader.load();
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle(title);

        stage.show();
    }

    private  void loadpage(ActionEvent event, String title, String url) throws IOException{
        ((Node) event.getSource()).getScene().getWindow().hide();

        Parent root= FXMLLoader.load(getClass().getResource(url));
        Scene scene=new Scene(root);

        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/image/1.jpeg")));
        stage.setResizable(false);
        stage.show();
    }

    public static void loadp(ActionEvent event, String title, String url) throws IOException{
        new outils().loadpage(event,title,url);
    }
    public static void loadw(ActionEvent event, String title, String url) throws IOException{
        new outils().loadwindow(event,title,url);
    }

    public static void searchglobale(TextField searchgolbale, ObservableList list, TableView table){

        searchgolbale.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(searchgolbale.textProperty().get().isEmpty()){
                    table.setItems(list);
                    return;
                }
                ObservableList items= FXCollections.observableArrayList();
                ObservableList<TableColumn> colone=table.getColumns();

                for(int ligne=0;ligne<list.size();ligne++){
                    for(int col=0;col<colone.size();col++){
                        TableColumn colvar=colone.get(col);
                        String cellvalue=colvar.getCellData(list.get(ligne)).toString();
                        cellvalue=cellvalue.toLowerCase();
                        if(cellvalue.contains(searchgolbale.getText().toLowerCase() )&& cellvalue.startsWith(searchgolbale.getText().toLowerCase())){
                            items.add(list.get(ligne));
                            break;
                        }
                    }
                }
                table.setItems(items);
            }
        });
    }


}
