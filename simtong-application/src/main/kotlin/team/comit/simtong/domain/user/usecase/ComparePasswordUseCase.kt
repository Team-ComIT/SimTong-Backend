package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.user.exception.DifferentPasswordException
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase

/**
 *
 * 비밀번호 일치 여부 요청을 담당하는 MatchPasswordUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/12/05
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class ComparePasswordUseCase(
    private val queryUserPort: QueryUserPort,
    private val securityPort: UserSecurityPort
) {

    fun execute(password: String) {
        val user = queryUserPort.queryUserById(securityPort.getCurrentUserId())
            ?: throw UserNotFoundException.EXCEPTION

        if (!securityPort.compare(password, user.password)) {
            throw DifferentPasswordException.EXCEPTION
        }
    }

}