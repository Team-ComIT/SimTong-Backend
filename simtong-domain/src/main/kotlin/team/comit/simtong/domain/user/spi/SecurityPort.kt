package team.comit.simtong.domain.user.spi

interface SecurityPort {

    fun compare(target: String, encryptedPassword: String): Boolean

    fun encode(password: String): String
}