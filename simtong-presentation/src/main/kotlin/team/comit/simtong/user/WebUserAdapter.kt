package team.comit.simtong.user

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.user.dto.ResetPasswordRequest
import team.comit.simtong.domain.user.dto.SignInRequest
import team.comit.simtong.domain.user.dto.SignUpRequest
import team.comit.simtong.domain.user.dto.UserInfoResponse
import team.comit.simtong.domain.user.usecase.ResetPasswordUseCase
import team.comit.simtong.domain.user.usecase.SignInUseCase
import team.comit.simtong.domain.user.usecase.SignUpUseCase
import team.comit.simtong.domain.user.usecase.UserInfoUseCase
import team.comit.simtong.user.dto.request.WebResetPasswordRequest
import team.comit.simtong.user.dto.request.WebSignInRequest
import team.comit.simtong.user.dto.request.WebSignUpRequest
import javax.validation.Valid

/**
 *
 * User에 관한 요청을 받는 WebUserAdapter
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/04
 * @version 1.0.0
 **/
@RestController
@RequestMapping("/users")
class WebUserAdapter(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val getInfoUseCase: UserInfoUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun signUp(@Valid @RequestBody request: WebSignUpRequest): TokenResponse {
        return signUpUseCase.execute(
            SignUpRequest(
                name = request.name,
                email = request.email,
                password = request.password,
                nickname = request.nickname,
                profileImagePath = request.profileImagePath,
                employeeNumber = request.employeeNumber
            )
        )
    }

    @PostMapping("/tokens")
    fun signIn(@Valid @RequestBody request: WebSignInRequest): TokenResponse {
        return signInUseCase.execute(
            SignInRequest(
                employeeNumber = request.employeeNumber,
                password = request.password
            )
        )
    }

    @GetMapping("/information")
    fun getMyInfo(): UserInfoResponse {
        return getInfoUseCase.execute()
    }

    @PutMapping("/password/initialization")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun resetPassword(@Valid @RequestBody request: WebResetPasswordRequest) {
        resetPasswordUseCase.execute(
            ResetPasswordRequest(
                email = request.email,
                employeeNumber = request.employeeNumber,
                newPassword = request.newPassword
            )
        )
    }

}