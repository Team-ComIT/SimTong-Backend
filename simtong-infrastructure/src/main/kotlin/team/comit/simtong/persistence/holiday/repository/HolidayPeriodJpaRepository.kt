package team.comit.simtong.persistence.holiday.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.holiday.entity.HolidayPeriodJpaEntity

/**
 *
 * Spring Jpa 기능을 사용해 휴무일 작성 기간의 데이터를 가져오는 HolidayPeriodJpaRepository
 *
 * @author Chokyunghyeon
 * @date 2022/12/20
 * @version 1.0.0
 **/
@Repository
interface HolidayPeriodJpaRepository : CrudRepository<HolidayPeriodJpaEntity, HolidayPeriodJpaEntity.Id> {
}