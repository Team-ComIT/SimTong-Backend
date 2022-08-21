package team.comit.simtong.persistence.spot

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.spot.entity.SpotJpaEntity
import java.util.*

/**
  *
  * Spring Repository의 기능을 이용하는 SpotJpaRepository
  *
  * @author kimbeomjin
  * @date 2022/08/21
 **/
@Repository
interface SpotJpaRepository : CrudRepository<SpotJpaEntity, UUID> {
}