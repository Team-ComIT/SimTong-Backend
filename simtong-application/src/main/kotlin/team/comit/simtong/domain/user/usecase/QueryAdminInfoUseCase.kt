package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.spot.exception.SpotNotFoundException
import team.comit.simtong.domain.user.dto.QueryAdminInfoResponse
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserQuerySpotPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase

/**
 *
 * 관리자 계정의 정보 조회를 담당하는 QueryAdminInfoUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/12/11
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class QueryAdminInfoUseCase(
    private val queryUserPort: QueryUserPort,
    private val querySpotPort: UserQuerySpotPort,
    private val securityPort: UserSecurityPort
) {

    fun execute(): QueryAdminInfoResponse {
        val user = queryUserPort.queryUserById(securityPort.getCurrentUserId())
            ?: throw UserNotFoundException.EXCEPTION

        val spot = querySpotPort.querySpotById(user.spotId)
            ?: throw SpotNotFoundException.EXCEPTION

        return QueryAdminInfoResponse(
            name = user.name,
            email = user.email,
            nickname = user.nickname,
            spot = QueryAdminInfoResponse.SpotResponse(
                id = spot.id,
                name = spot.name
            ),
            profileImagePath = user.profileImagePath
        )
    }
}