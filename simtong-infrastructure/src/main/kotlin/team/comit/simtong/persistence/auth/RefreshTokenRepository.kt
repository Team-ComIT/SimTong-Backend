package team.comit.simtong.persistence.auth

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.auth.entity.RefreshTokenEntity

/**
 *
 * Spring Repository의 기능을 이용하는 RefreshTokenRepository
 *
 * @author Chokyunghyeon
 * @date 2022/09/01
 * @version 1.0.0
 **/
@Repository
interface RefreshTokenRepository : CrudRepository<RefreshTokenEntity, String> {
}