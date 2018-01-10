package com.loserbird.bookstore.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: bingqin
 * @Date: 2018/1/9
 * @Description:
 **/
public class OrderBookVo {

    private List<OrderItemVo> orderItemVoList;
    private BigDecimal bookTotalPrice;

    public List<OrderItemVo> getOrderItemVoList() {
        return orderItemVoList;
    }

    public void setOrderItemVoList(List<OrderItemVo> orderItemVoList) {
        this.orderItemVoList = orderItemVoList;
    }

    public BigDecimal getBookTotalPrice() {
        return bookTotalPrice;
    }

    public void setBookTotalPrice(BigDecimal bookTotalPrice) {
        this.bookTotalPrice = bookTotalPrice;
    }
}
