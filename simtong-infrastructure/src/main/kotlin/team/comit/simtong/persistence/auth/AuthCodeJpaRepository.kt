package team.comit.simtong.persistence.auth

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.auth.entity.AuthCodeJpaEntity

/**
  *
  * Spring Repository의 기능을 이용하는 AuthCodeJpaRepository
  *
  * @author JoKyungHyeon
  * @date 2022/08/22
 **/
@Repository
interface AuthCodeJpaRepository : CrudRepository<AuthCodeJpaEntity, String> {
}