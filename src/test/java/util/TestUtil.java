package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestUtil {

    public static String genNumStr() {
        int num = (int) (Math.random() * 100_000);
        return Integer.toString(num);
    }

    public static void executeCommit(EntityManager em, Runnable runnable) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            runnable.run();
            transaction.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
        }
    }

    public static void executeRollback(EntityManager em, Runnable runnable) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            runnable.run();
            transaction.rollback();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
        }
    }

}
