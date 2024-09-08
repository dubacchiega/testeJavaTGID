package infra;

import usuarios.Empresa;

/**
 * Classe DAO específica para a entidade {@link Empresa}.
 *
 * Esta classe estende a classe genérica {@link DAO} e fornece uma implementação específica para
 * operações de persistência com a entidade {@link Empresa}.
 */
public class DAOEmpresa extends DAO<Empresa>{

    /**
     * Construtor padrão que inicializa o DAO para a entidade {@link Empresa}.
     *
     * Este construtor chama o construtor da superclasse {@link DAO} passando a classe {@link Empresa}.
     */
    public DAOEmpresa(){
        super(Empresa.class);
    }
}
