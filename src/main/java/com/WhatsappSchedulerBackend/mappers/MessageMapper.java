package com.WhatsappSchedulerBackend.mappers;

import com.WhatsappSchedulerBackend.model.entities.Message;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements RowMapper<Message> {
    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {

        Message message = new Message();
        message.setMessageId(rs.getInt(1));
        message.setAccountId(rs.getInt(2));
        message.setMessageBody(rs.getString(3));
        message.setScheduledDateTime(rs.getTimestamp(4).toLocalDateTime());
        message.setSendTo(Integer.valueOf(rs.getString(5)));
        message.setMessageStatus(rs.getInt(6));
        return message;
    }
}
