package com.suarkadipa.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.suarkadipa.helper.Constant;
import com.suarkadipa.helper.Helper;
import com.suarkadipa.megenepan.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by GUS ARI on 1/29/2018.
 */

public class LMSpeedUpCalculatorFragment extends SherlockFragment{
    private EditText m3m;
    private EditText m5m;
    private EditText m10m;
    private EditText m15m;
    private Button mCalculate;
    private TextView mtv3m;
    private TextView mtv5m;
    private TextView mtv10m;
    private TextView mtv15m;
    private EditText m30m;
    private TextView mtv30m;
    private EditText m60m;
    private TextView mtv60m;
    private EditText m3h;
    private TextView mtv3h;
    private EditText m8h;
    private TextView mtv8h;
    private EditText m15h;
    private TextView mtv15h;
    private EditText m24h;
    private TextView mtv24h;
    private EditText m3d;
    private TextView mtv3d;
    private EditText m7d;
    private TextView mtv7d;
    private EditText m30d;
    private TextView mtv30d;
    private TextView mtvTotal;
    private long totalSeconds;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lm_speedup_calculator,
                container, false);

        m3m = (EditText) rootView.findViewById(R.id.et3m);
        mtv3m = (TextView) rootView.findViewById(R.id.tv3m);
        m5m = (EditText) rootView.findViewById(R.id.et5m);
        mtv5m = (TextView) rootView.findViewById(R.id.tv5m);
        m10m = (EditText) rootView.findViewById(R.id.et10m);
        mtv10m = (TextView) rootView.findViewById(R.id.tv10m);
        m15m = (EditText) rootView.findViewById(R.id.et15m);
        mtv15m = (TextView) rootView.findViewById(R.id.tv15m);

        m30m = (EditText) rootView.findViewById(R.id.et30m);
        mtv30m = (TextView) rootView.findViewById(R.id.tv30m);

        m60m = (EditText) rootView.findViewById(R.id.et60m);
        mtv60m = (TextView) rootView.findViewById(R.id.tv60m);

        m3h = (EditText) rootView.findViewById(R.id.et3h);
        mtv3h = (TextView) rootView.findViewById(R.id.tv3h);

        m8h = (EditText) rootView.findViewById(R.id.et8h);
        mtv8h = (TextView) rootView.findViewById(R.id.tv8h);

        m15h = (EditText) rootView.findViewById(R.id.et15h);
        mtv15h = (TextView) rootView.findViewById(R.id.tv15h);

        m24h = (EditText) rootView.findViewById(R.id.et24h);
        mtv24h = (TextView) rootView.findViewById(R.id.tv24h);

        m3d = (EditText) rootView.findViewById(R.id.et3d);
        mtv3d = (TextView) rootView.findViewById(R.id.tv3d);

        m7d = (EditText) rootView.findViewById(R.id.et7d);
        mtv7d = (TextView) rootView.findViewById(R.id.tv7d);

        m30d = (EditText) rootView.findViewById(R.id.et30d);
        mtv30d = (TextView) rootView.findViewById(R.id.tv30d);

        mtvTotal = (TextView) rootView.findViewById(R.id.tvTotal);

        mCalculate = (Button) rootView.findViewById(R.id.btCalculate);

        mCalculate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                calculate(m3m, Constant.MINUTES_3, mtv3m);
                calculate(m5m, Constant.MINUTES_5, mtv5m);
                calculate(m10m, Constant.MINUTES_10, mtv10m);
                calculate(m15m, Constant.MINUTES_15, mtv15m);
                calculate(m30m, Constant.MINUTES_30, mtv30m);
                calculate(m60m, Constant.MINUTES_60, mtv60m);
                calculate(m3h, Constant.HOURS_3, mtv3h);
                calculate(m8h, Constant.HOURS_8, mtv8h);
                calculate(m15h, Constant.HOURS_15, mtv15h);
                calculate(m24h, Constant.HOURS_24, mtv24h);
                calculate(m3d, Constant.DAYS_3, mtv3d);
                calculate(m7d, Constant.DAYS_7, mtv7d);
                calculate(m30d, Constant.DAYS_30, mtv30d);
                calculateTotal();
            }
        });

        return rootView;
    }

    private void calculate(EditText timeInput, int timeConst, TextView result) {
        String sSource = timeInput.getText().toString();
        if(sSource.isEmpty()) {
            sSource = "0";
        }

        int nSource = Integer.parseInt(sSource);
        long seconds = nSource * timeConst;

        // add total seconds for total calculations
        totalSeconds += seconds;

        convertTime(seconds, result);
    }

    private void calculateTotal() {
        long seconds = totalSeconds;
        convertTime(seconds, mtvTotal);

        // reset total
        totalSeconds = 0;
        Helper.toastIt(getActivity(), getString(R.string.created_by));
    }

    private void convertTime(long seconds, TextView result) {
        int day = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (day *24);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);

        String sResult = " " + day + " D, " + hours + " H, " + minute + " M";
        if(result == mtv3h || result == mtv8h || result == mtv15h || result == mtv24h) {
            sResult = " " + day + " D, " + hours + " H";
        } else if (result == mtv3d || result == mtv7d || result == mtv30d) {
            sResult = " " + day + " D";
        }

        result.setText(sResult);
    }
}
