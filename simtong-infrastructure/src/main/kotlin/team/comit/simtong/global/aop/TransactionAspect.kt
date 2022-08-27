package team.comit.simtong.global.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.support.DefaultTransactionDefinition
import java.util.concurrent.atomic.AtomicInteger

/**
 *
 * 사용자 UseCase에 클래스 단위로 Transaction을 적용시키는 TransactionAspect
 *
 * @author kimbeomjin
 * @date 2022/08/27
 * @version 1.0.0
 **/
@Component
@Aspect
class TransactionAspect(
    private val transactionManager: PlatformTransactionManager
) {

    @Pointcut("@annotation(team.comit.simtong.global.annotation.UseCase)")
    fun getUseCases() {
    }

    @Pointcut("@annotation(team.comit.simtong.global.annotation.ReadOnlyUseCase)")
    fun getReadOnlyUseCases() {
    }

    // TODO @Transactional 어노테이션 적용 고려
    @Around("getUseCases()")
    fun applyTransaction(joinPoint: ProceedingJoinPoint): Any? {
        val transaction: TransactionStatus = transactionManager.getTransaction(DefaultTransactionDefinition())
        return try {
            val useCase = joinPoint.proceed()
            transactionManager.commit(transaction)
            useCase
        } catch (e: Throwable) {
            e.printStackTrace()
            transactionManager.rollback(transaction)
            null
        }
    }

    // TODO @Transactional(readOnly=true) 어노테이션 적용 고려
    @Around("getReadOnlyUseCases()")
    fun applyReadOnlyTransaction(joinPoint: ProceedingJoinPoint): Any? {
        return try {
            ThreadLocal.withInitial { AtomicInteger(0) }.get().incrementAndGet()
            joinPoint.proceed()
        } catch (e: Throwable) {
            e.printStackTrace()
            null
        } finally {
            ThreadLocal.withInitial { AtomicInteger(0) }.get().decrementAndGet()
        }
    }
}