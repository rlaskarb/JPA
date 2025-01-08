package com.ohgiraffers.mappiong.section03.compositekey.embeddedid;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class LikedBookNo {

    @Column(name="liked_book_no")
    private int likedBookNo;

    public LikedBookNo() {
    }


    public LikedBookNo(int likedBookNo) {
        this.likedBookNo = likedBookNo;
    }
}
