package team.comit.simtong.domain.file.usecase

import team.comit.simtong.domain.file.exception.InvalidEmployeeException
import team.comit.simtong.domain.file.spi.QueryEmployeeCertificatePort
import team.comit.simtong.global.annotation.ReadOnlyUseCase

@ReadOnlyUseCase
class CheckEmployeeUseCase(
    private val queryEmployeeCertificatePort: QueryEmployeeCertificatePort
) {

    fun execute(name: String, employeeNumber: Int) {
        val isEmployee = queryEmployeeCertificatePort.existsEmployeeCertificateByNameAndEmployeeNumber(
            name, employeeNumber
        )

        if (!isEmployee) {
            throw InvalidEmployeeException
        }
    }
}