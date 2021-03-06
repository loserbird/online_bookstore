package com.loserbird.bookstore.dao;

import com.loserbird.bookstore.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    Cart selectCartByUserIdBookId(@Param("userId") Integer userId, @Param("bookId")Integer bookId);

    List<Cart> selectCartByUserId(Integer userId);

    int selectCartBookCheckedStatusByUserId(Integer userId);

    int deleteByUserIdbookIds(@Param("userId") Integer userId,@Param("bookIdList")List<String> bookIdList);
    int deleteByUserIdbookId(@Param("userId") Integer userId,@Param("bookId")Integer bookId);

    int checkedOrUncheckedBook(@Param("userId") Integer userId,@Param("bookId")Integer bookId,@Param("checked") Integer checked);

    int selectCartBookCount(@Param("userId") Integer userId);


    List<Cart> selectCheckedCartByUserId(Integer userId);

    int updateBatch(@Param("userId") Integer userId,@Param("bookIdList")List<Integer> bookIdList);
}