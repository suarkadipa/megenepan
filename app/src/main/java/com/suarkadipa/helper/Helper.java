package com.suarkadipa.helper;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.suarkadipa.megenepan.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Helper {
	public static void toastIt(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * getSavedDate.
	 *
	 * @param context the context
	 * @param sourceDate the source date ex: ApplicationSetting.getUserSavedDate(getActivity());
	 * @param textviewTarget the textview target
	 */
	public static void getSavedDate(Context context, Long sourceDate, TextView textviewTarget) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(sourceDate * 1000);
		SimpleDateFormat df = new SimpleDateFormat(context.getString(R.string.date_format), Locale.getDefault());
		String newDate = df.format(calendar.getTime());
		textviewTarget.setText(newDate);
	}
	
	public static void hideKeyboard(Context context) {
		InputMethodManager inputManager = (InputMethodManager) context
	            .getSystemService(Context.INPUT_METHOD_SERVICE);

	    //check if no view has focus:
	    View view = ((Activity) context).getCurrentFocus();
	    if (view == null)
	        return;

	    inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}
}
