package com.gaochaojin.service;

import com.gaochaojin.bean.Account;
import com.gaochaojin.bean.Message;
import com.gaochaojin.dao.AccountMapper;
import com.gaochaojin.dao.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: gaochaojin
 * @Email: gaochaojin@163.com
 * @Description:
 * @Date Created in 15:36 2020/2/20
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    AccountMapper accountMapper;

    @Override
    public void updateAmount(int amount, String userId) {
        // 操作支付宝的本地account账单表
        Account account = new Account();
        account.setUserId(userId);
        account.setAmount(amount);
        accountMapper.updateAccount(account);
    }

    @Override
    public int queryMessageCountByMessageId(int messageId) {
        int count = messageMapper.queryCountFromMessage(messageId);
        return count;
    }

    @Override
    public int insertMessage(String userId, int messageId, int amount) {
        Message message = new Message();
        message.setMessageId(messageId);
        message.setAmount(amount);
        message.setUserId(userId);
        int count = messageMapper.addMessage(message);
        return count;
    }
}
