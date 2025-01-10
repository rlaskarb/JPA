package com.ohgiraffers.jpql.section03.projection;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectionService {

    private ProjectionRepository repository;

    public ProjectionService(ProjectionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Menu> singleEntityProjection() {
        List<Menu> menuList = repository.singleEntityProjection();

        Menu menu = repository.findMenu(1);
        System.out.println(menu);

        menuList.get(7).setMenuName("세상에서 제일 맛있는 유니콘 고기");

        return menuList;
    }


    /* 이 메서드는 특정 메뉴 코드에 대한 카테고리를 JPQL 쿼리를 통해 조회 */
    @Transactional
    public BiDirectionCategory biDirectionEntityProjection(int menuCode) {
        BiDirectionCategory categoryOfMenu = repository.biDirectionEntityProjection(menuCode);

        categoryOfMenu.getMenuList().forEach(System.out::println);

        return categoryOfMenu;
    }

}