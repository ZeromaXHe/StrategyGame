package com.zerox.viewController;

import com.zerox.controller.MainController;
import com.zerox.entity.Result;
import com.zerox.model.FinancialAccount;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Author: zhuxi
 * @Time: 2021/10/15 15:58
 * @Description: mvc体系下的ViewController，和Spring三层体系下的后端Controller区分开来
 * 参考：https://edencoding.com/mvc-in-javafx/
 * @ModifiedBy: zhuxi
 */
@Controller("MainViewController")
public class MainViewController {
    // Back-end Controller
    private MainController mainController;

    @Autowired
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    //Model
    private FinancialAccount account;
    //View nodes
    @FXML
    private Label accountHolder;
    @FXML
    private Label accountNumber;
    @FXML
    private Label accountBalance;
    @FXML
    private TextField amountTextField;

    public void initialize() {
        Result<String> result = mainController.getAccountHolderName();
        String body = result.getBody();
        String accountHolderName = body == null ? "unknown" : body;
        //get model
        account = new FinancialAccount(accountHolderName, 6626, 1000d);
        //link Model with View
        accountHolder.textProperty().bind(account.accountHolderProperty());
        accountBalance.textProperty().bind(account.accountBalanceProperty().asString());
        accountNumber.textProperty().bind(account.accountNumberProperty().asString());
        //link Controller to View - ensure only numeric input (integers) in text field
        amountTextField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getText().matches("\\d+") || change.getText().equals("")) {
                return change;
            } else {
                change.setText("");
                change.setRange(
                        change.getRangeStart(),
                        change.getRangeStart()
                );
                return change;
            }
        }));
    }

    @FXML
    private void handleDeposit(Event event) {
        account.deposit(getAmount());
        event.consume();
    }

    @FXML
    private void handleWithdrawal(Event event) {
        account.withdraw(getAmount());
        event.consume();
    }

    private double getAmount() {
        if (amountTextField.getText().equals("")) return 0;
        return Double.parseDouble(amountTextField.getText());
    }
}
