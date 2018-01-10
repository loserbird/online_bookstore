package com.loserbird.bookstore.controller.backend;

import com.github.pagehelper.PageInfo;
import com.loserbird.bookstore.common.Const;
import com.loserbird.bookstore.common.ServerResponse;
import com.loserbird.bookstore.pojo.User;
import com.loserbird.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @Author: bingqin
 * @Date: 2018/1/10
 * @Description:
 **/
@Controller
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping("list.do")
    public ModelAndView list(@RequestParam(value = "username",required = false)String username,
                             @RequestParam(value = "email",required = false)String email,
                             @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                             HttpSession session){

        ModelAndView mav = new ModelAndView();
        ServerResponse<PageInfo> result;
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null || user.getRole() == Const.Role.ROLE_CUSTOMER){
            result = ServerResponse.createByErrorMessage("无权限操作");
            mav.setViewName("/login.jsp");
        }else{
            result = iUserService.selectByUsernameEmail(username,email,pageNum,pageSize);
            mav.setViewName("/user_list.jsp");
        }
        mav.addObject("result",result);
        return mav;

    }

    @RequestMapping("delete.do")
    public ModelAndView list(HttpSession session,Integer userId){

        ModelAndView mav = new ModelAndView();
        ServerResponse<PageInfo> result;
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null || user.getRole() == Const.Role.ROLE_CUSTOMER){
            result = ServerResponse.createByErrorMessage("无权限操作");
            mav.setViewName("/login.jsp");
        }else{
            result = iUserService.deleteUser(userId);
            mav.setViewName("/user_list.jsp");
        }
        mav.addObject("result",result);
        return mav;

    }

    @RequestMapping("detail.do")
    public ModelAndView detail(HttpSession session,Integer userId){
        ModelAndView mav = new ModelAndView();
        ServerResponse<User> result;
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null || user.getRole() == Const.Role.ROLE_CUSTOMER){
            result = ServerResponse.createByErrorMessage("无权限操作");
            mav.setViewName("/login.jsp");
        }else{
            result = iUserService.detail(userId);
            mav.setViewName("/user_detail.jsp");
        }
        mav.addObject("result",result);
        return mav;

    }

    @RequestMapping("update.do")
    public ModelAndView update(HttpSession session,User user){
        ModelAndView mav = new ModelAndView();
        ServerResponse<User> result;
        User user2 = (User)session.getAttribute(Const.CURRENT_USER);
        if(user2 == null || user2.getRole() == Const.Role.ROLE_CUSTOMER){
            result = ServerResponse.createByErrorMessage("无权限操作");
            mav.setViewName("/login.jsp");
        }else{
            iUserService.update(user);
            result = iUserService.detail(user.getId());
            mav.setViewName("/user_detail.jsp");
        }
        mav.addObject("result",result);
        return mav;

    }



}
