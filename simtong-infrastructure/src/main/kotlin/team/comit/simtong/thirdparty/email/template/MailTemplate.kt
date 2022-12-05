package team.comit.simtong.thirdparty.email.template

/**
 *
 * AWS SES Template를 관리하는 EmailTemplate
 *
 * @author Chokyunghyeon
 * @date 2022/09/08
 * @version 1.0.0
 **/
enum class MailTemplate(
    val templateName: String
) {

    AuthCode("SIMTONG_SIGNUP_TEMPLATE")

}