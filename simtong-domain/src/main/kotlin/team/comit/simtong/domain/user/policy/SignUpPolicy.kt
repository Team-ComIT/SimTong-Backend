package team.comit.simtong.domain.user.policy

import team.comit.simtong.domain.auth.exception.UncertifiedEmailException
import team.comit.simtong.domain.auth.exception.UsedEmailException
import team.comit.simtong.domain.spot.exception.SpotNotFoundException
import team.comit.simtong.domain.team.exception.TeamNotFoundException
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserQueryAuthCodeLimitPort
import team.comit.simtong.domain.user.spi.UserQuerySpotPort
import team.comit.simtong.domain.user.spi.UserQueryTeamPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.Policy

/**
 *
 * 회원 가입 정책을 관리하는 SignUpPolicy
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/05
 * @version 1.0.0
 **/
@Policy
class SignUpPolicy(
//    private val nickNamePort: NickNamePort,
    private val userQueryTeamPort: UserQueryTeamPort,
    private val userQuerySpotPort: UserQuerySpotPort,
    private val userQueryAuthCodeLimitPort: UserQueryAuthCodeLimitPort,
    private val queryUserPort: QueryUserPort,
    private val userSecurityPort: UserSecurityPort
) {

    fun implement(
        nickname: String?,
        name: String,
        email: String,
        password: String,
        employeeNumber: Int,
        profileImagePath: String?
    ): User {
        if (queryUserPort.existsUserByEmail(email)) {
            throw UsedEmailException.EXCEPTION
        }

        val authCodeLimit = userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(email)

        if (authCodeLimit == null || !authCodeLimit.isVerified) {
            throw UncertifiedEmailException.EXCEPTION
        }

        // TODO 비즈니스 로직 직접 구현
        // 임직원 확인

        val spot = userQuerySpotPort.querySpotByName("test spotName") // 임직원 확인시 지점 이름 가져오기
            ?: throw SpotNotFoundException.EXCEPTION

        val team = userQueryTeamPort.queryTeamByName("test teamName") // 임직원 확인시 팀 이름 가져오기
            ?: throw TeamNotFoundException.EXCEPTION

        return User(
            nickname = nickname ?: "", // nickNamePort.random()
            name = name,
            email = email,
            password = userSecurityPort.encode(password),
            employeeNumber = employeeNumber,
            authority = Authority.ROLE_COMMON,
            spotId = spot.id,
            teamId = team.id,
            profileImagePath = profileImagePath ?: User.defaultImage
        )
    }
}