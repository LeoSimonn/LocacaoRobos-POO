package aplicacao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import dados.*;



public class NovoClienteController {

    private CadastroClientes cadastroClientes;

    private StatusText statusText;
   
    @FXML
    private ChoiceBox<String> perfilChoiceBox;
    
    @FXML
    private Label anoLabel;
    
    @FXML
    private TextField anoTextField;
    
    @FXML
    private Label cpfLabel;
    
    @FXML
    private TextField cpfTextField;

    @FXML
    private TextField codigoTextField;

    @FXML
    private TextField nomeTextField;

    @FXML
    private Button limparButton;

    @FXML
    private Button salvarButton;

    @FXML
    private Button mostrarClientesButton;

    @FXML
    public void initialize() {
        perfilChoiceBox.getItems().addAll("Pessoa Jurídica", "Pessoa Física");

        perfilChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            
            if ("Pessoa Jurídica".equals(newValue)) {
                anoLabel.setVisible(true);
                anoTextField.setVisible(true);
                cpfLabel.setVisible(false);
                cpfTextField.setVisible(false);

            } else if ("Pessoa Física".equals(newValue)) {
                anoLabel.setVisible(false);
                anoTextField.setVisible(false);
                cpfLabel.setVisible(true);
                cpfTextField.setVisible(true);
            }
        });

        limparButton.setOnAction(event -> limparCampos());

        salvarButton.setOnAction(event -> criarCliente());

        mostrarClientesButton.setOnAction(event -> mostrarClientes());
    }

    public void setCadastroClientes(CadastroClientes cadastroClientes) {
        this.cadastroClientes = cadastroClientes;
    }

    public void setStatusText (StatusText statusText) {
        this.statusText = statusText;
    }

    private void limparCampos() {
        codigoTextField.clear();
        nomeTextField.clear();
        perfilChoiceBox.getSelectionModel().clearSelection();
        anoTextField.clear();
        cpfTextField.clear();
        anoLabel.setVisible(false);
        anoTextField.setVisible(false);
        cpfLabel.setVisible(false);
        cpfTextField.setVisible(false);
        statusText.setText("");
    }

    private void mostrarClientes(){

        String dadosCliente = "LISTA DE CLIENTES | [◉_◉] ACME ROBOTS®" + "\n";

        for (Cliente c: cadastroClientes) {
          
            if (c instanceof Individual) {
                Individual i = (Individual) c;
                dadosCliente += "Cod: " + i.getCodigo() + " | " + i.getNome()  + " | " + i.getCpf() + " | Individual " + "\n";
            } 
            
            else if (c instanceof Empresarial) {
                Empresarial e = (Empresarial) c;
                dadosCliente += "Cod: " + e.getCodigo() + " | " + e.getNome()  + " | " + e.getAno() + " | Empresarial " + "\n";
            }
        }

        statusText.setText(dadosCliente);
    }

    private void criarCliente() {
        String codigo = codigoTextField.getText();
        String nome = nomeTextField.getText();
        String perfil = perfilChoiceBox.getValue();
        int codigoInt = 0;

        try {
        
            codigoInt = Integer.parseInt(codigo);
        
        } catch (IllegalArgumentException e) {

            statusText.setText("Preencha os dados corretamente. Cliente não cadastrado!");
            return;
        }
        

        if (perfil.equalsIgnoreCase("Pessoa Física")) {
            String cpf = cpfTextField.getText();               
            Individual individual = new Individual(codigoInt, nome, cpf);
            
            if (cadastroClientes.adicionarCliente(individual)){
                statusText.setText("Cliente Individual Cadastrado com Sucesso!");
            }

            else {
                statusText.setText("Cliente já existente no registro!");
            }
            
        } 
        
        else if (perfil.equalsIgnoreCase("Pessoa Jurídica")) {
            String ano = anoTextField.getText();
            int anoInt = 0;

            try {
                anoInt = Integer.parseInt(ano);

            } catch (IllegalArgumentException e) {
                
                statusText.setText("Preencha os dados corretamente. Cliente não cadastrado!");
                return;

            }
            
            Empresarial empresarial = new Empresarial(codigoInt, nome, anoInt);
            
            if (cadastroClientes.adicionarCliente(empresarial)) {
                statusText.setText("Cliente Empresarial Cadastrado com Sucesso!");
            }

            else {
                statusText.setText("Cliente já existente no registro!");
            }
        }
    }

}
