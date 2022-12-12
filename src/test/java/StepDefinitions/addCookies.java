package StepDefinitions;

import com.opencsv.CSVReader;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class addCookies {

    public static void addAllCookies(WebDriver driver) throws Exception {

        Reader reader = Files.newBufferedReader(Paths.get("/Users/inaco/Downloads/CucumberTestNG/src/test/resources/cookies.csv"));
        CSVReader csvReader = new CSVReader(reader);
        String[] nextLine;
        while ((nextLine = csvReader.readNext()) != null) {
            addCookie(nextLine[0], nextLine[1],driver);
        }
    }
    public static void addCookie(String name, String value,WebDriver driver) {
        driver.manage().addCookie(new Cookie(name, value));
    }
}
