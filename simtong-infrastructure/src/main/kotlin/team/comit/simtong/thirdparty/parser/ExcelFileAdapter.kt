package team.comit.simtong.thirdparty.parser

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_NULL_AND_BLANK
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Component
import team.comit.simtong.domain.file.converter.FileExtensions.XLS
import team.comit.simtong.domain.file.converter.FileExtensions.XLSX
import team.comit.simtong.domain.file.exception.FileInvalidExtensionException
import team.comit.simtong.domain.file.exception.FileNotValidContentException
import team.comit.simtong.domain.file.model.EmployeeCertificate
import team.comit.simtong.domain.file.spi.ParseFilePort
import java.io.File

/**
 *
 * 엑셀 파일 구문 분석을 담당하는 ExcelFileAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/12/06
 * @version 1.0.0
 **/
@Component
class ExcelFileAdapter : ParseFilePort {

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
        }.onFailure {
            throw FileNotValidContentException.EXCEPTION
        }

        return employeeCertificateList
    }

    private fun transferToExcel(file: File) : Workbook {
        val inputStream = file.inputStream()

        return runCatching {
            when (file.extension.uppercase()) {
                XLS -> HSSFWorkbook(inputStream)
                XLSX -> XSSFWorkbook(inputStream)
                else -> throw FileInvalidExtensionException.EXCEPTION
            }
        }.also {
            inputStream.close()
            file.delete()
        }.getOrThrow()
    }
}