package team.comit.simtong.persistence.team

import org.springframework.stereotype.Component
import team.comit.simtong.domain.team.model.Team
import team.comit.simtong.domain.team.spi.TeamPort
import team.comit.simtong.persistence.team.mapper.TeamMapper
import java.util.UUID

/**
 *
 * Team의 영속성을 관리하는 TeamPersistenceAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.2.3
 **/
@Component
class TeamPersistenceAdapter(
    private val teamJpaRepository: TeamJpaRepository,
    private val teamMapper: TeamMapper
) : TeamPort {

    override fun queryTeamsBySpotId(spotId: UUID): List<Team> {
        return teamJpaRepository.queryTeamJpaEntitiesBySpotId(spotId)
            .map(teamMapper::toDomainNotNull)
    }

    override fun queryTeamByName(name: String): Team? {
        return teamJpaRepository.queryTeamJpaEntityByName(name)
            .let(teamMapper::toDomain)
    }

}