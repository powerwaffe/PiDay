package edu.dtcc.spaol1.piday;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    // UI TextView and Count object
    private TextView countTextView;
    private Integer count;

    // PI variable and string variant of it for substring usage
    Double PI = Math.PI;
    String PIString = PI.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference the TextView UI element on the layout
        countTextView = (TextView) findViewById(R.id.piCounter);

        // Initialize the counter
        count = 0;

        // Create a thread and start it
        Thread thread = new Thread(countNumbers);
        thread.start();
    }

    // Initialize the counter to zero each time the application launches
    @Override
    protected void onStart()
    {
        super.onStart();
        count = 0;
    }

    // Thread counter
    private Runnable countNumbers = new Runnable()
    {
        private static final int DELAY = 1000;
        @Override
        public void run()
        {
            try
            {
                while (count < 16)
                {
                    count++;
                    Thread.sleep(DELAY);
                    threadHandler.sendEmptyMessage(0);
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    };

    // Thread handler
    public Handler threadHandler = new Handler()
    {
        public void handleMessage (android.os.Message message)
        {
            // Display PI
            countTextView.setText(PIString.substring(0, count));
        }
    };
}
