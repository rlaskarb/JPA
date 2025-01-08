package com.ohgiraffers.mappiong.section03.compositekey.idclass;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class IdClassTest {

    /*comment
     *  복합키가 존재하는 테이블의 매핑 전략
     *  1.EmbeddedId
     *  - @Embeddable 클래스에 복합키를 정의하고 사용할 엔티티에서 @Embedded 로 복학키 클래스를 매핑한다.
     *  2.IdClass
     *  -복합키를 필드로 정의한 클래스를 이용해서 엔티티 클래스에 @IdClass 라는 어노테이션으로 매핑을 한다.
     *
     *  ps 이 둘 차이는
     *   -1번 방식은 복합키로 묶인 클래스를 ID로 사용하여 객체 지향적인 방식인다.
     *   -2번 방식은 관계형 데이터 베이스 즉 DB와 친화적인 방식이다. */


    @Autowired
    private CartService service;



    private static Stream<Arguments> getInfo(){
        return Stream.of(
                Arguments.of(1,1,10452),
                Arguments.of(1,2,5845),
                Arguments.of(2,1,2545),
                Arguments.of(2,2,15358)
        );

    }



    @ParameterizedTest(name =" {0}번 회원이 {1} 번 책을 카트에 {2} 권 담기")
    @MethodSource("getInfo")
    void testIdClass(int cartOwnerNo , int addedBookNo , int quantity ){
        CartDTO cartDTO = new CartDTO(
                cartOwnerNo,addedBookNo,quantity
        );


        Assertions.assertDoesNotThrow(
                () -> service.addItemToCart(cartDTO)
        );
    }


}
