import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.configuration.RunConfiguration
import java.text.SimpleDateFormat
import java.nio.file.*

class ReportRenamer {

	@AfterTestSuite
	def renameReportFolder(TestSuiteContext testSuiteContext) {
		try {
			// Get the report folder path
			String reportsDir = RunConfiguration.getReportFolder()

			// Get the Test Suite Name
			String suiteName = testSuiteContext.getTestSuiteId().split('/').last()

			// Generate a timestamp
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())

			// Create a custom folder name
			String customName = "${suiteName}_${timestamp}"

			// Get the parent directory of the reports folder
			Path reportPath = Paths.get(reportsDir)
			Path parentDir = reportPath.getParent()

			// Define the new folder path
			Path newPath = parentDir.resolve(customName)

			// Rename the folder
			Files.move(reportPath, newPath)

			println "Report folder renamed to: ${newPath}"

		} catch (Exception e) {
			println "Error while renaming the report folder: ${e.getMessage()}"
		}
	}
}