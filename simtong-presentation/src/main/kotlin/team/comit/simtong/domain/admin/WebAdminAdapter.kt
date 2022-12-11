package team.comit.simtong.domain.admin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.user.dto.AdminInfoResponse
import team.comit.simtong.domain.user.dto.SignInRequest
import team.comit.simtong.domain.user.dto.request.SignInWebRequest
import team.comit.simtong.domain.user.usecase.AdminInfoUseCase
import team.comit.simtong.domain.user.usecase.AdminSignInUseCase
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
    private val adminSignInUseCase: AdminSignInUseCase,
    private val adminInfoUseCase: AdminInfoUseCase
) {

    @PostMapping("/tokens")
    fun signIn(@Valid @RequestBody request: SignInWebRequest): TokenResponse {
        return adminSignInUseCase.execute(
            SignInRequest(
                employeeNumber = request.employeeNumber,
                password = request.password
            )
        )
    }

    @GetMapping("/information")
    fun getAdminInfo(): AdminInfoResponse {
        return adminInfoUseCase.execute()
    }

}