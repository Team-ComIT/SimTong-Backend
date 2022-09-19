package team.comit.simtong.persistence.spot.mapper

import org.mapstruct.Mapper
import team.comit.simtong.domain.spot.model.Spot
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.spot.entity.SpotJpaEntity

/**
 *
 * SpotEntity와 DomainSpot을 변환하는 SpotMapper
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
@Mapper
abstract class SpotMapper : GenericMapper<SpotJpaEntity, Spot> {
}