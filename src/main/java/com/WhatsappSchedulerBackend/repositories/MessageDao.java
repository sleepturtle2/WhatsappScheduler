package com.WhatsappSchedulerBackend.repositories;

import com.WhatsappSchedulerBackend.model.entities.Entities;
import com.WhatsappSchedulerBackend.model.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageDao implements DBInterface{

    @Autowired
    JdbcTemplate template;

    @Override
    public int insert(Entities entity) {
        // TODO Auto-generated method stub
        String query = "insert into Message (Account_ID, MSG, Scheduled_time, Send_To, Status) values (?,?,?,?,?)";
        int res = template.update(query, ((Message) entity).get(), ((Message) entity).getMsg(), ((Message) entity).getTimestamp(), ((Message) entity).getSend_To(), ((Message) entity).getStatus());
        return res;
    }

    @Override
    public int update(Entities entity) {
        // TODO Auto-generated method stub
        String query = "update Message set Status = ? where Message_ID = ?";
        int res = template.update(query, ((Message) entity).getStatus(), ((Message) entity).getMessage_ID());
        return res;
    }

    @Override
    public int delete(Entities entity) {
        // TODO Auto-generated method stub
        return 0;
    }
}
