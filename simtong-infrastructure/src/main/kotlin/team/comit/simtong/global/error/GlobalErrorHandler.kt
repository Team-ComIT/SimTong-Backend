package team.comit.simtong.global.error

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 *
 * 전체적으로 발생하는 예외를 핸들링하는 GlobalErrorHandler
 *
 * @author kimbeomjin
 * @date 2022/08/22
 * @version 1.0.0
 **/
@RestControllerAdvice
class GlobalErrorHandler {

    /**
     * SQL 문을 통한 데이터의 삽입/수정이 무결성 제약 조건을 위반할 경우 발생
     */
    @ExceptionHandler(DataIntegrityViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleDataIntegrityViolationException(
        exception: DataIntegrityViolationException
    ): ErrorResponse? {
        return ErrorResponse.of(exception)
    }

    /**
     * 적합하지 않거나 적절하지 못한 인자를 메서드에 넘기면 발생
     */
    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleIllegalArgumentException(exception: IllegalArgumentException): ErrorResponse? {
        return ErrorResponse.of(GlobalErrorCode.BAD_REQUEST)
    }
}