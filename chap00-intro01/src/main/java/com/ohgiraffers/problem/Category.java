package com.ohgiraffers.problem;

public class Category {

    private int categoryCode;
    private String categoryNenu;


    public Category() {
    }

    public Category(int categoryCode, String categoryNenu) {
        this.categoryCode = categoryCode;
        this.categoryNenu = categoryNenu;
    }


    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getcategoryNenu() {
        return categoryNenu;
    }

    public void setcategoryNenu(String categoryNenu) {
        this.categoryNenu = categoryNenu;
    }


    @Override
    public String toString() {
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryNenu='" + categoryNenu + '\'' +
                '}';
    }


}
