package com.loserbird.bookstore.controller.backend;

import com.github.pagehelper.PageInfo;
import com.loserbird.bookstore.common.Const;
import com.loserbird.bookstore.common.ServerResponse;
import com.loserbird.bookstore.pojo.User;
import com.loserbird.bookstore.service.IOrderService;
import com.loserbird.bookstore.service.IUserService;
import com.loserbird.bookstore.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;



@Controller
@RequestMapping("/manage/order")
public class OrderManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IOrderService iOrderService;

    @RequestMapping("list.do")
    public ModelAndView orderList(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                  @RequestParam(value = "status",required = false)Integer status){
        ModelAndView mav = new ModelAndView();
        ServerResponse<PageInfo> result;
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null || user.getRole() == Const.Role.ROLE_CUSTOMER){
            result = ServerResponse.createByErrorMessage("无权限操作");
            mav.setViewName("/login.jsp");
        }else{
            result = iOrderService.manageList(status,pageNum,pageSize);
            mav.setViewName("/order_list.manage.jsp");
            mav.addObject("status",status);
        }
        mav.addObject("result",result);
        return mav;
    }

    @RequestMapping("detail.do")
    public ModelAndView orderDetail(HttpSession session, Long orderNo){
        ModelAndView mav = new ModelAndView();
        ServerResponse<OrderVo> result;
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null || user.getRole() == Const.Role.ROLE_CUSTOMER){
            result = ServerResponse.createByErrorMessage("无权限操作");
            mav.setViewName("/login.jsp");
        }else{
            mav.setViewName("/order_detail_manage.jsp");
            result = iOrderService.manageDetail(orderNo);
        }
        mav.addObject("result",result);
        return mav;
    }



    @RequestMapping("search.do")
    public ModelAndView orderSearch(HttpSession session, Long orderNo, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        ModelAndView mav = new ModelAndView();
        ServerResponse<PageInfo> result;
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null || user.getRole() == Const.Role.ROLE_CUSTOMER){
            result = ServerResponse.createByErrorMessage("无权限操作");
            mav.setViewName("/login.jsp");
        }else{
            result = iOrderService.manageSearch(orderNo,pageNum,pageSize);
            mav.setViewName("/order_search.manage.jsp");
        }
        mav.addObject("result",result);
        return mav;
    }



    @RequestMapping("send_goods.do")
    public ModelAndView orderSendGoods(HttpSession session, Long orderNo){
        ModelAndView mav = new ModelAndView();
        ServerResponse<OrderVo> result;
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null || user.getRole() == Const.Role.ROLE_CUSTOMER){
            result = ServerResponse.createByErrorMessage("无权限操作");
            mav.setViewName("/login.jsp");
        }else{
            //发货
            iOrderService.manageSendGoods(orderNo);
            mav.setViewName("/order_detail_manage.jsp");
            result = iOrderService.manageDetail(orderNo);
        }
        mav.addObject("result",result);
        return mav;
    }

    @RequestMapping("finish.do")
    public ModelAndView finishOrder(HttpSession session, Long orderNo){
        ModelAndView mav = new ModelAndView();
        ServerResponse<OrderVo> result;
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null || user.getRole() == Const.Role.ROLE_CUSTOMER){
            result = ServerResponse.createByErrorMessage("无权限操作");
            mav.setViewName("/login.jsp");
        }else{
            //完结订单
            iOrderService.FinishOrder(orderNo);
            mav.setViewName("/order_detail_manage.jsp");
            result = iOrderService.manageDetail(orderNo);
        }
        mav.addObject("result",result);
        return mav;
    }








}
