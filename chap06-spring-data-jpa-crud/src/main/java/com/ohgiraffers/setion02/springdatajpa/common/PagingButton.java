package com.ohgiraffers.setion02.springdatajpa.common;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PagingButton {

    private int currentPage; // 현재 페이지
    private int startPage; // 시작페이지
    private int endPage; // 마지막 페이지

}
