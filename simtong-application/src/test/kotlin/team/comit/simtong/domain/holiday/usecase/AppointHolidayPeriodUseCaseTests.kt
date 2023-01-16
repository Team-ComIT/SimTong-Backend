package team.comit.simtong.domain.holiday.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.holiday.dto.request.AppointHolidayPeriodData
import team.comit.simtong.domain.holiday.spi.CommandHolidayPeriodPort
import team.comit.simtong.domain.holiday.spi.HolidayQueryUserPort
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.global.annotation.SimtongTest
import java.time.LocalDate
import java.util.UUID

@SimtongTest
class AppointHolidayPeriodUseCaseTests {

    @MockBean
    private lateinit var commandHolidayPeriodPort: CommandHolidayPeriodPort

    @MockBean
    private lateinit var queryUserPort: HolidayQueryUserPort

    @MockBean
    private lateinit var securityPort: HolidaySecurityPort

    private lateinit var appointHolidayPeriodUseCase: AppointHolidayPeriodUseCase

    private val id: UUID = UUID.randomUUID()

    private val userStub: User by lazy {
        User.of(
            id = id,
            nickname = "test nickname",
            email = "test@test.com",
            name = "test name",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_ADMIN,
            spotId = id,
            teamId = id,
            profileImagePath = User.DEFAULT_IMAGE
        )
    }

    private val requestStub: AppointHolidayPeriodData by lazy {
        AppointHolidayPeriodData(
            year = 2022,
            month = 12,
            startAt = LocalDate.now(),
            endAt = LocalDate.now()
        )
    }

    @BeforeEach
    fun setUp() {
        appointHolidayPeriodUseCase = AppointHolidayPeriodUseCase(
            commandHolidayPeriodPort = commandHolidayPeriodPort,
            queryUserPort = queryUserPort,
            securityPort = securityPort
        )
    }

    @Test
    fun `휴무일 작성 기간 설정 성공`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        // when & then
        assertDoesNotThrow {
            appointHolidayPeriodUseCase.execute(requestStub)
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
            appointHolidayPeriodUseCase.execute(requestStub)
        }
    }

}