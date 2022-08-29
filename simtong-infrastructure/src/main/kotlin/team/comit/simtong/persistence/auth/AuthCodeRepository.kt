package team.comit.simtong.persistence.auth

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.auth.entity.AuthCodeEntity

/**
  *
  * Spring Repository의 기능을 이용하는 AuthCodeRepository
  *
  * @author JoKyungHyeon
  * @date 2022/08/22
  * @version 1.0.0
 **/
@Repository
interface AuthCodeRepository : CrudRepository<AuthCodeEntity, String> {
}