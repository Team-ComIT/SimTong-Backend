package team.comit.simtong.persistence.menu.entity

import java.io.Serializable
import java.time.LocalDate
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class MenuId(

    @Column(name = "spot_id", columnDefinition = "BINARY(16)")
    val spotId: UUID,

    val date: LocalDate

) : Serializable