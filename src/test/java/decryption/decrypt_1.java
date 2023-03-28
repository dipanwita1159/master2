package decryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class decrypt_1 {

    public static WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/login");
    }

    @Test(priority = 1)
    public void decryptPasswordAndLogin() throws IOException, InterruptedException {
        // Open the input Excel file
        File inputFile = new File("C:\\Users\\DELL\\eclipse-workspace\\S.Grid\\Book.xlsx");
        FileInputStream fis = new FileInputStream(inputFile);

        // Create a Workbook object
        Workbook workbook = WorkbookFactory.create(fis);

        // Get the sheet with the user credentials
        Sheet sheet = workbook.getSheet("Sheet1");

        // Loop through all the rows in the sheet starting from the second row (index 1)
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            // Get the user id and encrypted password from the row
            Cell encryptedPasswordCell = row.getCell(0);
            String encryptedPassword = encryptedPasswordCell.getStringCellValue();
            Cell userIdCell = row.getCell(1);
            String userId = userIdCell.getStringCellValue();

            // Decode the encrypted password
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword.getBytes());
            String decryptedPassword = new String(decodedBytes);

            // Enter the user id and decrypted password in the respective fields
            WebElement userIdField = driver.findElement(By.xpath("//*[@id=\"Email\"]"));
            userIdField.sendKeys(userId);
            WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"Password\"]"));
            passwordField.sendKeys(decryptedPassword);

            // Click the login button
            WebElement loginButton = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input"));
            loginButton.click();

            // Wait for the home page to load
            Thread.sleep(5000);

            // Verify if user is logged in successfully
           // WebElement welcomeText = driver.findElement(By.id("welcomeText"));
           // String welcomeMessage = welcomeText.getText();
           // Assert.assertEquals(welcomeMessage, "Welcome " + userId + "!");

            // Logout from the application
           // WebElement logoutButton = driver.findElement(By.id("logoutButton"));
           // logoutButton.click();

            // Wait for the logout page to load
            Thread.sleep(5000);

            // Clear the fields for the next iteration
            userIdField.clear();
            passwordField.clear();
        }

        // Close the workbook and stream
        workbook.close();
        fis.close();
    }

    @AfterTest
    public void teardown() {
        // Close the browser
        driver.quit();
    }
}
