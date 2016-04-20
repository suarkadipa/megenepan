package com.suarkadipa.fragment;

import java.util.Calendar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.suarkadipa.helper.Helper;
import com.suarkadipa.megenepan.R;
import com.suarkadipa.model.ApplicationSetting;

public class ServiceScheduleFragment extends SherlockFragment implements
		OnClickListener {
	private EditText mETPrevOliGardan;
	private EditText mETPrevFilterUdara;
	private EditText mETPrevBusi;
	private EditText mETPrevVBelt;
	private int dfGantiOli = 1500;
	private int dfService = 4000;
	private int dfFilterUdara = 15000;
	private int dfBusi = 8000;
	private int dfOliGardan = 5000;
	private int dfVBelt = 24000;
	private EditText mETPrevService;
	private EditText mETNextGantiOli;
	private Button mBtPrevGantiOli;
	private Button mBtNextGantiOli;
	private EditText mETNextService;
	private Button mBtPrevService;
	private Button mBtNextService;
	private EditText mETNextOliGardan;
	private Button mBtPrevOliGardan;
	private Button mBtNextOliGardan;
	private EditText mETNextFilterUdara;
	private Button mBtPrevFilterUdara;
	private Button mBtNextFilterUdara;
	private EditText mETNextBusi;
	private Button mBtPrevBusi;
	private Button mBtNextBusi;
	private EditText mETNextVBelt;
	private Button mBtPrevVBelt;
	private Button mBtNextVBelt;
	private int mUserDefaultGantiOli;
	private int mUserDefaultService;
	private int mUserDefaultOliGardan;
	private int mUserDefaultFilterUdara;
	private int mUserDefaultBusi;
	private int mUserDefaultVBelt;
	private EditText mETPrevGantiOli;
	private EditText mETKmSekarang;
	private int mUserDefaultKmSekarang;
	private Button mBtKmUpdate;
	private EditText mETKmLastUpdated;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_service_schedule,
				container, false);
		
		setHasOptionsMenu(true);
		
		mETKmSekarang = (EditText) rootView.findViewById(R.id.etKmNow);
		mBtKmUpdate = (Button) rootView.findViewById(R.id.btKmUpdate);
		mETKmLastUpdated = (EditText) rootView.findViewById(R.id.etKmLastUpdated);

		mETPrevGantiOli = (EditText) rootView.findViewById(R.id.etPrevGO);
		mETNextGantiOli = (EditText) rootView.findViewById(R.id.etNextGO);
		mBtPrevGantiOli = (Button) rootView.findViewById(R.id.btPrevGO);
		mBtNextGantiOli = (Button) rootView.findViewById(R.id.btNextGO);

		mETPrevService = (EditText) rootView.findViewById(R.id.etPrevService);
		mETNextService = (EditText) rootView.findViewById(R.id.etNextService);
		mBtPrevService = (Button) rootView.findViewById(R.id.btPrevService);
		mBtNextService = (Button) rootView.findViewById(R.id.btNextService);

		mETPrevOliGardan = (EditText) rootView.findViewById(R.id.etPrevOG);
		mETNextOliGardan = (EditText) rootView.findViewById(R.id.etNextOG);
		mBtPrevOliGardan = (Button) rootView.findViewById(R.id.btPrevOG);
		mBtNextOliGardan = (Button) rootView.findViewById(R.id.btNextOG);

		mETPrevFilterUdara = (EditText) rootView.findViewById(R.id.etPrevFU);
		mETNextFilterUdara = (EditText) rootView.findViewById(R.id.etNextFU);
		mBtPrevFilterUdara = (Button) rootView.findViewById(R.id.btPrevFU);
		mBtNextFilterUdara = (Button) rootView.findViewById(R.id.btNextFU);

		mETPrevBusi = (EditText) rootView.findViewById(R.id.etPrevBusi);
		mETNextBusi = (EditText) rootView.findViewById(R.id.etNextBusi);
		mBtPrevBusi = (Button) rootView.findViewById(R.id.btPrevBusi);
		mBtNextBusi = (Button) rootView.findViewById(R.id.btNextBusi);

		mETPrevVBelt = (EditText) rootView.findViewById(R.id.etPrevVB);
		mETNextVBelt = (EditText) rootView.findViewById(R.id.etNextVB);
		mBtPrevVBelt = (Button) rootView.findViewById(R.id.btPrevVB);
		mBtNextVBelt = (Button) rootView.findViewById(R.id.btNextVB);

		loadDefaultValues();
		initializeEvents();

		return rootView;
	}

	private void loadDefaultValues() {
		mUserDefaultKmSekarang = ApplicationSetting
				.getUserKmNow(getActivity());
		
		// get saved date
		Helper.getSavedDate(getActivity(), ApplicationSetting.getUserKmNowLastUpdated(getActivity()), mETKmLastUpdated);
				
		mUserDefaultGantiOli = ApplicationSetting
				.getUserKmDataGantiOli(getActivity());
		mUserDefaultService = ApplicationSetting
				.getUserKmDataService(getActivity());
		mUserDefaultOliGardan = ApplicationSetting
				.getUserKmDataOliGardan(getActivity());
		mUserDefaultFilterUdara = ApplicationSetting
				.getUserKmDataFilterUdara(getActivity());
		mUserDefaultBusi = ApplicationSetting.getUserKmDataBusi(getActivity());
		mUserDefaultVBelt = ApplicationSetting
				.getUserKmDataVBelt(getActivity());

		mETKmSekarang.setText(String.valueOf(mUserDefaultKmSekarang));
		mETPrevGantiOli.setText(String.valueOf(mUserDefaultGantiOli));
		mETPrevService.setText(String.valueOf(mUserDefaultService));
		mETPrevOliGardan.setText(String.valueOf(mUserDefaultOliGardan));
		mETPrevFilterUdara.setText(String.valueOf(mUserDefaultFilterUdara));
		mETPrevBusi.setText(String.valueOf(mUserDefaultBusi));
		mETPrevVBelt.setText(String.valueOf(mUserDefaultVBelt));
		
		calculateValue(mUserDefaultGantiOli, dfGantiOli, mETNextGantiOli);
		calculateValue(mUserDefaultService, dfService, mETNextService);
		calculateValue(mUserDefaultOliGardan, dfOliGardan, mETNextOliGardan);
		calculateValue(mUserDefaultFilterUdara, dfFilterUdara, mETNextFilterUdara);
		calculateValue(mUserDefaultBusi, dfBusi, mETNextBusi);
		calculateValue(mUserDefaultVBelt, dfVBelt, mETNextVBelt);
		
		checkCloseService();

	}

	private void calculateValue(int userDefaultValue, int defaultValue, EditText editTextTarget) {
		String result = "";
		if (userDefaultValue < defaultValue) {
			result = String.valueOf(defaultValue);
		} else {
			result = String.valueOf(userDefaultValue + defaultValue);
		}

		editTextTarget.setText(result);
	}

	private void initializeEvents() {
		mBtKmUpdate.setOnClickListener(this);
		mBtPrevGantiOli.setOnClickListener(this);
		mBtNextGantiOli.setOnClickListener(this);
		mBtPrevService.setOnClickListener(this);
		mBtNextService.setOnClickListener(this);
		mBtPrevOliGardan.setOnClickListener(this);
		mBtNextOliGardan.setOnClickListener(this);
		mBtPrevFilterUdara.setOnClickListener(this);
		mBtNextFilterUdara.setOnClickListener(this);
		mBtPrevBusi.setOnClickListener(this);
		mBtNextBusi.setOnClickListener(this);
		mBtPrevVBelt.setOnClickListener(this);
		mBtNextVBelt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btKmUpdate:
			int kmSekarang = Integer.parseInt(mETKmSekarang.getText().toString());
			
			// get today's date
			Calendar calThisTime = Calendar.getInstance();
			calThisTime.get(Calendar.YEAR);
			calThisTime.get(Calendar.MONTH);
			calThisTime.get(Calendar.DAY_OF_MONTH);
			long timeInMilis = calThisTime.getTimeInMillis();
			long timeInSeconds = timeInMilis/1000;
			
			// save to preference
			ApplicationSetting.setUserKmNow(getActivity(), kmSekarang);
			ApplicationSetting.setUserKmNowLastUpdated(getActivity(), timeInSeconds);
			
			// get saved date
			Helper.getSavedDate(getActivity(), timeInSeconds, mETKmLastUpdated);
			
			checkCloseService();
			
			break;
		case R.id.btPrevGO:
			reCalculateUserDefault(mETPrevGantiOli, mETNextGantiOli, mUserDefaultGantiOli, dfGantiOli, getString(R.string.ganti_oli), true);

			break;
		case R.id.btNextGO:
			reCalculateUserDefault(mETPrevGantiOli, mETNextGantiOli, mUserDefaultGantiOli, dfGantiOli, getString(R.string.ganti_oli), false);

			break;
		case R.id.btPrevService:
			reCalculateUserDefault(mETPrevService, mETNextService, mUserDefaultService, dfService, getString(R.string.service), true);

			break;
		case R.id.btNextService:
			reCalculateUserDefault(mETPrevService, mETNextService, mUserDefaultService, dfService, getString(R.string.service), false);
			
			break;
		case R.id.btPrevOG:
			reCalculateUserDefault(mETPrevOliGardan, mETNextOliGardan, mUserDefaultOliGardan, dfOliGardan, getString(R.string.oli_gardan), true);

			break;
		case R.id.btNextOG:
			reCalculateUserDefault(mETPrevOliGardan, mETNextOliGardan, mUserDefaultOliGardan, dfOliGardan, getString(R.string.oli_gardan), false);

			break;
		case R.id.btPrevFU:
			reCalculateUserDefault(mETPrevFilterUdara, mETNextFilterUdara, mUserDefaultFilterUdara, dfFilterUdara, getString(R.string.filter_udara), true);

			break;
		case R.id.btNextFU:
			reCalculateUserDefault(mETPrevFilterUdara, mETNextFilterUdara, mUserDefaultFilterUdara, dfFilterUdara, getString(R.string.filter_udara), false);

			break;
		case R.id.btPrevBusi:
			reCalculateUserDefault(mETPrevBusi, mETNextBusi, mUserDefaultBusi, dfBusi, getString(R.string.busi), true);

			break;
		case R.id.btNextBusi:
			reCalculateUserDefault(mETPrevBusi, mETNextBusi, mUserDefaultBusi, dfBusi, getString(R.string.busi), false);

			break;
		case R.id.btPrevVB:
			reCalculateUserDefault(mETPrevVBelt, mETNextVBelt, mUserDefaultVBelt, dfVBelt, getString(R.string.v_belt), true);

			break;
		case R.id.btNextVB:
			reCalculateUserDefault(mETPrevVBelt, mETNextVBelt, mUserDefaultVBelt, dfVBelt, getString(R.string.v_belt), false);

			break;

		default:
			break;
		}
	}

	private void reCalculateUserDefault(EditText editTextPrev, EditText editTextNext, int mUserDefault, int mDf, String title, boolean isPrev) {
		Helper.toastIt(getActivity(), "Ganti " + title + " tiap " + String.valueOf(mDf) + " km.");
		
		if (!editTextPrev.getText().toString().isEmpty()) {
			mUserDefault = Integer.parseInt(editTextPrev.getText().toString());
			
			int newValue = 0;			
			newValue = isPrev ? mUserDefault - mDf : mUserDefault + mDf;
			
			editTextPrev.setText(String.valueOf(newValue));
			editTextNext.setText(String.valueOf(newValue + mDf));
			
			if (title.equals(getString(R.string.ganti_oli))) {
				ApplicationSetting.setUserKmDataGantiOli(getActivity(), newValue);
			} else if  (title.equals(getString(R.string.service))) {
				ApplicationSetting.setUserKmDataService(getActivity(), newValue);
			} else if  (title.equals(getString(R.string.oli_gardan))) {
				ApplicationSetting.setUserKmDataOliGardan(getActivity(), newValue);
			} else if  (title.equals(getString(R.string.filter_udara))) {
				ApplicationSetting.setUserKmDataFilterUdara(getActivity(), newValue);
			} else if  (title.equals(getString(R.string.busi))) {
				ApplicationSetting.setUserKmDataBusi(getActivity(), newValue);
			} else if  (title.equals(getString(R.string.v_belt))) {
				ApplicationSetting.setUserKmDataVBelt(getActivity(), newValue);
			}
		} else {
			Helper.toastIt(getActivity(), title + " tidak boleh kosong");
		}
		
		checkCloseService();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.service_menu, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String title = "";
		String message = "";
		String initialValue = "";
		switch (item.getItemId()) {
		case R.id.settings_km_close:
			title = getString(R.string.km_close_desc);
			message = getString(R.string.isi) + " " + title;
			initialValue = String.valueOf(ApplicationSetting.getUserKmCloseServiceSetting(getActivity()));
			
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
			alertDialog.setTitle(title);
			alertDialog.setMessage(message);

			final EditText input = new EditText(getActivity());
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT);
			input.setLayoutParams(lp);
			input.setInputType(InputType.TYPE_CLASS_NUMBER);
			input.setText(initialValue);
			input.setSelectAllOnFocus(true);
			alertDialog.setView(input);
			alertDialog.setIcon(R.drawable.action_settings);

			alertDialog.setPositiveButton(getString(R.string.isi),
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					String inputText = input.getText().toString();
					if (inputText != null && !inputText.isEmpty()) {
						int intInput = Integer.parseInt(inputText);
						ApplicationSetting.setUserKmCloseServiceSetting(getActivity(), intInput);
						
						Helper.hideKeyboard(getActivity());
						checkCloseService();
					}
				}
			});

			alertDialog.setNegativeButton(getString(R.string.batal),
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});

			alertDialog.show();
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void checkCloseService() {
		int closeService = ApplicationSetting.getUserKmCloseServiceSetting(getActivity());
		int intKmSekarang = getIntValueOfEditText(mETKmSekarang);
		int intBusi = getIntValueOfEditText(mETNextBusi);
		int intFilterUdara = getIntValueOfEditText(mETNextFilterUdara);
		int intGantiOli = getIntValueOfEditText(mETNextGantiOli);
		int intOliGardan = getIntValueOfEditText(mETNextOliGardan);
		int intService = getIntValueOfEditText(mETNextService);
		int intVBelt = getIntValueOfEditText(mETNextVBelt);
		
		if ((intVBelt - intKmSekarang) <= closeService) {
			setTextAlert(mETNextVBelt);
		} else {
			setTextClear(mETNextVBelt);
		}
		
		if ((intBusi - intKmSekarang) <= closeService) {
			setTextAlert(mETNextBusi);
		} else {
			setTextClear(mETNextBusi);
		}

		if ((intFilterUdara - intKmSekarang) <= closeService) {
			setTextAlert(mETNextFilterUdara);
		} else {
			setTextClear(mETNextFilterUdara);
		}
		
		if ((intGantiOli - intKmSekarang) <= closeService) {
			setTextAlert(mETNextGantiOli);
		} else {
			setTextClear(mETNextGantiOli);
		}

		if ((intOliGardan - intKmSekarang) <= closeService) {
			setTextAlert(mETNextOliGardan);
		} else {
			setTextClear(mETNextOliGardan);
		}

		if ((intService - intKmSekarang) <= closeService) {
			setTextAlert(mETNextService);
		} else {
			setTextClear(mETNextService);
		}
		
	}
	
	private int getIntValueOfEditText(EditText editTextTarget) {
		int result = Integer.parseInt(editTextTarget.getText().toString());
		return result;
	}
	
	private void setTextAlert(EditText editTextTarget) {
		editTextTarget.setTextColor(Color.YELLOW);
		editTextTarget.setBackgroundColor(Color.RED);
	}
	
	private void setTextClear(EditText editTextTarget) {
		editTextTarget.setBackgroundColor(Color.parseColor("#ff669900"));
	}

}
