package com.ohgiraffers.mappiong.section02.embedded;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class BookRegistDTO {

    private String bookTitle;
    private String author;
    private String publisher;
    private LocalDate createdDate;
    private int regularPrice;
    private  double discountRate;



}
