package Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe responsável pela validação de números de CPF (Cadastro de Pessoas Físicas).
 *
 * Esta classe oferece funcionalidades para verificar se um CPF é válido com base nas regras de cálculo dos dígitos verificadores.
 */
public class Cpf {

    private String cpf;
    private int tamanho;
    List<Integer> cpfNum;
    int lastNum;
    int penultimateNumb;

    /**
     * Construtor para a criação de uma instância da classe {@link Cpf}.
     *
     * @param cpf O CPF a ser validado, deve estar no formato de 11 dígitos numéricos.
     */
    public Cpf(String cpf) {
        this.cpf = cpf;
        tamanho = cpf.length();
        cpfNum = Arrays.stream(cpf.split("")).map(n -> Integer.parseInt(n)).toList();
        lastNum = cpfNum.get(cpfNum.size() - 1);
        penultimateNumb = cpfNum.get(cpfNum.size() - 2);
    }

    /**
     * Verifica se o CPF é uma repetição de um único dígito (ex: "11111111111").
     *
     * @return {@code true} se o CPF for uma repetição de dígitos; {@code false} caso contrário.
     */
    public boolean numberRepeat() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tamanho; i++) {
            sb.append(firstDigit());
        }
        String resposta = sb.toString();

        return cpf.equals(resposta);
    }

    /**
     * Obtém o primeiro dígito do CPF.
     *
     * @return O primeiro dígito do CPF como uma string.
     */
    private String firstDigit() {
        char caracter = cpf.charAt(0);
        return String.valueOf(caracter);
    }

    /**
     * Valida um dos dígitos verificadores do CPF.
     *
     * @param contador O valor inicial do peso para o cálculo do dígito verificador.
     * @return O dígito verificador calculado.
     */
    private int validate(int contador) {
        List<Integer> cpfNumCopy = new ArrayList<>(cpfNum);
        cpfNumCopy.remove(cpfNumCopy.size() - 1);
        cpfNumCopy.remove(cpfNumCopy.size() - 1);
        int resultado = 0;
        for (int n : cpfNumCopy) {
            int num_peso = n * contador;
            resultado += num_peso;
            contador -= 1;
        }
        resultado *= 10;
        resultado %= 11;

        return resultado > 9 ? 0 : resultado;
    }

    /**
     * Valida o CPF verificando a repetição de dígitos e os dígitos verificadores.
     *
     * @return {@code true} se o CPF for válido; {@code false} caso contrário.
     */
    public boolean validateFinal() {
        if (numberRepeat()) {
            return false;
        }
        if (cpfNum.size() != 11) {
            return false;
        }
        int validation1 = validate(10);
        int validation2 = validate(11);
        if (validation1 == penultimateNumb && validation2 == lastNum) {
            return true;
        }
            return false;
    }
}
