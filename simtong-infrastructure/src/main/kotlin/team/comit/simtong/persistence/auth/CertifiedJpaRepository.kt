package team.comit.simtong.persistence.auth

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
  *
  * Spring Repository의 기능을 이용하는 CertifiedJpaRepository
  *
  * @author JoKyungHyeon
  * @date 2022/08/22
  * @version 1.0.0
 **/
@Repository
interface CertifiedJpaRepository : CrudRepository<CertifiedJpaRepository, String> {
}