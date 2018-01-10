package com.loserbird.bookstore.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: bingqin
 * @Date: 2018/1/9
 * @Description:
 **/
public class CartVo {
    private List<CartBookVo> cartBookVoList;
    private BigDecimal cartTotalPrice;
    private Boolean allChecked;//是否已经都勾选

    public List<CartBookVo> getCartBookVoList() {
        return cartBookVoList;
    }

    public void setCartBookVoList(List<CartBookVo> cartBookVoList) {
        this.cartBookVoList = cartBookVoList;
    }

    public BigDecimal getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(BigDecimal cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public Boolean getAllChecked() {
        return allChecked;
    }

    public void setAllChecked(Boolean allChecked) {
        this.allChecked = allChecked;
    }
}
