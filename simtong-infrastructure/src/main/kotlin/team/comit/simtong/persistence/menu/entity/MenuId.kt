package team.comit.simtong.persistence.menu.entity

import java.io.Serializable
import java.time.LocalDate
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 *
 * 메뉴 엔티티의 기본키인 MenuId
 *
 * @author Chokyunghyeon
 * @date 2022/12/01
 * @version 1.0.0
 **/
@Embeddable
data class MenuId(

    @Column(nullable = false)
    val spotId: UUID,

    @Column(nullable = false)
    val date: LocalDate

) : Serializable