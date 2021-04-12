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

int id = 0

String name = ''

String username = ''

String email = ''

int count = 0

for (int i = 0; i<10; i++){
	id = result[i].id
	
	name = result[i].name
	
	username = result[i].username
	
	email = result[i].email
	
	count = i + 1
	
	'Verify data-type Id is Integer'
	if (id instanceof Integer) {
		KeywordUtil.markPassed('Id('+ count +') data type is correct')
	
		WS.comment(id.toString())
	} else {
		KeywordUtil.markFailed('Id('+ count +') data type id is wrong')
	}
	
	'Verify data-type Name is String'
	if (name instanceof String) {
		KeywordUtil.markPassed('Name('+ count +') data type is correct')
	
		WS.comment(name)
	} else {
		KeywordUtil.markFailed('Name('+ count +') data type is wrong')
	}
	
	'Verify data-type Username is String'
	if (username instanceof String) {
		KeywordUtil.markPassed('Username('+ count +') data type is correct')
	
		WS.comment(username)
	} else {
		KeywordUtil.markFailed('Username('+ count +') data type is wrong')
	}
	
	'Verify data-type Email is String'
	if (email instanceof String) {
		KeywordUtil.markPassed('Email('+ count +') data type is correct')
	
		WS.comment(email)
	} else {
		KeywordUtil.markFailed('Email('+ count +') data type is wrong')
	}
}

