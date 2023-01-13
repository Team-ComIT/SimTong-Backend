package team.comit.simtong.domain.holiday.dto.request

/**
 *
 * 휴무일 공유 요청 정보를 전달하는 ShareHolidayData
 *
 * @author Chokyunghyeon
 * @date 2023/01/13
 * @version 1.2.5
 **/
data class ShareHolidayData(
    val year: Int,

    val month: Int
)