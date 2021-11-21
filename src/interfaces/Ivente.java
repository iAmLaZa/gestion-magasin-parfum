package interfaces;

import classes.Vente;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;

public interface Ivente {

    public boolean ajoutervente();
    public ObservableList<Vente> listevente(LocalDate l) throws SQLException;
    public double Totale(String l) throws SQLException;
    public double bene(String l) throws SQLException;
    public int nbvente(String l) throws SQLException;
    public ObservableList<Vente> listeventemios(int annees,int mois) throws SQLException;
    public ObservableList<Vente> listeventeannees(int annees ) throws SQLException;
}
