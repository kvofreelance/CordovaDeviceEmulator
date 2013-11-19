package com.beardapps.cordovadeviceemulator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class GoToDialog extends AlertDialog {
	private boolean mError = false;
	//private Prefs mPrefs;
	private CordovaDeviceEmulator mActivity;
	private SharedPreferences sp;
	private String[] autoCompleteArray;
	private AutoCompleteTextView gotoEdit;
	
	
    @Override
    public void dismiss() {
    	if (!mError) {
    		super.dismiss();
    	}
    }
    
    @Override
    public void show() {
    	readLastUrls();
    	super.show();
    }
    
    
    
    
	public GoToDialog(CordovaDeviceEmulator activity) {
		super(activity);
		
		mActivity = activity;
		
		LayoutInflater factory = LayoutInflater.from(mActivity);
		final View view = factory.inflate(R.layout.goto_dialog, null);
		
		//Get all saved urls
		readLastUrls();
		
		gotoEdit = (AutoCompleteTextView) view
				.findViewById(R.id.goto_edit);
		gotoEdit.setText(mActivity.currentUrl);
		setLastUrlAdapter();
		
		setView(view);
		setTitle(R.string.goto_dialog_title);
		
		setButton(BUTTON_POSITIVE, mActivity.getResources().getString(R.string.ok),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String newUrl = gotoEdit.getText().toString();
						saveLastUrls(newUrl);
						mActivity.dismissDialog(mActivity.GOTO_DIALOG);
						mActivity.gotoUrl(newUrl);
						
					}
				});
		setButton(BUTTON_NEUTRAL, mActivity.getResources().getString(R.string.clear),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {	
						gotoEdit.setText("");
					}
				});
		setButton(BUTTON_NEGATIVE,  mActivity.getResources().getString(R.string.cancel),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						mActivity.dismissDialog(mActivity.GOTO_DIALOG);
					}
				});

	}
	
	public void readLastUrls() {
		sp = mActivity.getSharedPreferences("CordovaDeviceEmulator", mActivity.MODE_PRIVATE);
		autoCompleteArray = sp.getString("lastUrls", "").split(";");
		Log.d("SelectedImages", sp.getString("lastUrls", ""));
		setLastUrlAdapter();
	}
	
	public void setLastUrlAdapter() {
		if(gotoEdit != null) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity,
	                android.R.layout.simple_spinner_dropdown_item, autoCompleteArray);
			gotoEdit.setAdapter(adapter);
		}
	}
	
	public void saveLastUrls(String newUrl) {
		if(autoCompleteArray != null && sp != null) {
			//Check if newUrl presented in autoCompleteArray
			Boolean isPresent = false;
			for (String item : autoCompleteArray) {
				if(item.equals(newUrl)) {
					isPresent = true;
					break;
				}
			}
			
			if(!isPresent) {
				int lastSavedElement = autoCompleteArray.length;
				if(autoCompleteArray.length > 20) {
					lastSavedElement -= 1;
				}
			
				String stringToSave = newUrl + ";";
				for (int i = 0; i < lastSavedElement; i++ ) {
					stringToSave = stringToSave.concat(autoCompleteArray[i]+";");
				}

				SharedPreferences.Editor editor = sp.edit();
				editor.putString("lastUrls", stringToSave);
				editor.commit();
			}
		}

	}
}
