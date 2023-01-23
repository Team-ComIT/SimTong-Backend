package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.user.dto.response.FindEmployeeNumberResponse
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.global.annotation.UseCase
import java.util.UUID

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

    fun execute(name: String, spotId: UUID, email: String): FindEmployeeNumberResponse {
        val user = queryUserPort.queryUserByNameAndSpotAndEmail(name, spotId, email)
            ?: throw UserExceptions.NotFound()

        return user.employeeNumber.value
            .let(::FindEmployeeNumberResponse)
    }
}