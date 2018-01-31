package com.suarkadipa.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.suarkadipa.helper.Constant;
import com.suarkadipa.helper.Helper;
import com.suarkadipa.megenepan.R;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by GUS ARI on 1/30/2018.
 */

public class LMResourcesCalculatorFragment extends SherlockFragment {
    private TextView mtv5k;
    private EditText m5k;
    private EditText m10k;
    private TextView mtv10k;
    private EditText m12k;
    private TextView mtv12k;
    private EditText m15k;
    private TextView mtv15k;
    private EditText m20k;
    private TextView mtv20k;
    private EditText m30k;
    private TextView mtv30k;
    private EditText m35k;
    private TextView mtv35k;
    private EditText m150k;
    private TextView mtv150k;
    private EditText m500k;
    private TextView mtv500k;
    private EditText m2m;
    private TextView mtv2m;
    private EditText m6m;
    private TextView mtv6m;
    private TextView mtvTotal;
    private Button mCalculate;
    private long totalResources;
    private EditText m1p5k;
    private TextView mtv1p5k;
    private EditText m3k;
    private TextView mtv3k;
    private EditText m4k;
    private TextView mtv4k;
    private EditText m6k;
    private TextView mtv6k;
    private EditText m7k;
    private TextView mtv7k;
    private EditText m9k;
    private TextView mtv9k;
    private EditText m600k;
    private TextView mtv600k;
    private EditText m50k;
    private TextView mtv50k;
    private EditText m200k;
    private TextView mtv200k;
    private EditText m25k;
    private TextView mtv25k;
    private EditText m1p5m;
    private TextView mtv1p5m;
    private EditText m5m;
    private TextView mtv5m;
    private EditText m15m;
    private TextView mtv15m;
    private EditText m300;
    private TextView mtv300;
    private EditText m3p5k;
    private TextView mtv3p5k;
    private EditText m2k;
    private TextView mtv2k;
    private EditText m2p5k;
    private TextView mtv2p5k;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lm_resources_calculator,
                container, false);

        m300 = (EditText) rootView.findViewById(R.id.et300);
        mtv300 = (TextView) rootView.findViewById(R.id.tv300);

        m1p5k = (EditText) rootView.findViewById(R.id.et1p5k);
        mtv1p5k = (TextView) rootView.findViewById(R.id.tv1p5k);

        m2k = (EditText) rootView.findViewById(R.id.et2k);
        mtv2k = (TextView) rootView.findViewById(R.id.tv2k);

        m2p5k = (EditText) rootView.findViewById(R.id.et2p5k);
        mtv2p5k = (TextView) rootView.findViewById(R.id.tv2p5k);

        m3k = (EditText) rootView.findViewById(R.id.et3k);
        mtv3k = (TextView) rootView.findViewById(R.id.tv3k);

        m3p5k = (EditText) rootView.findViewById(R.id.et3p5k);
        mtv3p5k = (TextView) rootView.findViewById(R.id.tv3p5k);

        m4k = (EditText) rootView.findViewById(R.id.et4k);
        mtv4k = (TextView) rootView.findViewById(R.id.tv4k);

        m5k = (EditText) rootView.findViewById(R.id.et5k);
        mtv5k = (TextView) rootView.findViewById(R.id.tv5k);

        m6k = (EditText) rootView.findViewById(R.id.et6k);
        mtv6k = (TextView) rootView.findViewById(R.id.tv6k);

        m7k = (EditText) rootView.findViewById(R.id.et7k);
        mtv7k = (TextView) rootView.findViewById(R.id.tv7k);

        m9k = (EditText) rootView.findViewById(R.id.et9k);
        mtv9k = (TextView) rootView.findViewById(R.id.tv9k);

        m10k = (EditText) rootView.findViewById(R.id.et10k);
        mtv10k = (TextView) rootView.findViewById(R.id.tv10k);

        m12k = (EditText) rootView.findViewById(R.id.et12k);
        mtv12k = (TextView) rootView.findViewById(R.id.tv12k);

        m15k = (EditText) rootView.findViewById(R.id.et15k);
        mtv15k = (TextView) rootView.findViewById(R.id.tv15k);

        m20k = (EditText) rootView.findViewById(R.id.et20k);
        mtv20k = (TextView) rootView.findViewById(R.id.tv20k);

        m25k = (EditText) rootView.findViewById(R.id.et25k);
        mtv25k = (TextView) rootView.findViewById(R.id.tv25k);

        m30k = (EditText) rootView.findViewById(R.id.et30k);
        mtv30k = (TextView) rootView.findViewById(R.id.tv30k);

        m35k = (EditText) rootView.findViewById(R.id.et35k);
        mtv35k = (TextView) rootView.findViewById(R.id.tv35k);

        m50k = (EditText) rootView.findViewById(R.id.et50k);
        mtv50k = (TextView) rootView.findViewById(R.id.tv50k);

        m150k = (EditText) rootView.findViewById(R.id.et150k);
        mtv150k = (TextView) rootView.findViewById(R.id.tv150k);

        m200k = (EditText) rootView.findViewById(R.id.et200k);
        mtv200k = (TextView) rootView.findViewById(R.id.tv200k);

        m500k = (EditText) rootView.findViewById(R.id.et500k);
        mtv500k = (TextView) rootView.findViewById(R.id.tv500k);

        m600k = (EditText) rootView.findViewById(R.id.et600k);
        mtv600k = (TextView) rootView.findViewById(R.id.tv600k);

        m1p5m = (EditText) rootView.findViewById(R.id.et1p5m);
        mtv1p5m = (TextView) rootView.findViewById(R.id.tv1p5m);

        m2m = (EditText) rootView.findViewById(R.id.et2m);
        mtv2m = (TextView) rootView.findViewById(R.id.tv2m);

        m5m = (EditText) rootView.findViewById(R.id.et5m);
        mtv5m = (TextView) rootView.findViewById(R.id.tv5m);

        m6m = (EditText) rootView.findViewById(R.id.et6m);
        mtv6m = (TextView) rootView.findViewById(R.id.tv6m);

        m15m = (EditText) rootView.findViewById(R.id.et15m);
        mtv15m = (TextView) rootView.findViewById(R.id.tv15m);
        
        mtvTotal = (TextView) rootView.findViewById(R.id.tvTotal);

        mCalculate = (Button) rootView.findViewById(R.id.btCalculate);

        mCalculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                calculate(m300, Constant.RSS_300, mtv300);
                calculate(m1p5k, Constant.RSS_1p5K, mtv1p5k);
                calculate(m2k, Constant.RSS_2K, mtv2k);
                calculate(m2p5k, Constant.RSS_2p5K, mtv2p5k);
                calculate(m3k, Constant.RSS_3K, mtv3k);
                calculate(m3p5k, Constant.RSS_3p5K, mtv3p5k);
                calculate(m4k, Constant.RSS_4K, mtv4k);
                calculate(m5k, Constant.RSS_5K, mtv5k);
                calculate(m6k, Constant.RSS_6K, mtv6k);
                calculate(m7k, Constant.RSS_7K, mtv7k);
                calculate(m9k, Constant.RSS_9K, mtv9k);
                calculate(m10k, Constant.RSS_10K, mtv10k);
                calculate(m12k, Constant.RSS_12K, mtv12k);
                calculate(m15k, Constant.RSS_15K, mtv15k);
                calculate(m20k, Constant.RSS_20K, mtv20k);
                calculate(m25k, Constant.RSS_25K, mtv25k);
                calculate(m30k, Constant.RSS_30K, mtv30k);
                calculate(m35k, Constant.RSS_35K, mtv35k);
                calculate(m50k, Constant.RSS_50K, mtv50k);
                calculate(m150k, Constant.RSS_150K, mtv150k);
                calculate(m200k, Constant.RSS_200K, mtv200k);
                calculate(m500k, Constant.RSS_500K, mtv500k);
                calculate(m600k, Constant.RSS_600K, mtv600k);
                calculate(m1p5m, Constant.RSS_1P5M, mtv1p5m);
                calculate(m2m, Constant.RSS_2M, mtv2m);
                calculate(m5m, Constant.RSS_5M, mtv5m);
                calculate(m6m, Constant.RSS_6M, mtv6m);
                calculate(m15m, Constant.RSS_15M, mtv15m);
                calculateTotal();
            }
        });
        
        return rootView;
    }

    private void calculate(EditText rssInput, int Const, TextView result) {
        String sSource = rssInput.getText().toString();
        if(sSource.isEmpty()) {
            sSource = "0";
        }

        int nSource = Integer.parseInt(sSource);
        int resources = nSource * Const;

        // add total seconds for total calculations
        totalResources += resources;

        convertUnits(resources, result);
    }

    private void calculateTotal() {
        long resources = totalResources;
        convertUnits(resources, mtvTotal);

        // reset total
        totalResources = 0;
        Helper.toastIt(getActivity(), getString(R.string.created_by));
    }

    private void convertUnits(long resources, TextView result) {
        String sResult = " " + NumberFormat.getNumberInstance(Locale.US).format(resources);

        result.setText(sResult);
    }
}
