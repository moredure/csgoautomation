package csgoautomation;

import oracle.jvm.hotspot.jfr.JFR;
import rx.Subscriber;
import rx.subjects.PublishSubject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicBoolean;

public class CsGoAutomation {
    private boolean csGoEnabled;
    private boolean csMoneyEnabled;
    private JButton updateCsMoneyPercentButton;
    private JPanel window;
    private JButton startCsGoButton;
    private JButton startCsMoneyButton;
    private JButton updateCsGoPercentButton;
    private JButton updateBlacklistsButton;
    private JTextField csGoPercentArea;
    private JTextField csMoneyPercentArea;
    private JTextArea blacklistArea;
    private CsGoService csGo;
    private CsMoneyService csMoney;
    static PublishSubject<String> emptyCsGo = PublishSubject.create();
    static PublishSubject<String> emptyCsMoney = PublishSubject.create();
    private JFrame frame;
    public JPanel getWindow() {
        return window;
    }

    public CsGoAutomation() {
        String[] blacklist = new String[]{};
        csGo  = new CsGoService(blacklist, "0%");
        csGo.start();
        csMoney = new CsMoneyService(blacklist, "0%");
        csMoney.start();
        emptyCsGo.subscribe(this::onEmptyCsGoBalance);
        emptyCsMoney.subscribe(this::onEmptyCsMoneyBalance);
        startCsGoButton.addActionListener(this::onClickCsGoButton);
        startCsMoneyButton.addActionListener(this::onClickCsMoneyButton);
        updateCsGoPercentButton.addActionListener(this::onCsGoPercentUpdate);
        updateCsMoneyPercentButton.addActionListener(this::onCsMoneyPercentUpdate);
        updateBlacklistsButton.addActionListener(this::onBlacklistUpdate);
    }

    private void onEmptyCsGoBalance(String message) {
        SwingUtilities.invokeLater(() -> {
            startCsGoButton.setText("Start Cs Go");
            JOptionPane.showMessageDialog(null, message);
        });
    }

    private void onEmptyCsMoneyBalance(String message) {
        SwingUtilities.invokeLater(() -> {
            startCsMoneyButton.setText("Start Cs Money");
            JOptionPane.showMessageDialog(null, message);
        });
    }

    private void onClickCsGoButton(ActionEvent event) {
        startCsGoButton.setEnabled(false);
        new Thread(() -> {
            if (!csGo.isPaused()) {
                csGo.pause();
                startCsGoButton.setText("Start Cs Go");
            } else {
                csGo.go();
                startCsGoButton.setText("Stop Cs Go");
            }
            startCsGoButton.setEnabled(true);
        }).start();
    }

    private void onClickCsMoneyButton(ActionEvent e) {
        startCsMoneyButton.setEnabled(false);
        new Thread(() -> {
            if (!csMoney.isPaused()) {
                csMoney.pause();
                startCsMoneyButton.setText("Start Cs Money");
            } else {
                csMoney.go();
                startCsMoneyButton.setText("Stop Cs Money");
            }
            startCsMoneyButton.setEnabled(true);
        }).start();
    }

    private void onBlacklistUpdate(ActionEvent e) {
        String[] blackList = getBlacklistAreaText();
        csGo.setBlacklist(blackList);
        csMoney.setBlacklist(blackList);
    }

    private String[] getBlacklistAreaText() {
        return blacklistArea.getText().split("\n");
    }

    private void onCsGoPercentUpdate(ActionEvent e) {
        csGo.setPercent(csGoPercentArea.getText());
    }

    private void onCsMoneyPercentUpdate(ActionEvent e) {
        csMoney.setPercent(csGoPercentArea.getText());
    }
}
