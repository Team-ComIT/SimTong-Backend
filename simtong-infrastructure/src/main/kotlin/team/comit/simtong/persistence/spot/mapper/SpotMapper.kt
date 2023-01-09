package team.comit.simtong.persistence.spot.mapper

import org.springframework.stereotype.Component
import team.comit.simtong.domain.spot.model.Spot
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.spot.entity.SpotJpaEntity

/**
 *
 * SpotEntity와 DomainSpot을 변환하는 SpotMapper
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/18
 * @version 1.2.5
 **/
@Component
class SpotMapper : GenericMapper<SpotJpaEntity, Spot> {

    override fun toEntity(model: Spot): SpotJpaEntity {
        return SpotJpaEntity(
            id = model.id,
            name = model.name,
            location = model.location,
        )
    }

    override fun toDomain(entity: SpotJpaEntity?): Spot? {
        return entity?.let {
            Spot(
                id = it.id!!,
                name = it.name,
                location = it.location
            )
        }
    }

    override fun toDomainNotNull(entity: SpotJpaEntity): Spot {
        return Spot(
            id = entity.id!!,
            name = entity.name,
            location = entity.location
        )
    }
}