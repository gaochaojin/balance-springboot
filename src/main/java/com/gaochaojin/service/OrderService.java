package com.gaochaojin.service;

/**
 * @Author: gaochaojin
 * @Email: gaochaojin@163.com
 * @Description:
 * @Date Created in 15:35 2020/2/20
 */
public interface OrderService {

    public void updateAmount(int amount, String userId);

    public int queryMessageCountByMessageId(int messageId);

    public int insertMessage(String userId, int messageId, int amount);
}
