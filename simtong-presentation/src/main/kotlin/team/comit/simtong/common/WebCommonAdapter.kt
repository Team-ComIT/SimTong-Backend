package team.comit.simtong.common

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import team.comit.simtong.common.dto.request.ChangePasswordWebRequest
import team.comit.simtong.common.dto.request.CheckAccountByNameAndEmailWebRequest
import team.comit.simtong.common.dto.request.FindEmployeeNumberWebRequest
import team.comit.simtong.common.dto.request.ResetPasswordWebRequest
import team.comit.simtong.common.dto.response.FindEmployeeNumberWebResponse
import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.auth.usecase.ReissueTokenUseCase
import team.comit.simtong.domain.user.dto.ChangePasswordRequest
import team.comit.simtong.domain.user.dto.CheckAccountByNameAndEmailRequest
import team.comit.simtong.domain.user.dto.FindEmployeeNumberRequest
import team.comit.simtong.domain.user.dto.ResetPasswordRequest
import team.comit.simtong.domain.user.usecase.ChangePasswordUseCase
import team.comit.simtong.domain.user.usecase.CheckAccountByNameAndEmailUseCase
import team.comit.simtong.domain.user.usecase.CheckEmailDuplicationUseCase
import team.comit.simtong.domain.user.usecase.FindEmployeeNumberUseCase
import team.comit.simtong.domain.user.usecase.ResetPasswordUseCase
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

/**
 *
 * User와 Admin에 관한 요청을 받는 WebCommonAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/11
 * @version 1.0.0
 **/
@RestController
@RequestMapping("/commons")
class WebCommonAdapter(
    private val reissueTokenUseCase: ReissueTokenUseCase,
    private val findEmployeeNumberUseCase: FindEmployeeNumberUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val checkEmailDuplicationUseCase: CheckEmailDuplicationUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val checkAccountByNameAndEmailUseCase: CheckAccountByNameAndEmailUseCase
) {

    @GetMapping("/employee-number")
    fun findEmployeeNumber(@Valid request: FindEmployeeNumberWebRequest): FindEmployeeNumberWebResponse {
        val result = findEmployeeNumberUseCase.execute(
            FindEmployeeNumberRequest(
                name = request.name, spotId = request.spotId, email = request.email
            )
        )

        return FindEmployeeNumberWebResponse(result)
    }

    @PutMapping("/token/reissue")
    fun reissueJsonWebToken(@RequestHeader("Refresh-Token") request: String): TokenResponse {
        return reissueTokenUseCase.execute(request)
    }

    @PutMapping("/password/initialization")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun resetPassword(@Valid @RequestBody request: ResetPasswordWebRequest) {
        resetPasswordUseCase.execute(
            ResetPasswordRequest(
                email = request.email,
                employeeNumber = request.employeeNumber,
                newPassword = request.newPassword
            )
        )
    }

    @GetMapping("/email/duplication")
    fun checkEmailDuplication(@Email @NotBlank @RequestParam email: String) {
        checkEmailDuplicationUseCase.execute(email)
    }
    
    @PutMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changePassword(@Valid @RequestBody request: ChangePasswordWebRequest) {
        changePasswordUseCase.execute(
            ChangePasswordRequest(
                password = request.password,
                newPassword = request.newPassword
            )
        )
    }

    @PutMapping("/account/existence")
    fun checkAccountByNameAndEmail(@Valid request: CheckAccountByNameAndEmailWebRequest) {
        checkAccountByNameAndEmailUseCase.execute(
            CheckAccountByNameAndEmailRequest(
                name = request.name,
                email = request.email
            )
        )
    }

}