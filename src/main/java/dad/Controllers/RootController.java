package dad.Controllers;

import dad.model.Grupo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    // model
    private ObjectProperty<Grupo> grupo = new SimpleObjectProperty<>();

    @FXML
    private Tab alumnosTab;

    @FXML
    private Tab grupoTab;

    @FXML
    private BorderPane root;

    // controllers
    private ToolbarController toolbarController;
    private GrupoController grupoController;
    private AlumnosController alumnosController;

    public RootController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
        loader.setController(this);
        loader.load();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Load ToolbarView
            FXMLLoader toolbarLoader = new FXMLLoader(getClass().getResource("/fxml/ToolbarView.fxml"));
            Pane toolbarView = toolbarLoader.load();
            toolbarController = toolbarLoader.getController();
            root.setTop(toolbarView);

            // Load AlumnosView
            FXMLLoader alumnosLoader = new FXMLLoader(getClass().getResource("/fxml/AlumnosView.fxml"));
            Pane alumnosView = alumnosLoader.load();
            alumnosController = alumnosLoader.getController();
            alumnosTab.setContent(alumnosView);

            // Load GrupoView
            FXMLLoader grupoLoader = new FXMLLoader(getClass().getResource("/fxml/GrupoView.fxml"));
            Pane grupoView = grupoLoader.load();
            grupoController = grupoLoader.getController();
            grupoTab.setContent(grupoView);

            // bindings
            toolbarController.grupoProperty().bindBidirectional(grupo);
            grupoController.grupoProperty().bind(grupo);

            // listeners
            grupo.addListener(this::onGrupoChanged);

            grupo.set(new Grupo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onGrupoChanged(ObservableValue<? extends Grupo> observable, Grupo oldValue, Grupo newValue) {
        if (oldValue != null) {
            alumnosController.alumnosProperty().unbind();
        }
        if (newValue != null) {
            alumnosController.alumnosProperty().bind(newValue.alumnosProperty());
        }
    }

    public Parent getView() {
        return root;
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