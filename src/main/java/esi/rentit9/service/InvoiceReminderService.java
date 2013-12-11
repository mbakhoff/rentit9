package esi.rentit9.service;

import esi.rentit9.domain.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kmetsalu on 11/12/13.
 */
@Service
public class InvoiceReminderService{

    @Autowired
    private JavaMailSender smtp;
    /**
     * Sends reminders every day at 8 a clock
     */
    @Async
    @Scheduled(cron = "* * 8 * * ?")
    public void sendReminderForUnpaidInvoices(){
        List<Invoice> unpaid=Invoicing.getUnpaidInvoices();
        for(Invoice inv:unpaid){
            inv.getPurchaseOrder().getBuildit().getInterop().sendInvoice(smtp,inv);
        }
    }
}
