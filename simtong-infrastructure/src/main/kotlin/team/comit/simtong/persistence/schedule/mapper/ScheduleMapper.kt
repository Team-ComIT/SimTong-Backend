package team.comit.simtong.persistence.schedule.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.springframework.beans.factory.annotation.Autowired
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.schedule.entity.ScheduleJpaEntity
import team.comit.simtong.persistence.spot.SpotJpaRepository
import team.comit.simtong.persistence.user.repository.UserJpaRepository

/**
 *
 * Schedule Entity와 Schedule Aggregate를 변환하는 ScheduleMapper
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.2.3
 **/
@Mapper
abstract class ScheduleMapper : GenericMapper<ScheduleJpaEntity, Schedule> {

    @Autowired
    protected lateinit var userJpaRepository: UserJpaRepository

    @Autowired
    protected lateinit var spotJpaRepository: SpotJpaRepository

    @Mappings(
        Mapping(target = "userId", expression = "java(entity.getUser().getId())"),
        Mapping(target = "spotId", expression = "java(entity.getSpot().getId())")
    )
    abstract override fun toDomain(entity: ScheduleJpaEntity?): Schedule?

    @Mappings(
        Mapping(target = "userId", expression = "java(entity.getUser().getId())"),
        Mapping(target = "spotId", expression = "java(entity.getSpot().getId())")
    )
    abstract override fun toDomainNotNull(entity: ScheduleJpaEntity): Schedule

    @Mappings(
        Mapping(target = "user", expression = "java(userJpaRepository.findById(model.getUserId()).orElse(null))"),
        Mapping(target= "spot", expression = "java(spotJpaRepository.findById(model.getSpotId()).orElse(null))")
    )
    abstract override fun toEntity(model: Schedule): ScheduleJpaEntity
}