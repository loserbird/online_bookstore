package com.loserbird.bookstore.controller.portal;

import com.github.pagehelper.PageInfo;
import com.loserbird.bookstore.common.ServerResponse;
import com.loserbird.bookstore.pojo.Book;
import com.loserbird.bookstore.pojo.Category;
import com.loserbird.bookstore.service.IBookService;
import com.loserbird.bookstore.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by geely
 */

@Controller
@RequestMapping("/book/")
public class BookController {

    @Autowired
    private IBookService iBookService;

    @Autowired
    private ICategoryService iCategoryService;



    @RequestMapping("detail.do")
    public ModelAndView detail(Integer bookId){
        ServerResponse<Book> result = iBookService.selectBook(bookId);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/bookDetail.jsp");
        mav.addObject("result",result);
        return mav;
    }


    @RequestMapping("search.do")
    public ModelAndView search(Book book, String categoryName,
                               @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        if(!categoryName.equals("全部")){
            Category category = iCategoryService.queryCategoryByName(categoryName);
            book.setCategoryId(category.getId());
        }
        ServerResponse<PageInfo>  result = iBookService.searchOnsaleBook(book,pageNum,pageSize);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/index.jsp");
        mav.addObject("result",result);
        return mav;
    }

    @RequestMapping("list.do")
    public ModelAndView list( @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/index.jsp");
        ServerResponse<PageInfo>  result = iBookService.searchOnsaleBook(new Book(),pageNum,pageSize);
        mav.addObject("result",result);
        return mav;
    }





}
