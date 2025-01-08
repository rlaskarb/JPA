package com.ohgiraffers.mappiong.section03.compositekey.idclass;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CartRepository {



    @PersistenceContext
    private EntityManager manager;



    public void save(Cart newCart) {

        manager.persist(newCart);

    }


}
