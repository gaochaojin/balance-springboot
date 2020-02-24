package com.gaochaojin.listener;

import com.alibaba.fastjson.JSONObject;
import com.gaochaojin.bean.Message;
import com.gaochaojin.rabbitmq.RabbitmqSender;
import com.gaochaojin.service.OrderService;
import com.mysql.cj.protocol.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @Author: gaochaojin
 * @Email: gaochaojin@163.com
 * @Description:
 * @Date Created in 16:33 2020/2/20
 */
@Slf4j
@Component
public class MessageListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TransactionTemplate transactionTemplate;

    @Autowired
    OrderService orderService;

    @Autowired
    RabbitmqSender rabbitmqSender;

    @RabbitListener(queues = "jack.message")
    public void process(String strMsg) {
        final Message message = JSONObject.parseObject(strMsg, Message.class);
        log.info("=========开始消费支付宝的消息============" + JSONObject.toJSONString(message));
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                int messageId = message.getMessageId();
                int count = orderService.queryMessageCountByMessageId(messageId);
                if (count == 0) {
                    orderService.updateAmount(message.getAmount(), message.getUserId());
                    orderService.insertMessage(message.getUserId(), message.getMessageId(), message.getAmount());
                } else {
                    logger.info("异常转账");
                }
                return null;
            }
        });

        // 回调支付宝修改状态接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", message.getMessageId());
        jsonObject.put("respCode", "ok");
        log.info("========发送转账成功应答消息给支付宝==========" + jsonObject.toJSONString());
        rabbitmqSender.sendMessage("exchange.message.response", "jack.message.routeKey.response", jsonObject.toJSONString());
    }

}
