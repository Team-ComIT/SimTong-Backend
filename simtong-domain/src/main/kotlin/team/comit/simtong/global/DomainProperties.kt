package team.comit.simtong.global

import team.comit.simtong.global.exception.NotInitializationPropertiesException
import java.util.Properties

/**
 *
 * Domain Aggregate의 설정 값을 받는 DomainProperties
 *
 * @author Chokyunghyeon
 * @date 2022/10/29
 * @version 1.0.0
 **/
object DomainProperties : Properties() {

    override fun getProperty(key: String): String {
        return super.getProperty(key) ?: throw NotInitializationPropertiesException
    }

}