package com.polaroid.app.command;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SendMailHelper {

	@Autowired
	private JavaMailSender javaMailSender;

	// 메일을 송신한다.
	public void sendMail(String fromAddress, String[] toAddress, String subect, String body) throws Exception {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(fromAddress);
		msg.setTo(toAddress);
		msg.setSubject(subect);
		msg.setText(body);

		try {
			javaMailSender.send(msg);
		} catch (Exception ex) {
			log.error("failed to send mail : {}", ex);
			throw ex;
		}
	}

	/*
	 * Map<String, Object> map = new HashMap<String, Object>(); map.put("name",
	 * "일길동"); map.put("message", "안녕하세요");
	 */

	// 지정한 템플릿의 메일 본문을 반환한다.
	// @param template : mail (resources/templates/mail.html) : 템플릿
	// @param objects : Template에 전달할 데이터 설정

	public String getMailBody(String template, Map<String, Object> objects) {

		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());

		Context context = new Context();

		// Template에 전달할 데이터 설정
		objects.forEach(context::setVariable);

		return templateEngine.process(template, context);

	}

	private StringTemplateResolver templateResolver() {
		StringTemplateResolver resolver = new StringTemplateResolver();
		resolver.setTemplateMode("TEXT");
		resolver.setCacheable(false);
		return resolver;
	}

}