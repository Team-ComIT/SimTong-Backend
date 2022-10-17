package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.user.dto.CheckMatchedAccountRequest
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase

/**
 *
 * 해당 이름과 이메일을 가진 계정 여부 확인을 담당하는 CheckMatchedAccountUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/10/15
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class CheckMatchedAccountUseCase(
    private val queryUserPort: QueryUserPort
) {

    fun execute(request: CheckMatchedAccountRequest) {

        if (!queryUserPort.existsUserByNameAndEmail(request.name, request.email)) {
            throw UserNotFoundException.EXCEPTION
        }
    }

}