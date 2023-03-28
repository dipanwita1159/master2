package decryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class encript {

    @Test(priority = 1)
    public void encryptData() throws IOException {
        // Open the input Excel file
        File inputFile = new File("C:\\Users\\DELL\\eclipse-workspace\\S.Grid\\Book.xlsx");
        FileInputStream fis = new FileInputStream(inputFile);

        // Create a Workbook object
        Workbook workbook = WorkbookFactory.create(fis);

        // Get the sheet with the data to encrypt
        Sheet sheet = workbook.getSheet("Sheet1");

        // Loop through all the rows in the sheet
        for (Row row : sheet) {
            // Get the cell in column 1
            Cell cell = row.getCell(0);
            
            // Check if the cell is not null and not empty
            if (cell != null && !cell.getStringCellValue().isEmpty()) {
                // Get the cell value
                String dataToEncrypt = cell.getStringCellValue();

                // Encode the data
                byte[] encodedBytes = Base64.getEncoder().encode(dataToEncrypt.getBytes());
                String encryptedData = new String(encodedBytes);

                // Write the encrypted data back to the same cell
                cell.setCellValue(encryptedData);
            }
        }

        // Close the workbook and stream
        workbook.write(new FileOutputStream(inputFile));
        workbook.close();
        fis.close();
    }
}
