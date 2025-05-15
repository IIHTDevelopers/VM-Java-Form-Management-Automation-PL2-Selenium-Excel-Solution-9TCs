

package testcases;

import java.util.Map;
import java.io.File;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import coreUtilities.testutils.ApiHelper;
import coreUtilities.utils.FileOperations;
import pages.FormManagement_L1_Pages;
import pages.StartupPage;
import testBase.AppTestBase;
import testdata.LocatorsFactory;

public class FormManagement_L1_TestCases extends AppTestBase {
	
	
	By registerNavigationMenu = By.xpath("//a[contains(text() , 'Register')]"); 
	By firstNameTextbox = By.xpath("//input[@placeholder='First Name']"); 
	By lastNameTextbox = By.xpath("//input[@placeholder='Last Name']"); 
	By addressInputAreabox = By.xpath("//textarea[@ng-model='Adress']"); 
	By emailAddressTextbox = By.xpath("//input[@type='email']"); 
	By phoneNumberTextbox = By.xpath("//input[@type='tel']"); 
	By maleRadioButton = By.xpath("//input[@value='Male']"); 
	By feMaleRadioButton = By.xpath("//input[@value='FeMale']"); 
	By cricketCheckBox = By.xpath("//input[@value='Cricket']"); 
	By moviesCheckBox = By.xpath("//input[@value='Movies']"); 
	By hockeyCheckBox = By.xpath("//input[@value='Hockey']"); 
	
	Map<String, String> configData;
	Map<String, String> loginCredentials;
	String expectedDataFilePath = testDataFilePath+"expected_data.xlsx";
	StartupPage startupPage;
	FormManagement_L1_Pages RegisterPageInstance;
	LocatorsFactory LocatorsFactoryInstance=new LocatorsFactory(driver);
	
	
	
	@Parameters({"browser", "environment"})
	@BeforeClass(alwaysRun = true)
	public void initBrowser(String browser, String environment) throws Exception {
		configData = new FileOperations().readExcelPOI(config_filePath, environment);
		configData.put("url", configData.get("url").replaceAll("[\\\\]", ""));
		configData.put("browser", browser);
		
		boolean isValidUrl = new ApiHelper().isValidUrl(configData.get("url"));
		Assert.assertTrue(isValidUrl, configData.get("url")+" might be Server down at this moment. Please try after sometime.");
		initialize(configData);
		startupPage = new StartupPage(driver);
	}
	
	
	@Test(priority = 1, groups = {"sanity"}, description="Navigate to the URL and Validate the Home Page")
	public void DemoRegisterAutomation() throws Exception {
		RegisterPageInstance = new FormManagement_L1_Pages(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "HomePage_Title");
		Assert.assertEquals(RegisterPageInstance.validateTitleOfHomePage(), expectedData.get("pageTitle"));
		Assert.assertTrue(LocatorsFactoryInstance.registerNavigationMenu(driver).isDisplayed(), "switch to menu is not present in the current page, Please check manually");
	}	
	
	@Test(priority = 2, groups = {"sanity"}, description="Click SwitchTo  Alert Link. and Validate if control is navigated to new page")
	public void clickOnSwitchToAlertAndValidateTitleOfPage() throws Exception {
		
		softAssert = new SoftAssert();
		RegisterPageInstance = new FormManagement_L1_Pages(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "alerts_Page");
		Assert.assertEquals(RegisterPageInstance.clickOnSwitchToAlertandValidateTitleOfPage(), expectedData.get("alertsTitle"));
		Assert.assertTrue(LocatorsFactoryInstance.getswitchToNavigationMenu(driver).isDisplayed(), "switch to menu is not present in the current page, Please check manually");	
	}	
	
	@Test(priority = 3, groups = {"sanity"}, description="Click on button to display the alert box and Validate if alert popup is shown.")
	public void handleAlertsPopupAndValidateTheTextInsideAnAlertsPopup() throws Exception {
		RegisterPageInstance = new FormManagement_L1_Pages(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "alerts_message");
		Assert.assertEquals(RegisterPageInstance.handleAlertsPopupAndValidateTheTextInsideAnAlertsPopup(), expectedData.get("alertsMessage"));
		Assert.assertTrue(LocatorsFactoryInstance.getAlertWithOkButton(driver).isDisplayed(), "switch to menu is not present in the current page, Please check manually");		
	}	
	
	@Test(priority = 4, groups = {"sanity"}, description="Click on Register menu option to navigate to Register page. and  Fill the form with data provided in excel sheet.")
	public void clickOnRegisterLinkAndFillTheForms() throws Exception {
		RegisterPageInstance = new FormManagement_L1_Pages(driver);
		String expectedDataFilePath = testDataFilePath+"expected_data.xlsx";
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "userdetails");
		Assert.assertTrue(RegisterPageInstance.clickOnRegisterLinkandFillTheForms(expectedData), "Register button is not present, please check manually");
		Assert.assertTrue(LocatorsFactoryInstance.firstNameTextboxIsPresent(driver).isDisplayed(), "First name text field is not present in the current page, Please check manually");
	}	


	
	@Test(priority = 5, groups = {"sanity"}, description="Click on the country dropdown and Select each country option one by one.")
	public void clickOnCountryDropDownAndSelectEachCountryOptionsOneByOne() throws Exception {
		RegisterPageInstance = new FormManagement_L1_Pages(driver);
		Assert.assertTrue(RegisterPageInstance.clickOnSelectCountryAndSelectEachCountry(), "Register button is not present, please check manually");	
		Assert.assertTrue(LocatorsFactoryInstance.countryDropdownIsPresent(driver).isDisplayed(), "Country dropdown menu is not present in the current page, Please check manually");
	}	
	
	@Test(priority = 6, groups = {"sanity"}, description="Click on the country dropdown and Validate if each country option is selectable from the dropdown.")
	public void clickOnCountryDropDownAndValidateEachCountryOptionsIsSelectableOneByOneFromDropdown() throws Exception {
		RegisterPageInstance = new FormManagement_L1_Pages(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "userdetails");
		Assert.assertEquals(RegisterPageInstance.selectAustraliaInCountryDrpdownAndValidate(), expectedData.get("CountryName"));
		Assert.assertTrue(LocatorsFactoryInstance.refreshButton(driver).isDisplayed(), "Country dropdown is not present in the current page, Please check manually");
	}	
	
	@Test(priority = 7, groups = {"sanity"}, description=" Check and uncheck each hobby checkbox and Validate that the checkboxes are responding correctly to user interaction, allowing selection and deselection.")
	public void checkAndUncheckEachCheckBoxAndValidateThatCheckBox() throws Exception {
		RegisterPageInstance = new FormManagement_L1_Pages(driver);
		Assert.assertTrue(RegisterPageInstance.checkUncheckCheckBoxAndValidateThatCheckBox(), "Hobbies check box is not present, please check manually");	
		Assert.assertTrue(LocatorsFactoryInstance.hockeyCheckBox(driver).isDisplayed(), "Country dropdown menu is not present in the current page, Please check manually");
	}	
	
	@Test(priority = 8, groups = {"sanity"}, description="Select each radio button option for gender and Validate that only one radio button option should be selectable at a time.")
	public void selectEachRadioOptionsForGenderValidateThatOnlyOneRadioButtonShouldBeSelect() throws Exception {
		RegisterPageInstance = new FormManagement_L1_Pages(driver);
		Assert.assertTrue(RegisterPageInstance.selectRadioButtonvalidateRadioButtonOptionIsSelectable(), "Radio buttons are  not present, please check manually");	
		Assert.assertTrue(LocatorsFactoryInstance.maleradioButton(driver).isDisplayed(), "Country dropdown menu is not present in the current page, Please check manually");
	}	
	
	@Test(priority = 9, groups = {"sanity"}, description="Select different dates from the Date Of Birth fields")
	public void selectMonthYearDateInDropdown() throws Exception {
		RegisterPageInstance = new FormManagement_L1_Pages(driver);
		Assert.assertTrue(RegisterPageInstance.selectYearMonthDate(), "date,month and year dropdowns are  not present, please check manually");	
		Assert.assertTrue(LocatorsFactoryInstance.selectYearDropdown(driver).isDisplayed(), "Country dropdown menu is not present in the current page, Please check manually");
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		System.out.println("before closing the browser");
		browserTearDown();
	}
	
	@AfterMethod
	public void retryIfTestFails() throws Exception {
		startupPage.navigateToUrl(configData.get("url"));
	}
}
