package team.comit.simtong.admin

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.user.dto.SignInRequest
import team.comit.simtong.domain.user.usecase.AdminSignInUseCase
import team.comit.simtong.user.dto.request.WebSignInRequest
import javax.validation.Valid

/**
 *
 * Admin에 관한 요청을 받는 WebAdminAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/10/04
 * @version 1.0.0
 **/
@RestController
@RequestMapping("/admins")
class WebAdminAdapter(
    private val adminSignInUseCase: AdminSignInUseCase
) {

    @PostMapping("/tokens")
    fun signIn(@Valid @RequestBody request: WebSignInRequest): TokenResponse {
        return adminSignInUseCase.execute(
            SignInRequest(
                employeeNumber = request.employeeNumber,
                password = request.password
            )
        )
    }

}