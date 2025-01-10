package com.ohgiraffers.jpql.section03.projection;

import jakarta.persistence.*;

/*Hibernate 는 엔티티를 통해 데이터 베이스와 상호작하는 ORM 프레임 워크 입니다.*/


@Entity(name = "BiDirectionMenu")
@Table(name = "tbl_menu")
public class BiDirectionMenu {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @ManyToOne
    @JoinColumn(name = "category_code")
    private BiDirectionCategory category;

    @Column(name = "orderable_status")
    private String orderableStatus;

    protected BiDirectionMenu() {}

}
