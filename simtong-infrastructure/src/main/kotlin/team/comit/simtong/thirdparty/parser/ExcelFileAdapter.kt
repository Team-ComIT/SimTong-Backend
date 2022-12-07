package team.comit.simtong.thirdparty.parser

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Component
import team.comit.simtong.domain.file.FileExtensionUtils.XLS
import team.comit.simtong.domain.file.FileExtensionUtils.XLSX
import team.comit.simtong.domain.file.exception.FileInvalidExtensionException
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
        val inputStream = file.inputStream()
        val excel = when (file.extension) {
            XLS -> HSSFWorkbook(inputStream)
            XLSX -> XSSFWorkbook(inputStream)
            else -> {
                inputStream.close()
                file.delete()
                throw FileInvalidExtensionException.EXCEPTION
            }
        }.also {
            inputStream.close()
        }

        val employeeCertificateList = mutableListOf<EmployeeCertificate>()
        runCatching {
            val workSheet = excel.getSheetAt(0)

            for (i in 1 until workSheet.lastRowNum) {
                val employeeData = workSheet.getRow(i).let {
                    EmployeeCertificate(
                        employeeNumber = it.getCell(1, Row.MissingCellPolicy.RETURN_NULL_AND_BLANK).numericCellValue.toInt(),
                        name = it.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).stringCellValue,
                        spotName = it.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).stringCellValue,
                        teamName = it.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).stringCellValue
                    )
                }

                employeeCertificateList.add(employeeData)
            }
        }.also {
            file.delete()
            if (it.isFailure) {
                throw it.exceptionOrNull()!! // TODO Exception Handling
            }
        }

        return employeeCertificateList
    }
}