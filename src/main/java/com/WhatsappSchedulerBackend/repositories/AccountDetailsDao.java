package com.WhatsappSchedulerBackend.repositories;

import com.WhatsappSchedulerBackend.mappers.AccountDetailsMapper;
import com.WhatsappSchedulerBackend.model.entities.Entities;
import com.WhatsappSchedulerBackend.model.entities.GupshupAccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountDetailsDao implements DBInterface{
    @Autowired
    JdbcTemplate template;

    @Override
    public int insert(Entities entity){
        return 0;
    }

    @Override
    public int update(Entities entity){
        return 0;
    }

    @Override
    public int delete(Entities entity){
        return 0;
    }

    public String retrieve(int accountId){
        String query = "select * from Gupshup_Account_Details where Account_ID = ?";
        RowMapper<GupshupAccountDetails> rowMapper = new AccountDetailsMapper();
        GupshupAccountDetails credential = this.template.queryForObject(query, rowMapper, accountId);
        System.out.println(credential.getGupshupApiKey());
        return credential.getGupshupApiKey();
    }
}
