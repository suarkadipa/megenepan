package com.suarkadipa.fragment;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.suarkadipa.helper.Helper;
import com.suarkadipa.megenepan.R;
import com.suarkadipa.model.ApplicationSetting;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class BalineseCalendarFragment extends SherlockFragment {

	private static TextView mTxtDate;
	private static TextView mTxtCalc;
	private String[] mSpinner;
	private Button mBtnCalc;
	private Button mBtnAddToCal;
	private EditText mTxtNama;
	private List<String> outputMilis;
	private static TextView mTxtCustom;
	private static Spinner spinner;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_balinese_calendar,
				container, false);

		mSpinner = getResources().getStringArray(R.array.hitung_hari_items);
		spinner = new Spinner(getActivity());
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item, mSpinner);
		spinnerArrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner = (Spinner) rootView.findViewById(R.id.spinner1);
		spinner.setAdapter(spinnerArrayAdapter);

		mTxtDate = (TextView) rootView.findViewById(R.id.txtDate);
		mTxtCalc = (TextView) rootView.findViewById(R.id.txtCalc);
		mTxtCustom = (TextView) rootView.findViewById(R.id.txtCustom);
		mBtnCalc = (Button) rootView.findViewById(R.id.btnCalc);
		mBtnAddToCal = (Button) rootView.findViewById(R.id.btnAddToCal);
		mTxtNama = (EditText) rootView.findViewById(R.id.txtName);

		// get saved date
		Helper.getSavedDate(getActivity(), ApplicationSetting.getUserSavedDate(getActivity()), mTxtCalc);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String dateString = mTxtCalc.getText().toString();
				SimpleDateFormat df = new SimpleDateFormat(getString(R.string.date_format), Locale.getDefault());
				int year = 0;
				int month = 0;
				int day = 0;
				try {
					Date date = df.parse(dateString);

					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					year = calendar.get(Calendar.YEAR);
					month = calendar.get(Calendar.MONTH);
					day = calendar.get(Calendar.DAY_OF_MONTH);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				if (spinner.getSelectedItemPosition() == 0) {
					mTxtCustom.setHint(getString(R.string.hari));
					mTxtCustom.setVisibility(View.VISIBLE);
					if (!mTxtCustom.getText().toString().isEmpty()) {
						calculateDate(year, month, day);
					}
				} else if (spinner.getSelectedItemPosition() == 1) {
					mTxtCustom.setHint(getString(R.string.bulan));
					mTxtCustom.setVisibility(View.VISIBLE);
					if (!mTxtCustom.getText().toString().isEmpty()) {
						calculateDate(year, month, day);
					}
				} else {
					mTxtCustom.setVisibility(View.GONE);
					if (!mTxtCalc.getText().toString().isEmpty()) {
						calculateDate(year, month, day);
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) { }
		});
		
		mTxtCustom.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) { }
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) { }
			
			@Override
			public void afterTextChanged(Editable s) {
				String dateString = mTxtCalc.getText().toString();
				SimpleDateFormat df = new SimpleDateFormat(getString(R.string.date_format), Locale.getDefault());
				int year = 0;
				int month = 0;
				int day = 0;
				try {
					Date date = df.parse(dateString);

					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					year = calendar.get(Calendar.YEAR);
					month = calendar.get(Calendar.MONTH);
					day = calendar.get(Calendar.DAY_OF_MONTH);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				if (!mTxtCustom.getText().toString().isEmpty()) {
					calculateDate(year, month, day);
				}
			}
		});

		mBtnCalc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				DialogFragment newFragment = new DatePickerFragment();
				newFragment.show(getFragmentManager(), getString(R.string.date_picker));

			}
		});
		
		mBtnAddToCal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!mTxtNama.getText().toString().isEmpty()) {
					for (String item : outputMilis) {
						addEvent(item, item);
					}
					toastIt(getString(R.string.success_add_to_calendar));
				} else {
					toastIt(getString(R.string.name_cannot_be_empty));
				}
			}
		});

		return rootView;
	}
	
	private void addEvent(String startTimeMillis, String endTimeMillis) {
		String title = "";
		
		// day in minutes
		int oneDay = 1440;
		int reminderTime = 1 * oneDay;
		
		if (spinner.getSelectedItemPosition() == 3) {
			title = getString(R.string.dedinan);
		} else if (spinner.getSelectedItemPosition() == 5) {
			title = getString(R.string.otonan);
		}
		
		String titleAndDesc = title + mTxtNama.getText().toString();
		
		ContentResolver cr = getActivity().getContentResolver();
		ContentValues values = new ContentValues();
	    values.put(Events.CALENDAR_ID, 1);

	    values.put(Events.TITLE, titleAndDesc);
	    values.put(Events.DESCRIPTION, titleAndDesc);
	    values.put(Events.EVENT_LOCATION, getString(R.string.di_rumah));

	    values.put(Events.DTSTART, startTimeMillis);
	    values.put(Events.DTEND, endTimeMillis);
	    values.put(Events.ALL_DAY, true); 
	    values.put(Events.HAS_ALARM, true);

	    String timeZone = TimeZone.getDefault().getID();
	    values.put(Events.EVENT_TIMEZONE, timeZone);

	    Uri EVENTS_URI;
	    Uri REMINDERS_URI;
	    if (Build.VERSION.SDK_INT >= 8) {
	        EVENTS_URI = Uri.parse("content://com.android.calendar/events");
	        REMINDERS_URI = Uri.parse("content://com.android.calendar/reminders");
	    } else {
	        EVENTS_URI = Uri.parse("content://calendar/events");
	        REMINDERS_URI = Uri.parse("content://calendar/reminders");
	    }

	    Uri event = cr.insert(EVENTS_URI, values);
	    
	    long eventID = Long.parseLong(event.getLastPathSegment());
	    
	    values = new ContentValues();
	    values.put(Reminders.EVENT_ID, eventID);
	    values.put(Reminders.METHOD, Reminders.METHOD_ALERT);
	    values.put(Reminders.MINUTES, String.valueOf(reminderTime));
	    cr.insert(REMINDERS_URI, values);
	}

	public class DatePickerFragment extends DialogFragment implements
			OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day);
			long timeInMilis = cal.getTimeInMillis();
			long timeInSeconds = timeInMilis/1000;
			ApplicationSetting.setUserSavedDate(getActivity(), timeInSeconds);
			
			calculateDate(year, month, day);
		}
	}

	private void toastIt(String message) {
		Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
	}

	private void calculateDate(int year, int month, int day) {
		int pos = spinner.getSelectedItemPosition();
		String posName = spinner.getSelectedItem().toString() + " dari ";
		int addition = 0;
		int satuBulanBali = 35;
		String prevDate = "";
		String formattedDate = "";
		List<String> outputs = new ArrayList<String>();
		outputMilis = new ArrayList<String>();
		
		// Do something with the date chosen by the user
		SimpleDateFormat df = new SimpleDateFormat(getString(R.string.date_format), Locale.getDefault());
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		
		// get today's date
		Calendar calndr = Calendar.getInstance();
		Calendar calThisTime = Calendar.getInstance();
		calThisTime.get(Calendar.YEAR);
		calThisTime.get(Calendar.MONTH);
		calThisTime.get(Calendar.DAY_OF_MONTH);
		Date dateNow = calThisTime.getTime();
		
		switch (pos) {
		case 0:
			if (!mTxtCustom.getText().toString().isEmpty()) {
				addition = Integer.parseInt(mTxtCustom.getText().toString());
				posName = addition + " hari setelah ";
				calndr.set(Calendar.YEAR, year);
				calndr.set(Calendar.MONTH, month);
				calndr.set(Calendar.DAY_OF_MONTH, day + addition);
				prevDate = df.format(cal.getTime());
				formattedDate = df.format(calndr.getTime());
				outputs.add(formattedDate);
				
				setResult(prevDate, posName, outputs);
			} else {
				toastIt(getString(R.string.jumlah_hari_harus_diisi));
			}
			break;
		case 1:
			if (!mTxtCustom.getText().toString().isEmpty()) {
				int bulanAddition = 0;
				bulanAddition = Integer.parseInt(mTxtCustom.getText().toString());
				addition = satuBulanBali * bulanAddition;
				posName = bulanAddition + " bulan setelah ";
				calndr.set(Calendar.YEAR, year);
				calndr.set(Calendar.MONTH, month);
				calndr.set(Calendar.DAY_OF_MONTH, day + addition);
				prevDate = df.format(cal.getTime());
				formattedDate = df.format(calndr.getTime());
				outputs.add(formattedDate);
				
				setResult(prevDate, posName, outputs);
			} else {
				toastIt(getString(R.string.jumlah_bulan_harus_diisi));
			}
			break;
		case 2:
			addition = satuBulanBali + 7;
			calndr.set(Calendar.YEAR, year);
			calndr.set(Calendar.MONTH, month);
			calndr.set(Calendar.DAY_OF_MONTH, day + addition);
			prevDate = df.format(cal.getTime());
			formattedDate = df.format(calndr.getTime());
			outputs.add(formattedDate);
			
			setResult(prevDate, posName, outputs);
			break;
		case 3:
			addition = satuBulanBali;
			prevDate = df.format(cal.getTime());
			// dedinan hanya sampai 4 oton/kira2 2 tahun  oleh karena itu perulangan dedinan hanya 4*5 = 20 kali
			int xOton = 4;
			int batasDedinan = xOton * 5;
			for (int i = 0; i < batasDedinan; i++) {
				calndr.set(Calendar.YEAR, year);
				calndr.set(Calendar.MONTH, month);
				calndr.set(Calendar.DAY_OF_MONTH, day + addition);
				Date calcTime = calndr.getTime();
				formattedDate = df.format(calndr.getTime());
				// kenapa dedinan pertama tidak dimasukkan? karena dedinan boleh setelah abulan pitung dina, 
				// karena dedinan kurang dari abulan pitung dina, dimana sebelum abulan pitung dina, masih sebel, 
				// artinya tidak boleh dilakukan upacara dewa yadnya.
				if (calcTime.after(dateNow) && i > 0) {
					outputMilis.add(String.valueOf(calndr.getTimeInMillis()));
					outputs.add("\n"+formattedDate);
				}
				addition = addition + satuBulanBali;
			}
			
			setResult(prevDate, posName, outputs);
			break;
		case 4:
			addition = satuBulanBali * 3;
			calndr.set(Calendar.YEAR, year);
			calndr.set(Calendar.MONTH, month);
			calndr.set(Calendar.DAY_OF_MONTH, day + addition);
			prevDate = df.format(cal.getTime());
			formattedDate = df.format(calndr.getTime());
			outputs.add(formattedDate);
			
			setResult(prevDate, posName, outputs);
			break;
		case 5:
			addition = satuBulanBali * 6;
			prevDate = df.format(cal.getTime());
			int batas = 130;
			int counter = 0;
			for (int i = 0; i < batas; i++) {
				calndr.set(Calendar.YEAR, year);
				calndr.set(Calendar.MONTH, month);
				calndr.set(Calendar.DAY_OF_MONTH, day + addition);
				Date calcTime = calndr.getTime();
				outputMilis.add(String.valueOf(calndr.getTimeInMillis()));
				formattedDate = df.format(calcTime);
				if (calcTime.after(dateNow) && counter < 5) {
					outputs.add("\n"+formattedDate);
					counter++;
					if (counter > 4) {
						break;
					}
				}
				addition = addition + (satuBulanBali * 6);
			}
			
			setResult(prevDate, posName, outputs);
			break;
		case 6:
//			DateTime start = new DateTime(2008, 2, 8, 0, 0, 0, 0);
//      DateTime end = new DateTime();
//      Period period = new Period(start, end);
//      System.out.println(" user is " + period.getYears() + " years " + period.getMonths() + " months old");>
			prevDate = df.format(cal.getTime());
			DateTime start = new DateTime(cal.getTime());
			DateTime end = new DateTime();
			Period period = new Period(start, end);
			String tahun = String.valueOf(period.getYears());
			String bulan = String.valueOf(period.getMonths());
			String hari = String.valueOf(period.getDays());
			
			outputs.add(tahun + " tahun, " + bulan + " bulan, " + hari + " hari.");
			
			setResult(prevDate, posName, outputs);
			
			break;

		default:
			break;
		}
	}
	
	private void setResult(String prevDate, String posName, List<String> outputs) {
		mTxtCalc.setText(prevDate);
		mTxtDate.setText(posName + prevDate + " adalah \n" + outputs.toString().replace("[", "").replace("]", ""));
		
		if (spinner.getSelectedItemPosition() == 3 || spinner.getSelectedItemPosition() == 5) {
			mBtnAddToCal.setVisibility(View.VISIBLE);
		} else {
			mBtnAddToCal.setVisibility(View.GONE);
		}
	}
}
