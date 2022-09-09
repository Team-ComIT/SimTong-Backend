package team.comit.simtong.domain.user.spi

interface CheckUserPort {

    fun existsUserByEmail(email: String): Boolean

}