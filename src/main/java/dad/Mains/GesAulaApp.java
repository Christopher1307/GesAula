package dad.Mains;


import dad.Controllers.RootController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GesAulaApp extends Application {

    public static Stage primaryStage;

    private RootController rootController;


//    public void start(Stage primaryStage) throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
//        Parent root = loader.load(); // Aquí se inicializa el controlador automáticamente
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GesAulaApp.primaryStage = primaryStage;

        rootController = new RootController();

        Scene scene = new Scene(rootController.getView(), 640, 480);

        primaryStage.getIcons().add(new Image(getClass().getResource("/images/app-icon-64x64.png").toExternalForm()));
        primaryStage.setTitle("GesAula");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}


