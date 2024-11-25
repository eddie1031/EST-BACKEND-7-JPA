package eg5;

import domain.eg5.Coffee;
import domain.eg5.CoffeeDto;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import util.TestUtil;

import java.util.List;

import static util.TestUtil.*;

@Slf4j
public class JpqlTests {

    static EntityManagerFactory emf;
    EntityManager em;

    @BeforeAll
    static void setUp() {
        emf = Persistence.createEntityManagerFactory("est-hibernate-exp2");
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
    @DisplayName("jpql select test")
    void jpql_select_test() throws Exception {

        executeCommit(em, () -> {
            Coffee coffee = Coffee.builder()
                    .name("아메리카노")
                    .amount(1)
                    .price(1500)
                    .build();
            em.persist(coffee);

            Coffee coffee2 = Coffee.builder()
                    .name("바닐라라떼")
                    .amount(1)
                    .price(3000)
                    .build();
            em.persist(coffee2);
        });

        executeCommit(em, () -> {


//            String jpql = "select c.id, c.name, c.amount, c.price from Coffee c";
            String jpql = "select c from Coffee c where c.name = :name order by c.price desc";

//            Query query = em.createQuery(jpql);

            // "select c from Coffee where c.name = :name"

//            List resultList = query.getResultList();
//            for (Object o : resultList) {
//                Object[] objects = (Object[]) o;
//
//                log.info("objects[0] = {}", objects[0]);
//                log.info("objects[1] = {}", objects[1]);
//                log.info("objects[2] = {}", objects[2]);
//                log.info("objects[3] = {}", objects[3]);
//            }

            TypedQuery<Coffee> query = em.createQuery(jpql, Coffee.class);
            List<Coffee> resultList = query.getResultList();

            for (Coffee coffee : resultList) {
                log.info("coffee.getId() = {}", coffee.getId());
                log.info("coffee.getName() = {}", coffee.getName());
                log.info("coffee.getAmount() = {}", coffee.getAmount());
                log.info("coffee.getPrice() = {}", coffee.getPrice());
            }

        });

        log.info("=================================================");

        executeCommit(em, () -> {

            String jpql = "select c.name from Coffee c";

            TypedQuery<String> query = em.createQuery(jpql, String.class);
            List<String> resultList = query.getResultList();

            for (String coffeeName : resultList) {
                log.info("coffeeName = {}", coffeeName);
            }

        });

        log.info("=================================================");

        executeCommit(em, () -> {
//            String jpql = "select c from Coffee c where c.name = ?1 and c.price = ?2";
//            String jpql = "select c from Coffee c where c.name = :name and c.price = :price";
            String jpql = "select c from Coffee c where c.name = :name";

            TypedQuery<Coffee> query = em.createQuery(jpql, Coffee.class);

            // 2가지 방법
            // 1. 위치기반 ENUM
//            query.setParameter(1, "아메리카노");
//            query.setParameter(2, 1500);

//            List<Coffee> resultList = query.getResultList();

//             2. 이름기반 파리미터 바인딩
            query.setParameter("name", "아메리카노");
//            query.setParameter("price", 1500);
            // JPQL 연습

            List<Coffee> resultList = query.getResultList();

        });

        log.info("=================================================");

        // JPQL -> String, Entity, DTO

        executeCommit(em, () -> {
           String jpql = "select new domain.eg5.CoffeeDto(c.name, c.price) from Coffee c where c.name = :name";

            TypedQuery<CoffeeDto> query = em.createQuery(jpql, CoffeeDto.class);
            query.setParameter("name", "아메리카노");

            List<CoffeeDto> resultList = query.getResultList();
            for (CoffeeDto coffeeDto : resultList) {
                log.info("coffeeDto.getName() = {}", coffeeDto.getName());
            }

        });


    }



}
