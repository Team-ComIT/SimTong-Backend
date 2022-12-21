package team.comit.simtong.persistence.holiday.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.springframework.beans.factory.annotation.Autowired
import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.holiday.entity.HolidayJpaEntity
import team.comit.simtong.persistence.spot.SpotJpaRepository
import team.comit.simtong.persistence.user.repository.UserJpaRepository

/**
 *
 * 휴무일 엔티티와 도메인 휴무일 변환을 담당하는 HolidayMapper
 *
 * @author Chokyunghyeon
 * @date 2022/12/02
 * @version 1.0.0
 **/
@Mapper
abstract class HolidayMapper : GenericMapper<HolidayJpaEntity, Holiday> {

    @Autowired
    protected lateinit var userJpaRepository: UserJpaRepository

    @Autowired
    protected lateinit var spotJpaRepository: SpotJpaRepository

    @Mappings(
        Mapping(target = "userId", expression = "java(entity.getId().getUserId())"),
        Mapping(target = "spotId", expression = "java(entity.getSpot().getId())"),
        Mapping(target = "date", expression = "java(entity.getId().getDate())")
    )
    abstract override fun toDomain(entity: HolidayJpaEntity?): Holiday?

    @Mappings(
        Mapping(target = "user", expression = "java(userJpaRepository.findById(model.getUserId()).orElse(null))"),
        Mapping(target = "spot", expression = "java(spotJpaRepository.findById(model.getSpotId()).orElse(null))"),
        Mapping(target = "id", expression = "java(new HolidayJpaEntity.Id(model.getDate(), model.getUserId()))")
    )
    abstract override fun toEntity(model: Holiday): HolidayJpaEntity
}