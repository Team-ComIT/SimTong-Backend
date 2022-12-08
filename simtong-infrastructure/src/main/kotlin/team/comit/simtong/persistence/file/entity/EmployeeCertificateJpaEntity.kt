package team.comit.simtong.persistence.file.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 *
 * 사원 명부를 관리하는 EmployeeCertificateJpaEntity
 *
 * @author Chokyunghyeon
 * @date 2022/12/08
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_employee_certificate")
data class EmployeeCertificateJpaEntity(
    @Id
    val employeeNumber: Int,

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    val name: String,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    val spotName: String,

    @Column(columnDefinition = "VARCHAR(8)", nullable = false)
    val teamName: String
)