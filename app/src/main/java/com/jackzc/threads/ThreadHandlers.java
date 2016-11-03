package com.jackzc.threads;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;
import android.widget.RelativeLayout;

public class ThreadHandlers extends AppCompatActivity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            _set_label_text("Nice job Jack!");

            RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_thread);
            layout.setBackgroundColor(Color.GRAY);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
    }

    public void threadButtonClicked(View view) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long timer = System.currentTimeMillis() + 10000;
                while (System.currentTimeMillis() < timer) {
                    synchronized (this) {
                        try {
                            wait(timer - System.currentTimeMillis());
                        } catch (Exception ex) {
                        }
                    }
                }

                handler.sendEmptyMessage(0);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        this._set_label_text("Waiting for thread handler!");

    }

    private void _set_label_text(String message) {
        TextView label = (TextView) findViewById(R.id.lb_message);
        label.setText(message);
    }
}
