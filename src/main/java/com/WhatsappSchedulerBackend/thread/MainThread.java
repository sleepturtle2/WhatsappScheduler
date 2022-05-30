package com.WhatsappSchedulerBackend.thread;

import com.WhatsappSchedulerBackend.repositories.MessageDao;
import com.WhatsappSchedulerBackend.utils.VendorSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("thread")
public class MainThread implements Runnable{

    @Autowired
    MessageDao messageDAO;

    @Autowired
    VendorSender sender;

    public MainThread() {
        // run();
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(30000);
                sender.sendBatch(messageDAO.retrieveScheduledMessages());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }



}

