package team.comit.simtong.domain.user.spi

interface CheckEmployeePort {

    fun checkList(name: String, employeeNumber: Int)
}