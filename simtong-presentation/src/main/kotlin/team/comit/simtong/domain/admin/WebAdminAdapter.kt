package team.comit.simtong.domain.admin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.admin.dto.request.SignInWebRequest
import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.user.dto.AdminSignInRequest
import team.comit.simtong.domain.user.dto.QueryAdminInfoResponse
import team.comit.simtong.domain.user.usecase.AdminSignInUseCase
import team.comit.simtong.domain.user.usecase.QueryAdminInfoUseCase
import javax.validation.Valid

/**
 *
 * Admin에 관한 요청을 받는 WebAdminAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/10/04
 * @version 1.2.3
 **/
@RestController
@RequestMapping("/admins")
class WebAdminAdapter(
    private val adminSignInUseCase: AdminSignInUseCase,
    private val queryAdminInfoUseCase: QueryAdminInfoUseCase
) {

    @PostMapping("/tokens")
    fun signIn(@Valid @RequestBody request: SignInWebRequest): TokenResponse {
        return adminSignInUseCase.execute(
            AdminSignInRequest(
                employeeNumber = request.employeeNumber.value,
                password = request.password.value
            )
        )
    }

    @GetMapping("/information")
    fun getAdminInfo(): QueryAdminInfoResponse {
        return queryAdminInfoUseCase.execute()
    }

}