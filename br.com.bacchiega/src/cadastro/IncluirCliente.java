package cadastro;

import infra.DAOCliente;
import usuarios.Cliente;

import java.util.Scanner;

/**
 * Classe responsável por incluir um novo cliente no sistema.
 *
 * Esta classe contém o método principal {@code main} que cria uma instância de {@link Cliente}
 * e a persiste no banco de dados usando o DAO {@link DAOCliente}.
 */
public class IncluirCliente {

    /**
     * Método principal para a inclusão de um cliente no banco de dados.
     *
     * Cria uma instância de {@link Cliente} com dados predefinidos e utiliza
     * o {@link DAOCliente} para realizar a inclusão de forma atômica, garantindo
     * que a transação seja realizada completamente ou não seja realizada.
     *
     * @param args Argumentos da linha de comando (não utilizados neste caso).
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Solicita e lê o nome do cliente
        System.out.println("Informe o nome do cliente: ");
        String nome = sc.nextLine();

        // Solicita e lê o CPF do cliente
        System.out.println("Informe o CPF do cliente: ");
        String cpf = sc.nextLine();

        // Solicita e lê o saldo do cliente
        System.out.println("Informe o saldo do cliente: ");
        double saldo = sc.nextDouble();

        // Criação de uma nova instância de Cliente com dados fornecidos.
        Cliente cliente = new Cliente(nome, cpf, saldo);

        // Criação do DAOCliente para gerenciar a persistência do cliente.
        DAOCliente dao = new DAOCliente();

        // Inclusão do cliente no banco de dados de forma atômica.
        dao.incluirAtomico(cliente);

        // Fecha o Scanner
        sc.close();
    }
}
