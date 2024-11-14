package dad.gesaula.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class RootController {

    @FXML
    private Tab alumnosTab;

    @FXML
    private Tab grupoTab;

    @FXML
    private BorderPane root;

    public RootController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        try {
            // Load AlumnosView
            FXMLLoader alumnosLoader = new FXMLLoader(getClass().getResource("/fxml/AlumnosView.fxml"));
            Pane alumnosView = alumnosLoader.load();
            alumnosTab.setContent(alumnosView);

            // Load GrupoView
            FXMLLoader grupoLoader = new FXMLLoader(getClass().getResource("/fxml/GrupoView.fxml"));
            Pane grupoView = grupoLoader.load();
            grupoTab.setContent(grupoView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }
}