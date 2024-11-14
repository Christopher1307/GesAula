package dad.gesaula.Controllers;

import dad.gesaula.model.Alumno;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AlumnosController {


    //model
    private final ListProperty<Alumno> alumnos = new SimpleListProperty<>(FXCollections.observableArrayList());


    @FXML
    private TableView<Alumno> EstudianteTablaView;

    @FXML
    private TableColumn<Alumno, Number> birthdateTableColumn;

    @FXML
    private TableColumn<Alumno, String> lastnameTableColumn;

    @FXML
    private TableColumn<Alumno, String> nameTableColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private VBox emptyBox;

    @FXML
    private Button newButton;

    @FXML
    private SplitPane root;

    @FXML
    private GridPane tablaGridPane;


    public AlumnosController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlumnosView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onDeleteAction(ActionEvent event) {

    }

    @FXML
    void onNewAction(ActionEvent event) {

    }

}
