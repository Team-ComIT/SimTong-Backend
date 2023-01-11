package team.comit.simtong.domain.holiday.dto.response

import team.comit.simtong.domain.holiday.model.value.HolidayType
import java.time.LocalDate
import java.util.UUID

/**
 *
 * 지점 직원의 휴무일을 반환하는 QueryEmployeeHolidayResponse
 *
 * @author kimbeomjin
 * @date 2022/12/22
 * @version 1.0.0
 **/
data class QueryEmployeeHolidayResponse(
    val holidays: List<Holiday>
) {

    /**
     *
     * 지점 직원의 휴무일 정보를 반환하는 Holiday
     *
     * @author kimbeomjin
     * @date 2022/12/22
     * @version 1.0.0
     **/
    data class Holiday(
        val date: LocalDate,
        val type: HolidayType,
        val user: Employee
    ) {

        /**
         *
         * 지점 직원 정보를 반환하는 Employee
         *
         * @author kimbeomjin
         * @date 2022/12/22
         * @version 1.0.0
         **/
        data class Employee(
            val id: UUID,
            val name: String,
            val team: String,
            val spot: String
        )
    }
}
