package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.spot.exception.SpotExceptions
import team.comit.simtong.domain.user.dto.response.QueryUserInfoResponse
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserQuerySpotPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase

/**
 *
 * 유저 계정의 정보 조회를 담당하는 QueryUserInfoUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/09/27
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class QueryUserInfoUseCase(
    private val queryUserPort: QueryUserPort,
    private val securityPort: UserSecurityPort,
    private val querySpotPort: UserQuerySpotPort
) {

    fun execute(): QueryUserInfoResponse {
        val currentUserId = securityPort.getCurrentUserId()

        val user = queryUserPort.queryUserById(currentUserId) ?: throw UserExceptions.NotFound()
        val spot = querySpotPort.querySpotById(user.spotId) ?: throw SpotExceptions.NotFound()

        return QueryUserInfoResponse(
            name = user.name,
            email = user.email,
            nickname = user.nickname,
            spot = spot.name,
            profileImagePath = user.profileImagePath
        )
    }
}