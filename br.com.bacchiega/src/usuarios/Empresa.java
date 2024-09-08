package usuarios;

import Validator.Cnpj;

import javax.persistence.*;

/**
 * Representa uma empresa no sistema.
 *
 * Esta classe é uma entidade JPA que mapeia a tabela 'Empresa' no banco de dados.
 *
 * Atributos:
 * - id: Identificador único da empresa.
 * - nome: Nome da empresa.
 * - cnpj: CNPJ da empresa, deve ser válido.
 * - taxa: Taxa associada à empresa.
 * - saldo: Saldo disponível da empresa.
 */
@Entity
public class Empresa {

    /**
     * Identificador único da empresa.
     *
     * É a chave primária da entidade e é gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome da empresa.
     *
     * Não pode ser nulo.
     */
    @Column(nullable = false)
    private String nome;

    /**
     * CNPJ da empresa.
     *
     * Não pode ser nulo e deve ser um CNPJ válido.
     */
    @Column(nullable = false)
    private String cnpj;

    /**
     * Taxa associada à empresa.
     *
     * Não pode ser nulo.
     */
    @Column(nullable = false)
    private Double taxa;

    /**
     * Saldo disponível da empresa.
     *
     * Não pode ser nulo.
     */
    @Column(nullable = false)
    private Double saldo;

    /**
     * Construtor padrão para a criação de instâncias da entidade {@link Empresa}.
     */
    public Empresa() {
    }

    /**
     * Construtor para a criação de uma nova instância da entidade {@link Empresa}.
     *
     * @param nome O nome da empresa.
     * @param cnpj O CNPJ da empresa, deve ser válido.
     * @param taxa A taxa associada à empresa.
     * @param saldo O saldo disponível da empresa.
     *
     * @throws IllegalArgumentException Se o CNPJ fornecido for inválido.
     */
    public Empresa(String nome, String cnpj, Double taxa, Double saldo) {
        if (!Cnpj.isCNPJ(cnpj)) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
        this.nome = nome;
        this.cnpj = cnpj;
        this.taxa = taxa;
        this.saldo = saldo;
    }

    /**
     * Obtém o identificador único da empresa.
     *
     * @return O identificador único da empresa.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador único da empresa.
     *
     * @param id O identificador único da empresa.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome da empresa.
     *
     * @return O nome da empresa.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da empresa.
     *
     * @param nome O nome da empresa.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o CNPJ da empresa.
     *
     * @return O CNPJ da empresa.
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Define o CNPJ da empresa.
     *
     * @param cnpj O CNPJ da empresa.
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Obtém a taxa associada à empresa.
     *
     * @return A taxa associada à empresa.
     */
    public Double getTaxa() {
        return taxa;
    }

    /**
     * Define a taxa associada à empresa.
     *
     * @param taxa A taxa associada à empresa.
     */
    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

    /**
     * Obtém o saldo disponível da empresa.
     *
     * @return O saldo disponível da empresa.
     */
    public Double getSaldo() {
        return saldo;
    }

    /**
     * Define o saldo disponível da empresa.
     *
     * @param saldo O saldo disponível da empresa.
     */
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
