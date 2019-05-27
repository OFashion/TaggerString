package com.tonytangandroid.recycling.pager.adapter.taggerstring;

import android.os.Bundle;
import android.widget.TextView;

import com.rosevision.ofashion.util.TaggerString;

import androidx.appcompat.app.AppCompatActivity;

import static com.rosevision.ofashion.util.TaggerString.TaggerStyleType.BOLD;
import static com.rosevision.ofashion.util.TaggerString.TaggerStyleType.ITALIC;
import static com.rosevision.ofashion.util.TaggerString.TaggerStyleType.UNDERLINE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvHello = findViewById(R.id.tv_hello);
        tvHello.setText(TaggerString.from(R.string.app_name_format, this)
                .with("normal_dynamic_text", " normal dynamic text")
                .with("bold_text", "Bold Text", BOLD)
                .with("italic_text", "Italic  Text", ITALIC)
                .with("underline_text", "Underline Text", UNDERLINE)
                .formatCustom());
    }
}
