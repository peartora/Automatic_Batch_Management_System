package com.example.batchmanagingsystem.service;

import com.example.batchmanagingsystem.event.EmailSendEvent;
import com.samskivert.mustache.Mustache;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@RequiredArgsConstructor
@Service
public class EmailService
{
    private final JavaMailSender mailSender;

    @Value("classpath:email/batch.mustache")
    private Resource emailTemplateResource;

    public static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Async
    @EventListener
    public void sendMail(EmailSendEvent event) throws MessagingException
    {
        final Map<String, Object> data = new HashMap<>();
        data.put("infos", event.getInfos());

        final String template = asString(this.emailTemplateResource);
        final String content = Mustache.compiler().compile(template).execute(data);

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        messageHelper.setTo(event.getTo());
        messageHelper.setSubject(event.getTitle());
        messageHelper.setText(content, true);

        this.mailSender.send(mimeMessage);
    }
}
