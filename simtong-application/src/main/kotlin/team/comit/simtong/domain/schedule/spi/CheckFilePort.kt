package team.comit.simtong.domain.schedule.spi

/**
 *
 * 파일 확인을 요청하는 CheckFilePort
 *
 * @author Chokyunghyeon
 * @date 2022/10/11
 * @version 1.0.0
 **/
interface CheckFilePort {

    fun existsPath(path: String): Boolean

}