package eg2._1;

import domain.eg2._3.GymMembership;
import domain.eg2._3.Level;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static util.TestUtil.executeCommit;

public class EntityObjTests {

    static EntityManagerFactory emf;
    EntityManager em;

    @BeforeAll
    static void setUp() {
        emf = Persistence.createEntityManagerFactory("est-hibernate-exp1");
    }

    @AfterAll
    static void close() {
        emf.close();
    }

    @BeforeEach
    void init() {
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
    }

    @Test
    @DisplayName("TABLE STRATEGY TEST")
    void tage_stg_test() throws Exception {

    }

    @Test
    @DisplayName("ENUM TEST")
    void enum_test() throws Exception {

        executeCommit(em, () -> {
            GymMembership gymMembership1 = new GymMembership();
            gymMembership1.setMembershipLevel(Level.GOLD);
            GymMembership gymMembership2 = new GymMembership();
            gymMembership2.setMembershipLevel(Level.SILVER);
            GymMembership gymMembership3 = new GymMembership();
            gymMembership3.setMembershipLevel(Level.GENERAL);

            em.persist(gymMembership1);
            em.persist(gymMembership2);
            em.persist(gymMembership3);

        });

    }

}
