package team.comit.simtong.domain.common

import org.hibernate.validator.constraints.Range
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.auth.usecase.ReissueTokenUseCase
import team.comit.simtong.domain.common.dto.request.ChangePasswordWebRequest
import team.comit.simtong.domain.common.dto.request.FindEmployeeNumberWebRequest
import team.comit.simtong.domain.common.dto.request.ResetPasswordWebRequest
import team.comit.simtong.domain.common.dto.response.FindEmployeeNumberWebResponse
import team.comit.simtong.domain.spot.dto.SpotResponse
import team.comit.simtong.domain.spot.usecase.ShowSpotListUseCase
import team.comit.simtong.domain.team.dto.QueryTeamsResponse
import team.comit.simtong.domain.team.usecase.QueryTeamsUseCase
import team.comit.simtong.domain.user.dto.ChangePasswordRequest
import team.comit.simtong.domain.user.dto.CheckMatchedAccountRequest
import team.comit.simtong.domain.user.dto.FindEmployeeNumberRequest
import team.comit.simtong.domain.user.dto.ResetPasswordRequest
import team.comit.simtong.domain.user.usecase.ChangePasswordUseCase
import team.comit.simtong.domain.user.usecase.CheckEmailDuplicationUseCase
import team.comit.simtong.domain.user.usecase.CheckMatchedAccountUseCase
import team.comit.simtong.domain.user.usecase.ComparePasswordUseCase
import team.comit.simtong.domain.user.usecase.FindEmployeeNumberUseCase
import team.comit.simtong.domain.user.usecase.ResetPasswordUseCase
import team.comit.simtong.domain.user.value.EmployeeNumber
import team.comit.simtong.domain.user.value.Password
import java.util.UUID
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

/**
 *
 * User와 Admin에 관한 요청을 받는 WebCommonAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/11
 * @version 1.2.4
 **/
@Validated
@RestController
@RequestMapping("/commons")
class WebCommonAdapter(
    private val reissueTokenUseCase: ReissueTokenUseCase,
    private val findEmployeeNumberUseCase: FindEmployeeNumberUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val checkEmailDuplicationUseCase: CheckEmailDuplicationUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val checkMatchedAccountUseCase: CheckMatchedAccountUseCase,
    private val showSpotListUseCase: ShowSpotListUseCase,
    private val comparePasswordUseCase: ComparePasswordUseCase,
    private val queryTeamsUseCase: QueryTeamsUseCase
) {

    @GetMapping("/employee-number")
    fun findEmployeeNumber(@Valid @ModelAttribute request: FindEmployeeNumberWebRequest): FindEmployeeNumberWebResponse {
        val result = findEmployeeNumberUseCase.execute(
            FindEmployeeNumberRequest(
                name = request.name,
                spotId = request.spotId,
                email = request.email
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
                employeeNumber = request.employeeNumber.value,
                newPassword = request.newPassword.value
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
                password = request.password.value,
                newPassword = request.newPassword.value
            )
        )
    }

    @GetMapping("/account/existence")
    fun checkMatchedAccount(
        @Range(min = EmployeeNumber.MIN_VALUE, max = EmployeeNumber.MAX_VALUE)
        @RequestParam employeeNumber: EmployeeNumber,
        @NotBlank @Email
        @RequestParam email: String
    ) {
        checkMatchedAccountUseCase.execute(
            CheckMatchedAccountRequest(
                employeeNumber = employeeNumber.value,
                email = email
            )
        )
    }

    @GetMapping("/spot")
    fun showSpotList(): SpotResponse {
        return showSpotListUseCase.execute()
    }

    @GetMapping("/password/compare")
    fun comparePassword(@NotBlank @RequestParam password: Password) {
        comparePasswordUseCase.execute(password.value)
    }

    @GetMapping("/team/{spot-id}")
    fun queryTeams(@PathVariable(name = "spot-id") spotId: UUID): QueryTeamsResponse {
        return queryTeamsUseCase.execute(spotId)
    }

}