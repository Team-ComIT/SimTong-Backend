package team.comit.simtong.persistence.holiday.repository

import org.springframework.data.repository.CrudRepository
import team.comit.simtong.domain.holiday.model.HolidayPeriod
import team.comit.simtong.persistence.holiday.entity.HolidayPeriodId

/**
 *
 * Spring Jpa 기능을 사용해 휴무일 작성 기간의 데이터를 가져오는 HolidayPeriodJpaRepository
 *
 * @author Chokyunghyeon
 * @date 2022/12/20
 * @version 1.0.0
 **/
interface HolidayPeriodJpaRepository : CrudRepository<HolidayPeriod, HolidayPeriodId> {
}