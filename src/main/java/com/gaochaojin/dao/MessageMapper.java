package com.gaochaojin.dao;

import com.gaochaojin.bean.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: gaochaojin
 * @Email: gaochaojin@163.com
 * @Description:
 * @Date Created in 15:32 2020/2/20
 */
public interface MessageMapper {

    @Select("select count(*) from t_message where message_id = #{messageId}")
    int queryCountFromMessage(int messageId);

    @Insert("insert into t_message(user_id,message_id,amount) values(#{userId},#{messageId},#{amount})")
    int addMessage(Message message);

}
