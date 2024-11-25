package eg4;

import domain.eg3._1.Player;
import domain.eg3._1.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.*;
import util.TestUtil;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static util.TestUtil.*;

@Slf4j
public class ProxyObjTests {

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
    @DisplayName("No proxy Obj")
    void no_proxy_object_test() throws Exception {

        ArrayList<Player> players = new ArrayList<>();

        // 로직 1
        executeCommit(em, () -> {

            Player player = em.find(Player.class, 1);
            Team team = player.getTeam();

            log.info("team.getName() = {}", team.getName());
            players.add(player);

        });

        em.clear();

        Player findPlayer = players.get(0);
        log.info("findPlayer.getName() = {}", findPlayer.getName());

        Team team = findPlayer.getTeam();
        log.info("team.getName() = {}", team.getName());

    }


    @Test
    @DisplayName("proxy obj")
    void proxy_object_test() throws Exception {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Player player = null;

        // 로직 2
        try {

            player = em.find(Player.class, 1);
            log.info("player.getName() = {}", player.getName());

            em.detach(player);

            transaction.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
        }

        em.close();

        Team team = player.getTeam();
        assertThatThrownBy(
                () -> {
                    team.getName();
                }
        ).isInstanceOf(LazyInitializationException.class);

    }

    @Test
    @DisplayName("check proxy object")
    void check_proxy_test() throws Exception {

        // entityManger.getReference();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Player player = null;

        // 로직 2
        try {
            player = em.getReference(Player.class, 1);
            transaction.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
        }

        em.close();

        boolean result = emf.getPersistenceUnitUtil().isLoaded(player);
        assertThat(result).isFalse();

    }

}
