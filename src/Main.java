import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
    public static void main(String[] args) throws Exception {
        
        launch(args);
 
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/layout/mainLayout.fxml"));
        Parent root = fxmlloader.load(); 
        Scene tela = new Scene(root);

        primaryStage.setTitle(" [◉_◉] ACME ROBOTS® ");
        primaryStage.setScene(tela);
        primaryStage.show();
    }
    
}
