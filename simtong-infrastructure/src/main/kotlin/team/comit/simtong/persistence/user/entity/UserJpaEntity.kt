package team.comit.simtong.persistence.user.entity

import org.hibernate.annotations.ColumnDefault
import org.hibernate.validator.constraints.Length
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.persistence.BaseUUIDEntity
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_user")
class UserJpaEntity(

    @field:NotNull
    @Column(unique = true)
    val employeeNumber: Int,

    @field:NotNull
    @Column(unique = true)
    val email: String,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 11)
    val authority: Authority,

    @field:NotNull
    @field:Length(max = 10)
    val name: String,

    @field:NotNull
    @field:Length(max = 20)
    @Column(unique = true)
    val nickname: String,

    @field:NotNull
    @Column(columnDefinition = "CHAR(60)")
    val password: String,

    @Column(columnDefinition = "CHAR(60)")
    val adminCode: String,

    @field:NotNull
    @ColumnDefault("'default image'") // TODO 기본 프로필 이미지 설정
    val profileImagePath: String,

    val deletedAt: LocalDateTime?
) : BaseUUIDEntity()