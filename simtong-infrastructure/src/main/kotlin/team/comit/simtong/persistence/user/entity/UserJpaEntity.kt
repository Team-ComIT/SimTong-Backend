package team.comit.simtong.persistence.user.entity

import org.hibernate.annotations.ColumnDefault
import org.hibernate.validator.constraints.Length
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.persistence.BaseEntity
import team.comit.simtong.persistence.spot.entity.SpotJpaEntity
import team.comit.simtong.persistence.team.entity.TeamJpaEntity
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint
import javax.validation.constraints.NotNull

/**
 *
 * 유저(직원, 관리자) 정보를 관리하는 UserJpaEntity
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/08/21
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_user")
class UserJpaEntity(
    override val id: UUID?,

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

    @field:NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", columnDefinition = "BINARY(16)")
    val spot: SpotJpaEntity,

    @field:NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", columnDefinition = "BINARY(16)")
    val team: TeamJpaEntity,

    @field:NotNull
    @ColumnDefault("'default image'") // TODO 기본 프로필 이미지 설정
    val profileImagePath: String,

    val deletedAt: LocalDateTime?
) : BaseEntity(id)