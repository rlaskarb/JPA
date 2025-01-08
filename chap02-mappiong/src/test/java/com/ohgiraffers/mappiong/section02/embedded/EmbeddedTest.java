package com.ohgiraffers.mappiong.section02.embedded;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@SpringBootTest
public class EmbeddedTest {


    @Autowired
    private BookService service;


    private static Stream<Arguments> getBook(){
        return Stream.of(
                Arguments.of(
                        "안사",
                        "안팔아",
                        "싫러",
                        LocalDate.now(),
                        300,
                        0.9
                )
        );

    }



    @ParameterizedTest
    @DisplayName("임베디드 시 테스트")
    @MethodSource("getBook")
    void testEmbeddedPrice(String bookTitle , String author , String publisher , LocalDate createDate , int regularPrice , double discountRate){

        BookRegistDTO newBook = new BookRegistDTO(
                bookTitle,author,publisher,createDate,regularPrice,discountRate
        );

        Assertions.assertDoesNotThrow(
                () -> service.regustBook(newBook)

        );


    }
}
