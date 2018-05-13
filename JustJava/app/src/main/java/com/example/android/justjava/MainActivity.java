package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity;
    private int price = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate) {
        String message = "Name: Bender\n";
        message += "Add whipped cream? " + hasWhippedCream + "\n";
        message += "Add chocolate? " + hasChocolate + "\n";
        message += "Quantity: " + quantity + "\n";
        message += "Total " + NumberFormat.getCurrencyInstance().format(price) + "\n";
        message += "Thank you!";
        displayMessage(message);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamView = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamView.isChecked();
        Log.i("MainActivity", "Whipped cream: " + hasWhippedCream);

        CheckBox chocolateView = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateView.isChecked();
        Log.i("MainActivity", "Chocolate: " + hasChocolate);

        int price = calculatePrice();
        Log.i("MainActivity", "The Price is " + price);

        createOrderSummary(price, hasWhippedCream, hasChocolate);
    }

    private int calculatePrice() {
        return quantity * price;
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void increment(View view) {
        quantity++;
        display(quantity);
        displayPrice(quantity * price);
    }

    public void decrement(View view) {
        if (quantity > 0) quantity--;
        display(quantity);
        displayPrice(quantity * price);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("quantity", quantity);
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        outState.putString("price_text", priceTextView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        quantity = savedInstanceState.getInt("quantity", 0);
        String priceMessage = savedInstanceState.getString("price_text", "0");

        display(quantity);
        displayMessage(priceMessage);
    }
}
