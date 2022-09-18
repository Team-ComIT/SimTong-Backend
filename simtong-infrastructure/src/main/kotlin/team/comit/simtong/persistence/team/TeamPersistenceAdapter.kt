package team.comit.simtong.persistence.team

import org.springframework.stereotype.Component
import team.comit.simtong.domain.team.spi.DomainQueryTeamPort
import team.comit.simtong.persistence.team.mapper.TeamMapper
import java.util.*

/**
 *
 * Team의 영속성을 관리하는 TeamPersistenceAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
@Component
class TeamPersistenceAdapter(
    private val teamJpaRepository: TeamJpaRepository,
    private val teamMapper: TeamMapper
): DomainQueryTeamPort {

    override fun queryTeamById(id: UUID) = teamMapper.toDomain(
        teamJpaRepository.queryTeamJpaEntityById(id)
    )

    override fun queryTeamByName(name: String) = teamMapper.toDomain(
        teamJpaRepository.queryTeamJpaEntityByName(name)
    )

}