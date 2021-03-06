package com.joelapenna.foursquared;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import static com.joelapenna.foursquared.FoursquaredSettings.DEBUG;


public final class AuthenticatorService extends Service {
    
    public static final String ACCOUNT_TYPE = "com.joelapenna.foursquared";
    private static final String TAG = "AuthenticatorService";
	
	private Authenticator mAuthenticator = null;
	
	public IBinder onBind(Intent intent) {
	    Log.i(TAG, "uid is " + this.getApplicationInfo().uid);
	    if (DEBUG) Log.d(TAG, "onBind()");
	    if (intent.getAction().equals(android.accounts.AccountManager.ACTION_AUTHENTICATOR_INTENT)) {
	        IBinder binder = mAuthenticator.getIBinder();
	        if (DEBUG) Log.d(TAG, "returning binder " + binder);
	        return binder;
	    } else {
	        return null;
	    }
	}

	public void onCreate() {
	    if (DEBUG) Log.d(TAG, "onCreate()");
	    Foursquared foursquared = (Foursquared)getApplication();
	    mAuthenticator = new Authenticator(this, foursquared);
	}
	
}
