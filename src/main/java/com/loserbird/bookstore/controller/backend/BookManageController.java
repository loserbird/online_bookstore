package com.loserbird.bookstore.controller.backend;

import com.loserbird.bookstore.common.ServerResponse;
import com.loserbird.bookstore.pojo.Book;
import com.loserbird.bookstore.pojo.Category;
import com.loserbird.bookstore.service.IBookService;
import com.loserbird.bookstore.service.ICategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @Author: bingqin
 * @Date: 2018/1/8
 * @Description:
 **/
@Controller
@RequestMapping("/manage/book")
public class BookManageController {

    private Logger logger = LoggerFactory.getLogger(BookManageController.class);


    @Autowired
    private IBookService iBookService;

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping("save.do")
    public ModelAndView save(Book book, String categoryName, MultipartFile file, HttpServletRequest request){
        String fileName = file.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("upload");

        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);


        try {
            file.transferTo(targetFile);
        }catch (IOException e) {
            e.printStackTrace();
        }
        book.setImage("upload/"+targetFile.getName());
        Category category = iCategoryService.queryCategoryByName(categoryName);
        book.setCategoryId(category.getId());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/addBook.jsp");
        ServerResponse<String> result = iBookService.saveOrUpdate(book);
        mav.addObject("result",result);
        return mav;
    }
    @RequestMapping("update.do")
    public ModelAndView update(Book book,String categoryName){
        Category category = iCategoryService.queryCategoryByName(categoryName);
        book.setCategoryId(category.getId());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/updateBook.jsp");
        iBookService.saveOrUpdate(book);
        ServerResponse<Book> result = iBookService.selectBook(book.getId());
        result.setMsg("修改成功");
        mav.addObject("result",result);
        return mav;
    }

    @RequestMapping("list.do")
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/queryAllBook.jsp");
        ServerResponse<List<Book>> result = iBookService.search(new Book());
        mav.addObject("result",result);
        return mav;
    }

    @RequestMapping("search.do")
    public ModelAndView search(Book book,String categoryName){
        if(!categoryName.equals("全部")){
            Category category = iCategoryService.queryCategoryByName(categoryName);
            book.setCategoryId(category.getId());
        }
        ServerResponse<List<Book>> result = iBookService.search(book);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/queryAllBook.jsp");
        mav.addObject("result",result);
        return mav;
    }

    @RequestMapping("updownBook.do")
    public ModelAndView updownBook(Integer[] bookId){
        List<Integer> bookIds = Arrays.asList(bookId);
        iBookService.updownBook(bookIds);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/queryAllBook.jsp");
        ServerResponse<List<Book>> result = iBookService.search(new Book());
        mav.addObject("result",result);
        return mav;
    }

    @RequestMapping("detail.do")
    public ModelAndView detail(Integer bookId){
        ServerResponse<Book> result = iBookService.selectBook(bookId);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/updateBook.jsp");
        mav.addObject("result",result);
        return mav;
    }



}
