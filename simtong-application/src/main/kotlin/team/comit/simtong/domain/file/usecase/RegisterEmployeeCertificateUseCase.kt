package team.comit.simtong.domain.file.usecase

import team.comit.simtong.domain.file.spi.CommandEmployeeCertificatePort
import team.comit.simtong.domain.file.spi.ParseEmployeeCertificateFilePort
import team.comit.simtong.global.annotation.UseCase
import java.io.File

/**
 *
 * 직원 명부 등록 요청을 담당하는 RegisterEmployeeCertificateUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/12/06
 * @version 1.0.0
 **/
@UseCase
class RegisterEmployeeCertificateUseCase(
    private val commandEmployeeCertificatePort: CommandEmployeeCertificatePort,
    private val parseEmployeeCertificateFilePort: ParseEmployeeCertificateFilePort
) {

    fun execute(file: File) {
        val employeeList = parseEmployeeCertificateFilePort.importEmployeeCertificate(file)

        commandEmployeeCertificatePort.saveAll(employeeList)
    }
}