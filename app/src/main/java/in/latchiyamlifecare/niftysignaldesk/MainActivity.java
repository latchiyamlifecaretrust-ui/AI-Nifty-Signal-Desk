
package in.latchiyamlifecare.niftysignaldesk;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.ViewGroup;

public class MainActivity extends Activity {

    private final int BG = Color.rgb(10, 15, 28);
    private final int CARD = Color.rgb(20, 28, 44);
    private final int WHITE = Color.WHITE;
    private final int MUTED = Color.rgb(155, 170, 190);
    private final int GREEN = Color.rgb(46, 204, 113);
    private final int YELLOW = Color.rgb(255, 193, 7);
    private LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(BG);
        getWindow().setNavigationBarColor(BG);
        getWindow().getDecorView().setSystemUiVisibility(0);

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(BG);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(16), dp(18), dp(16), dp(25));
        root.setBackgroundColor(BG);

        scroll.addView(root);
        setContentView(scroll);

        addTitle("AI NIFTY SIGNAL DESK", 24, WHITE, true);
        addTitle("SMART MARKET DASHBOARD  |  PAPER MODE",
                12, MUTED, false);

        addGap(18);

        LinearLayout market = makeCard();
        addTo(market, "NIFTY 50", 17, WHITE, true);
        addTo(market, "--", 34, WHITE, true);
        addTo(market, "● MARKET DATA NOT CONNECTED",
                13, YELLOW, true);
        addTo(market, "Waiting for verified market data",
                12, MUTED, false);
        root.addView(market, params());

        addSection("15-MINUTE CHART");

        LinearLayout chartCard = makeCard();
        ChartView chart = new ChartView();
        chartCard.addView(chart, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dp(230)));
        addTo(chartCard, "Candlestick data not connected",
                12, MUTED, false);
        root.addView(chartCard, params());

        addSection("TECHNICAL INDICATORS");

        LinearLayout indicators = makeCard();
        addRow(indicators, "VWAP", "Waiting for data");
        addRow(indicators, "EMA 9", "Waiting for data");
        addRow(indicators, "EMA 21", "Waiting for data");
        addRow(indicators, "RSI (14)", "Waiting for data");
        root.addView(indicators, params());

        addSection("AI SIGNAL");

        LinearLayout signal = makeCard();
        TextView wait = makeText("WAIT", 32, YELLOW, true);
        wait.setGravity(Gravity.CENTER);
        signal.addView(wait, params());

        TextView reason = makeText(
                "Verified market data is required.",
                13, MUTED, false);
        reason.setGravity(Gravity.CENTER);
        signal.addView(reason, params());

        TextView mode = makeText(
                "PAPER TRADING  |  LIVE ORDERS DISABLED",
                12, GREEN, true);
        mode.setGravity(Gravity.CENTER);
        mode.setPadding(0, dp(12), 0, 0);
        signal.addView(mode, params());
        root.addView(signal, params());

        addSection("PAPER TRADE LEVELS");

        LinearLayout levels = makeCard();
        addRow(levels, "Entry", "--");
        addRow(levels, "Stop-loss", "--");
        addRow(levels, "Target", "--");
        addRow(levels, "Quantity", "0");
        root.addView(levels, params());

        addSection("VIRTUAL PROFIT & LOSS");

        LinearLayout pnl = makeCard();
        TextView amount = makeText("₹0.00", 30, WHITE, true);
        amount.setGravity(Gravity.CENTER);
        pnl.addView(amount, params());

        TextView note = makeText(
                "No virtual trade opened.",
                12, MUTED, false);
        note.setGravity(Gravity.CENTER);
        pnl.addView(note, params());
        root.addView(pnl, params());

        addGap(16);
        addTitle("DATA: DISCONNECTED", 12, MUTED, true);
        addTitle("No real money trades are placed.", 12, GREEN, false);
    }

    private int dp(float value) {
        return (int) (value * getResources()
                .getDisplayMetrics().density + 0.5f);
    }

    private TextView makeText(String value, int size,
                              int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setPadding(0, dp(5), 0, dp(5));
        if (bold) {
            t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        }
        return t;
    }

    private void addTitle(String value, int size,
                          int color, boolean bold) {
        TextView t = makeText(value, size, color, bold);
        t.setGravity(Gravity.CENTER);
        root.addView(t, params());
    }

    private LinearLayout makeCard() {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(dp(15), dp(13), dp(15), dp(13));

        android.graphics.drawable.GradientDrawable bg =
                new android.graphics.drawable.GradientDrawable();
        bg.setColor(CARD);
        bg.setCornerRadius(dp(14));
        box.setBackground(bg);
        return box;
    }

    private void addTo(LinearLayout parent, String value,
                       int size, int color, boolean bold) {
        parent.addView(makeText(value, size, color, bold), params());
    }

    private void addSection(String value) {
        addGap(8);
        TextView t = makeText(value, 13, MUTED, true);
        root.addView(t, params());
        addGap(4);
    }

    private void addGap(int size) {
        View gap = new View(this);
        root.addView(gap, new LinearLayout.LayoutParams(1, dp(size)));
    }

    private LinearLayout.LayoutParams params() {
        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        p.bottomMargin = dp(9);
        return p;
    }

    private void addRow(LinearLayout parent,
                        String name, String value) {
        LinearLayout row = new LinearLayout(this);
        row.setGravity(Gravity.CENTER_VERTICAL);

        TextView left = makeText(name, 14, MUTED, false);
        row.addView(left, new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        TextView right = makeText(value, 13, WHITE, true);
        right.setGravity(Gravity.RIGHT);
        row.addView(right);

        parent.addView(row, params());
    }

    private class ChartView extends View {
        private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        ChartView() {
            super(MainActivity.this);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(CARD);

            paint.setColor(Color.rgb(45, 57, 76));
            paint.setStrokeWidth(dp(1));

            for (int i = 0; i < 6; i++) {
                float y = dp(15) + i * (getHeight() - dp(30)) / 5f;
                canvas.drawLine(dp(5), y, getWidth() - dp(5), y, paint);
            }

            for (int i = 0; i < 7; i++) {
                float x = dp(5) + i * (getWidth() - dp(10)) / 6f;
                canvas.drawLine(x, dp(10), x,
                        getHeight() - dp(10), paint);
            }

            paint.setColor(MUTED);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(dp(14));
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("15-MINUTE CHART",
                    getWidth() / 2f, getHeight() / 2f - dp(8), paint);

            paint.setTextSize(dp(11));
            paint.setTypeface(Typeface.DEFAULT);
            canvas.drawText("Waiting for market data",
                    getWidth() / 2f, getHeight() / 2f + dp(15), paint);
        }
    }
}
