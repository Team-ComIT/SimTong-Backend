package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase

/**
 *
 * 비밀번호 일치 여부 요청을 담당하는 MatchPasswordUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/12/05
 * @version 1.2.5
 **/
@ReadOnlyUseCase
class ComparePasswordUseCase(
    private val queryUserPort: QueryUserPort,
    private val securityPort: UserSecurityPort
) {

    fun execute(password: String) {
        val user = queryUserPort.queryUserById(securityPort.getCurrentUserId())
            ?: throw UserExceptions.NotFound()

        if (!securityPort.compare(password, user.password.value)) {
            throw UserExceptions.DifferentPassword()
        }
    }
}