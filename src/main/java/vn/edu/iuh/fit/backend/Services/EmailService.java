package vn.edu.iuh.fit.backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;


    /**
     * Hàm gửi email đơn giản.
     * @param to       Địa chỉ email người nhận.
     * @param subject  Tiêu đề email.
     * @param body     Nội dung email.
     */
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("your-email@gmail.com");

        mailSender.send(message);
    }
}
