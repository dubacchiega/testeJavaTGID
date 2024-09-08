package usuarios;

import Validator.Cpf;

import javax.persistence.*;

/**
 * Representa um cliente no sistema.
 *
 * Esta classe é uma entidade JPA que mapeia a tabela 'Cliente' no banco de dados.
 *
 * Atributos:
 * - id: Identificador único do cliente.
 * - nome: Nome completo do cliente.
 * - cpf: CPF do cliente, deve ser válido.
 * - saldo: Saldo disponível do cliente, com precisão de até duas casas decimais.
 */
@Entity
public class Cliente {

    /**
     * Identificador único do cliente.
     *
     * É a chave primária da entidade e é gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome completo do cliente.
     *
     * Não pode ser nulo.
     */
    @Column(nullable = false)
    private String nome;

    /**
     * CPF do cliente.
     *
     * Não pode ser nulo.
     */
    @Column(nullable = false)
    private String cpf;

    /**
     * Saldo disponível do cliente.
     *
     * Não pode ser nulo e tem precisão de 11 dígitos no total, com 2 casas decimais.
     */
    @Column(nullable = false, precision = 11, scale = 2)
    private Double saldo;

    /**
     * Construtor padrão para a criação de instâncias da entidade {@link Cliente}.
     */
    public Cliente() {
    }

    /**
     * Construtor para a criação de uma nova instância da entidade {@link Cliente}.
     *
     * @param nome O nome completo do cliente.
     * @param cpf O CPF do cliente, deve ser válido.
     * @param saldo O saldo disponível do cliente.
     *
     * @throws IllegalArgumentException Se o CPF fornecido for inválido.
     */
    public Cliente(String nome, String cpf, Double saldo) {
        Cpf validar = new Cpf(cpf);
        if (!validar.validateFinal()) {
            throw new IllegalArgumentException("CPF inválido");
        }
        this.nome = nome;
        this.cpf = cpf;
        this.saldo = saldo;
    }

    /**
     * Obtém o identificador único do cliente.
     *
     * @return O identificador único do cliente.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador único do cliente.
     *
     * @param id O identificador único do cliente.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o CPF do cliente.
     *
     * @return O CPF do cliente.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do cliente.
     *
     * @param cpf O CPF do cliente.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Obtém o nome completo do cliente.
     *
     * @return O nome completo do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome completo do cliente.
     *
     * @param nome O nome completo do cliente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o saldo disponível do cliente.
     *
     * @return O saldo disponível do cliente.
     */
    public Double getSaldo() {
        return saldo;
    }

    /**
     * Define o saldo disponível do cliente.
     *
     * @param saldo O saldo disponível do cliente.
     */
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
