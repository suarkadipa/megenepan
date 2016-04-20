package com.suarkadipa.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockFragment;
import com.suarkadipa.helper.Helper;
import com.suarkadipa.megenepan.R;
import com.suarkadipa.model.ApplicationSetting;

public class MontlyBudgetFragment extends SherlockFragment implements OnClickListener{
	private EditText mGaji;
	private EditText mDanaSosial;
	private EditText mHutang;
	private EditText mTabunganDanInvestasi;
	private EditText mHiburan;
	private EditText mKebutuhanSehariHari;
	private String danaSosial;
	private String hutang;
	private String tabungan;
	private String kebutuhan;
	private String hiburan;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_monthly_budget,
				container, false);


		mGaji = (EditText) rootView.findViewById(R.id.etGaji);
		mDanaSosial = (EditText) rootView.findViewById(R.id.etDanaSosial);
		mHutang = (EditText) rootView.findViewById(R.id.etHutang);
		mTabunganDanInvestasi = (EditText) rootView.findViewById(R.id.etTabunganDanInvestasi);
		mKebutuhanSehariHari = (EditText) rootView.findViewById(R.id.etKebutuhanSehariHari);
		mHiburan = (EditText) rootView.findViewById(R.id.etHiburan);
		
		
		initializeEvents();
		initializeValues();
		
		return rootView;
	}

	private void initializeEvents() {
		mDanaSosial.setOnClickListener(this);
		mHutang.setOnClickListener(this);
		mTabunganDanInvestasi.setOnClickListener(this);
		mKebutuhanSehariHari.setOnClickListener(this);
		mHiburan.setOnClickListener(this);
	}

	private void initializeValues() {
		// TODO Auto-generated method stub
		int gaji = ApplicationSetting.getUserGaji(getActivity());
		mGaji.setText(String.valueOf(gaji));
		
		// 2.5% - 10 % untuk dana sosial seperti zakat dan sedekah 
		int intDanaSosialMin = (int) (gaji*(0.025));
		int intDanaSosialMax = (int) (gaji*(0.1));
		String stringDanaSosialMin = String.valueOf(intDanaSosialMin);
		String stringDanaSosialMax = String.valueOf(intDanaSosialMax);
		danaSosial = stringDanaSosialMin + " (2.5 %) -" + stringDanaSosialMax + " (10 %) untuk dana sosial seperti zakat dan sedekah.";
		mDanaSosial.setText(stringDanaSosialMin);
		
		// Max 35% untuk cicilan seluruh hutang
		int intHutang = (int) (gaji*0.35);
		String stringHutang = String.valueOf(intHutang);
		hutang = "Max " + stringHutang + " (35%) untuk cicilan seluruh hutang.";
		mHutang.setText(stringHutang);
		
		// Min 10% untuk tabungan dan investasi
		int intTabunganDanInvestasi = (int) (gaji*0.1);
		String stringTabunganDanInvestasi = String.valueOf(intTabunganDanInvestasi);
		tabungan = "Min " + stringTabunganDanInvestasi + " (10 %) untuk tabungan dan investasi.";
		mTabunganDanInvestasi.setText(stringTabunganDanInvestasi);
		
		// 40 - 60 % untuk kebutuhan sehari-hari
		int intKebutuhanMin = (int) (gaji*(0.4));
		int intKebutuhanMax = (int) (gaji*(0.6));
		String stringKebutuhanMin = String.valueOf(intKebutuhanMin);
		String stringKebutuhanMax = String.valueOf(intKebutuhanMax);
		kebutuhan = stringKebutuhanMin + " (40 %) -" + stringKebutuhanMax + " (60 %) untuk kebutuhan sehari-hari.";
		mKebutuhanSehariHari.setText(stringKebutuhanMin);
		
		// Max 10% untuk hiburan
		int intHiburan = (int) (gaji*0.1);
		String stringHiburan = String.valueOf(intHiburan);
		hiburan = "Max " + stringHiburan + " (10 %) untuk untuk hiburan.";
		mHiburan.setText(stringHiburan);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.etDanaSosial:
			Helper.toastIt(getActivity(), danaSosial);
			break;
		case R.id.etHutang:
			Helper.toastIt(getActivity(), hutang);
			break;
		case R.id.etTabunganDanInvestasi:
			Helper.toastIt(getActivity(), tabungan);
			break;
		case R.id.etKebutuhanSehariHari:
			Helper.toastIt(getActivity(), kebutuhan);
			break;
		case R.id.etHiburan:
			Helper.toastIt(getActivity(), hiburan);
			break;

		default:
			break;
		}
	}
}
