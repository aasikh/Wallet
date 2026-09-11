package wallet.demo.service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    private final JavaMailSender mailerSender;
    public EmailService(JavaMailSender mailSender){
        this.mailerSender = mailSender;
    }

    public boolean send(String email , String name) {

        try {
            String subject = "Welcome to Zenvault";
            ClassPathResource templates = new ClassPathResource("templates/welcome-email.html");
            ClassPathResource logo = new ClassPathResource("static/images/zenvault-logo.png");

            String body = new String(templates.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            body = body.replace("{{name}}", name);
            MimeMessage message = mailerSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setText(body, true);
            helper.setSubject(subject);
            helper.setTo(email);
            helper.addInline("zenvaultLogo", logo);
            mailerSender.send(message);
          return true;
        } catch (IOException e) {

            e.printStackTrace();
            return false;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
