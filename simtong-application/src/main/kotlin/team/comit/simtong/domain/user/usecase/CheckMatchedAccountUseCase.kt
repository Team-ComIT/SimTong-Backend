package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase

/**
 *
 * 해당 사원번호와 이메일을 가진 계정 여부 확인을 담당하는 CheckMatchedAccountUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/10/15
 * @version 1.2.5
 **/
@ReadOnlyUseCase
class CheckMatchedAccountUseCase(
    private val queryUserPort: QueryUserPort
) {

    fun execute(employeeNumber: Int, email: String) {

        if (!queryUserPort.existsUserByEmployeeNumberAndEmail(employeeNumber, email)) {
            throw UserExceptions.NotFound()
        }
    }
}