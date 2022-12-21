package team.comit.simtong.persistence.holiday.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.springframework.beans.factory.annotation.Autowired
import team.comit.simtong.domain.holiday.model.HolidayPeriod
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.holiday.entity.HolidayPeriodJpaEntity
import team.comit.simtong.persistence.spot.SpotJpaRepository

/**
 *
 * 휴무일 작성 기간 모델와 도메인 휴무일 작성 기간을 변환하는 HolidayPeriodMapper
 *
 * @author Chokyunghyeon
 * @date 2022/12/20
 * @version 1.0.0
 **/
@Mapper
abstract class HolidayPeriodMapper : GenericMapper<HolidayPeriodJpaEntity, HolidayPeriod> {

    @Autowired
    protected lateinit var spotJpaRepository: SpotJpaRepository

    @Mappings(
        Mapping(target = "spotId", expression = "java(entity.getId().getSpotId())"),
        Mapping(target = "year", expression = "java(entity.getId().getYear())"),
        Mapping(target = "month", expression = "java(entity.getId().getMonth())")
    )
    abstract override fun toDomain(entity: HolidayPeriodJpaEntity?): HolidayPeriod?

    @Mappings(
        Mapping(target = "id", expression = "java(new HolidayPeriodJpaEntity.Id(model.getYear(), model.getMonth(), model.getSpotId()))"),
        Mapping(target = "spot", expression = "java(spotJpaRepository.findById(model.getSpotId()).orElse(null))")
    )
    abstract override fun toEntity(model: HolidayPeriod): HolidayPeriodJpaEntity
}