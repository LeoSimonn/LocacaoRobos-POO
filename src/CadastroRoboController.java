import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CadastroRoboController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField tipoField;

    @FXML
    private TextArea mensagemArea;

    @FXML
    private Button cadastrarButton;

    @FXML
    private Button limparButton;

    @FXML
    private Button mostrarTodosButton;

    @FXML
    private Button fecharButton;

    private List<CadastroRobo> listaRobos = new ArrayList<>();

    @FXML
    private void initialize() {
        cadastrarButton.setOnAction(event -> cadastrarRobo());
        limparButton.setOnAction(event -> limparCampos());
        mostrarTodosButton.setOnAction(event -> mostrarTodosRobos());
        fecharButton.setOnAction(event -> fecharAplicacao());
    }

    @FXML
    private void cadastrarRobo() {
        try {
            String id = idField.getText();
            String nome = nomeField.getText();
            String tipo = tipoField.getText();

            // Validação dos campos
            if (id.isEmpty() || nome.isEmpty() || tipo.isEmpty()) {
                mensagemArea.setText("Por favor, preencha todos os campos.");
                return;
            }

            // Validação para garantir que o ID seja um número
            if (!id.matches("\\d+")) {
                mensagemArea.setText("O campo ID deve conter apenas números.");
                return;
            }

            // Validação para garantir que o nome e tipo sejam strings
            if (!nome.matches("[a-zA-Z]+") || !tipo.matches("[a-zA-Z]+")) {
                mensagemArea.setText("Os campos Nome e Tipo devem conter apenas letras.");
                return;
            }

            // Verificar se o ID já existe
            for (CadastroRobo robo : listaRobos) {
                if (robo.getId().equals(id)) {
                    mensagemArea.setText("Já existe um robô com o ID fornecido.");
                    return;
                }
            }

            CadastroRobo novoRobo = new CadastroRobo(id, nome, tipo);

            // Inserir o novo robô na posição correta para manter a lista ordenada
            int index = 0;
            while (index < listaRobos.size() && Integer.parseInt(listaRobos.get(index).getId()) < Integer.parseInt(id)) {
                index++;
            }
            listaRobos.add(index, novoRobo);
            mensagemArea.setText("Robô cadastrado: " + novoRobo);

            limparCampos();

            // Exibir mensagem de confirmação na área de texto
            mensagemArea.setText("Robô cadastrado com sucesso!");

        } catch (Exception e) {
            mensagemArea.setText("Erro ao cadastrar robô: " + e.getMessage());
        }
    }

    @FXML
    private void limparCampos() {
        idField.clear();
        nomeField.clear();
        tipoField.clear();
        mensagemArea.clear();
    }

    @FXML
    private void mostrarTodosRobos() {
        StringBuilder sb = new StringBuilder();
        for (CadastroRobo robo : listaRobos) {
            sb.append(robo).append("\n");
        }
        mensagemArea.setText(sb.toString());
    }

    @FXML
    private void fecharAplicacao() {
        Stage stage = (Stage) fecharButton.getScene().getWindow();
        stage.close();
    }
}
