package infra;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;


/**
 * Classe DAO genérica para operações básicas de persistência usando JPA.
 *
 * @param <E> O tipo da entidade que será manipulada.
 */

public class DAO<E> { // recebe um tipo genérico

    /**
     * Fabrica de gerenciadores de entidades estática, compartilhada entre todas as instâncias da classe DAO.
     */
    private static EntityManagerFactory emf;

    /**
     * Gerenciador de entidades, responsável pelas operações de persistência.
     */
    private EntityManager em;

    /**
     * Classe da entidade que será manipulada por esta instância do DAO.
     */
    private Class<E> classe;

    /**
     * Logger utilizado para registrar mensagens de log. Aqui é utilizado o Logger da API padrão do Java.
     */
    private static final Logger logger = Logger.getLogger(DAO.class.getName());

    /**
     * Bloco estático responsável pela criação do EntityManagerFactory.
     *
     * 1. O bloco tenta criar o `EntityManagerFactory` usando o nome de persistência definido.
     * 2. Caso ocorra uma exceção, o erro é capturado e registrado usando o `Logger` no nível `SEVERE`.
     * 3. Adicionalmente, o `Logger` é configurado para gravar as mensagens em um arquivo de log (`dao.log`).
     */
    static {
        try{
            // Configurando o logger para gravar em um arquivo dao.log
            FileHandler fileHandler = new FileHandler("dao.log", true);
            fileHandler.setFormatter(new SimpleFormatter()); // Formato simples para o log
            logger.addHandler(fileHandler);

            // Tentativa de criação do EntityManagerFactory
            emf = Persistence.createEntityManagerFactory("br.com.bacchiega");
        } catch (Exception e) {

            // Logando o erro no nível SEVERE caso a criação do EntityManagerFactory falhe
            logger.log(Level.SEVERE, "Erro ao criar EntityManagerFactory", e);
        }
    }

    /**
     * Construtor padrão, usado para criar um DAO sem uma classe específica.
     */
    public DAO(){
        this(null);
    }

    /**
     * Construtor que aceita a classe da entidade para que o DAO possa realizar operações sobre ela.
     *
     * @param classe A classe da entidade que este DAO irá manipular.
     */
    public DAO(Class<E> classe){
        this.classe = classe;
        em = emf.createEntityManager();
    }

    /**
     * Inicia uma transação no banco de dados.
     *
     * @return A própria instância do DAO para encadeamento de métodos.
     */
    public DAO<E> abrirTransacao(){
        em.getTransaction().begin();
        return this;
    }

    /**
     * Finaliza uma transação, confirmando as alterações no banco de dados.
     *
     * @return A própria instância do DAO para encadeamento de métodos.
     */
    public DAO<E> fecharTransacao(){
        em.getTransaction().commit();
        return this;
    }

    /**
     * Persiste uma entidade no banco de dados.
     *
     * @param entidade A entidade a ser persistida.
     * @return A própria instância do DAO para encadeamento de métodos.
     */
    public DAO<E> incluir(E entidade){
        em.persist(entidade);
        return this;
    }

    /**
     * Persiste uma entidade no banco de dados dentro de uma transação atômica (abrindo e fechando a transação automaticamente).
     *
     * @param entidade A entidade a ser persistida.
     * @return A própria instância do DAO para encadeamento de métodos.
     */
    public DAO<E> incluirAtomico(E entidade){
        return  this.abrirTransacao().incluir(entidade).fecharTransacao();
    }

    /**
     * Modifica uma entidade já persistida no banco de dados.
     *
     * @param entidade A entidade a ser atualizada.
     * @return A própria instância do DAO para encadeamento de métodos.
     */
    public DAO<E> modificar(E entidade){
        em.merge(entidade);
        return this;
    }

    /**
     * Busca uma entidade no banco de dados pelo seu identificador.
     *
     * @param id O identificador da entidade.
     * @return A entidade encontrada ou null se não houver correspondência.
     */
    public E obterPorID(Object id){
        return em.find(classe, id);
    }

    /**
     * Retorna todas as entidades do tipo E presentes no banco de dados, limitando a 10 resultados por padrão.
     *
     * @return Uma lista contendo as entidades encontradas.
     */
    public List<E> obterTodos(){
        return this.obterTodos(10, 0);
    }

    /**
     * Retorna uma lista de entidades paginada, com uma quantidade e um deslocamento definidos.
     *
     * @param quantidade A quantidade de entidades a serem retornadas.
     * @param deslocamento O número de entidades a pular antes de começar a retornar os resultados.
     * @return Uma lista de entidades.
     */
    public List<E> obterTodos(int quantidade, int deslocamento){
        if (classe == null){
            throw new UnsupportedOperationException("Classe nula.");
        }

        String jpql = "SELECT e FROM " + classe.getName() + " e";
        TypedQuery<E> query = em.createQuery(jpql, classe);
        query.setMaxResults(quantidade);
        query.setFirstResult(deslocamento);
        return query.getResultList();
    }

    /**
     * Fecha o gerenciador de entidades, liberando recursos alocados.
     */
    public void fechar(){
        em.close();
    }
}
