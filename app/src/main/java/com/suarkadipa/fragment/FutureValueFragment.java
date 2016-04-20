package com.suarkadipa.fragment;

import java.text.NumberFormat;
import java.util.Locale;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.suarkadipa.megenepan.R;

public class FutureValueFragment extends SherlockFragment implements ValidationListener {
	
	@Required(order = 1, message = "Nilai sekarang diperlukan.")
	private EditText mNilaiSekarang;

	@Required(order = 2, message = "Suku bunga diperlukan.")
	private EditText mSukuBunga;

	@Required(order = 3, message = "Lama investasi diperlukan.")
	private EditText mWaktu;
	private Button mHitung;
	private TextView mHasil;
	private boolean hasError = false;

	private Validator validator;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_future_value, container, false);
		
		mNilaiSekarang = (EditText) rootView.findViewById(R.id.editTextNilaiSekarang);
		mSukuBunga = (EditText) rootView.findViewById(R.id.editTextSukuBunga);
		mWaktu = (EditText) rootView.findViewById(R.id.editTextWaktu);
		mHitung = (Button) rootView.findViewById(R.id.buttonHitung);
		mHasil = (TextView) rootView.findViewById(R.id.textViewHasil);

		mNilaiSekarang.addTextChangedListener(new TextWatcher() {

			boolean isManualChange = false;

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (isManualChange) {
					isManualChange = false;
					return;
				}

				try {
					String value = s.toString().replace(",", "");
					String reverseValue = new StringBuilder(value).reverse()
							.toString();
					StringBuilder finalValue = new StringBuilder();
					for (int i = 1; i <= reverseValue.length(); i++) {
						char val = reverseValue.charAt(i - 1);
						finalValue.append(val);
						if (i % 3 == 0 && i != reverseValue.length() && i > 0) {
							finalValue.append(",");
						}
					}
					isManualChange = true;
					mNilaiSekarang.setText(finalValue.reverse());
					mNilaiSekarang.setSelection(finalValue.length());
				} catch (Exception e) {
					// Do nothing since not a number
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

		});

		// instantiate validator
		validator = new Validator(this);
		validator.setValidationListener(this);
		
		mWaktu.setOnEditorActionListener(new OnEditorActionListener() {        
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				if(actionId==EditorInfo.IME_ACTION_DONE){
		            calculate();
		        }
				return false;
			}
		});

		mHitung.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				validator.validate();
				if (!hasError) {
					calculate();
				}
			}
		});
		
		return rootView;
	}
	
	protected void calculate() {
		String nilaiSkrg = mNilaiSekarang
				.getText().toString();
		String nilaiSekarangFormatted = nilaiSkrg.replace(",", "");
		int nilaiSekarang = Integer.parseInt(nilaiSekarangFormatted);
		double sukuBunga = Double.parseDouble(mSukuBunga.getText()
				.toString());
		int waktu = Integer.parseInt(mWaktu.getText().toString());
		double hasilHitung = nilaiSekarang
				* Math.pow(1 + (sukuBunga / 100.0), -(waktu));
		
		String result = NumberFormat.getNumberInstance(Locale.US).format(hasilHitung);

		mHasil.setText("Nilai uang Rp. " + nilaiSkrg
				+ "\ndi " + waktu +" tahun ke depan"
				+ "\ndengan suku bunga " + sukuBunga + " % per th \n= Rp. "
				+ result);
	}

	@Override
	public void onValidationFailed(View failedView, Rule<?> failedRule) {
		String message = failedRule.getFailureMessage();

		if (failedView instanceof EditText) {
			failedView.requestFocus();
			((EditText) failedView).setError(message);
		} else {
			Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
		}

		hasError = true;
	}

	@Override
	public void onValidationSucceeded() {
		hasError = false;
	}

}
