package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.user.dto.FindEmployeeNumberRequest
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 사원 번호 찾기 기능을 담당하는 FindEmployeeNumberUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/09/11
 * @version 1.2.5
 **/
@UseCase
class FindEmployeeNumberUseCase(
    private val queryUserPort: QueryUserPort
) {

    fun execute(request: FindEmployeeNumberRequest): Int {
        val user = queryUserPort.queryUserByNameAndSpotAndEmail(request.name, request.spotId, request.email)
            ?: throw UserExceptions.NotFound()

        return user.employeeNumber.value
    }
}