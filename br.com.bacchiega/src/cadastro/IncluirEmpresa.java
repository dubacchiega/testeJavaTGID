package cadastro;

import infra.DAOEmpresa;
import usuarios.Empresa;

import java.util.Scanner;

/**
 * Classe responsável por incluir uma nova empresa no sistema.
 *
 * Esta classe contém o método principal {@code main} que cria uma instância de {@link Empresa}
 * e a persiste no banco de dados usando o DAO {@link DAOEmpresa}.
 */
public class IncluirEmpresa {

    /**
     * Método principal para a inclusão de uma empresa no banco de dados.
     *
     * Solicita ao usuário informações sobre a empresa, como nome, CNPJ, taxa e saldo.
     * Cria uma instância de {@link Empresa} com os dados fornecidos e utiliza
     * o {@link DAOEmpresa} para realizar a inclusão de forma atômica, garantindo
     * que a transação seja realizada completamente ou não seja realizada.
     *
     * @param args Argumentos da linha de comando (não utilizados neste caso).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Solicita e lê o nome da empresa
        System.out.println("Informe o nome da empresa: ");
        String nomeEmpresa = sc.nextLine();

        // Solicita e lê o CNPJ da empresa
        System.out.println("Informe o CNPJ da empresa: ");
        String cnpj = sc.nextLine();

        // Solicita e lê a taxa da empresa
        System.out.println("Informe a taxa da empresa: ");
        double taxa = sc.nextDouble();

        // Solicita e lê o saldo da empresa
        System.out.println("Informe o saldo da empresa: ");
        double saldo = sc.nextDouble();

        // Cria uma nova instância de Empresa com os dados fornecidos
        Empresa empresa = new Empresa(nomeEmpresa, cnpj, taxa, saldo);

        // Cria uma instância do DAOEmpresa para gerenciar a persistência da empresa
        DAOEmpresa dao = new DAOEmpresa();

        // Inclui a empresa no banco de dados de forma atômica
        dao.incluirAtomico(empresa);

        // Fecha o Scanner
        sc.close();
    }
}
