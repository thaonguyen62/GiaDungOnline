package ltwwwjava.btl.dogiadungtructuyen.service.impl;

import ltwwwjava.btl.dogiadungtructuyen.service.EmailService;
import ltwwwjava.btl.dogiadungtructuyen.utils.Constants;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String toEmail, String subject) {
        MimeMessage msg = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText( "<p> Đăng kí thành công </p>" +  "<p>Thank you,</p><p>Gia dung Online Technical Support</p>", true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        javaMailSender.send(msg);
    }
}
