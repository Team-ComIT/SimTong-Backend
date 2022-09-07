package team.comit.simtong.thirdparty.email

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.amazonaws.services.simpleemail.model.Destination
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import team.comit.simtong.domain.auth.spi.SendEmailPort
import team.comit.simtong.thirdparty.email.template.MailTemplate

/**
 *
 * Mail 전송을 처리하는 MailSendAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/08
 * @version 1.0.0
 **/
@Component
class MailSendAdapter(
    private val amazonSimpleEmailService: AmazonSimpleEmailService,
    private val awsSESProperties: AwsSESProperties,
    private val objectMapper: ObjectMapper
): SendEmailPort {

    override fun sendAuthCode(code: String, email: String) {
        val templateData = HashMap<String, String>()
        templateData["code"] = code

        sendEmail(
            template = MailTemplate.AuthCode,
            templateData = templateData,
            email
        )
    }

    private fun sendEmail(template: MailTemplate, templateData: Map<String, String>, vararg emails: String) {
        val emailRequest = SendTemplatedEmailRequest()
        emailRequest.also {
            it.destination = Destination(emails.toList())
            it.template = template.name
            it.source = awsSESProperties.source
            it.templateData = objectMapper.writeValueAsString(templateData)
        }

        amazonSimpleEmailService.sendTemplatedEmail(emailRequest)
    }
}