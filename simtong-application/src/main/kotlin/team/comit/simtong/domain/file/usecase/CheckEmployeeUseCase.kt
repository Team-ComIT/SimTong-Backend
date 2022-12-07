package team.comit.simtong.domain.file.usecase

import team.comit.simtong.domain.file.exception.InvalidEmployeeException
import team.comit.simtong.domain.file.spi.QueryEmployeeCertificatePort
import team.comit.simtong.global.annotation.ReadOnlyUseCase

/**
 *
 * 입력받은 이름과 사원번호를 DB에서 비교해 일치하는지 확인하는 CheckEmployeeUseCase
 *
 * @author kimbeomjin
 * @date 2022/12/07
 * @version 1.0.0
 **/
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