package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase

/**
 *
 * 닉네임 중복 여부를 확인하는 CheckNicknameDuplicationUseCase
 *
 * @author kimbeomjin
 * @date 2022/10/15
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class CheckNicknameDuplicationUseCase(
    private val queryUserPort: QueryUserPort
) {

    fun execute(nickname: String) {
        if (queryUserPort.existsUserByNickname(nickname)) {
            throw UserExceptions.AlreadyUsedNickname()
        }
    }

}