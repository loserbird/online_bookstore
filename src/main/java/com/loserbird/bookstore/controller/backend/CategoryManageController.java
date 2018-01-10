package com.loserbird.bookstore.controller.backend;

import com.loserbird.bookstore.common.Const;
import com.loserbird.bookstore.common.ResponseCode;
import com.loserbird.bookstore.common.ServerResponse;
import com.loserbird.bookstore.pojo.Category;
import com.loserbird.bookstore.pojo.User;
import com.loserbird.bookstore.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @Author: bingqin
 * @Date: 2018/1/8
 * @Description:
 **/
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping("add.do")
    public ModelAndView addCategory(HttpSession session, String categoryName){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ModelAndView mav = new ModelAndView();
        ServerResponse result;
        if(user == null){
            result =  ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }else{
           result = iCategoryService.addCategory(categoryName);
        }
        mav.addObject("result",result);
        mav.setViewName("/addCategory.jsp");
        return mav;


    }

    @RequestMapping("update.do")
    public ModelAndView setCategoryName(HttpSession session,Integer categoryId,String categoryName){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ModelAndView mav = new ModelAndView();
        ServerResponse result;
        if(user == null){
            result =  ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }else{
            iCategoryService.updateCategoryName(categoryId,categoryName);
            result = iCategoryService.getAllCategory();
            result.setMsg("修改成功");
        }

        mav.setViewName("/queryCategory.jsp");
        mav.addObject("result",result);
        return mav;

    }
    @RequestMapping("queryall.do")
    public ModelAndView queryAllCategory(){
        ServerResponse<List<Category>> result = iCategoryService.getAllCategory();
        ModelAndView mav = new ModelAndView("/queryCategory.jsp","result",result);
        return mav;
    }
    @RequestMapping("getAllCategoryName.do")
    public void getAllCategoryNames(HttpServletResponse resp) throws IOException {
        ServerResponse<List<Category>> result = iCategoryService.getAllCategory();
        StringBuilder sb = new StringBuilder();
        List<Category> list = result.getData();
        for(int i=0;i<list.size();i++){
            sb.append(list.get(i).getName());
            if(i<list.size()-1) sb.append(",");
        }
        resp.getWriter().print(sb.toString());
    }

}
