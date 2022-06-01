package com.WhatsappSchedulerBackend.repositories;

import com.WhatsappSchedulerBackend.mappers.MessageMapper;
import com.WhatsappSchedulerBackend.model.entities.Entities;
import com.WhatsappSchedulerBackend.model.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class MessageDao implements DBInterface{

    @Autowired
    JdbcTemplate template;

    @Override
    public int insert(Entities entity) {
        String query = "insert into Message ( account_id, message_body, scheduled_date_time, send_to, message_status) values (?,?,?,?,?)";
        int res = template.update(query, ((Message) entity).getAccountId(), ((Message) entity).getMessageBody(), ((Message) entity).getScheduledDateTime(), ((Message) entity).getSendTo(), ((Message) entity).getMessageStatus());
//        System.out.println(res);
        return res;
    }

    @Override
    public int update(Entities entity) {
        // TODO Auto-generated method stub
        String query = "update Message set Message_Status = ? where Message_ID = ?";
        int res = template.update(query, ((Message) entity).getMessageStatus(), ((Message) entity).getMessageId());
//        System.out.println(res);
        return res;
    }

    @Override
    public int delete(Entities entity) {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<Message> retrieveScheduledMessages(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = localDateTime.format(formatter);
        System.out.println(formatDateTime);
        String query = "select * from Message where Scheduled_Date_Time <= '" + Timestamp.valueOf(LocalDateTime.parse(formatDateTime.replace(" ", "T"))).toString().replace(".0", "") +  "' and message_status = 0";
        RowMapper<Message> mapper = new MessageMapper();
        List<Message> messages = template.query(query, mapper);
        return messages;
    }

    public int retrieveMessageId(Entities entity){
        String query = "select * from Message where scheduled_date_time = '" + ((Message) entity).getScheduledDateTime() + "' and account_id = " + ((Message) entity).getAccountId() + " and send_to = " + ((Message) entity).getSendTo();
        RowMapper<Message> mapper = new MessageMapper();
        Message message = template.queryForObject(query, mapper);
        return message.getMessageId();
    }

    public Message retrieveMessageById(Long id) {
        String query = "select * from Message where message_id = " + id;
        RowMapper<Message> mapper = new MessageMapper();
        Message message = template.queryForObject(query, mapper);
        return message;
    }
}
