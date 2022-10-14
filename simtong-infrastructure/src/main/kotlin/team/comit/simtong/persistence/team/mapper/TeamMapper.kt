package team.comit.simtong.persistence.team.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.springframework.beans.factory.annotation.Autowired
import team.comit.simtong.domain.team.model.Team
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.spot.SpotJpaRepository
import team.comit.simtong.persistence.team.entity.TeamJpaEntity

/**
 *
 * TeamEntity와 DomainTeam을 변환하는 TeamMapper
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
@Mapper
abstract class TeamMapper : GenericMapper<TeamJpaEntity, Team> {

    @Autowired
    protected lateinit var spotJpaRepository: SpotJpaRepository

    @Mapping(target = "spot", expression = "java(spotJpaRepository.findById(model.getSpotId()).orElse(null))")
    abstract override fun toEntity(model: Team): TeamJpaEntity

    @Mapping(target = "spotId", expression = "java(entity.getSpot().getId())")
    abstract override fun toDomain(entity: TeamJpaEntity?): Team?

}