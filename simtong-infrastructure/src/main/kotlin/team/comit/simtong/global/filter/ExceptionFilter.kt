package team.comit.simtong.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import io.sentry.Sentry
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import team.comit.simtong.global.error.dto.ErrorResponse
import team.comit.simtong.global.exception.BusinessException
import team.comit.simtong.global.exception.GlobalExceptions
import team.comit.simtong.global.exception.WebException
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
            Sentry.captureException(e)
            writeErrorCode(e, response)
        } catch (e: Exception) {
            Sentry.captureException(e)
            when (e.cause) {
                is BusinessException -> writeErrorCode(e.cause as BusinessException, response)
                is WebException -> writeErrorCode(e.cause as WebException, response)
                else -> {
                    e.printStackTrace()
                    writeErrorCode(GlobalExceptions.InternalServerError(), response)
                }
            }
        }
    }

    private fun writeErrorCode(exception: BusinessException, response: HttpServletResponse) {
        response.run {
            characterEncoding = StandardCharsets.UTF_8.name()
            status = exception.status
            contentType = MediaType.APPLICATION_JSON_VALUE
            writer.write(
                objectMapper.writeValueAsString(
                    ErrorResponse.of(exception)
                )
            )
        }
    }

    private fun writeErrorCode(exception: WebException, response: HttpServletResponse) {
        response.run {
            characterEncoding = StandardCharsets.UTF_8.name()
            status = exception.status
            contentType = MediaType.APPLICATION_JSON_VALUE
            writer.write(
                objectMapper.writeValueAsString(
                    ErrorResponse.of(exception)
                )
            )
        }
    }
}