package team.comit.simtong.persistence.auth

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.auth.entity.AuthCodePolicyEntity

/**
 *
 * Spring Repository의 기능을 이용하는 AuthPolicyRepository
 *
 * @author Chokyunghyeon
 * @date 2022/08/29
 * @version 1.0.0
 **/
@Repository
interface AuthCodePolicyRepository: CrudRepository<AuthCodePolicyEntity, String> {
}