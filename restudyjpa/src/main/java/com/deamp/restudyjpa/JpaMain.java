package com.deamp.restudyjpa;


import com.deamp.restudyjpa.jpaDefault.helloJpa.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("restudyjpa");
        EntityManager em = emf.createEntityManager();
        //code
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Movie movie = new Movie();
            movie.setDirector("aaa");
            movie.setActor("bbb");
            movie.setName("cho");
            movie.setPrice(1999);
            em.persist(movie);

        }catch (Exception e){
            em.close();
            emf.close();
        }
        em.close();
        emf.close();
    }
}
