package team.comit.simtong.domain.menu.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.menu.dto.request.SaveMenuRequest
import team.comit.simtong.domain.menu.exception.MenuExceptions
import team.comit.simtong.domain.menu.model.Menu
import team.comit.simtong.domain.menu.spi.CommandMenuPort
import team.comit.simtong.domain.menu.spi.ParseMenuFilePort
import team.comit.simtong.domain.menu.spi.QueryMenuPort
import team.comit.simtong.global.annotation.SimtongTest
import java.io.File
import java.time.LocalDate
import java.util.UUID

@SimtongTest
class SaveMenuUseCaseTests {

    @MockBean
    private lateinit var parseMenuFilePort: ParseMenuFilePort

    @MockBean
    private lateinit var queryMenuPort: QueryMenuPort

    @MockBean
    private lateinit var commandMenuPort: CommandMenuPort

    private lateinit var saveMenuUseCase: SaveMenuUseCase

    private val fileStub = File("")
    private val year = 2022
    private val month = 12
    private val spotId = UUID.randomUUID()

    private val menuStub: Menu by lazy {
        Menu(
            date = LocalDate.of(year, month, 1),
            meal = "오늘 아침은 아침밥",
            spotId = UUID.randomUUID()
        )
    }

    private val requestStub: SaveMenuRequest by lazy {
        SaveMenuRequest(
            file = fileStub,
            year = year,
            month = month,
            spotId = spotId
        )
    }

    @BeforeEach
    fun setUp() {
        saveMenuUseCase = SaveMenuUseCase(
            parseMenuFilePort = parseMenuFilePort,
            queryMenuPort = queryMenuPort,
            commandMenuPort = commandMenuPort
        )
    }

    @Test
    fun `메뉴 저장 성공`() {
        // given
        given(parseMenuFilePort.importMenu(fileStub, year, month, spotId))
            .willReturn(listOf(menuStub))

        given(queryMenuPort.existsMenuByMonthAndSpotId(LocalDate.of(year, month, 1), spotId))
            .willReturn(false)

        // when & then
        assertDoesNotThrow {
            saveMenuUseCase.execute(requestStub)
        }
    }

    @Test
    fun `같은 달에 메뉴가 이미 존재함`() {
        // given
        given(parseMenuFilePort.importMenu(fileStub, year, month, spotId))
            .willReturn(listOf(menuStub))

        given(queryMenuPort.existsMenuByMonthAndSpotId(LocalDate.of(year, month, 1), spotId))
            .willReturn(true)

        // when & then
        assertThrows<MenuExceptions.AlreadyExistsSameMonth> {
            saveMenuUseCase.execute(requestStub)
        }
    }

}