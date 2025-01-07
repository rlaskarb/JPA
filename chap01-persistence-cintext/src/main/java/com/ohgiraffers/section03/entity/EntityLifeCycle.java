package com.ohgiraffers.section03.entity;

import com.ohgiraffers.section01.entitymanager.EntityManagerGenerator;
import jakarta.persistence.EntityManager;

public class EntityLifeCycle {

    private EntityManager manager;

    public EntityManager getManagerInstance() {
        return manager;
    }


    public Menu findMenuBuMenuCode(int menuCode) {

        manager = EntityManagerGenerator.getInstance();

        return manager.find(Menu.class , menuCode);
    }
}
