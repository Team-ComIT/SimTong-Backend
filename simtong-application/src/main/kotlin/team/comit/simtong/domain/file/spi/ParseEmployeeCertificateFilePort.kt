package team.comit.simtong.domain.file.spi

import team.comit.simtong.domain.file.model.EmployeeCertificate
import java.io.File

/**
 *
 * EmployeeCertificate 파일 파싱을 요청하는 ParseEmployeeCertificateFilePort
 *
 * @author Chokyunghyeon
 * @date 2022/12/06
 * @version 1.0.0
 **/
interface ParseEmployeeCertificateFilePort {

    fun importEmployeeCertificate(file: File): List<EmployeeCertificate>
}