package br.com.emanuelgabriel.apienvioemail.services.email;

import br.com.emanuelgabriel.apienvioemail.domain.mapper.request.EmailRequestDTO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author emanuel.sousa
 */

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    @Qualifier("emailConfigBean")
    private Configuration emailConfig;

    /**
     * @autor emanuel.sousa
     * @param mailModel
     * @param nome
     * @param url
     * @throws MessagingException
     * @throws IOException
     * @throws TemplateException
     */
    public void enviarEmail(EmailRequestDTO mailModel, String nome, String url) throws MessagingException, IOException, TemplateException {

        Map model = new HashMap();
        model.put("nome", nome);
        model.put("url", url);

        mailModel.setModel(model);

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Template template = emailConfig.getTemplate("email.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailModel.getModel());

        mimeMessageHelper.setTo(mailModel.getEmailDestino());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(mailModel.getAssunto());
        mimeMessageHelper.setFrom(mailModel.getEmailOrigem());

        emailSender.send(message);

    }


}
