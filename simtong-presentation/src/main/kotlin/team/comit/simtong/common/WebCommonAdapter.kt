package team.comit.simtong.common

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import team.comit.simtong.common.dto.request.WebCheckEmailDuplicationRequest
import team.comit.simtong.common.dto.request.WebFindEmployeeNumberRequest
import team.comit.simtong.common.dto.response.WebFindEmployeeNumberResponse
import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.auth.usecase.ReissueTokenUseCase
import team.comit.simtong.domain.user.dto.CheckEmailDuplicationRequest
import team.comit.simtong.domain.user.dto.FindEmployeeNumberRequest
import team.comit.simtong.domain.user.dto.ResetPasswordRequest
import team.comit.simtong.domain.user.usecase.CheckEmailDuplicationUseCase
import team.comit.simtong.domain.user.usecase.FindEmployeeNumberUseCase
import team.comit.simtong.domain.user.usecase.ResetPasswordUseCase
import team.comit.simtong.user.dto.request.WebResetPasswordRequest
import javax.validation.Valid

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
    private val checkEmailDuplicationUseCase: CheckEmailDuplicationUseCase
) {

    @GetMapping("/employee-number")
    fun findEmployeeNumber(@Valid request: WebFindEmployeeNumberRequest): WebFindEmployeeNumberResponse {
        val result = findEmployeeNumberUseCase.execute(
            FindEmployeeNumberRequest(
                name = request.name, spotId = request.spotId, email = request.email
            )
        )

        return WebFindEmployeeNumberResponse(result)
    }

    @PutMapping("/token/reissue")
    fun reissueJsonWebToken(@RequestHeader("Refresh-Token") request: String): TokenResponse {
        return reissueTokenUseCase.execute(request)
    }

    @PutMapping("/password")
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

    @GetMapping("/email/overlap")
    fun checkEmailOverlap(@Valid request: WebCheckEmailDuplicationRequest) {
        checkEmailDuplicationUseCase.execute(CheckEmailDuplicationRequest(
            email = request.email
        ))
    }

}