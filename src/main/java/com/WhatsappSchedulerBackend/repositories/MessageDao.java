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
        String query = "insert into Message ( account_id, message_body, scheduled_date_time, send_To, message_status) values (?,?,?,?,?)";
        int res = template.update(query, ((Message) entity).getAccountId(), ((Message) entity).getMessageBody(), ((Message) entity).getScheduledDateTime(), ((Message) entity).getSendTo(), ((Message) entity).getMessageStatus());
        return res;
    }

    @Override
    public int update(Entities entity) {
        // TODO Auto-generated method stub
        String query = "update Message set Status = ? where Message_ID = ?";
        int res = template.update(query, ((Message) entity).getMessageStatus(), ((Message) entity).getMessageId());
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
        String query = "select * from Message where Scheduled_Date_Time <= '" + Timestamp.valueOf(LocalDateTime.parse(formatDateTime.replace(" ", "T"))).toString().replace(".0", "") +  "' and status = 0";
        RowMapper<Message> mapper = new MessageMapper();
        List<Message> messages = template.query(query, mapper);
        return messages;
    }

    public int retrieveMessageId(Entities entity){
        String query = "select * from Message where Scheduled_Time = '" + ((Message) entity).getScheduledDateTime() + "' and Account_ID = " + ((Message) entity).getAccountId() + " and Send_To = " + ((Message) entity).getSendTo();
        RowMapper<Message> mapper = new MessageMapper();
        Message message = template.queryForObject(query, mapper);
        return message.getMessageId();
    }

    public Message retrieveMessageById(Long id) {
        String query = "select * from Message where Message_ID = " + id;
        RowMapper<Message> mapper = new MessageMapper();
        Message message = template.queryForObject(query, mapper);
        return message;
    }
}
