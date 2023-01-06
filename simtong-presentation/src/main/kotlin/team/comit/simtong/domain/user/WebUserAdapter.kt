package team.comit.simtong.domain.user

import org.hibernate.validator.constraints.Length
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
import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.file.usecase.CheckEmployeeUseCase
import team.comit.simtong.domain.user.dto.ChangeEmailRequest
import team.comit.simtong.domain.user.dto.ChangeNicknameRequest
import team.comit.simtong.domain.user.dto.ChangeProfileImageRequest
import team.comit.simtong.domain.user.dto.QueryUserInfoResponse
import team.comit.simtong.domain.user.dto.SignUpRequest
import team.comit.simtong.domain.user.dto.UserSignInRequest
import team.comit.simtong.domain.user.dto.request.ChangeEmailWebRequest
import team.comit.simtong.domain.user.dto.request.ChangeNicknameWebRequest
import team.comit.simtong.domain.user.dto.request.ChangeProfileImageWebRequest
import team.comit.simtong.domain.user.dto.request.ChangeSpotWebRequest
import team.comit.simtong.domain.user.dto.request.SignInWebRequest
import team.comit.simtong.domain.user.dto.request.SignUpWebRequest
import team.comit.simtong.domain.user.usecase.ChangeEmailUseCase
import team.comit.simtong.domain.user.usecase.ChangeNicknameUseCase
import team.comit.simtong.domain.user.usecase.ChangeProfileImageUseCase
import team.comit.simtong.domain.user.usecase.ChangeSpotUseCase
import team.comit.simtong.domain.user.usecase.CheckNicknameDuplicationUseCase
import team.comit.simtong.domain.user.usecase.QueryUserInfoUseCase
import team.comit.simtong.domain.user.usecase.SignInUseCase
import team.comit.simtong.domain.user.usecase.SignUpUseCase
import team.comit.simtong.domain.user.value.EmployeeNumber
import team.comit.simtong.domain.user.value.NickName
import javax.validation.Valid
import javax.validation.constraints.Pattern

/**
 *
 * User에 관한 요청을 받는 WebUserAdapter
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/04
 * @version 1.2.3
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
    fun signUp(@Valid @RequestBody request: SignUpWebRequest): TokenResponse {
        return signUpUseCase.execute(
            SignUpRequest(
                name = request.name,
                email = request.email,
                password = request.password.value,
                nickname = request.nickname.value,
                profileImagePath = request.profileImagePath,
                employeeNumber = request.employeeNumber.value,
                deviceToken = request.deviceToken
            )
        )
    }

    @PostMapping("/tokens")
    fun signIn(@Valid @RequestBody request: SignInWebRequest): TokenResponse {
        return signInUseCase.execute(
            UserSignInRequest(
                employeeNumber = request.employeeNumber.value,
                password = request.password.value,
                deviceToken = request.deviceToken
            )
        )
    }

    @GetMapping("/information")
    fun getUserInfo(): QueryUserInfoResponse {
        return queryUserInfoUseCase.execute()
    }

    @PutMapping("/nickname")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changeNickname(@Valid @RequestBody request: ChangeNicknameWebRequest) {
        changeNicknameUseCase.execute(
            ChangeNicknameRequest(request.nickname.value)
        )
    }

    @PutMapping("/email")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changeEmail(@Valid @RequestBody request: ChangeEmailWebRequest) {
        changeEmailUseCase.execute(
            ChangeEmailRequest(request.email)
        )
    }

    @PutMapping("/profile-image")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changeProfileImage(@Valid @RequestBody request: ChangeProfileImageWebRequest) {
        changeProfileImageUseCase.execute(
            ChangeProfileImageRequest(request.profileImagePath)
        )
    }

    @PutMapping("/spot")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changeSpot(@Valid @RequestBody request: ChangeSpotWebRequest) {
        changeSpotUseCase.execute(request.spotId)
    }

    @GetMapping("/nickname/duplication")
    fun checkNicknameDuplication(
        @Length(min = NickName.MIN_LENGTH, max = NickName.MAX_LENGTH)
        @Pattern(regexp = NickName.PATTERN)
        @RequestParam nickname: NickName
    ) {
        checkNicknameDuplicationUseCase.execute(nickname.value)
    }

    @GetMapping("/verification-employee")
    fun checkEmployee(
        @RequestParam name: String,
        @Range(min = EmployeeNumber.MIN_VALUE, max = EmployeeNumber.MAX_VALUE)
        @RequestParam("employee_number") employeeNumber: EmployeeNumber
    ) {
        checkEmployeeUseCase.execute(name, employeeNumber.value)
    }

}