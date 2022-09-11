package team.comit.simtong.domain.common

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.common.dto.request.WebFindEmployeeNumberRequest
import team.comit.simtong.domain.common.dto.response.WebFindEmployeeNumberResponse
import team.comit.simtong.domain.user.usecase.FindEmployeeNumberUseCase
import team.comit.simtong.domain.user.usecase.dto.FindEmployeeNumberRequest

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
    private val findEmployeeNumberUseCase: FindEmployeeNumberUseCase
) {

    @GetMapping("/employee-number")
    fun findEmployeeNumber(request: WebFindEmployeeNumberRequest): WebFindEmployeeNumberResponse {
        return WebFindEmployeeNumberResponse(
            findEmployeeNumberUseCase.execute(FindEmployeeNumberRequest(
                name = request.name,
                spot = request.spot,
                email = request.email
            ))
        )
    }

}