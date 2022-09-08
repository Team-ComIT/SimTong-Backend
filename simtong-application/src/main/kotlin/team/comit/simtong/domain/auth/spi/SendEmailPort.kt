package team.comit.simtong.domain.auth.spi

/**
 *
 * Mail 전송을 요청하는 SendEmailPort
 *
 * @author Chokyunghyeon
 * @date 2022/09/08
 * @version 1.0.0
 **/
interface SendEmailPort {

    fun sendAuthCode(code: String, email: String) // TODO 비동기 처리

}