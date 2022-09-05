package team.comit.simtong.domain.user

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.user.dto.request.WebSignUpRequest
import team.comit.simtong.domain.user.usecase.SignUpUseCase
import team.comit.simtong.domain.user.dto.SignUpRequest
import javax.validation.Valid

/**
 *
 * User에 관한 요청을 받는 WebUserAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
@RestController
@RequestMapping("/users")
class WebUserAdapter(
    private val signUpUseCase: SignUpUseCase
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun signUp(@Valid @RequestBody request: WebSignUpRequest) {
        signUpUseCase.execute(
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

}