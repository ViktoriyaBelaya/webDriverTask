package com.epam.gmail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.gmail.utils.DriverUtils;

public class FilterPage extends Page{

	private final static String URL = "https://mail.google.com/mail/#settings/filters";
	
	@FindBy(xpath = "//span[@class='sA'][text()='Создать новый фильтр']")
	private WebElement buttonCreateNewFilter;
	
	@FindBy(xpath = "//input[@class='ZH nr aQa']")
	private WebElement inputFrom;
		
	@FindBy(xpath = "//input[@type='checkbox'][following-sibling::label[contains(text(),'Есть прикрепленные файлы')]]")
	private WebElement checkboxHasAttachment;
	
	/*@FindBy(xpath = "//div[@aria-label='Оператор размера']/div[@class='J-J5-Ji J-JN-M-I-JG']")
	private WebElement chooseSizeAttachment;
	
	@FindBy(xpath = "//div[contains(text(),'больше')][@class = 'J-N-Jz']")
	private WebElement moreSize;
	
	@FindBy(xpath = "//div[contains(text(),'меньше')][@class = 'J-N-Jz']")
	private WebElement lessSize;
	
	@FindBy(xpath = "//input[@type = 'text'][@aria-label='Значение размера']")
	private WebElement inputSizeAttachment;
	*/
	
	@FindBy(xpath = "//div[@class='acM']")
	private WebElement linkCreateFilterNext;
	
	@FindBy(xpath = "//input[@type='checkbox'][following-sibling::label[contains(text(),'Удалить')]]")
	private WebElement checkboxDelete;
	
	@FindBy(xpath = "//input[@type='checkbox'][following-sibling::label[contains(text(),'Всегда помечать как важное')]]")
	private WebElement checkboxMarkAsImportant;
	
	@FindBy(xpath = "//div[contains(text(),'Создать фильтр')]")
	private WebElement buttonCreateFilter;
	
	@FindBy(xpath = "//span[contains(text(),'Фильтр создан.')]")
	private WebElement messageFilterCreate; 
	
	@FindBy(xpath = "//span[@selector='all']")
	private WebElement allFilter;
	
	@FindBy(xpath = "//button[contains(text(),'Удалить')]")
	private WebElement buttobDeleteFilters;
	
	@FindBy(xpath = "//div[contains(text(),'Фильтры удалены.')]")
	private WebElement messageDeleteAllFilters;
	
	@FindBy(xpath = "//button[@name='ok']")
	private WebElement buttonOK;
	
	public void clickOK(){
		buttonOK.click();
	}
	
	public void clickCreateFilter(){
		buttonCreateFilter.click();
		DriverUtils.waitElementVisible(driver, messageFilterCreate, 100);
	}
	
	public void selectAllFilters(){
		allFilter.click();
	}
	
	public void deleteAllFilters(){
		DriverUtils.waitElementVisible(driver, buttobDeleteFilters, 100);
		buttobDeleteFilters.click();	
		DriverUtils.clickOkAlert(driver);
		DriverUtils.waitElementVisible(driver, messageDeleteAllFilters, 100);
	}
	
	public void clickCheckboxMarkAsImportant(){
		checkboxMarkAsImportant.click();
	}
	
	public void clickCheckboxDelete(){
		DriverUtils.waitElementVisible(driver, checkboxDelete, 100);
		checkboxDelete.click();
	}
	
	public void clickCheckboxHasAttachment(){
		checkboxHasAttachment.click();
	}
	
	public void clickLinkNextCreateFilter(){
		linkCreateFilterNext.click();
	}
	public void enterInputFromFilter(String user){
		DriverUtils.waitElementVisible(driver, inputFrom, 100);
		inputFrom.sendKeys(user);		
	}
	
	
	public FilterPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}
	
	public void clickCreateNewFilter(){
		DriverUtils.waitElementVisible(driver, buttonCreateNewFilter, 100);
		buttonCreateNewFilter.click();
	}

	@Override
	public void openPage() {
		driver.navigate().to(URL);
		
	}

}
