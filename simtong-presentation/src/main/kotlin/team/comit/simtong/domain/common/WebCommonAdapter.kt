package team.comit.simtong.domain.common

import org.springframework.web.bind.annotation.*
import team.comit.simtong.domain.auth.usecase.ReissueTokenUseCase
import team.comit.simtong.domain.auth.usecase.dto.TokenResponse
import team.comit.simtong.domain.common.dto.request.WebFindEmployeeNumberRequest
import team.comit.simtong.domain.common.dto.response.WebFindEmployeeNumberResponse
import team.comit.simtong.domain.user.usecase.FindEmployeeNumberUseCase
import team.comit.simtong.domain.user.usecase.dto.FindEmployeeNumberRequest
import javax.validation.Valid

/**
 *
 * User와 Admin에 관한 요청을 받는 WebCommonAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/11
 * @version 1.0.0
 **/
@RestController
@RequestMapping("/commons")
class WebCommonAdapter(
    private val reissueTokenUseCase: ReissueTokenUseCase,
    private val findEmployeeNumberUseCase: FindEmployeeNumberUseCase
) {

    @GetMapping("/employee-number")
    fun findEmployeeNumber(@Valid request: WebFindEmployeeNumberRequest): WebFindEmployeeNumberResponse {
        val result = findEmployeeNumberUseCase.execute(
            FindEmployeeNumberRequest(
                name = request.name, spotId = request.spotId, email = request.email
            ))

        return WebFindEmployeeNumberResponse(result)
    }

    @PutMapping("/token/reissue")
    fun reissueJsonWebToken(@RequestHeader("Refresh-Token") request: String): TokenResponse {
        return reissueTokenUseCase.execute(request)
    }

}