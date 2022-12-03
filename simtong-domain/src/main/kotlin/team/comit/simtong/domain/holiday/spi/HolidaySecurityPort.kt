package team.comit.simtong.domain.holiday.spi

import java.util.UUID

/**
 *
 * Holiday Domain에서 보안에 관해 요청하는 HolidaySecurityPort
 *
 * @author Chokyunghyeon
 * @date 2022/12/03
 * @version 1.0.0
 **/
interface HolidaySecurityPort {

    fun getCurrentUserId(): UUID

}