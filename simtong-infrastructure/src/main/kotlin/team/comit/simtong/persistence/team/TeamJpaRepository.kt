package team.comit.simtong.persistence.team

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.team.entity.TeamJpaEntity
import java.util.*

/**
 *
 * Spring Repository의 기능을 이용하는 TeamJpaRepository
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/08/21
 * @version 1.0.0
 **/
@Repository
interface TeamJpaRepository : CrudRepository<TeamJpaEntity, UUID> {

    fun queryTeamJpaEntityByName(name: String): TeamJpaEntity?

    fun queryTeamJpaEntitiesBySpotId(spotId: UUID): List<TeamJpaEntity>

}