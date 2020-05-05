package com.company;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        CurrencyViewModel testCase = new CurrencyViewModel(500.006, Locale.US);
        System.out.println( testCase.getAmountWithSymbols() );

    }
}
