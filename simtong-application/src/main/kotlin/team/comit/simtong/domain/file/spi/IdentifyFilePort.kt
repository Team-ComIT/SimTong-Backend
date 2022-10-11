package team.comit.simtong.domain.file.spi

/**
 *
 * 파일 확인을 요청하는 IdentifyFilePort
 *
 * @author Chokyunghyeon
 * @date 2022/10/11
 * @version 1.0.0
 **/
interface IdentifyFilePort {

    fun existsPath(path: String): Boolean

}