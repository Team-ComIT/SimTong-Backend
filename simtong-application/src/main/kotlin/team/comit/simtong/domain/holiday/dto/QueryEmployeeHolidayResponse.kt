package team.comit.simtong.domain.holiday.dto

import team.comit.simtong.domain.holiday.model.HolidayType
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
     * QueryEmployeeHolidayResponse
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
         * QueryEmployeeHolidayResponse
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
