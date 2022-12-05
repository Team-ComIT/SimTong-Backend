package team.comit.simtong.thirdparty.email

import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsync
import com.amazonaws.services.simpleemail.model.Destination
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import team.comit.simtong.domain.auth.spi.SendEmailPort
import team.comit.simtong.thirdparty.email.template.MailTemplate

/**
 *
 * AWS SES를 이용해 메일 전송을 처리하는 AwsSESAdapter
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/08
 * @version 1.0.0
 **/
@Component
class AwsSESAdapter(
    private val amazonSimpleEmailServiceAsync: AmazonSimpleEmailServiceAsync,
    private val awsSESProperties: AwsSESProperties,
    private val objectMapper: ObjectMapper
) : SendEmailPort {

    override fun sendAuthCode(code: String, email: String) {
        val templateData = mapOf(
            "code" to code
        )

        sendEmail(
            mailTemplate = MailTemplate.AuthCode,
            data = templateData,
            email
        )
    }

    private fun sendEmail(mailTemplate: MailTemplate, data: Map<String, String>, vararg emails: String) {
        val emailRequest = SendTemplatedEmailRequest().apply {
            destination = Destination(emails.toList())
            template = mailTemplate.name
            source = awsSESProperties.source
            templateData = objectMapper.writeValueAsString(data)
        }

        amazonSimpleEmailServiceAsync.sendTemplatedEmailAsync(emailRequest)
    }
}