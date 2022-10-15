package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.auth.exception.UsedEmailException
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase

/**
 *
 * 중복된 이메일인지 검사를 담당하는 CheckEmailDuplicationUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/10/14
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class CheckEmailDuplicationUseCase(
    private val queryUserPort: QueryUserPort
) {

    fun execute(email: String) {
        if (queryUserPort.existsUserByEmail(email)) {
            throw UsedEmailException.EXCEPTION
        }
    }

}