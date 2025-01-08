package com.ohgiraffers.mappiong.section03.compositekey.idclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
@ToString
public class CartDTO {

    private int cartOwnerMemberNo;
    private int addBookNo;
    private int quantity;


}
