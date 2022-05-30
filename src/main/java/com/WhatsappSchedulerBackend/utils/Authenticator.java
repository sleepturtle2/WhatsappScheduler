package com.WhatsappSchedulerBackend.utils;

import com.WhatsappSchedulerBackend.model.domains.RequestDto;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class Authenticator {
    private Exception exception;

    public int verifyClient(RequestDto request){
        try{
            if(request.getClientId().isEmpty())
                return 411;

            if(!request.getUToken().isEmpty()){
                if(!Pattern.matches("[a-zA-Z@._0-9]{5,32}", request.getUToken()))
                    return 412;
            }
            else
                return 412;

            if(!request.getUToken().equals("PASSWORD"))
                throw exception;
        } catch(Exception e){
                return 511;
        }
        return 0; //no error
    }
}
