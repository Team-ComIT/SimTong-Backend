package team.comit.simtong.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import team.comit.simtong.global.error.BusinessException
import team.comit.simtong.global.error.ErrorProperty
import team.comit.simtong.global.error.ErrorResponse
import team.comit.simtong.global.exception.InternalServerErrorException
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * SecurityFilterChain에 등록해 필터 단의 예외를 처리하기 위한 ExceptionFilter
 *
 * @author kimbeomjin
 * @date 2022/08/22
 * @version 1.0.0
 **/
class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: BusinessException) {
            writeErrorCode(e.exceptionProperty, response)
        } catch (e: Exception) {
            when (e.cause) {
                is BusinessException -> writeErrorCode((e.cause as BusinessException).exceptionProperty, response)
                else -> {
                    e.printStackTrace()
                    writeErrorCode(InternalServerErrorException.EXCEPTION.exceptionProperty, response)
                }
            }
        }
    }

    private fun writeErrorCode(exception: ErrorProperty, response: HttpServletResponse) {
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.status = exception.status()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(
            objectMapper.writeValueAsString(
                ErrorResponse.of(exception)
            )
        )
    }
}