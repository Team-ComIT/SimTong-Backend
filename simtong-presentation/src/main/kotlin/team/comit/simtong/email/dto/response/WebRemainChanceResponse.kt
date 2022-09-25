package team.comit.simtong.email.dto.response

/**
 *
 * 이메일 인증 코드의 남은 발송 횟수를 전송하는 WebRemainChanceResponse
 *
 * @author Chokyunghyeon
 * @date 2022/09/24
 * @version 1.0.0
 **/
data class WebRemainChanceResponse(
    val remainChance: Short
)