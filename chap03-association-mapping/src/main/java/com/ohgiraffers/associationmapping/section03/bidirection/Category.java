package com.ohgiraffers.associationmapping.section03.bidirection;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity(name = "bi_category")
@Table(name = "tbl_category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
//@ToString
public class Category {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private  Integer refCategoryCode;


    /*comment
    *  메뉴->카테고리 참조
    *  카테고리-> 메뉴 참조하고 있다. = 양방향
    *  객체의 참조는 둘인데, 왜래키가 하나인 상황을
    *  해결하기 위해서 두 연관 관계중 하나들 정해 테이블의
    *  외래키를 관리한다. 이를 연관 관계의 주인(Owner)라고 한다.
    *  .
    *  mappedBy 속석은 연관관계의 주인이 아닌 쪽(외래키가 없는쪽)에
    *  사용이 되며 , 주인 엔티티의 연관 된 필드를 가르킨다. */

    @OneToMany(mappedBy = "category")
    private List<Menu> menuList;


    public Category(int categoryCode, String categoryName, Integer ref) {
        this.categoryCode=categoryCode;
        this.categoryName=categoryName;
        this.refCategoryCode=ref;
    }




}
