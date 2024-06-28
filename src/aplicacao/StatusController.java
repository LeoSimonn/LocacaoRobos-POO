package aplicacao;

import dados.StatusText;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class StatusController {

    private StatusText statusText;

    @FXML
    private TextArea statusTextArea;

    @FXML
    private void initialize() {
        // Inicialização, se necessário
    }

    public void setStatusText (StatusText statusText) {
        statusTextArea.textProperty().unbind();
        this.statusText = statusText;
        statusTextArea.textProperty().bind(statusText.textProperty());
    }

    public void addMessage(String message) {
        statusTextArea.appendText(message + "\n");
    }

    public void clearMessages() {
        statusTextArea.clear();
    }
}
