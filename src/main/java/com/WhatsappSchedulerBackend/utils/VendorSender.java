package com.WhatsappSchedulerBackend.utils;

import com.WhatsappSchedulerBackend.model.entities.Message;
import com.WhatsappSchedulerBackend.repositories.AccountDetailsDao;
import com.WhatsappSchedulerBackend.repositories.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Service
public class VendorSender {

    @Autowired
    AccountDetailsDao accountDetails;

    @Autowired
    MessageDao messageDAO;

    private final RestTemplate template = new RestTemplateBuilder().build();

    public void sendBatch(List<Message> messages){
        for (Message message : messages) {
            send(message);
        }
        return;
    }

    public void send(Message message){
        String url = "https://api.gupshup.io/sm/api/v1/msg";
        String apikey = accountDetails.retrieve(message.getAccountId());
        System.out.println(apikey);

        String mString = URLEncoder.encode("{\"type\":\"text\",\"text\":\""+message.getMessageBody()+"\"}", StandardCharsets.UTF_8) ;
        System.out.println("URL ENCODED MSG = " + mString);


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)).header("Accept", "application/json").header("apikey", apikey).header("Content-Type", "application/x-www-form-urlencoded").method(
                        "POST", HttpRequest.BodyPublishers.ofString("message="+mString+"&src.name=WhatsappScheduleText&channel=whatsapp&source=917834811114&destination="+message.getSendTo())).build();

        System.out.println("Test = " + "message="+mString+"&src.name=WhatsappScheduleText&channel=whatsapp&source=917834811114&destination="+message.getSendTo());
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if (response.statusCode() == HttpStatus.ACCEPTED.value()) {
                message.setMessageStatus(1);
                int r = messageDAO.update(message);
            } else {
                message.setMessageStatus(400);
                messageDAO.update(message);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        int r = messageDAO.update(message);
    }
}
