package dad.Controllers;

import dad.Mains.GesAulaApp;
import dad.model.Alumno;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AlumnosController {

    // model
    private final ListProperty<Alumno> alumnos = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final ObjectProperty<Alumno> selectedAlumno = new SimpleObjectProperty<>();

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
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize table view
        EstudianteTablaView.itemsProperty().bind(alumnos);
        selectedAlumno.bind(EstudianteTablaView.getSelectionModel().selectedItemProperty());

        // cell factories
        nameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        lastnameTableColumn.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());
        birthdateTableColumn.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().fechaNacimientoProperty().get().toEpochDay());
        });

        // Add listener to update selectedAlumno
        selectedAlumno.addListener(this::onSelectedAlumnoChanged);
    }

    private void onSelectedAlumnoChanged(ObservableValue<? extends Alumno> observable, Alumno oldValue, Alumno newValue) {
        if (oldValue != null) {
            // Unbind old value
            tablaGridPane.visibleProperty().unbind();
            tablaGridPane.visibleProperty().bind(emptyBox.visibleProperty());
        }
        if (newValue != null) {
            // Bind new value
            tablaGridPane.visibleProperty().unbind();
            tablaGridPane.visibleProperty().bind(emptyBox.visibleProperty().not());
        }
    }

    public SplitPane getView() {
        return root;
    }

    @FXML
    void onDeleteAction(ActionEvent event) {
        Alumno seleccionado = EstudianteTablaView.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Eliminar Alumno", "No se puede eliminar el alumno.", "Por favor, seleccione un alumno para eliminar.");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(GesAulaApp.primaryStage);
            alert.setTitle("Eliminar Alumno");
            alert.setHeaderText("Está a punto de eliminar al alumno: " + seleccionado.getNombre());
            alert.setContentText("¿Está seguro de que desea eliminar este alumno?");
            Optional<ButtonType> opcion = alert.showAndWait();
            if (opcion.isPresent() && opcion.get() == ButtonType.OK) {
                alumnos.remove(seleccionado);
            }
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String cabecera, String contenido) {
        Alert alert = new Alert(tipo);
        alert.initOwner(GesAulaApp.primaryStage);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    void onNewAction(ActionEvent event) {
        Alumno nuevo = new Alumno();
        nuevo.setNombre("Sin nombre");
        nuevo.setApellidos("Sin apellidos");
        this.alumnos.add(nuevo);
    }

    public ListProperty<Alumno> alumnosProperty() {
        return alumnos;
    }
}