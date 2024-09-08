package infra;

import usuarios.Cliente;

/**
 * Classe DAO específica para a entidade {@link Cliente}.
 *
 * Esta classe estende a classe genérica {@link DAO} e fornece uma implementação específica para
 * operações de persistência com a entidade {@link Cliente}.
 */
public class DAOCliente extends DAO<Cliente> {

    /**
     * Construtor padrão que inicializa o DAO para a entidade {@link Cliente}.
     *
     * Este construtor chama o construtor da superclasse {@link DAO} passando a classe {@link Cliente}.
     */
    public DAOCliente(){

        super(Cliente.class);
    }
}
