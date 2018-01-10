package com.loserbird.bookstore.service;

import com.github.pagehelper.PageInfo;
import com.loserbird.bookstore.common.ServerResponse;
import com.loserbird.bookstore.vo.OrderVo;


public interface IOrderService {
    ServerResponse pay(Long orderNo, Integer userId);
    ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);
    ServerResponse createOrder(Integer userId, Integer shippingId);
    ServerResponse<String> cancel(Integer userId, Long orderNo);
    ServerResponse getOrderCartBook(Integer userId);
    ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo);
    ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize);
    ServerResponse<PageInfo> getOrderList(Integer userId, Integer status,int pageNum, int pageSize);



    //backend
    ServerResponse<PageInfo> manageList(Integer status,int pageNum, int pageSize);
    ServerResponse<OrderVo> manageDetail(Long orderNo);
    ServerResponse<PageInfo> manageSearch(Long orderNo, int pageNum, int pageSize);
    ServerResponse<String> manageSendGoods(Long orderNo);
    ServerResponse<String> FinishOrder(Long orderNo);


}
