package bootcamp.proyectoFinal.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements iEmail {

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public boolean sendEmail(Email email) {

        return  validateEmail(email.getEmail(), email.getContent(), email.getSubject());
    }

    public boolean validateEmail(String email, String content, String subject)
    {
        boolean send = false;

        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper msgHelper = new MimeMessageHelper(msg);
        try {
            msgHelper.setTo(email);
            msgHelper.setText(content);
            msgHelper.setSubject(subject);
            mailSender.send(msg);
            send = true;
        }catch (MessagingException e) {
            send = false;
        }
        return  send;
    }
}
