package team.comit.simtong.domain.holiday.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.holiday.exception.HolidayExceptions
import team.comit.simtong.domain.holiday.spi.HolidayQueryUserPort
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPeriodPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.global.annotation.SimtongTest
import java.time.LocalDate
import java.util.UUID

@SimtongTest
class CheckHolidayPeriodUseCaseTests {

    @MockBean
    private lateinit var queryHolidayPeriodPort: QueryHolidayPeriodPort

    @MockBean
    private lateinit var queryUserPort: HolidayQueryUserPort

    @MockBean
    private lateinit var securityPort: HolidaySecurityPort

    private lateinit var checkHolidayPeriodUseCase: CheckHolidayPeriodUseCase

    private val id: UUID = UUID.randomUUID()

    private val date: LocalDate = LocalDate.now()

    private val userStub: User by lazy {
        User(
            id = id,
            nickname = "test nickname",
            name = "test name",
            email = "test@test.com",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_COMMON,
            spotId = id,
            teamId = id,
            profileImagePath = User.DEFAULT_IMAGE
        )
    }

    @BeforeEach
    fun setUp() {
        checkHolidayPeriodUseCase = CheckHolidayPeriodUseCase(
            queryHolidayPeriodPort = queryHolidayPeriodPort,
            queryUserPort = queryUserPort,
            securityPort = securityPort
        )
    }

    @Test
    fun `휴무표 작성 기간일 때`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(queryHolidayPeriodPort.existsHolidayPeriodByWithinPeriodAndSpotId(date, userStub.spotId))
            .willReturn(true)

        // when & then
        assertDoesNotThrow {
            checkHolidayPeriodUseCase.execute()
        }
    }

    @Test
    fun `휴무표 작성 기간이 아닐 때`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(queryHolidayPeriodPort.existsHolidayPeriodByWithinPeriodAndSpotId(date, userStub.spotId))
            .willReturn(false)

        // when & then
        assertThrows<HolidayExceptions.NotWritablePeriod> {
            checkHolidayPeriodUseCase.execute()
        }
    }

    @Test
    fun `유저를 찾을 수 없음`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(null)

        // when & then
        assertThrows<UserExceptions.NotFound> {
            checkHolidayPeriodUseCase.execute()
        }
    }

}