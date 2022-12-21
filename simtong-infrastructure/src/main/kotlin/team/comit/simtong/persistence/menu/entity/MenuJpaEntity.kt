package team.comit.simtong.persistence.menu.entity

import org.springframework.data.domain.Persistable
import team.comit.simtong.persistence.spot.entity.SpotJpaEntity
import java.io.Serializable
import java.time.LocalDate
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.PostLoad
import javax.persistence.PrePersist
import javax.persistence.Table
import javax.validation.constraints.NotNull

/**
 *
 * 지점에 속한 메뉴를 관리하는 MenuJpaEntity
 *
 * @author kimbeomjin
 * @date 2022/09/20
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_menu")
class MenuJpaEntity(

    @EmbeddedId
    val menuId: Id,

    @MapsId("spotId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", columnDefinition = "BINARY(16)", nullable = false)
    val spot: SpotJpaEntity,

    @field:NotNull
    val meal: String
) : Persistable<MenuJpaEntity.Id> {

    /**
     *
     * 메뉴 엔티티의 기본키인 MenuId
     *
     * @author Chokyunghyeon
     * @date 2022/12/01
     * @version 1.0.0
     **/
    @Embeddable
    data class Id(

        @Column(nullable = false)
        val spotId: UUID,

        @Column(nullable = false)
        val date: LocalDate
    ) : Serializable

    @Transient
    private var isNew = true

    override fun getId(): Id {
        return menuId
    }

    override fun isNew(): Boolean {
        return isNew
    }

    @PrePersist
    @PostLoad
    fun markNotNew() {
        isNew = false
    }
}