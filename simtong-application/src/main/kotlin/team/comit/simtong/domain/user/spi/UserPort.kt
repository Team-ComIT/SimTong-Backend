package team.comit.simtong.domain.user.spi

import team.comit.simtong.domain.holiday.spi.HolidayQueryUserPort
import team.comit.simtong.domain.menu.spi.MenuQueryUserPort
import team.comit.simtong.domain.notification.spi.NotificationQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort

/**
 *
 * User Domain에 관한 요청하는 UserPort
 *
 * @author kimbeomjin
 * @date 2022/09/18
 * @version 1.0.0
 **/
interface UserPort : QueryUserPort, CommandUserPort, CommandDeviceTokenPort, MenuQueryUserPort, ScheduleQueryUserPort,
    HolidayQueryUserPort, NotificationQueryUserPort