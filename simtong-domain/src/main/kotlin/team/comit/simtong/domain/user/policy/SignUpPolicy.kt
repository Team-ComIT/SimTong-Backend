package team.comit.simtong.domain.user.policy

import team.comit.simtong.domain.auth.exception.UncertifiedEmailException
import team.comit.simtong.domain.user.dto.DomainSignUpRequest
import team.comit.simtong.domain.auth.exception.UsedEmailException
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.auth.spi.CheckAuthCodePolicyPort
import team.comit.simtong.domain.auth.spi.CheckUserPort
import team.comit.simtong.domain.user.spi.SecurityPort
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
    private val checkAuthCodePolicyPort: CheckAuthCodePolicyPort,
    private val checkUserPort: CheckUserPort,
    private val securityPort: SecurityPort
) {

    fun implement(request: DomainSignUpRequest): User {

        if(checkAuthCodePolicyPort.checkCertifiedEmail(request.email)) {
            throw UncertifiedEmailException.EXCEPTION
        }

        if(checkUserPort.existsUserByEmail(request.email)) {
            throw UsedEmailException.EXCEPTION
        }

        // TODO 비즈니스 로직 직접 구현
        // 임직원 확인

        return User(
            name = request.name,
            email = request.email,
            password = securityPort.encode(request.password),
            nickname = request.nickname ?: "", // nickNamePort.random()
            employeeNumber = request.employeeNumber,
            authority = Authority.ROLE_COMMON,
            profileImagePath = request.profileImagePath ?: User.defaultImage
        )
    }
}