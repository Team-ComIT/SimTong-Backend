package team.comit.simtong.domain.file.spi

import team.comit.simtong.domain.file.model.EmployeeCertificate
import java.io.File

/**
 *
 * 파일 분석을 요청하는 ParseFilePort
 *
 * @author Chokyunghyeon
 * @date 2022/12/06
 * @version 1.0.0
 **/
interface ParseFilePort {

    fun importEmployeeCertificate(file: File): List<EmployeeCertificate>
}