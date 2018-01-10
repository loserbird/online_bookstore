package com.loserbird.bookstore.dao;

import com.loserbird.bookstore.pojo.Book;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> search(Book book);
    List<Book> searchOnsaleBook(Book book);

    int updownBook(List<Integer> bookIds);
}