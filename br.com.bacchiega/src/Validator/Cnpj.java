package Validator;

import java.util.InputMismatchException;

/**
 * Classe responsável pela validação de números de CNPJ (Cadastro Nacional da Pessoa Jurídica).
 *
 * Esta classe fornece um método estático para verificar a validade de um CNPJ. O CNPJ deve ter 14 dígitos e
 * os dois últimos dígitos são dígitos verificadores calculados com base nos 12 primeiros dígitos.
 */
public class Cnpj {

    /**
     * Verifica se o CNPJ fornecido é válido.
     *
     * O método realiza as seguintes verificações:
     * - O CNPJ deve ter exatamente 14 dígitos.
     * - O CNPJ não pode ser uma sequência de dígitos iguais (ex: "11111111111111").
     * - O método calcula os dígitos verificadores com base nos 12 primeiros dígitos e compara com os dígitos fornecidos.
     *
     * @param cnpj O CNPJ a ser validado, deve estar no formato de 14 dígitos.
     * @return {@code true} se o CNPJ for válido; {@code false} caso contrário.
     */
    public static boolean isCNPJ(String cnpj) {

        // Verifica se o CNPJ é uma sequência de dígitos iguais ou se o comprimento é diferente de 14
        char caracter = cnpj.charAt(0);
        String firstDigit = String.valueOf(caracter);

        int tamanho = cnpj.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tamanho; i++) {
            sb.append(firstDigit);
        }
        String resposta = sb.toString();
        if (cnpj.equals(resposta) || (tamanho != 14))
            return false;

        char dig13, dig14;
        int sm, i, r, num, peso;

        try {
            // Calcula o primeiro dígito verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else
                dig13 = (char) ((11 - r) + 48);

            // Calcula o segundo dígito verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else
                dig14 = (char) ((11 - r) + 48);

            // Compara os dígitos verificadores calculados com os fornecidos
            if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
                return true;
            else
                return false;
        } catch (InputMismatchException erro) {
            // Retorna false se ocorrer qualquer exceção durante o processo de validação
            return false;
        }
    }
}
