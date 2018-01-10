package com.loserbird.bookstore.service;

import com.github.pagehelper.PageInfo;
import com.loserbird.bookstore.common.ServerResponse;
import com.loserbird.bookstore.pojo.Book;

import java.util.List;

public interface IBookService {

    ServerResponse<String> saveOrUpdate(Book book);

    ServerResponse<List<Book>> search(Book book);

    ServerResponse<String> updownBook(List<Integer> bookIds);

    ServerResponse<Book> selectBook(Integer bookId);

    ServerResponse<PageInfo> searchOnsaleBook(Book book, int pageNum, int pageSize);

}
