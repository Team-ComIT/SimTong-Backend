package team.comit.simtong.domain.schedule.usecase

import team.comit.simtong.domain.schedule.model.value.Scope
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.domain.schedule.spi.vo.SpotSchedule
import team.comit.simtong.global.annotation.ReadOnlyUseCase
import java.time.LocalDate

/**
 *
 * 모든 지점 일정 조회 요청을 담당하는 QueryEntireSpotScheduleUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/11/26
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class QueryEntireSpotScheduleUseCase(
    private val querySchedulePort: QuerySchedulePort
) {

    fun execute(startAt: LocalDate, endAt: LocalDate): List<SpotSchedule> {
        return querySchedulePort.querySpotSchedulesByPeriodAndScope(startAt, endAt, Scope.ENTIRE)
    }
}