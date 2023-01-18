package team.comit.simtong.domain.common

import org.hibernate.validator.constraints.Range
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import team.comit.simtong.domain.auth.dto.response.TokenResponse
import team.comit.simtong.domain.auth.usecase.ReissueTokenUseCase
import team.comit.simtong.domain.common.dto.ChangePasswordRequest
import team.comit.simtong.domain.common.dto.FindEmployeeNumberRequest
import team.comit.simtong.domain.common.dto.ResetPasswordRequest
import team.comit.simtong.domain.spot.dto.SpotResponse
import team.comit.simtong.domain.spot.usecase.ShowSpotListUseCase
import team.comit.simtong.domain.team.dto.QueryTeamsResponse
import team.comit.simtong.domain.team.usecase.QueryTeamsUseCase
import team.comit.simtong.domain.user.dto.response.FindEmployeeNumberResponse
import team.comit.simtong.domain.user.model.EmployeeNumber
import team.comit.simtong.domain.user.usecase.ChangePasswordUseCase
import team.comit.simtong.domain.user.usecase.CheckEmailDuplicationUseCase
import team.comit.simtong.domain.user.usecase.CheckMatchedAccountUseCase
import team.comit.simtong.domain.user.usecase.ComparePasswordUseCase
import team.comit.simtong.domain.user.usecase.FindEmployeeNumberUseCase
import team.comit.simtong.domain.user.usecase.ResetPasswordUseCase
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
 * @version 1.2.5
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
    fun findEmployeeNumber(@Valid @ModelAttribute request: FindEmployeeNumberRequest): FindEmployeeNumberResponse {
        return findEmployeeNumberUseCase.execute(request.toData())
    }

    @PutMapping("/token/reissue")
    fun reissueJsonWebToken(@RequestHeader("Refresh-Token") request: String): TokenResponse {
        return reissueTokenUseCase.execute(request)
    }

    @PutMapping("/password/initialization")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun resetPassword(@Valid @RequestBody request: ResetPasswordRequest) {
        resetPasswordUseCase.execute(request.toData())
    }

    @GetMapping("/email/duplication")
    fun checkEmailDuplication(@Email @NotBlank @RequestParam email: String) {
        checkEmailDuplicationUseCase.execute(email)
    }

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changePassword(@Valid @RequestBody request: ChangePasswordRequest) {
        changePasswordUseCase.execute(request.toData())
    }

    @GetMapping("/account/existence")
    fun checkMatchedAccount(
        @Range(min = EmployeeNumber.MIN_VALUE, max = EmployeeNumber.MAX_VALUE)
        @RequestParam employeeNumber: Int,
        @NotBlank @Email
        @RequestParam email: String
    ) {
        checkMatchedAccountUseCase.execute(employeeNumber, email)
    }

    @GetMapping("/spot")
    fun showSpotList(): SpotResponse {
        return showSpotListUseCase.execute()
    }

    @GetMapping("/password/compare")
    fun comparePassword(@NotBlank @RequestParam password: String) {
        comparePasswordUseCase.execute(password)
    }

    @GetMapping("/team/{spot-id}")
    fun queryTeams(@PathVariable(name = "spot-id") spotId: UUID): QueryTeamsResponse {
        return queryTeamsUseCase.execute(spotId)
    }

}