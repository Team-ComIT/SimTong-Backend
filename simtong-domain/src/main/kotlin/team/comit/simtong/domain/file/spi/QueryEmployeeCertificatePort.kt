package team.comit.simtong.domain.file.spi

interface QueryEmployeeCertificatePort {

    fun existsEmployeeCertificateByNameAndEmployeeNumber(name: String, employeeNumber: Int): Boolean

}