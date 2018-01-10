package com.loserbird.bookstore.vo;

import java.math.BigDecimal;

/**
 * @Author: bingqin
 * @Date: 2018/1/9
 * @Description:
 **/
public class CartBookVo {

    private Integer id;
    private Integer userId;
    private Integer bookId;
    private Integer quantity;//购物车中此书籍的数量
    private String bookName;
    private String bookImage;
    private BigDecimal bookPrice;
    private Integer bookStatus;
    private BigDecimal bookTotalPrice;
    private Integer bookStock;
    private Integer bookChecked;//此书籍是否勾选

    private String limitQuantity;//限制数量的一个返回结果

    public String getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(String limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookImage() {
        return bookImage;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public Integer getBookStatus() {
        return bookStatus;
    }

    public BigDecimal getBookTotalPrice() {
        return bookTotalPrice;
    }

    public Integer getBookStock() {
        return bookStock;
    }

    public Integer getBookChecked() {
        return bookChecked;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }

    public void setBookTotalPrice(BigDecimal bookTotalPrice) {
        this.bookTotalPrice = bookTotalPrice;
    }

    public void setBookStock(Integer bookStock) {
        this.bookStock = bookStock;
    }

    public void setBookChecked(Integer bookChecked) {
        this.bookChecked = bookChecked;
    }
}
