package ozog.service;


import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ozog.model.Mail;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class MailServiceTest {

    @Mock
    JavaMailSender mailSender;

    @InjectMocks
    MailService mailService;

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void sendMail(){
        Mail mail =createMail();
        MimeMessagePreparator preparator = mailService.getMimeMessagePreparator(mail);
        mailSender.send(preparator);
    }


    private Mail createMail(){
        Mail mail = new Mail();
        mail.setMailAddress("someone");
        mail.setMailTopic("New calories in meal");
        mail.setMailText("test");
        return mail;
    }
}
