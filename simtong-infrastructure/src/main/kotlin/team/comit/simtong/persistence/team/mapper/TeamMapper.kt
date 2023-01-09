package team.comit.simtong.persistence.team.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.team.model.Team
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.spot.SpotJpaRepository
import team.comit.simtong.persistence.team.entity.TeamJpaEntity

/**
 *
 * TeamEntity와 DomainTeam을 변환하는 TeamMapper
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/18
 * @version 1.2.5
 **/
@Component
class TeamMapper(
    private val spotJpaRepository: SpotJpaRepository
) : GenericMapper<TeamJpaEntity, Team> {

    override fun toEntity(model: Team): TeamJpaEntity {
        val spot = spotJpaRepository.findByIdOrNull(model.spotId)!!

        return TeamJpaEntity(
            id = model.id,
            name = model.name,
            spot = spot
        )
    }

    override fun toDomain(entity: TeamJpaEntity?): Team? {
        return entity?.let {
            Team(
                id = it.id!!,
                name = it.name,
                spotId = it.spot.id!!
            )
        }
    }

    override fun toDomainNotNull(entity: TeamJpaEntity): Team {
        return Team(
            id = entity.id!!,
            name = entity.name,
            spotId = entity.spot.id!!
        )
    }
}