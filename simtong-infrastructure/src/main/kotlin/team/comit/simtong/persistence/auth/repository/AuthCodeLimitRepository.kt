package team.comit.simtong.persistence.auth.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.auth.entity.AuthCodeLimitEntity

/**
 *
 * Spring Repository의 기능을 이용하는 AuthCodeLimitRepository
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/08/29
 * @version 1.0.0
 **/
@Repository
interface AuthCodeLimitRepository : CrudRepository<AuthCodeLimitEntity, String> {
}