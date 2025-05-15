package pages;


import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class FormManagement_L1_Pages extends StartupPage 
{
	public SoftAssert softAssert;
	/*
	 * WebElemet Declaration
	 */	
	//	TC-1&2 Locators
	By switchToNavigationMenu = By.xpath("//a[contains(text() , 'SwitchTo')]"); 
	By alertPopup = By.xpath("//a[contains(text() , 'Alerts')]"); 
//	TC-3 Locators
	By buttonToDisplayAnAlertBox = By.xpath("//button[contains(text() , 'click the button to display an  alert box:')]"); 
//	TC-4 Locators
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
//	TC-5,6
	By clickOnCountryDropdown = By.xpath("//span[@role='combobox']");
	By selectCountryAustralia = By.xpath("//li[contains(text(), 'Australia')]"); 
	By selectCountryBangladesh = By.xpath("//li[contains(text(), 'Bangladesh')]"); 
	By selectCountryDenmark = By.xpath("//li[contains(text(), 'Denmark')]"); 
	By selectCountryHongKong = By.xpath("//li[contains(text(), 'Hong Kong')]"); 
	By selectCountryIndia = By.xpath("//li[contains(text(), 'India')]"); 
	By selectCountryJapan = By.xpath("//li[contains(text(), 'Japan')]"); 

//	TC-9 Locators
	By selectYear = By.xpath("//select[@placeholder='Year']");
	By selectMonth = By.xpath("//select[@placeholder='Month']"); 
	By selectDate = By.xpath("//select[@placeholder='Day']"); 
	/*
	 * Getting the page name
	 */	
	String pageName = this.getClass().getSimpleName();


	/*
	 * constructor Initialization
	 */	
	public FormManagement_L1_Pages(WebDriver driver) 
	{
		super(driver);
	}	


	/**@Test1
	 * about this method validateTitleOfHomePage() 
	 * @param : null
	 * @description : get the title of the home page and validate
	 * @return : String
	 * @author : Yaksha
	 */
	public String validateTitleOfHomePage() throws Exception {
		String titleOfTheHomePage = "";
		try {
			titleOfTheHomePage = commonEvents.getTitle();
			System.out.println("Title of the HomePage:"+titleOfTheHomePage);
		}catch(Exception e) {
			throw e;
		}	
		return titleOfTheHomePage;
	}
	/**@Test2
	 * about this method clickOnSwitchToAlertandValidateTitlePage() 
	 * @param : null
	 * @description : click on the switch to alert and validate the title of this page
	 * @return : String
	 * @author : Yaksha
	 */
	public String  clickOnSwitchToAlertandValidateTitleOfPage() throws Exception {
		String titleOfThePage = "";
		try {
			commonEvents.click(switchToNavigationMenu);
			commonEvents.click(alertPopup);
			titleOfThePage = commonEvents.getTitle();
			System.out.println("Title of the alert page:"+titleOfThePage);
		}catch(Exception e) {
			throw e;
		}
		return titleOfThePage;
	} 

	/**@Test3
	 * about this method handleAlertsPopupValidateAnAlertsPopup() 
	 * @param : null
	 * @description : click on the ButtonToDisplayAnAlertBox then control switch in to alert popup and get the alert message and validate
	 * @return : String
	 * @author : Yaksha
	 */

	public String handleAlertsPopupAndValidateTheTextInsideAnAlertsPopup() throws Exception {
		String alertPopUpMessage = "";
		try {
			commonEvents.click(buttonToDisplayAnAlertBox);
			Alert alert = driver.switchTo().alert();
			Thread.sleep(1000);
			alertPopUpMessage =alert.getText();
			System.out.println("Alert popup Message: " + alertPopUpMessage );
			driver.switchTo().alert().accept();
		}catch(Exception e) {
			throw e;
		}
		return alertPopUpMessage;
	}

	/**@Test4
	 * about this method clickOnRegisterLinkandFillTheForms() 
	 * @param : Map<String, String>
	 * @description : Click on the register and fill data in all fields
	 * @return : boolean
	 * @author : Yaksha
	 */

	public boolean clickOnRegisterLinkandFillTheForms(Map<String, String> expectedData) throws Exception {
	    Boolean isFilled = false;
	    try {
	        commonEvents.click(registerNavigationMenu);
	        commonEvents.sendKeys(firstNameTextbox, expectedData.get("firstName"));
	        commonEvents.sendKeys(lastNameTextbox, expectedData.get("lastName"));
	        commonEvents.sendKeys(addressInputAreabox, expectedData.get("adds"));
	        commonEvents.sendKeys(emailAddressTextbox, expectedData.get("emaiI"));
	        commonEvents.sendKeys(phoneNumberTextbox, expectedData.get("phoneNo"));
	        Thread.sleep(1000);
	        commonEvents.click(maleRadioButton);
	        commonEvents.click(cricketCheckBox);
	        commonEvents.click(moviesCheckBox);
	        commonEvents.click(hockeyCheckBox);

	        // Check if all fields are filled
	        if (commonEvents.getAttribute(firstNameTextbox, "value").equals(expectedData.get("firstName")) &&
	            commonEvents.getAttribute(lastNameTextbox, "value").equals(expectedData.get("lastName")) &&
	            commonEvents.getAttribute(addressInputAreabox, "value").equals(expectedData.get("adds")) &&
	            commonEvents.getAttribute(emailAddressTextbox, "value").equals(expectedData.get("emaiI")) &&
	            commonEvents.getAttribute(phoneNumberTextbox, "value").equals(expectedData.get("phoneNo")) &&
	            commonEvents.isSelected(maleRadioButton) && // Check if radio button is selected
	            commonEvents.isSelected(cricketCheckBox) && // Check if checkbox is selected
	            commonEvents.isSelected(moviesCheckBox) && // Check if checkbox is selected
	            commonEvents.isSelected(hockeyCheckBox)) { // Check if checkbox is selected
	            isFilled = true;
	        }
	    } catch (Exception e) {
	        throw e;
	    }
	    return isFilled;
	}

	/**@Test5
	 * about this method clickOnSelectCountryAndSelectEachCountry() 
	 * @param : null
	 * @description : refresh the page and click and country drop down and select all the country from the drop down
	 * @return : boolean
	 * @author : Yaksha
	 */
	
	public boolean clickOnSelectCountryAndSelectEachCountry() throws Exception {
	    Boolean isFilled = false;
	    try {
	        System.out.println("TC 5: Starting test to select countries from dropdown");

	        boolean isDropdownDisplayed = commonEvents.isDisplayed(clickOnCountryDropdown);
	        System.out.println("Dropdown displayed: " + isDropdownDisplayed);

	        // Select Bangladesh
	        commonEvents.click(clickOnCountryDropdown);
	        System.out.println("Clicked dropdown to select 'Bangladesh'");
	        commonEvents.click(selectCountryBangladesh); 
	        boolean isBangladeshSelected = commonEvents.getText(clickOnCountryDropdown).equals("Bangladesh");
	        System.out.println("'Bangladesh' selected: " + isBangladeshSelected);

	        // Select Denmark
	        commonEvents.click(clickOnCountryDropdown);
	        System.out.println("Clicked dropdown to select 'Denmark'");
	        commonEvents.click(selectCountryDenmark);
	        boolean isDenmarkSelected = commonEvents.getText(clickOnCountryDropdown).equals("Denmark");
	        System.out.println("'Denmark' selected: " + isDenmarkSelected);

	        // Select Hong Kong
	        commonEvents.click(clickOnCountryDropdown);
	        System.out.println("Clicked dropdown to select 'Hong Kong'");
	        commonEvents.click(selectCountryHongKong);
	        boolean isHongKongSelected = commonEvents.getText(clickOnCountryDropdown).equals("Hong Kong");
	        System.out.println("'Hong Kong' selected: " + isHongKongSelected);

	        // Select Australia
	        commonEvents.click(clickOnCountryDropdown);
	        System.out.println("Clicked dropdown to select 'Australia'");
	        commonEvents.click(selectCountryAustralia); 
	        boolean isAustraliaSelected = commonEvents.getText(clickOnCountryDropdown).equals("Australia");
	        System.out.println("'Australia' selected: " + isAustraliaSelected);

	        // Final result
	        if (isDropdownDisplayed && isBangladeshSelected && isDenmarkSelected && isHongKongSelected && isAustraliaSelected) {
	            isFilled = true;
	            System.out.println("All countries selected successfully. Test passed.");
	        } else {
	            System.out.println("Some countries not selected correctly. Test failed.");
	        }

	    } catch (Exception e) {
	        System.out.println("Exception in clickOnSelectCountryAndSelectEachCountry: " + e.getMessage());
	        throw e;
	    }

	    return isFilled;
	}

	/**@Test6
	 * about this method selectEachCountryOneByOneFromCountryDrpdownAndValidate() 
	 * @param : null
	 * @description : refresh the page and click on country dropdown then select Australia and get the value and validate it 
	 * @return : String
	 * @author : Yaksha
	 */

	public String selectAustraliaInCountryDrpdownAndValidate() throws Exception {
		String getCountryNameFromCountryDropdown = "";
		try {	
			commonEvents.click(clickOnCountryDropdown);
	        commonEvents.click(selectCountryAustralia);	 
			getCountryNameFromCountryDropdown = commonEvents.getText(clickOnCountryDropdown);
			System.out.println("Fetch the selected Country Name "+ getCountryNameFromCountryDropdown);	
		}catch(Exception e) {
			throw e;
		}
		return getCountryNameFromCountryDropdown;
	}
	
	/**@Test7
	 * about this method checkUncheckCheckBoxAndValidateThatCheckBox() 
	 * @param : null
	 * @description : check all the check boxes and validate hockey check box is selected or not
	 * @return : boolean
	 * @author : Yaksha
	 */
	public boolean checkUncheckCheckBoxAndValidateThatCheckBox() throws Exception {
	    boolean areAllCheckBoxesSelected = false;

	    try {
	        System.out.println("Starting checkbox selection test...");

	        // Cricket
	        WebElement cricket = commonEvents.findElement(cricketCheckBox);
	        if (!cricket.isSelected()) {
	            commonEvents.click(cricketCheckBox);
	            System.out.println("'Cricket' checkbox was not selected. Clicking to select.");
	        } else {
	            System.out.println("'Cricket' checkbox is already selected.");
	        }

	        // Movies
	        WebElement movies = commonEvents.findElement(moviesCheckBox);
	        if (!movies.isSelected()) {
	            commonEvents.click(moviesCheckBox);
	            System.out.println("'Movies' checkbox was not selected. Clicking to select.");
	        } else {
	            System.out.println("'Movies' checkbox is already selected.");
	        }

	        // Hockey
	        WebElement hockey = commonEvents.findElement(hockeyCheckBox);
	        if (!hockey.isSelected()) {
	            commonEvents.click(hockeyCheckBox);
	            System.out.println("'Hockey' checkbox was not selected. Clicking to select.");
	        } else {
	            System.out.println("'Hockey' checkbox is already selected.");
	        }

	        // Validate final state
	        boolean isCricketCheckBoxSelected = cricket.isSelected();
	        boolean isMoviesCheckBoxSelected = movies.isSelected();
	        boolean isHockeyCheckBoxSelected = hockey.isSelected();

	        System.out.println("Cricket checkbox selected: " + isCricketCheckBoxSelected);
	        System.out.println("Movies checkbox selected: " + isMoviesCheckBoxSelected);
	        System.out.println("Hockey checkbox selected: " + isHockeyCheckBoxSelected);

	        if (isCricketCheckBoxSelected && isMoviesCheckBoxSelected && isHockeyCheckBoxSelected) {
	            areAllCheckBoxesSelected = true;
	            System.out.println("✅ All checkboxes are selected successfully.");
	        } else {
	            System.out.println("❌ One or more checkboxes are not selected.");
	        }

	    } catch (Exception e) {
	        System.out.println("❌ Exception in checkUncheckCheckBoxAndValidateThatCheckBox: " + e.getMessage());
	        throw e;
	    }

	    return areAllCheckBoxesSelected;
	}

	/**@Test8
	 * about this method selecteachRadioButtonvalidateEachRadioButtonoptionShouldBeSelectableAttime() 
	 * @param : null
	 * @description :  select each Radio Button validate Each Radio Button option Should Be selectable At time
	 * @return : boolean
	 * @author : Yaksha
	 */
	public boolean selectRadioButtonvalidateRadioButtonOptionIsSelectable() throws Exception {
	    Boolean isFemaleRadioButtonSelected = false;
	    Boolean ismaleRadioButtonSelected = false;
	    
	    try {
	        // Select the Male radio button and ensure Female radio button is not selected
	        commonEvents.click(maleRadioButton);
	        WebElement maleRadioButtonWebElement = commonEvents.findElement(maleRadioButton);
	        WebElement femaleRadioButtonWebElement = commonEvents.findElement(feMaleRadioButton);

	        // Check that Male radio button is selected and Female radio button is not selected
	        if (maleRadioButtonWebElement.isSelected() && !femaleRadioButtonWebElement.isSelected()) {
	        	ismaleRadioButtonSelected = true;
	            System.out.println("Male radio button is selected and Female radio button is not selected.");
	        }

	        Thread.sleep(2000);

	        // Select the Female radio button and ensure Male radio button is not selected
	        commonEvents.click(feMaleRadioButton);

	        // Check that Female radio button is selected and Male radio button is not selected
	        if (femaleRadioButtonWebElement.isSelected() && !maleRadioButtonWebElement.isSelected()) {
	            isFemaleRadioButtonSelected = true;
	            System.out.println("Female radio button is selected and Male radio button is not selected.");
	        }

	        Thread.sleep(2000);

	    } catch (Exception e) {
	        throw e;
	    }
	    
	    return (isFemaleRadioButtonSelected && ismaleRadioButtonSelected);
	}
	


	/**@Test9
	 * about this method selectYearMonthDate() 
	 * @param : null
	 * @description : select year,month and date in the dropdown using select class
	 * @return : return true if select year dropdown is present, else false
	 * @author : Yaksha
	 */

	public boolean selectYearMonthDate() throws Exception {
		Boolean isYeardropdownSelected =false;
		try {

			WebElement selectYeardropdown = commonEvents.findElement(selectYear);
			Select selectYear = new Select(selectYeardropdown);
			selectYear.selectByValue("1996");
			selectYeardropdown.isSelected();
			WebElement selectMonthdropdown = commonEvents.findElement(selectMonth);
			Select selectMonth = new Select(selectMonthdropdown);
			selectMonth.selectByVisibleText("June");
			selectMonthdropdown.isSelected();
			WebElement selectDatedropdown = commonEvents.findElement(selectDate);
			Select selectDate = new Select(selectDatedropdown);
			selectDate.selectByValue("25");	
			if(selectDatedropdown.isDisplayed()&& selectMonthdropdown.isDisplayed() &&   selectYeardropdown.isDisplayed()) {
				isYeardropdownSelected = true;
			}
		}catch(Exception e) {
			throw e;
		}
		return isYeardropdownSelected;
	}
}