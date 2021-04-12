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

'input name for request'
GlobalVariable.reqName = 'Aleph QA Test'

'input username for request'
GlobalVariable.reqUsername = 'aleph.test'

'input email for request'
GlobalVariable.reqEmail = 'aleph.test@mail.com'

response = WS.sendRequest(findTestObject('API/Post Typecode Users', [('name') : GlobalVariable.reqName, ('username') : GlobalVariable.reqUsername
            , ('email') : GlobalVariable.reqEmail]))

'Verify response code'
WS.verifyResponseStatusCode(response, 201)

def slurper = new groovy.json.JsonSlurper()

def result = slurper.parseText(response.getResponseBodyContent())

WS.comment(result.toString())

int idSatu = result.id

String nameSatu = result.name

String usernameSatu = result.username

String emailSatu = result.email

'Verify id field is not null'
WS.verifyNotMatch(idSatu.toString(), null, false)

'Verify name field is match with input name when request'
WS.verifyMatch(nameSatu, GlobalVariable.reqName, false)

'Verify username field is match with input username when request'
WS.verifyMatch(usernameSatu, GlobalVariable.reqUsername, false)

'Verify email field is match with input email when request'
WS.verifyMatch(emailSatu, GlobalVariable.reqEmail, false)

