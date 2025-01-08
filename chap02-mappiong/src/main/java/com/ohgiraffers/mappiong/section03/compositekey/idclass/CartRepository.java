package com.ohgiraffers.mappiong.section03.compositekey.idclass;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CartRepository {

    @Transactional
    public void addItemToCart(CartDTO cart){
        Cart newCart = new Cart(
                cart.getCartOwnerMemberNo(),
                cart.getAddBookNo(),
                cart.getQuantity()
        );

    }




}
