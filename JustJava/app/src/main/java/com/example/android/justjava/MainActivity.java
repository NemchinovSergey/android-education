package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    public static final int COFFEE_PRICE = 5;
    public static final int WHIPPED_CREAM_PRICE = 1;
    public static final int CHOCOLATE_PRICE = 2;

    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name) {
        String message = "Name: " + name + "\n";
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
        EditText editText = findViewById(R.id.name_edit_text);
        String name = editText.getText().toString();

        CheckBox whippedCreamView = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamView.isChecked();
        Log.i("MainActivity", "Whipped cream: " + hasWhippedCream);

        CheckBox chocolateView = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateView.isChecked();
        Log.i("MainActivity", "Chocolate: " + hasChocolate);

        int totalPrice = calculatePrice(hasWhippedCream, hasChocolate);
        Log.i("MainActivity", "The Price is " + totalPrice);

        createOrderSummary(totalPrice, hasWhippedCream, hasChocolate, name);
    }

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int coffeePrice = COFFEE_PRICE + (hasWhippedCream ? WHIPPED_CREAM_PRICE : 0) + (hasChocolate ? CHOCOLATE_PRICE : 0);
        return quantity * coffeePrice;
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
        if (quantity >= 100) {
            Toast.makeText(this,"You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
        displayPrice(quantity * COFFEE_PRICE);
    }

    public void decrement(View view) {
        if (quantity <= 1) {
            Toast.makeText(this,"You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        display(quantity);
        displayPrice(quantity * COFFEE_PRICE);
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
