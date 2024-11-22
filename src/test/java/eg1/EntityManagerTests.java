package eg1;

import domain.eg1.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import util.TestUtil;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static util.TestUtil.*;

@Slf4j
public class EntityManagerTests {

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
    @DisplayName("EntityManagerFactory 테스트")
    void entity_manger_factory_test() throws Exception {

        Map<String, Object> properties = emf.getProperties();

        String url = properties.get("jakarta.persistence.jdbc.url").toString();
        String driverName = properties.get("jakarta.persistence.jdbc.driver").toString();
        log.info("url = {}", url);
        log.info("driverName = {}", driverName);

        assertThat(url).isEqualTo("jdbc:mariadb://localhost:3306/est-hibernate-test");
        assertThat(driverName).isEqualTo("org.mariadb.jdbc.Driver");

    }

    @Test
    @DisplayName("EntityManager Test : SAVE")
    void entity_manger_test_insert_into() throws Exception {

        String memberName = getMemberName();
        Member member = genMember(memberName); // transient

        executeCommit(em, () -> {
            em.persist(member); // 엔티티 매니저(Persistence Context)를 이용해서 엔티티 인스턴스를 데이터베이스에 저장
        });

    }

    private static String getMemberName() {
        return "member" + genNumStr();
    }

    private Member genMember(String name) {
        return Member.builder()
                .id(name)
                .name(name)
                .build();
    }

    @Test
    @DisplayName("EntityManager Test : SELECT")
    void entity_manager_test_select() throws Exception {

        Member member = genMember(getMemberName());

        executeCommit(em, () -> {
            log.info("First Level Cache");
            em.persist(member);
            Member findMember = em.find(Member.class, member.getId());

            assertThat(findMember).isEqualTo(member);

            log.info("member = {}", member);
            log.info("findMember = {}", findMember);
            log.info("================================");
        });

        executeCommit(em, () -> {
            log.info("변경 감지");
            Member findMember = em.find(Member.class, member.getId());
            assertThat(findMember.getName()).isEqualTo(member.getName());

            findMember.setName("ADMIN");
        });

    }

}
