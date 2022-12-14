package team.comit.simtong.thirdparty.parser

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_NULL_AND_BLANK
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Component
import team.comit.simtong.domain.file.converter.FileExtensions.XLS
import team.comit.simtong.domain.file.converter.FileExtensions.XLSX
import team.comit.simtong.domain.file.exception.FileExceptions
import team.comit.simtong.domain.file.exception.WebFileExceptions
import team.comit.simtong.domain.file.model.EmployeeCertificate
import team.comit.simtong.domain.file.spi.ParseEmployeeCertificateFilePort
import team.comit.simtong.domain.menu.model.Menu
import team.comit.simtong.domain.menu.spi.ParseMenuFilePort
import java.io.File
import java.time.LocalDate
import java.util.UUID

/**
 *
 * 엑셀 파일 구문 파싱을 담당하는 ExcelFileAdapter
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/12/06
 * @version 1.2.3
 **/
@Component
class ExcelFileAdapter : ParseEmployeeCertificateFilePort, ParseMenuFilePort {

    override fun importEmployeeCertificate(file: File): List<EmployeeCertificate> {
        val workbook = transferToExcel(file)

        val employeeCertificateList = mutableListOf<EmployeeCertificate>()

        runCatching {
            val workSheet = workbook.getSheetAt(0)

            for (i in 1..workSheet.lastRowNum) {
                val employeeData = workSheet.getRow(i).run {
                    EmployeeCertificate(
                        employeeNumber = getCell(1, RETURN_NULL_AND_BLANK).numericCellValue.toInt(),
                        name = getCell(2, CREATE_NULL_AS_BLANK).stringCellValue,
                        spotName = getCell(3, CREATE_NULL_AS_BLANK).stringCellValue,
                        teamName = getCell(4, CREATE_NULL_AS_BLANK).stringCellValue
                    )
                }

                employeeCertificateList.add(employeeData)
            }
        }.onFailure { e ->
            e.printStackTrace()
            throw FileExceptions.NotValidContent()
        }

        return employeeCertificateList
    }

    override fun importMenu(file: File, year: Int, month: Int, spotId: UUID): List<Menu> {
        val workbook = transferToExcel(file)
        val menu = mutableListOf<Menu>()

        runCatching {
            val workSheet = workbook.getSheetAt(0)

            // cell 1, 4, 7, 10 .. -> 날짜
            // cell 2+3, 5+6, 8+9 .. -> 메뉴
            for (rowNum in 1 until workSheet.lastRowNum step 3) {

                val dayRow = workSheet.getRow(rowNum)
                val mealRow = workSheet.getRow(rowNum + 1)
                val dessertRow = workSheet.getRow(rowNum + 2)

                for (cellNum in 0..6) {

                    var meal = mealRow.getCell(cellNum, CREATE_NULL_AS_BLANK).stringCellValue
                        .replace("\n", ",") // 줄바꿈 기준으로 ,로 구분

                    /**
                     * 메뉴가 없는 경우 다음 cell로 이동
                     */
                    if (meal.isEmpty()) {
                        continue
                    }

                    val dessert = dessertRow.getCell(cellNum, CREATE_NULL_AS_BLANK).stringCellValue

                    if (dessert.isNotEmpty()) { // 디저트가 있다면 문자열 합치기
                        meal = "$meal,$dessert"
                    }

                    val day = dayRow.getCell(cellNum, CREATE_NULL_AS_BLANK).stringCellValue
                        .substringBeforeLast("(") // 숫자만 가져오기 ex. 1(월) -> 1
                        .toInt()

                    val menuData = Menu(
                        date = LocalDate.of(year, month, day),
                        meal = meal,
                        spotId = spotId,
                    )

                    menu.add(menuData)
                }
            }
        }.onFailure { e ->
            e.printStackTrace()
            throw FileExceptions.NotValidContent()
        }

        return menu
    }

    private fun transferToExcel(file: File): Workbook {
        val result = file.inputStream()
            .use { inputStream ->
                runCatching {
                    when (file.extension.uppercase()) {
                        XLS -> HSSFWorkbook(inputStream)
                        XLSX -> XSSFWorkbook(inputStream)
                        else -> throw WebFileExceptions.InvalidExtension()
                    }
                }
            }
            .also {
                file.delete()
            }

        return result.getOrThrow()
    }
}