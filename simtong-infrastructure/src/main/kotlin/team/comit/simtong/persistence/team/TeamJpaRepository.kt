package team.comit.simtong.persistence.team

import org.springframework.data.repository.CrudRepository
import team.comit.simtong.persistence.team.entity.TeamJpaEntity
import java.util.*

interface TeamJpaRepository : CrudRepository<TeamJpaEntity, UUID> {
}