package csgoautomation;

import csgoautomation.CsGoAutomation;
import csgoautomation.CsGoService;
import csgoautomation.CsMoneyService;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Main extends Thread {
    public static void main(String[] args) {
//        Drivers.csGoDriver.get("https://google.com");
        JFrame frame = new JFrame("CsGoAutomation");
        frame.setContentPane(new CsGoAutomation().getWindow());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
