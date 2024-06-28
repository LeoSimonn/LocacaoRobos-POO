package dados;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class CadastroClientes implements Iterable <Cliente> {

    private Set<Cliente> clientes;

    public CadastroClientes() {
        this.clientes = new TreeSet<>(Comparator.comparingInt(Cliente::getCodigo));
    }

    public boolean adicionarCliente(Cliente cliente) {
        
        for (Cliente c : clientes) {
            
            if (c.getCodigo() == cliente.getCodigo()) {
                return false; 
            }
        }

        return clientes.add(cliente);
    }

    public void removerCliente(int codigo) {
        Cliente clienteParaRemover = null;
        for (Cliente cliente : clientes) {
            if (cliente.getCodigo() == codigo) {
                clienteParaRemover = cliente;
                break;
            }
        }
        if (clienteParaRemover != null) {
            clientes.remove(clienteParaRemover);
        }
    }

    public Cliente consultarCliente(int codigo) {
        for (Cliente cliente : clientes) {
            if (cliente.getCodigo() == codigo) {
                return cliente;
            }
        }
        return null;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    @Override
    public Iterator<Cliente> iterator() {
        return clientes.iterator();
    }
}
