package team.comit.simtong.domain.user.policy

import team.comit.simtong.domain.user.dto.SignUpRequest
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.CheckEmailPort
import team.comit.simtong.domain.user.spi.NickNamePort
import team.comit.simtong.domain.user.spi.SecurityPort

/**
 *
 * 회원 가입 정책을 관리하는 SignUpPolicy
 *
 * @author Chokyunghyeon
 * @date 2022/09/05
 * @version 1.0.0
 **/
class SignUpPolicy(
    private val checkEmailPort: CheckEmailPort,
    private val securityPort: SecurityPort,
    private val randomNamePort: NickNamePort
) {

    fun implement(request: SignUpRequest): User {

        checkEmailPort.checkCertifiedEmail(request.email)
        checkEmailPort.checkUsedEmail(request.email)

        // TODO name & employeeNumber 확인 로직

        return User(
            id = null,
            name = request.name,
            email = request.email,
            password = securityPort.encode(request.password),
            nickname = request.nickname ?: randomNamePort.random(),
            employeeNumber = request.employeeNumber,
            authority = Authority.ROLE_COMMON,
            adminCode = null,
            profileImagePath = request.profileImagePath ?: User.defaultImage
        )
    }
}