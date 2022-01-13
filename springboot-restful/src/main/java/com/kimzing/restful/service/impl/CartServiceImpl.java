package com.kimzing.restful.service.impl;

import com.kimzing.utils.log.LogUtil;
import com.kimzing.restful.service.CartService;
import org.springframework.stereotype.Service;

/**
 * 购物车服务实现.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/1/3 16:12
 */
@Service
public class CartServiceImpl implements CartService {

    /**
     * 创建购物车
     * @param username
     */
    @Override
    public void createCart(String username) {
        LogUtil.info("正在创建[{}]购物车", username);
    }
}
