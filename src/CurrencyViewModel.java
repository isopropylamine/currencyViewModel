import com.ibm.icu.text.NumberFormat;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;


/**
 * Currency view model. Handles currency formatting and displaying.
 */
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = false)
public class CurrencyViewModel {

    private final BigDecimal amount;
    private final NumberFormat numberFormat;

    // constructor Test two
    public CurrencyViewModel(double amount, Locale locale) {
        this.amount = new BigDecimal(amount);
        this.numberFormat = NumberFormat.getCurrencyInstance(locale);

        //The rounded amount will change the currency amount to one with 2 digits after the decimal
        BigDecimal roundedAmount = this.amount.setScale(2, RoundingMode.HALF_UP);

        //if the decimal amount is equal to zero, we discard the digits after the decimal
        if(roundedAmount.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0 ) {
            numberFormat.setMaximumFractionDigits(0);
        }
    }

    /**
     * Get formatted currency with currency symbols.
     *
     * @return formatted amount with currency symbols. Example:
     * <p>
     * CurrencyViewModel(5.0, Locale.US) will be output as $5.00.
     */

    @ToString.Include
    public String getAmountWithSymbols() {

        return numberFormat.format(amount);
    }
}
