package transacao;

import infra.DAOCliente;
import infra.DAOEmpresa;
import usuarios.Cliente;
import usuarios.Empresa;

/**
 * Representa uma operação de saque entre um cliente e uma empresa.
 *
 * Esta classe gerencia a retirada de um valor da conta do cliente, aplicando uma taxa sobre o valor sacado.
 */
public class Saque {

    private double valor;
    private Long id_cliente;
    private Long id_empresa;
    private DAOCliente daoCliente = new DAOCliente();
    private DAOEmpresa daoEmpresa = new DAOEmpresa();

    /**
     * Construtor para a criação de uma nova instância da classe {@link Saque}.
     *
     * @param valor O valor a ser sacado.
     * @param id_cliente O identificador do cliente que está realizando o saque.
     * @param id_empresa O identificador da empresa que receberá a taxa do saque.
     */
    public Saque(double valor, Long id_cliente, Long id_empresa) {
        this.valor = valor;
        this.id_cliente = id_cliente;
        this.id_empresa = id_empresa;
    }

    /**
     * Obtém a instância da empresa com base no identificador fornecido.
     *
     * @return A instância da {@link Empresa} correspondente ao identificador.
     */
    private Empresa obterEmpresa() {
        return daoEmpresa.obterPorID(id_empresa);
    }

    /**
     * Obtém a instância do cliente com base no identificador fornecido.
     *
     * @return A instância da {@link Cliente} correspondente ao identificador.
     */
    private Cliente obterCliente() {
        return daoCliente.obterPorID(id_cliente);
    }

    /**
     * Obtém o valor do saque.
     *
     * @return O valor do saque.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor do saque.
     *
     * @param valor O valor a ser definido para o saque.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Valida se o saque pode ser realizado.
     *
     * Verifica se o valor do saque não excede o saldo disponível da empresa.
     *
     * @return {@code true} se o saque for válido; {@code false} caso contrário.
     */
    private boolean validate() {
        return valor <= obterEmpresa().getSaldo();
    }

    /**
     * Executa o saque, transferindo o valor da empresa para o cliente, aplicando a taxa.
     *
     * Se o saque for válido, o saldo do cliente é diminuído com o valor líquido e o saldo da empresa é aumentado com a taxa.
     * As transações são abertas e fechadas para garantir a integridade dos dados.
     */
    public void exec() {
        if (validate()) {
            double taxa = valor * obterEmpresa().getTaxa();
            double valorComTaxa = valor - taxa;

            Cliente cliente = obterCliente();
            Empresa empresa = obterEmpresa();

            cliente.setSaldo(cliente.getSaldo() - valorComTaxa);
            empresa.setSaldo(empresa.getSaldo() + taxa);

            daoCliente.abrirTransacao().modificar(cliente).fecharTransacao().fechar();
            daoEmpresa.abrirTransacao().modificar(empresa).fecharTransacao().fechar();
        }
    }
}
