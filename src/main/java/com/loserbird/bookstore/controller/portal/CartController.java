package com.loserbird.bookstore.controller.portal;


import com.github.pagehelper.PageInfo;
import com.loserbird.bookstore.common.Const;
import com.loserbird.bookstore.common.ResponseCode;
import com.loserbird.bookstore.common.ServerResponse;
import com.loserbird.bookstore.pojo.Shipping;
import com.loserbird.bookstore.pojo.User;
import com.loserbird.bookstore.service.IBookService;
import com.loserbird.bookstore.service.ICartService;
import com.loserbird.bookstore.service.IShippingService;
import com.loserbird.bookstore.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    private ICartService iCartService;

    @Autowired
    private IBookService iBookService;

    @Autowired
    IShippingService iShippingService;



    @RequestMapping("list.do")
    @ResponseBody
    public ModelAndView list(HttpSession session){
        ModelAndView mav = new ModelAndView();
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ServerResponse<CartVo> result;
        if(user ==null){
            mav.setViewName("/login.jsp");
        }else{
            result = iCartService.list(user.getId());
            mav.addObject("result",result);
            mav.setViewName("/cart_list.jsp");
        }
        return mav;
    }



    @RequestMapping("add.do")
    public ModelAndView add(HttpSession session, Integer count, Integer bookId){
        ModelAndView mav = new ModelAndView();
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ServerResponse<CartVo> result;
        if(user ==null){
            //result = ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
            mav.setViewName("/login.jsp");
        }else{
            result = iCartService.add(user.getId(),bookId,count);
            result.setMsg("添加成功");
            mav.addObject("result",result);
            mav.setViewName("/cart_list.jsp");
        }
        return mav;
    }



    @RequestMapping("update.do")
    public ModelAndView update(HttpSession session, Integer count, Integer bookId){
        ModelAndView mav = new ModelAndView();
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ServerResponse<CartVo> result;
        if(user ==null){
            mav.setViewName("/login.jsp");
        }else{
            System.out.println("----------------------");
            System.out.println(count);
            System.out.println("----------------------");
            result = iCartService.update(user.getId(),bookId,count);
            result.setMsg("修改成功");
            mav.addObject("result",result);
            mav.setViewName("/cart_list.jsp");
        }
        return mav;
    }

    @RequestMapping("delete.do")
    public ModelAndView delete(HttpSession session, Integer bookId){
        ModelAndView mav = new ModelAndView();
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ServerResponse<CartVo> result;
        if(user ==null){
            mav.setViewName("/login.jsp");
        }else{
            result = iCartService.delete(user.getId(),bookId);
            result.setMsg("删除成功");
            mav.addObject("result",result);
            mav.setViewName("/cart_list.jsp");
        }
        return mav;
    }

    @RequestMapping("order.do")
    public ModelAndView order(HttpSession session, Integer[] bookId){
        System.out.println(bookId[0]);
        List<Integer> bookIds = Arrays.asList(bookId);
        ModelAndView mav = new ModelAndView();
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ServerResponse<CartVo> result;
        if(user ==null){
            mav.setViewName("/login.jsp");
        }else{
            //先把所有不选，再把要选的选上
            iCartService.selectOrUnSelect(user.getId(),null,Const.Cart.UN_CHECKED);
            iCartService.updateBatch(user.getId(),bookIds);
            //从购物车中获取数据
            result = iCartService.list(user.getId());
            ServerResponse<PageInfo> pageInfo = iShippingService.list(user.getId(),1,10);
            Shipping shipping = (Shipping) pageInfo.getData().getList().get(0);//默认取第一个地址
            mav.addObject("shipping",shipping);
            //result.setMsg("");
            mav.addObject("result",result);
            mav.setViewName("/order_create.jsp");
        }
        return mav;
    }

    @RequestMapping("deleteBatch.do")
    @ResponseBody
    public ServerResponse<CartVo> deleteBook(HttpSession session, String bookIds){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.deleteBook(user.getId(),bookIds);
    }


    @RequestMapping("select_all.do")
    @ResponseBody
    public ServerResponse<CartVo> selectAll(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectOrUnSelect(user.getId(),null,Const.Cart.CHECKED);
    }

    @RequestMapping("un_select_all.do")
    @ResponseBody
    public ServerResponse<CartVo> unSelectAll(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectOrUnSelect(user.getId(),null,Const.Cart.UN_CHECKED);
    }



    @RequestMapping("select.do")
    @ResponseBody
    public ServerResponse<CartVo> select(HttpSession session, Integer bookId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectOrUnSelect(user.getId(),bookId,Const.Cart.CHECKED);
    }

    @RequestMapping("un_select.do")
    @ResponseBody
    public ServerResponse<CartVo> unSelect(HttpSession session, Integer bookId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectOrUnSelect(user.getId(),bookId,Const.Cart.UN_CHECKED);
    }



    @RequestMapping("get_cart_book_count.do")
    @ResponseBody
    public ServerResponse<Integer> getCartBookCount(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            return ServerResponse.createBySuccess(0);
        }
        return iCartService.getCartBookCount(user.getId());
    }




    //全选
    //全反选

    //单独选
    //单独反选

    //查询当前用户的购物车里面的产品数量,如果一个产品有10个,那么数量就是10.




}
