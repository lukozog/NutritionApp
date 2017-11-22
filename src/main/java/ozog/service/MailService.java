package ozog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import ozog.model.Mail;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service("mailService")
public class MailService {

    @Autowired
    JavaMailSender mailSender;

    public void sendEmail(Object object) {
        Mail mail = (Mail) object;

        MimeMessagePreparator preparator = getMimeMessagePreparator(mail);
        try {
            mailSender.send(preparator);
            System.out.println("Sending mail");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public MimeMessagePreparator getMimeMessagePreparator(final Mail mail) {
        return mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getMailAddress()));
            mimeMessage.setText(mail.getMailText());
            mimeMessage.setSubject(mail.getMailTopic());
        };
    }

}
