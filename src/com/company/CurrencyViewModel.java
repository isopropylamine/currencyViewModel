package com.company;

import com.ibm.icu.text.NumberFormat;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Locale;


/**
 * Currency view model. Handles currency formatting and displaying.
 */

@ToString(onlyExplicitlyIncluded = true, includeFieldNames = false)
public class CurrencyViewModel {

    private final BigDecimal amount;
    private final NumberFormat numberFormat;


    public CurrencyViewModel(double amount, Locale locale) {
        this.amount = new BigDecimal(amount);
        this.numberFormat = NumberFormat.getCurrencyInstance(locale);
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

        //if the decimal amount is less than 0.005, then the maximum fraction digits will be discarded
        if(amount.remainder(BigDecimal.ONE).compareTo(BigDecimal.valueOf(0.005)) == -1 ) {
            numberFormat.setMaximumFractionDigits(0);
        }

        return numberFormat.format(amount);


    }
}
