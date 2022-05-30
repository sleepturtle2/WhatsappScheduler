package com.WhatsappSchedulerBackend.mappers;

import com.WhatsappSchedulerBackend.model.entities.GupshupAccountDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDetailsMapper implements RowMapper<GupshupAccountDetails> {

    @Override
    public GupshupAccountDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        GupshupAccountDetails accountDetails = new GupshupAccountDetails();

        accountDetails.setAccountId(rs.getInt(1));
        accountDetails.setGupshupApiKey(rs.getString(2));

        return accountDetails;
    }
}
