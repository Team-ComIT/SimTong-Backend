package team.comit.simtong.persistence.menu.entity

import org.springframework.data.domain.Persistable
import team.comit.simtong.persistence.spot.entity.SpotJpaEntity
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
    val menuId: MenuId,

    @field:NotNull
    val meal: String,

    @MapsId("spotId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", columnDefinition = "BINARY(16)", nullable = false)
    val spot: SpotJpaEntity
) : Persistable<MenuId> {
    @Transient
    private var isNew = true

    override fun getId(): MenuId {
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