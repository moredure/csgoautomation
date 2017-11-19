package csgoautomation;

import org.openqa.selenium.chrome.ChromeDriver;
import rx.subjects.PublishSubject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class CsMoneyService extends AbstractTrader {
    private volatile String[] blacklist;

    public String[] getBlacklist() {
        return blacklist;
    }

    public String getPercent() {
        return percent;
    }

    private volatile String percent;

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public void setBlacklist(String[] blacklist) {
        this.blacklist = blacklist;
    }

    public CsMoneyService(String[] blacklist, String percent) {
        this.blacklist = blacklist;
        this.percent = percent;
    }

    @Override
    public void run() {
        while(true) {
            checkLock();
            try {
                Thread.sleep(4000);
            } catch(Exception e) {
                e.printStackTrace();
            }
            System.out.println("fuck");
//            if(Thread.currentThread().isInterrupted()) {
//                CsGoAutomation.emptyCsMoney.onNext("Fuck");
//                return;
//            }
        }
    }
}
