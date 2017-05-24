import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage stage; // Primary stage.
    public static Controller controller; // Primary controller.

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Set primary stage.
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLs/zipBrute.fxml"));
        primaryStage.setTitle("ZipBrute");
        Scene scene = new Scene(loader.load(), 550, 340);
        // Set stylesheet.
        scene.getStylesheets().add(String.valueOf(getClass().getResource("/Themes/Dark.css")));
        primaryStage.setScene(scene);
        // Set primary controller.
        controller = loader.getController();
        primaryStage.setMinHeight(340 + 22);
        primaryStage.setMinWidth(340);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
