package com.loserbird.bookstore.service;


import com.loserbird.bookstore.common.ServerResponse;
import com.loserbird.bookstore.pojo.Cart;
import com.loserbird.bookstore.vo.CartVo;

import java.util.List;


public interface ICartService {
    ServerResponse<CartVo> add(Integer userId, Integer bookId, Integer count);
    ServerResponse<CartVo> update(Integer userId, Integer bookId, Integer count);
    ServerResponse<CartVo> deleteBook(Integer userId, String bookIds);
    ServerResponse<CartVo> delete(Integer userId, Integer bookId);

    ServerResponse<CartVo> list(Integer userId);
    ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer bookId, Integer checked);
    ServerResponse<Integer> getCartBookCount(Integer userId);

    int updateBatch(Integer userId, List<Integer> bookIds);

    List<Cart> selectCheckedCartByUserId(Integer userId);
}
