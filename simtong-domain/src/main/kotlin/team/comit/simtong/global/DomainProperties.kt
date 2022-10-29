package team.comit.simtong.global

import java.lang.ClassLoader.getSystemResourceAsStream
import java.lang.System.getenv
import java.util.Properties

/**
 *
 * Domain Aggregate의 설정 값을 받는 DomainProperties
 *
 * @author Chokyunghyeon
 * @date 2022/10/29
 * @version 1.0.0
 **/
internal class DomainProperties {

    /**
     * Properties 동작 과정
     *
     * properties file을 읽어 Properties 객체로 변환
     *
     * 만약, value가 환경변수라면
     * value의 환경변수를 불러와 Properties 저장
     */
    companion object {
        @JvmField
        val properties = Properties().also { properties ->
            properties.load(getSystemResourceAsStream(DomainPropertiesPrefix.PROPERTIES_FILE))
            properties.forEach { entry ->
                (entry.value as String).let { str ->
                    if (str.startsWith('$')) {
                        str.replace(Regex("[$}{]"), "").split(':').let {
                            when (it.size) {
                                1 -> properties[entry.key] = getenv(it[0])
                                    ?: throw InternalError("Missing Environment Variable [${DomainPropertiesPrefix.PROPERTIES_FILE}] '${entry.key}'")
                                2 -> properties[entry.key] = getenv(it[0]) ?: it[1]
                            }
                        }
                    }
                }
            }
        }

        fun getProperty(key: String): String = properties.getProperty(key)
    }

}