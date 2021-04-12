import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.verification.WSResponseManager as WSResponseManager
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

response = WS.sendRequest(findTestObject('API/Get Typecode Users'))

'Verify response code'
WS.verifyResponseStatusCode(response, 200)

def slurper = new groovy.json.JsonSlurper()

def result = slurper.parseText(response.getResponseBodyContent())

WS.comment(result.toString())

int idSatu = result[0].id

String nameSatu = result[0].name

String usernameSatu = result[0].username

String emailSatu = result[0].email

'Verify data-type Id is Integer'
if (idSatu instanceof Integer) {
    KeywordUtil.markPassed('Id data type is correct')

    WS.comment(idSatu.toString())
} else {
    KeywordUtil.markFailed('Id data type id is wrong')
}

'Verify data-type Name is String'
if (nameSatu instanceof String) {
    KeywordUtil.markPassed('Name data type is correct')

    WS.comment(nameSatu)
} else {
    KeywordUtil.markFailed('Name data type is wrong')
}

'Verify data-type Username is String'
if (usernameSatu instanceof String) {
    KeywordUtil.markPassed('Username data type is correct')

    WS.comment(usernameSatu)
} else {
    KeywordUtil.markFailed('Username data type is wrong')
}

'Verify data-type Email is String'
if (emailSatu instanceof String) {
    KeywordUtil.markPassed('Email data type is correct')

    WS.comment(emailSatu)
} else {
    KeywordUtil.markFailed('Email data type is wrong')
}

