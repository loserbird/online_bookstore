package com.loserbird.bookstore.controller.portal;

import com.loserbird.bookstore.common.Const;
import com.loserbird.bookstore.common.ServerResponse;
import com.loserbird.bookstore.pojo.User;
import com.loserbird.bookstore.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/order/")
public class OrderController {

    private static  final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private IOrderService iOrderService;


    @RequestMapping("create.do")
    public ModelAndView create(HttpSession session, Integer shippingId){
        ModelAndView mav = new ModelAndView();
        ServerResponse result;
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            mav.setViewName("/login.jsp");
        }else{
            result = iOrderService.createOrder(user.getId(),shippingId);
            mav.setViewName("/order_create_success.jsp");
            mav.addObject("result",result);
        }
        return mav;
    }


    @RequestMapping("cancel.do")
    public ModelAndView cancel(HttpSession session, Long orderNo){
        ModelAndView mav = new ModelAndView();
        ServerResponse result;
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            mav.setViewName("/login.jsp");
        }else{
            iOrderService.cancel(user.getId(),orderNo);
            result = iOrderService.getOrderDetail(user.getId(),orderNo);
            mav.addObject("result",result);
            mav.setViewName("/order_detail.jsp");
        }
        return mav;
    }


   /* @RequestMapping("get_order_cart_product.do")
    @ResponseBody
    public ServerResponse getOrderCartProduct(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iOrderService.getOrderCartBook(user.getId());
    }*/



    @RequestMapping("detail.do")
    public ModelAndView detail(HttpSession session, Long orderNo){
        ModelAndView mav = new ModelAndView();
        ServerResponse result;
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            mav.setViewName("/login.jsp");
        }else{
            result = iOrderService.getOrderDetail(user.getId(),orderNo);
            mav.addObject("result",result);
            mav.setViewName("/order_detail.jsp");
        }
        return mav;
    }

    @RequestMapping("list.do")
    public ModelAndView list(HttpSession session,
                             @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                             @RequestParam(value = "status",required = false) Integer status){
        ModelAndView mav = new ModelAndView();
        ServerResponse result;
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            mav.setViewName("/login.jsp");
        }else{
            //result = iOrderService.getOrderList(user.getId(),pageNum,pageSize);
            result = iOrderService.getOrderList(user.getId(),status,pageNum,pageSize);
            mav.addObject("result",result);
            mav.setViewName("/order_list.jsp");
        }
        return mav;
    }


    @RequestMapping("pay.do")
    public ModelAndView pay(HttpSession session, Long orderNo, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        ServerResponse result;
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            mav.setViewName("/login.jsp");
        }else{
            result = iOrderService.pay(orderNo,user.getId());
            if(!result.isSuccess()) result.setMsg("支付失败");
            mav.setViewName("/order_pay_success.jsp");
            mav.addObject("result",result);
        }
        return mav;
    }


  /*  @RequestMapping("query_order_pay_status.do")
    @ResponseBody
    public ServerResponse<Boolean> queryOrderPayStatus(HttpSession session, Long orderNo){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }

        ServerResponse serverResponse = iOrderService.queryOrderPayStatus(user.getId(),orderNo);
        if(serverResponse.isSuccess()){
            return ServerResponse.createBySuccess(true);
        }
        return ServerResponse.createBySuccess(false);
    }*/



















}
