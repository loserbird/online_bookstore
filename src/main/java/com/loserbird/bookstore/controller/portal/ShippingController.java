package com.loserbird.bookstore.controller.portal;

import com.github.pagehelper.PageInfo;
import com.loserbird.bookstore.common.Const;
import com.loserbird.bookstore.common.ServerResponse;
import com.loserbird.bookstore.pojo.Shipping;
import com.loserbird.bookstore.pojo.User;
import com.loserbird.bookstore.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;



@Controller
@RequestMapping("/shipping/")
public class ShippingController {


    @Autowired
    private IShippingService iShippingService;


    @RequestMapping("add.do")
    public ModelAndView add(HttpSession session, Shipping shipping){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/addShipping.jsp");
        mav.addObject("result",iShippingService.add(user.getId(),shipping));
        return mav;
    }


    @RequestMapping("del.do")
    public ModelAndView del(HttpSession session, Integer shippingId){
        ModelAndView mav = new ModelAndView();
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        iShippingService.del(user.getId(),shippingId);
        ServerResponse result = iShippingService.list(user.getId(),1,10);
        result.setMsg("删除成功");
        mav.addObject("result",result);
        mav.setViewName("/queryShipping.jsp");
        return  mav;
    }

    @RequestMapping("update.do")
    public ModelAndView update(HttpSession session, Shipping shipping){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/updateShipping.jsp");
        iShippingService.update(user.getId(),shipping);
        ServerResponse result =  iShippingService.select(user.getId(),shipping.getId());
        result.setMsg("修改地址成功");
        mav.addObject("result",result);
        return mav;
    }


    @RequestMapping("select.do")
    public ModelAndView select(HttpSession session, Integer shippingId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/updateShipping.jsp");
        ServerResponse<Shipping> result = iShippingService.select(user.getId(),shippingId);
        result.setMsg("");
        mav.addObject("result",result);
        return  mav;
    }


    @RequestMapping("list.do")
    public ModelAndView list(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                             HttpSession session){
        ModelAndView mav = new ModelAndView();
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ServerResponse<PageInfo> result =  iShippingService.list(user.getId(),pageNum,pageSize);
        mav.addObject("result",result);
        mav.setViewName("/queryShipping.jsp");
        return mav;
    }














}
