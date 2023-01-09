package team.comit.simtong.persistence.holiday.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.holiday.model.HolidayPeriod
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.holiday.entity.HolidayPeriodJpaEntity
import team.comit.simtong.persistence.spot.SpotJpaRepository

/**
 *
 * 휴무일 작성 기간 모델와 도메인 휴무일 작성 기간을 변환하는 HolidayPeriodMapper
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/12/20
 * @version 1.2.5
 **/
@Component
class HolidayPeriodMapper(
    private val spotJpaRepository: SpotJpaRepository
) : GenericMapper<HolidayPeriodJpaEntity, HolidayPeriod> {

    override fun toEntity(model: HolidayPeriod): HolidayPeriodJpaEntity {
        val spot = spotJpaRepository.findByIdOrNull(model.spotId)!!

        return HolidayPeriodJpaEntity(
            id = HolidayPeriodJpaEntity.Id(
                year = model.year,
                month = model.month,
                spotId = model.spotId
            ),
            spot = spot,
            startAt = model.startAt,
            endAt = model.endAt
        )
    }

    override fun toDomain(entity: HolidayPeriodJpaEntity?): HolidayPeriod? {
        return entity?.let {
            HolidayPeriod(
                year = it.id.year,
                month = it.id.month,
                spotId = it.spot.id!!,
                startAt = it.startAt,
                endAt = it.endAt
            )
        }
    }

    override fun toDomainNotNull(entity: HolidayPeriodJpaEntity): HolidayPeriod {
        return HolidayPeriod(
            year = entity.id.year,
            month = entity.id.month,
            spotId = entity.spot.id!!,
            startAt = entity.startAt,
            endAt = entity.endAt
        )
    }
}