package team.comit.simtong.email

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.auth.usecase.CheckAuthCodeUseCase
import team.comit.simtong.domain.auth.usecase.SendAuthCodeUseCase
import team.comit.simtong.email.dto.request.WebCheckAuthCodeRequest
import team.comit.simtong.email.dto.request.WebSendAuthCodeRequest
import team.comit.simtong.email.dto.response.WebRemainChanceResponse
import javax.validation.Valid

/**
 *
 * 이메일에 관한 요청을 받는 WebEmailAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/24
 * @version 1.0.0
 **/
@RestController
@RequestMapping("/emails")
class WebEmailAdapter(
    private val sendAuthCodeUseCase: SendAuthCodeUseCase,
    private val checkAuthCodeUseCase: CheckAuthCodeUseCase
) {

    @PostMapping("/code")
    fun sendAuthCode(@Valid @RequestBody request: WebSendAuthCodeRequest): WebRemainChanceResponse {
        return WebRemainChanceResponse(
            sendAuthCodeUseCase.execute(request.email)
        )
    }

    @GetMapping
    fun checkAuthCode(@Valid request: WebCheckAuthCodeRequest) {
        checkAuthCodeUseCase.execute(request.email, request.code)
    }

}