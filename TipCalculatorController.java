import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;




public class TipCalculatorController {
    //formatters for currency and percentages
    private static final NumberFormat currency =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent =
            NumberFormat.getPercentInstance();
    
    private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default

    @FXML
    private TextField amountTextField;
    
    @FXML
    private Label tipPercentageLabel;
    
    @FXML
    private Slider tipPercentageSlider;
    
    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;
    
    //caluclates and displays the tip and total amounts
    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        try{
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);
            
            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        }
        catch(NumberFormatException ex){
            amountTextField.setText("Enter amount");
            amountTextField.selectAll();
            amountTextField.requestFocus();
        }
    }

    public void initialize() {
        /*
        assert tipTextField != null : "fx:id=\"tipTextField\" was not injected: check your FXML file 'TipCalculator.fxml'.";
        assert tipPercentageSlider != null : "fx:id=\"tipPercentageSlider\" was not injected: check your FXML file 'TipCalculator.fxml'.";
        assert amountTextField != null : "fx:id=\"amountTextField\" was not injected: check your FXML file 'TipCalculator.fxml'.";
        assert tipPercentageLabel != null : "fx:id=\"tipPercentageTotal\" was not injected: check your FXML file 'TipCalculator.fxml'.";
        assert totalTextField != null : "fx:id=\"totalTextField\" was not injected: check your FXML file 'TipCalculator.fxml'.";
        */

        //0-4rounds down, 5-9 rounds up
        currency.setRoundingMode(RoundingMode.HALF_UP);
        
        // listener for changes to tipPercentageSlider's value
        tipPercentageSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                        Number oldValue, Number newValue) {
                        tipPercentage =
                            BigDecimal.valueOf(newValue.intValue() / 100.0);
                        tipPercentageLabel.setText(percent.format(tipPercentage));
                    }
                }
        );
    }
}
