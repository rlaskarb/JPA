package com.ohgiraffers.mappiong.section03.compositekey.embeddedid;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class LikeDTO {

    private int likedMemberNo;
    private int likedBookNo;


    public int getLikedMemberNo() {
        return likedMemberNo;
    }

    public int getLikedBookNo() {
        return likedBookNo;
    }
}
