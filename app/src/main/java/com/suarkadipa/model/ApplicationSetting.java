package com.suarkadipa.model;

import android.content.Context;
import android.content.SharedPreferences;


public class ApplicationSetting {
	public static SharedPreferences getPrefs(Context context) {
		return context.getSharedPreferences(Metadata.PreferenceName,
				Context.MODE_PRIVATE);
	}
	
	public static Long getUserSavedDate(Context context) {
		return getPrefs(context).getLong(Metadata.userDateData, 1431417600);
	}

	public static void setUserSavedDate(Context context, Long value) {
		getPrefs(context).edit().putLong(Metadata.userDateData, value)
				.commit();
	}
	
	public static int getUserKmDataGantiOli(Context context) {
		return getPrefs(context).getInt(Metadata.userKmDataGantiOli, 9300);
	}
	
	public static void setUserKmDataGantiOli(Context context, int value) {
		getPrefs(context).edit().putInt(Metadata.userKmDataGantiOli, value)
				.commit();
	}
	
	public static int getUserKmDataService(Context context) {
		return getPrefs(context).getInt(Metadata.userKmDataService, 9300);
	}
	
	public static void setUserKmDataService(Context context, int value) {
		getPrefs(context).edit().putInt(Metadata.userKmDataService, value)
				.commit();
	}
	
	public static int getUserKmDataOliGardan(Context context) {
		return getPrefs(context).getInt(Metadata.userKmDataOliGardan, 9300);
	}
	
	public static void setUserKmDataOliGardan(Context context, int value) {
		getPrefs(context).edit().putInt(Metadata.userKmDataOliGardan, value)
				.commit();
	}
	
	public static int getUserKmDataFilterUdara(Context context) {
		return getPrefs(context).getInt(Metadata.userKmDataFilterUdara, 0);
	}
	
	public static void setUserKmDataFilterUdara(Context context, int value) {
		getPrefs(context).edit().putInt(Metadata.userKmDataFilterUdara, value)
				.commit();
	}
	
	public static int getUserKmDataBusi(Context context) {
		return getPrefs(context).getInt(Metadata.userKmDataBusi, 9300);
	}
	
	public static void setUserKmDataBusi(Context context, int value) {
		getPrefs(context).edit().putInt(Metadata.userKmDataBusi, value)
				.commit();
	}
	
	public static int getUserKmDataVBelt(Context context) {
		return getPrefs(context).getInt(Metadata.userKmDataVBelt, 0);
	}
	
	public static void setUserKmDataVBelt(Context context, int value) {
		getPrefs(context).edit().putInt(Metadata.userKmDataVBelt, value)
				.commit();
	}
	
	public static int getUserGaji(Context context) {
		return getPrefs(context).getInt(Metadata.userGaji, 9400000);
	}
	
	public static void setUserGaji(Context context, int value) {
		getPrefs(context).edit().putInt(Metadata.userGaji, value)
				.commit();
	}
	
	public static int getUserKmNow(Context context) {
		return getPrefs(context).getInt(Metadata.userKmNow, 12000);
	}
	
	public static void setUserKmNow(Context context, int value) {
		getPrefs(context).edit().putInt(Metadata.userKmNow, value)
				.commit();
	}
	
	public static Long getUserKmNowLastUpdated(Context context) {
		return getPrefs(context).getLong(Metadata.userKmNowLastUpdated, 1431417600);
	}
	
	public static void setUserKmNowLastUpdated(Context context, Long value) {
		getPrefs(context).edit().putLong(Metadata.userKmNowLastUpdated, value)
				.commit();
	}
	
	public static int getUserKmCloseServiceSetting(Context context) {
		return getPrefs(context).getInt(Metadata.userKmCloseServiceSetting, 100);
	}
	
	public static void setUserKmCloseServiceSetting(Context context, int value) {
		getPrefs(context).edit().putInt(Metadata.userKmCloseServiceSetting, value)
				.commit();
	}
	
	public static class Metadata {
		public static final String PreferenceName = "sharedPreferences";
		
		public static final String userDateData = "userDateData";
		public static final String userKmDataGantiOli = "userKmDataGantiOli";
		public static final String userKmDataService = "userKmDataService";
		public static final String userKmDataOliGardan = "userKmDataOliGardan";
		public static final String userKmDataFilterUdara = "userKmDataFilterUdara";
		public static final String userKmDataBusi = "userKmDataBusi";
		public static final String userKmDataVBelt = "userKmDataVBelt";
		public static final String userGaji = "userGaji";
		public static final String userKmNow = "userKmNow";
		public static final String userKmNowLastUpdated = "userKmNowLastUpdated";
		public static final String userKmCloseServiceSetting = "userKmCloseServiceSetting";
	}
}
