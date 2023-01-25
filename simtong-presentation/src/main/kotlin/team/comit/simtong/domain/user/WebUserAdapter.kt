package team.comit.simtong.domain.user

import org.hibernate.validator.constraints.Range
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.auth.dto.response.TokenResponse
import team.comit.simtong.domain.file.usecase.CheckEmployeeUseCase
import team.comit.simtong.domain.user.dto.ChangeEmailRequest
import team.comit.simtong.domain.user.dto.ChangeNicknameRequest
import team.comit.simtong.domain.user.dto.ChangeProfileImageRequest
import team.comit.simtong.domain.user.dto.ChangeSpotRequest
import team.comit.simtong.domain.user.dto.SignUpRequest
import team.comit.simtong.domain.user.dto.UserSignInRequest
import team.comit.simtong.domain.user.dto.response.QueryUserInfoResponse
import team.comit.simtong.domain.user.model.EmployeeNumber
import team.comit.simtong.domain.user.model.NickName
import team.comit.simtong.domain.user.usecase.ChangeEmailUseCase
import team.comit.simtong.domain.user.usecase.ChangeNicknameUseCase
import team.comit.simtong.domain.user.usecase.ChangeProfileImageUseCase
import team.comit.simtong.domain.user.usecase.ChangeSpotUseCase
import team.comit.simtong.domain.user.usecase.CheckNicknameDuplicationUseCase
import team.comit.simtong.domain.user.usecase.QueryUserInfoUseCase
import team.comit.simtong.domain.user.usecase.SignInUseCase
import team.comit.simtong.domain.user.usecase.SignUpUseCase
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

/**
 *
 * User에 관한 요청을 받는 WebUserAdapter
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/04
 * @version 1.2.5
 **/
@Validated
@RestController
@RequestMapping("/users")
class WebUserAdapter(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val queryUserInfoUseCase: QueryUserInfoUseCase,
    private val changeEmailUseCase: ChangeEmailUseCase,
    private val changeNicknameUseCase: ChangeNicknameUseCase,
    private val changeProfileImageUseCase: ChangeProfileImageUseCase,
    private val checkNicknameDuplicationUseCase: CheckNicknameDuplicationUseCase,
    private val changeSpotUseCase: ChangeSpotUseCase,
    private val checkEmployeeUseCase: CheckEmployeeUseCase
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun signUp(@Valid @RequestBody request: SignUpRequest): TokenResponse {
        return signUpUseCase.execute(request.toData())
    }

    @PostMapping("/tokens")
    fun signIn(@Valid @RequestBody request: UserSignInRequest): TokenResponse {
        return signInUseCase.execute(request.toData())
    }

    @GetMapping("/information")
    fun getUserInfo(): QueryUserInfoResponse {
        return queryUserInfoUseCase.execute()
    }

    @PutMapping("/nickname")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changeNickname(@Valid @RequestBody request: ChangeNicknameRequest) {
        changeNicknameUseCase.execute(request.toData())
    }

    @PutMapping("/email")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changeEmail(@Valid @RequestBody request: ChangeEmailRequest) {
        changeEmailUseCase.execute(request.toData())
    }

    @PutMapping("/profile-image")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changeProfileImage(@Valid @RequestBody request: ChangeProfileImageRequest) {
        changeProfileImageUseCase.execute(request.toData())
    }

    @PutMapping("/spot")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changeSpot(@Valid @RequestBody request: ChangeSpotRequest) {
        changeSpotUseCase.execute(request.toData())
    }

    @GetMapping("/nickname/duplication")
    fun checkNicknameDuplication(
        @NotNull @Pattern(regexp = NickName.PATTERN)
        @RequestParam nickname: String?
    ) {
        checkNicknameDuplicationUseCase.execute(nickname!!)
    }

    @GetMapping("/verification-employee")
    fun checkEmployee(
        @NotBlank @RequestParam name: String?,
        @NotNull @Range(min = EmployeeNumber.MIN_VALUE, max = EmployeeNumber.MAX_VALUE)
        @RequestParam("employee_number") employeeNumber: Int?
    ) {
        checkEmployeeUseCase.execute(
            name = name!!,
            employeeNumber = employeeNumber!!
        )
    }

}