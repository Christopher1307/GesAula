package dad.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.Mains.GesAulaApp;
import dad.model.Grupo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class ToolbarController implements Initializable {

    // model
    private final StringProperty nombreFichero = new SimpleStringProperty();
    private final ObjectProperty<Grupo> grupo = new SimpleObjectProperty<>();

    // view
    @FXML
    private ToolBar view;

    @FXML
    private Button nuevoButton;

    @FXML
    private TextField ficheroText;

    @FXML
    private Button guardarButton;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombreFichero.bind(ficheroText.textProperty());
    }

    public ToolBar getView() {
        return view;
    }

    @FXML
    void onSaveAction(ActionEvent event) {
        String ruta = nombreFichero.get();
        if (ruta != null && !ruta.isEmpty()) {
            try {
                getGrupo().save(new File(ruta));
                showAlert(AlertType.INFORMATION, "Guardar grupo", "Se ha guardado el grupo correctamente.",
                        "El grupo " + getGrupo().getDenominacion() + " se ha guardado en el fichero '" + ruta + "'.");
            } catch (Exception ex) {
                showAlert(AlertType.ERROR, "Guardar grupo", "Error al guardar el grupo.", ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            showAlert(AlertType.ERROR, "Guardar grupo", "Error al guardar el grupo.",
                    "Debe especificar la ruta del fichero donde se guardará el grupo.");
        }
    }

    @FXML
    void onNewAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.initOwner(GesAulaApp.primaryStage);
        alert.setTitle("Nuevo grupo");
        alert.setHeaderText("Va a crear un grupo nuevo.");
        alert.setContentText("¿Está seguro?");
        Optional<ButtonType> opcion = alert.showAndWait();
        if (opcion.isPresent() && opcion.get().equals(ButtonType.OK)) {
            grupo.set(new Grupo());
        }
    }

    private void showAlert(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.initOwner(GesAulaApp.primaryStage);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public ObjectProperty<Grupo> grupoProperty() {
        return this.grupo;
    }

    public Grupo getGrupo() {
        return this.grupoProperty().get();
    }

    public void setGrupo(final Grupo grupo) {
        this.grupoProperty().set(grupo);
    }
}