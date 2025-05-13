package com.candidateonboardingsystem.service;

import com.candidateonboardingsystem.domain.authDto.OtpMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendMailWithTemplate(String to, String subject, Map<String, Object> model) {
        Context context = new Context();
        context.setVariables(model);

        String htmlContent = templateEngine.process("Mail", context);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // true = isHtml
            helper.setFrom("your-email@gmail.com");

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendOtpMail(final OtpMessage otpMessage) {
        try{
            Context context = new Context();
            context.setVariable("otp", otpMessage.otp());
            context.setVariable("email", otpMessage.email());

            String htmlContent = templateEngine.process("otp-email-template", context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    mimeMessage,
                    true,
                    StandardCharsets.UTF_8.name()
            );
            helper.setTo(otpMessage.email());
            helper.setSubject("OTP Notification");
            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
        }
        catch (Exception e){
            log.error(e.toString());
        }
    }
}
