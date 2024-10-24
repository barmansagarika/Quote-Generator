package com.example.randomquote;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView quoteTextView;
    private Button generateButton;
    private SharedPreferences sharedPreferences;
    private static final String QUOTES_KEY = "quotes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteTextView = findViewById(R.id.quoteTextView);
        generateButton = findViewById(R.id.generateButton);
        sharedPreferences = getSharedPreferences("Random quote", MODE_PRIVATE);

        String quotes = loadQuotes();
        if (quotes.isEmpty()) {
            quotes = getDefaultQuotes();
            saveQuotes(quotes);
        }

        final String[] quoteArray = quotes.split("\\|");

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateQuote(quoteArray);
            }
        });
    }

    private void generateQuote(String[] quotes) {
        Random random = new Random();
        int index = random.nextInt(quotes.length);
        String randomQuote = quotes[index];
        quoteTextView.setText(randomQuote);
    }

    private String loadQuotes() {

        return sharedPreferences.getString(QUOTES_KEY, "");
    }

    private void saveQuotes(String quotes) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(QUOTES_KEY, quotes);
        editor.apply();
    }

    private String getDefaultQuotes() {

        return "The only way to do great work is to love what you do. – Steve Jobs|" +
                "Innovation distinguishes between a leader and a follower. – Steve Jobs|" +
                "The future belongs to those who believe in the beauty of their dreams. – Eleanor Roosevelt|" +
                "It always seems impossible until it is done. – Nelson Mandela|" +
                "Believe you can and you're halfway there. – Theodore Roosevelt|" +
                "The only limit to our realization of tomorrow will be our doubts of today. – Franklin D. Roosevelt|" +
                "Strive not to be a success, but rather to be of value. – Albert Einstein|" +
                "Your time is limited, don't waste it living someone else's life. – Steve Jobs|" +
                "The best way to predict the future is to invent it. – Alan Kay|" +
                "Success is not final, failure is not fatal: It is the courage to continue that counts. – Winston Churchill";
    }
}
