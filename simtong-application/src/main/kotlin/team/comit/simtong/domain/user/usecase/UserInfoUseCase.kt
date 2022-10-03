package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.spot.exception.SpotNotFoundException
import team.comit.simtong.domain.user.dto.UserInfoResponse
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserQuerySpotPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase

/**
 *
 * 사용자 정보 보기를 담당하는 GetInfoUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/09/27
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class UserInfoUseCase(
    private val queryUserPort: QueryUserPort,
    private val userSecurityPort: UserSecurityPort,
    private val userQuerySpotPort: UserQuerySpotPort
) {

    fun execute(): UserInfoResponse {
        val currentUserId = userSecurityPort.getCurrentUserId()

        val user = queryUserPort.queryUserById(currentUserId) ?: throw UserNotFoundException.EXCEPTION
        val spot = userQuerySpotPort.querySpotById(user.spotId) ?: throw SpotNotFoundException.EXCEPTION

        return UserInfoResponse(
            name = user.name,
            email = user.email,
            nickName = user.nickname,
            spot = spot.name,
            profileImagePath = user.profileImagePath
        )
    }
}