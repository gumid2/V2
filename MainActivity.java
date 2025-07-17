
package com.emocionador;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.view.Gravity;

import java.util.Locale;

public class MainActivity extends Activity {

    private TextToSpeech tts;
    private String estadoEmocional = "felicidad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.BLACK);
        layout.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.anime_face);
        layout.addView(imageView);

        TextView textView = new TextView(this);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setText("Hola... me siento muy feliz hoy.");
        layout.addView(textView);

        setContentView(layout, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(new Locale("es", "ES"));
                tts.speak("Hola, me siento muy feliz hoy.", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
