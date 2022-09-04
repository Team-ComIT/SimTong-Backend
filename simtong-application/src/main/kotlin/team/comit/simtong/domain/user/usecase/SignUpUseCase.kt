package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.user.exception.UsedEmailException
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.SaveUserPort
import team.comit.simtong.domain.user.usecase.dto.DomainSignUpRequest
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 사용자의 회원가입 기능을 담당하는 SignUpUseCase
 *
 * @author JoKyungHyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
@UseCase
class SignUpUseCase(
    private val queryUserPort: QueryUserPort,
    private val saveUserPort: SaveUserPort
) {

    fun execute(request: DomainSignUpRequest) {

        if(queryUserPort.existsUserByEmail(request.email)) {
            throw UsedEmailException.EXCEPTION
        }

        saveUserPort.saveUser(
            User.signUp(
                name = request.name,
                email = request.email,
                password = request.password,
                nickname = request.nickname,
                employeeNumber = request.employeeNumber,
                profileImagePath = request.profileImagePath
            )
        )
    }

}