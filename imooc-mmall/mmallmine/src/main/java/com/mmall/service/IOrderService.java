package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;

import java.util.Map;

public interface IOrderService {

    ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);

    public ServerResponse pay(Long orderNo, Integer userId, String path);

    ServerResponse aliCallback(Map<String, String> params);

    ServerResponse createOrder(Integer userId,Integer shippingId);
}
