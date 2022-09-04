package team.comit.simtong.domain.user

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.user.dto.request.WebSignUpRequest
import team.comit.simtong.domain.user.usecase.SignUpUseCase
import team.comit.simtong.domain.user.usecase.dto.DomainSignUpRequest
import javax.validation.Valid

/**
 *
 * User에 관한 요청을 받는 WebUserAdapter
 *
 * @author JoKyungHyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
@RestController
@RequestMapping("/users")
class WebUserAdapter(
    private val signUpUseCase: SignUpUseCase
) {

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(@RequestBody @Valid request: WebSignUpRequest) {

        // TODO Email 인증 확인 로직

        val nickname = "" // TODO 랜덤 닉네임 로직

        signUpUseCase.execute(DomainSignUpRequest(
            name = request.name,
            email = request.email,
            password = request.password,
            nickname = nickname,
            profileImagePath = request.profile_image_path,
            employeeNumber = request.employee_number
        ))


        // TODO Token Response
    }

}