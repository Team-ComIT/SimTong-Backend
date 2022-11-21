package team.comit.simtong.persistence.schedule.entity

import org.hibernate.annotations.ColumnDefault
import team.comit.simtong.domain.schedule.model.Scope
import team.comit.simtong.persistence.BaseEntity
import team.comit.simtong.persistence.spot.entity.SpotJpaEntity
import team.comit.simtong.persistence.user.entity.UserJpaEntity
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

/**
 *
 * 일정에 대해 관리하는 ScheduleJpaEntity
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_schedule")
class ScheduleJpaEntity(

    override val id: UUID?,

    @field:NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)")
    val user: UserJpaEntity,

    @field:NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", columnDefinition = "BINARY(16)")
    val spot: SpotJpaEntity,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    val title: String,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(10)")
    @Enumerated(EnumType.STRING)
    val scope: Scope,

    @field:NotNull
    val startAt: LocalDate,

    @field:NotNull
    val endAt: LocalDate,

    @field:NotNull
    @ColumnDefault("'08:30:00'")
    val alarmTime: LocalTime

) : BaseEntity(id)