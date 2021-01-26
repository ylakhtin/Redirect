import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import java.io.FileNotFoundException;

import static java.lang.System.*;

public class Redirect {
    private ChromeDriver driver;
    private String site = "https://publish-p17402-e70832.adobeaemcloud.com/";
    private String target = "us/en/home.html";
    private ArrayList<String> extra;

    @BeforeMethod
    public void start(){
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void close(){
        driver.quit();
    }

    @Test
    public void get() throws Exception {
        String s = "";
        int correct=0;
        int incorrect=0;

        ArrayList<String> extra = new ArrayList<>();
        try {
            File myFile = new File("input.txt");
            Scanner fileReader = new Scanner(myFile);
            while (fileReader.hasNextLine()) {
                extra.add(fileReader.nextLine());
            }
            fileReader.close();
        } catch (FileNotFoundException z) {
            out.println("File read error");
        }

        for (int i = 0; i < extra.size(); i++) {
            s = extra.get(i);
            driver.get(site + s);
            if (driver.getCurrentUrl().contains(site + target)) {
                System.out.println("Success. Redirection works fine for " + site + s);
                correct++;
            } else {
                System.out.println("Fail. " + site + s + " URL doesn't match the expected result");
                incorrect++;
            }
        }
        System.out.println("Passed: " + correct + " Failed:" + incorrect);

    }
}
