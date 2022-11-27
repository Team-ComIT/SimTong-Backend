package team.comit.simtong.domain.schedule.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.DomainPropertiesInitialization
import team.comit.simtong.domain.schedule.dto.AddIndividualScheduleRequest
import team.comit.simtong.domain.schedule.spi.CommandSchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleSecurityPort
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import java.time.LocalDate
import java.util.UUID

@Import(DomainPropertiesInitialization::class)
@ExtendWith(SpringExtension::class)
class AddIndividualScheduleUseCaseTests {

    @MockBean
    private lateinit var commandSchedulePort: CommandSchedulePort

    @MockBean
    private lateinit var queryUserPort: ScheduleQueryUserPort

    @MockBean
    private lateinit var securityPort: ScheduleSecurityPort

    private lateinit var addIndividualScheduleUseCase: AddIndividualScheduleUseCase

    private val id: UUID = UUID.randomUUID()

    private val userStub: User by lazy {
        User(
            id = id,
            nickname = "test nickname",
            name = "test name",
            email = "test@test.com",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_COMMON,
            spotId = UUID.randomUUID(),
            teamId = UUID.randomUUID(),
            profileImagePath = "test profile image"
        )
    }

    private val requestStub = AddIndividualScheduleRequest(
        title = "test title",
        startAt = LocalDate.now(),
        endAt = LocalDate.now(),
        alarm = null
    )

    @BeforeEach
    fun setUp() {
        addIndividualScheduleUseCase = AddIndividualScheduleUseCase(
            commandSchedulePort = commandSchedulePort,
            queryUserPort = queryUserPort,
            securityPort = securityPort
        )
    }

    @Test
    fun `개인 일정 추가 성공`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        // when & then
        assertDoesNotThrow {
            addIndividualScheduleUseCase.execute(requestStub)
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
        assertThrows<UserNotFoundException> {
            addIndividualScheduleUseCase.execute(requestStub)
        }
    }

}