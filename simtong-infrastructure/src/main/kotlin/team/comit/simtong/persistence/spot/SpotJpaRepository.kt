package team.comit.simtong.persistence.spot

import org.springframework.data.repository.CrudRepository
import team.comit.simtong.persistence.spot.entity.SpotJpaEntity
import java.util.*

interface SpotJpaRepository : CrudRepository<SpotJpaEntity, UUID> {
}