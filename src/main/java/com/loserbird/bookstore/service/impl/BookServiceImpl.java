package com.loserbird.bookstore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loserbird.bookstore.common.ServerResponse;
import com.loserbird.bookstore.dao.BookMapper;
import com.loserbird.bookstore.pojo.Book;
import com.loserbird.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iBookService")
public class BookServiceImpl implements IBookService {


    @Autowired
    private BookMapper bookMapper;


    @Override
    public ServerResponse<String> saveOrUpdate(Book book) {
        if(book.getId() != null){
            int rowCount = bookMapper.updateByPrimaryKeySelective(book);
            return ServerResponse.createBySuccessMessage("修改成功");
        }else{
            bookMapper.insert(book);
            return ServerResponse.createBySuccessMessage("添加书籍成功");

        }

    }

    @Override
    public ServerResponse<List<Book>> search(Book book) {
        return ServerResponse.createBySuccess("",bookMapper.search(book));
    }

    @Override
    public ServerResponse<String> updownBook(List<Integer> bookIds) {
        bookMapper.updownBook(bookIds);
        return ServerResponse.createBySuccess("书籍上下架成功");
    }

    @Override
    public ServerResponse<Book> selectBook(Integer bookId) {
        return ServerResponse.createBySuccess("",bookMapper.selectByPrimaryKey(bookId));
    }

    @Override
    public ServerResponse<PageInfo> searchOnsaleBook(Book book,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Book> bookList = bookMapper.searchOnsaleBook(book);
        PageInfo pageResult = new PageInfo(bookList);

        return ServerResponse.createBySuccess("",pageResult);
    }
}
