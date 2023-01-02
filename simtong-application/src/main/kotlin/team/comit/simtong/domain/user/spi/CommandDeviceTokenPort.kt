package team.comit.simtong.domain.user.spi

import team.comit.simtong.domain.user.model.DeviceToken

/**
 *
 * DeviceToken Domain에 관한 명령을 하는 CommandDeviceTokenPort
 *
 * @author kimbeomjin
 * @date 2022/12/31
 * @version 1.1.0
 **/
interface CommandDeviceTokenPort {

    fun save(deviceToken: DeviceToken): DeviceToken

}