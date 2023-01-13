package team.comit.simtong.domain.holiday.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.domain.holiday.model.HolidayStatus
import team.comit.simtong.domain.holiday.model.HolidayType
import team.comit.simtong.domain.holiday.spi.CommandHolidayPort
import team.comit.simtong.domain.holiday.spi.HolidayQueryUserPort
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.global.annotation.SimtongTest
import java.time.LocalDate
import java.util.UUID

@SimtongTest
class ShareHolidayUseCaseTests {

    @MockBean
    private lateinit var queryHolidayPort: QueryHolidayPort

    @MockBean
    private lateinit var commandHolidayPort: CommandHolidayPort

    @MockBean
    private lateinit var securityPort: HolidaySecurityPort

    @MockBean
    private lateinit var queryUserPort: HolidayQueryUserPort

    private lateinit var shareHolidayUseCase: ShareHolidayUseCase

    private val userId: UUID = UUID.randomUUID()
    private val spotId: UUID = UUID.randomUUID()

    private val userStub by lazy {
        User.of(
            id = userId,
            nickname = "test nickname",
            name = "test name",
            email = "test@test.com",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_COMMON,
            spotId = spotId,
            teamId = UUID.randomUUID(),
            profileImagePath = User.DEFAULT_IMAGE
        )
    }

    private val holidaysStub: List<Holiday> by lazy {
        listOf(
            Holiday(
                date = LocalDate.of(2022, 12, 1),
                employeeId = userId,
                type = HolidayType.HOLIDAY,
                spotId = spotId,
                status = HolidayStatus.COMPLETED
            )
        )
    }

    @BeforeEach
    fun setUp() {
        shareHolidayUseCase = ShareHolidayUseCase(
            queryHolidayPort, commandHolidayPort, securityPort, queryUserPort
        )
    }

    @Test
    fun `휴무표 공유 성공`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        given(queryHolidayPort.queryHolidaysByYearAndMonthAndSpotIdAndType(2022, 12, spotId, HolidayType.HOLIDAY))
            .willReturn(holidaysStub)

        // when & then
        assertDoesNotThrow {
            shareHolidayUseCase.execute(2022, 12)
        }
    }

    @Test
    fun `유저를 찾을 수 없음`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(null)

        // when & then
        assertThrows<UserExceptions.NotFound> {
            shareHolidayUseCase.execute(2022, 12)
        }
    }
}