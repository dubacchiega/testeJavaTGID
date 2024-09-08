import transacao.Deposito;
import transacao.Saque;

import java.util.Scanner;

/**
 * Classe principal para execução de operações de depósito e saque.
 *
 * Esta classe contém o método principal {@code main} que permite ao usuário selecionar entre
 * operações de depósito e saque, e realiza a transação correspondente utilizando as classes
 * {@link Deposito} e {@link Saque}.
 */
public class Main {
    /**
     * Método principal para execução interativa de operações de depósito e saque.
     *
     * Permite ao usuário escolher entre realizar um saque ou um depósito. Solicita os dados necessários
     * (valor, IDs do cliente e da empresa) e executa a transação correspondente.
     *
     * @param args Argumentos da linha de comando (não utilizados neste caso).
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1 - Saque\n2 - Depósito");
        int opcao = sc.nextInt();

        if (opcao == 1) {
            // Opção de saque selecionada
            System.out.println("Opção selecionada: Saque");
            System.out.println("Digite o valor do saque: ");
            double valor = sc.nextDouble();

            System.out.println("Digite o ID do Cliente: ");
            Long id_cliente = sc.nextLong();

            System.out.println("Digite o ID da Empresa: ");
            Long id_empresa = sc.nextLong();

            // Criação de uma instância de Saque e execução da operação
            Saque saque = new Saque(valor, id_cliente, id_empresa);
            saque.exec();

        } else if (opcao == 2) {
            // Opção de depósito selecionada
            System.out.println("Opção selecionada: Depósito");
            System.out.println("Digite o valor do depósito: ");
            double valor = sc.nextDouble();

            System.out.println("Digite o ID do Cliente: ");
            Long id_cliente = sc.nextLong();

            System.out.println("Digite o ID da Empresa: ");
            Long id_empresa = sc.nextLong();

            // Criação de uma instância de Depósito e execução da operação
            Deposito deposito = new Deposito(valor, id_cliente, id_empresa);
            deposito.exec();
        } else {
            // Opção inválida selecionada
            System.out.println("Operação inválida!");
        }
    }
}
