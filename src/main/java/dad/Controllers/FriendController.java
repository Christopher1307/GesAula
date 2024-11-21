package dad.Controllers;



import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dad.model.Alumno;
import dad.model.Sexo;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class FriendController implements Initializable {

    // model

    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty apellidos = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> nacimiento = new SimpleObjectProperty<>();
    private final ObjectProperty<Sexo> sexo = new SimpleObjectProperty<>();
    private final BooleanProperty repite = new SimpleBooleanProperty();

    private final ObjectProperty<Alumno> alumno = new SimpleObjectProperty<>();

    // view

    @FXML
    private GridPane view;

    @FXML
    private TextField nombreText;

    @FXML
    private TextField apellidosText;

    @FXML
    private DatePicker nacimientoDatePicker;

    @FXML
    private ComboBox<Sexo> sexoCombo;

    @FXML
    private CheckBox repiteCheck;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // bindings

        nombreText.textProperty().bindBidirectional(nombre);
        apellidosText.textProperty().bindBidirectional(apellidos);
        nacimientoDatePicker.valueProperty().bindBidirectional(nacimiento);
        sexoCombo.valueProperty().bindBidirectional(sexo);
        repiteCheck.selectedProperty().bindBidirectional(repite);

        sexoCombo.getItems().addAll(Sexo.values());

        // listeners

        alumno.addListener(this::onAlumnoChanged);

    }

    private void onAlumnoChanged(ObservableValue<? extends Alumno> o, Alumno ov, Alumno nv) {

        if (ov != null) {

            nombre.unbindBidirectional(ov.nombreProperty());
            apellidos.unbindBidirectional(ov.apellidosProperty());
            nacimiento.unbindBidirectional(ov.fechaNacimientoProperty());
            sexo.unbindBidirectional(ov.sexoProperty());
            repite.unbindBidirectional(ov.repiteProperty());

        }

        if (nv != null) {

            nombre.bindBidirectional(nv.nombreProperty());
            apellidos.bindBidirectional(nv.apellidosProperty());
            nacimiento.bindBidirectional(nv.fechaNacimientoProperty());
            sexo.bindBidirectional(nv.sexoProperty());
            repite.bindBidirectional(nv.repiteProperty());

        }


    }

    public GridPane getView() {
        return view;
    }

    public ObjectProperty<Alumno> alumnoProperty() {
        return this.alumno;
    }

    public Alumno getAlumno() {
        return this.alumnoProperty().get();
    }

    public void setAlumno(final Alumno alumno) {
        this.alumnoProperty().set(alumno);
    }

}