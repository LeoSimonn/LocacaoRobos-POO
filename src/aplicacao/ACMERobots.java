package aplicacao;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import dados.*;

import java.io.IOException;

public class ACMERobots {

    @FXML
    private VBox mainVBox;

    @FXML
    private MenuItem novoClienteMenuItem;

    @FXML
    private MenuItem novoRoboMenuItem;

    @FXML
    private MenuItem sairMenuItem;

    @FXML
    private AnchorPane leftAnchorPane;

    @FXML
    private AnchorPane rightAnchorPane;

    private CadastroClientes cadastroClientes;
    private StatusText statusText;

    @FXML
    public void initialize() {
        cadastroClientes = new CadastroClientes();
        statusText = new StatusText();
        novoClienteMenuItem.setOnAction(event -> loadNovoClienteLayout());
        novoRoboMenuItem.setOnAction(event -> loadNovoRoboLayout());
        sairMenuItem.setOnAction(event -> Platform.exit() );

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/status.fxml"));
            AnchorPane statusPane = loader.load();
        
            StatusController controller = loader.getController();
            controller.setStatusText(statusText);

            rightAnchorPane.getChildren().setAll(statusPane);
        
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadNovoClienteLayout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/novoCliente.fxml"));
            AnchorPane novoClientePane = loader.load();

            NovoClienteController controller = loader.getController();
            controller.setCadastroClientes(cadastroClientes);
            controller.setStatusText(statusText);
           

            leftAnchorPane.getChildren().setAll(novoClientePane);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadNovoRoboLayout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/novoRobo.fxml"));
            AnchorPane novoRoboPane = loader.load();

            leftAnchorPane.getChildren().setAll(novoRoboPane);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CadastroClientes getCadastroClientes() {
        return this.cadastroClientes;
    }
}
