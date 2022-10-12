package team.comit.simtong.domain.menu.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.menu.model.Menu
import team.comit.simtong.domain.menu.spi.MenuQueryUserPort
import team.comit.simtong.domain.menu.spi.MenuSecurityPort
import team.comit.simtong.domain.menu.spi.QueryMenuPort
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import java.time.LocalDate
import java.util.*

@ExtendWith(SpringExtension::class)
class QueryMenuByMonthUseCaseTests {

    @MockBean
    private lateinit var queryMenuPort: QueryMenuPort

    @MockBean
    private lateinit var queryUserPort: MenuQueryUserPort

    @MockBean
    private lateinit var menuSecurityPort: MenuSecurityPort

    private lateinit var queryMenuByMonthUseCase: QueryMenuByMonthUseCase

    private val currentUserId = UUID.randomUUID()
    private val spotId = UUID.randomUUID()
    private val now = LocalDate.now()

    private val userStub: User by lazy {
        User(
            id = currentUserId,
            name = "test name",
            nickname = "test nickname",
            email = "test email",
            password = "test encode password",
            employeeNumber = 1234567891,
            authority = Authority.ROLE_COMMON,
            spotId = spotId,
            teamId = UUID.randomUUID(),
            profileImagePath = "test profileImagePath"
        )
    }

    private val menuStub: Menu by lazy {
        Menu(
            date = LocalDate.of(2022, 9, 1),
            meal = "오늘 아침은 아침밥",
            spotId = UUID.randomUUID()
        )
    }

    private val menuStub2: Menu by lazy {
        Menu(
            date = LocalDate.of(2022, 9, 30),
            meal = "오늘 점심은 점심밥",
            spotId = UUID.randomUUID()
        )
    }

    @BeforeEach
    fun setUp() {
        queryMenuByMonthUseCase = QueryMenuByMonthUseCase(queryMenuPort, queryUserPort, menuSecurityPort)
    }

    @Test
    fun `메뉴 조회 성공`() {
        // given
        given(menuSecurityPort.getCurrentUserId())
            .willReturn(currentUserId)

        given(queryUserPort.queryUserById(currentUserId))
            .willReturn(userStub)

        given(queryMenuPort.queryMenuBySpotId(now.year, now.monthValue, userStub.spotId))
            .willReturn(
                listOf(menuStub, menuStub2)
            )

        // when
        val response = queryMenuByMonthUseCase.execute(now)

        // then
        assertThat(response).isNotNull
    }

    @Test
    fun `유저가 존재하지 않음`() {
        // given
        given(menuSecurityPort.getCurrentUserId())
            .willReturn(currentUserId)

        given(queryUserPort.queryUserById(currentUserId))
            .willReturn(null)

        // when & then
        assertThrows<UserNotFoundException> {
            queryMenuByMonthUseCase.execute(now)
        }
    }

}