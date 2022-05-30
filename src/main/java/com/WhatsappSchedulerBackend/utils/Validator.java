package com.WhatsappSchedulerBackend.utils;

import com.WhatsappSchedulerBackend.model.domains.RequestDto;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class Validator {
   PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

   private Exception exception;

   public int messageIsValid(RequestDto request){
      if(request.getMessage().isEmpty())
         return 413;

      if(!request.getScheduledTime().isEmpty()) {
         if (request.getScheduledTime().equals("now")) {

         } else if (!isTimeValid(request.getScheduledTime())) {
            return 414;
         }
      }
      else {
         return 414;
      }

      if(!phoneNumberUtil.isPossibleNumber(request.getSendTo(), "IN")){
         return 415;
      }

      return 0; //no error

   }

   private boolean isTimeValid(String time) {
      try {
         LocalDateTime scheduledTime = LocalDateTime.parse(time.replace(" ", "T"));
         if (scheduledTime.isBefore(LocalDateTime.now())) {
            throw exception;
         }
      } catch (Exception e) {
         return false;
      }
      return true;
   }
}
