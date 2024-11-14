package dad.gesaula.Mains;

import dad.gesaula.Controllers.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GesAulaApp  extends Application  {
    private RootController rootController = new RootController();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("GesAula");
        primaryStage.setScene(new Scene(rootController.getRoot()));
        primaryStage.show();
    }
}
