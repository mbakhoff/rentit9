package esi.rentit9.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;

@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IntegrationTest {

    @Autowired
    private JavaMailSender smtp;

    @Test
    public void testSmtpConfig() throws Exception {
        MimeMessage msg = smtp.createMimeMessage();
        msg.setSubject("testSmtpConfig");
        msg.setText("test runner at rentit9");
        msg.setRecipients(Message.RecipientType.TO, "rentit9esi@gmail.com");
        smtp.send(msg);
    }
}
