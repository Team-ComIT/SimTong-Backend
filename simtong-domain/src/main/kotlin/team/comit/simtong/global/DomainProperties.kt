package team.comit.simtong.global

import java.lang.ClassLoader.getSystemResourceAsStream
import java.lang.System.getenv
import java.util.Properties

internal class DomainProperties {

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