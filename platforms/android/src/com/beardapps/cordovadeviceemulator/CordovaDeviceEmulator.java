/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.beardapps.cordovadeviceemulator;

import org.apache.cordova.CordovaActivity;
import org.jtb.alogcat.FilterDialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CordovaDeviceEmulator extends CordovaActivity 
{
	private static final int MENU_GOTO = 0;
	private static final int MENU_LOGACTIVITY = 1;
	private static final int MENU_REFRESH = 2;
	public static final int GOTO_DIALOG = 0;
	public static String currentUrl = "";
	
	private GoToDialog mGoToDialog = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.init();
        
        super.loadUrlTimeoutValue = 60000;
        super.keepRunning = true;
    }
    
    public void gotoUrl(String url) {
    	currentUrl = url;
    	super.loadUrl(currentUrl);
    }
    
     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// TODO Auto-generated method stub
    	 menu.add(0,MENU_GOTO,0,"GoTo");
    	 menu.add(0,MENU_LOGACTIVITY,0,"LogActivity");
    	 menu.add(0,MENU_REFRESH,0,"Refresh");
    	return super.onPrepareOptionsMenu(menu);
    }
     
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    		case MENU_GOTO: {
    			showDialog(GOTO_DIALOG);
    			return true;
    		}
    		case MENU_LOGACTIVITY: {
    			Intent intent = new Intent(this, org.jtb.alogcat.LogActivity.class);
    	        startActivity(intent);
    			return true;
    		}
    		case MENU_REFRESH: {
    			gotoUrl(currentUrl);
    			return true;
    		}
    		default:
    			return false;//super.onOptionsItemSelected(item);
    	}
    }
    
    protected Dialog onCreateDialog(int id) {
		switch (id) {
		case GOTO_DIALOG:
			mGoToDialog = new GoToDialog(this);
			return mGoToDialog;
		}
		return null;
	}
}

