/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.ui.driver.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import suber.Suber;
import suber.backend.SuberDB;
import suber.backend.member.session.LoginSession;

/**
 * FXML Controller class
 *
 * @author Andrew
 */
public class DriverLandingPageController implements Initializable {

    SuberDB db;
    LoginSession session;

    @FXML
    private Button editOfferButton;

    @FXML
    private Button removeOfferButton;

    @FXML
    private Button editSeekButton;

    @FXML
    private Button removeSeekButton;

    @FXML
    private Button seekButton;

    @FXML
    private TableView offerTable;

    @FXML
    private TableView seekTable;

    @FXML
    private Text loginLabel;

    @FXML
    private void handleSeekButton(ActionEvent event) {
        try {
            Stage stage = (Stage) seekButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/DriverSeek.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            final Node source = (Node) event.getSource();
            final Stage stage2 = (Stage) source.getScene().getWindow();
            stage2.close();
        } catch (IOException ioex) {
            System.out.println(ioex);
        }
    }

    private void displayOfferTableData() {
        offerTable.setEditable(true);
        offerTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        ObservableList<ObservableList> data = FXCollections.observableArrayList();

        String tableDataQuery = "SELECT * FROM `" + db.getDatabaseName() + "`.`trip_offering`;";
        try {
            ResultSet rs = db.executeQuery(tableDataQuery);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                offerTable.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    System.out.println(row);
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);
            }
            offerTable.setItems(data);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void displaySeekTableData() {
        seekTable.setEditable(true);
        seekTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        ObservableList<ObservableList> data = FXCollections.observableArrayList();

        String tableDataQuery = "SELECT * FROM `" + db.getDatabaseName() + "`.`trip_seeking`;";
        try {
            ResultSet rs = db.executeQuery(tableDataQuery);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                seekTable.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    System.out.println(row);
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);
            }
            seekTable.setItems(data);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        db = new SuberDB();
        session = Suber.session;
        loginLabel.setText("Logged in as:\n" + session.getEmail());
        displayOfferTableData();
        displaySeekTableData();
    }

}
