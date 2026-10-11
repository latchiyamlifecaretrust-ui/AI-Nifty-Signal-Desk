package in.latchiyamlifecare.niftysignaldesk;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ScrollView;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(24, 32, 24, 24);
        page.setBackgroundColor(Color.rgb(12, 20, 36));

        TextView title = new TextView(this);
        title.setText("AI NIFTY SIGNAL DESK");
        title.setTextSize(24);
        title.setTextColor(Color.WHITE);
        title.setGravity(Gravity.CENTER);
        page.addView(title);

        TextView status = new TextView(this);
        status.setText("\n15-Minute Chart\n\nMarket Data: Not Connected\n\nVWAP + EMA 9/21 + RSI\n\nMode: PAPER TRADING\n\nNo live orders are placed.\n\nWait for verified market data.");
        status.setTextSize(18);
        status.setTextColor(Color.WHITE);
        page.addView(status);

        ScrollView scroll = new ScrollView(this);
        scroll.addView(page);
        setContentView(scroll);
    }
}