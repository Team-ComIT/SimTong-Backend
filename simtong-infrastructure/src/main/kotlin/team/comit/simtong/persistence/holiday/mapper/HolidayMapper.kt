package team.comit.simtong.persistence.holiday.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
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
 * @author kimbeomjin
 * @date 2022/12/02
 * @version 1.2.5
 **/
@Component
class HolidayMapper(
    private val userJpaRepository: UserJpaRepository,
    private val spotJpaRepository: SpotJpaRepository
) : GenericMapper<HolidayJpaEntity, Holiday> {

    override fun toEntity(model: Holiday): HolidayJpaEntity {
        val user = userJpaRepository.findByIdOrNull(model.userId)!!
        val spot = spotJpaRepository.findByIdOrNull(model.spotId)!!

        return HolidayJpaEntity(
            id = HolidayJpaEntity.Id(
                userId = model.userId,
                date = model.date
            ),
            type = model.type,
            user = user,
            spot = spot,
            status = model.status
        )
    }

    override fun toDomain(entity: HolidayJpaEntity?): Holiday? {
        return entity?.let {
            val id = it.id
            Holiday(
                date = id.date,
                userId = id.userId,
                type = it.type,
                spotId = it.spot.id!!,
                status = it.status
            )
        }
    }

    override fun toDomainNotNull(entity: HolidayJpaEntity): Holiday {
        val id = entity.id
        return Holiday(
            date = id.date,
            userId = id.userId,
            type = entity.type,
            spotId = entity.spot.id!!,
            status = entity.status
        )
    }
}