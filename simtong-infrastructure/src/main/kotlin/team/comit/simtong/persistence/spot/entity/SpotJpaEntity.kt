package team.comit.simtong.persistence.spot.entity

import team.comit.simtong.persistence.BaseUUIDEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 *
 * 지점에 대해 관리하는 SpotJpaEntity
 *
 * @author kimbeomjin
 * @date 2022/08/21
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_spot")
class SpotJpaEntity(
    override val id: UUID?,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    val name: String,

    @Column(columnDefinition = "VARCHAR(40)", nullable = false)
    val location: String
) : BaseUUIDEntity(id)