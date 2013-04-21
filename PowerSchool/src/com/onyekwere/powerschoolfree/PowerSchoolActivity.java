
package com.onyekwere.powerschoolfree;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.millennialmedia.android.MMAdView;
import com.millennialmedia.android.MMInterstitial;
import com.millennialmedia.android.MMAd;
import com.millennialmedia.android.MMBroadcastReceiver;
import com.millennialmedia.android.MMRequest;
import com.millennialmedia.android.MMSDK;
import com.millennialmedia.android.RequestListener.RequestListenerImpl;
import com.onyekwere.powerschoolfree.R;


public class PowerSchoolActivity extends Activity {
	
	private static final String TAG = "PowerSchool 2";
	public static final String url = null;
	private WebView mWebView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MMSDK.initialize(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        Log.i(TAG, "onCreate");
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.loadUrl("https://powerschool.somsd.k12.nj.us/public/");
        mWebView.setWebViewClient(new PowerSchoolClient());
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());
        MMAdView adView = new MMAdView(this);

      //Replace YOUR_APID with the APID provided to you by Millennial Media
      adView.setApid("105938");

      //Set your metadata in the MMRequest object
      MMRequest request = new MMRequest();

      //Add metadata here.

      //Add the MMRequest object to your MMAdView.
      adView.setMMRequest(request);

      //Sets the id to preserve your ad on configuration changes.
      adView.setId(MMSDK.getDefaultAdId());
      
      RelativeLayout adRelativeLayout = (RelativeLayout) findViewById(R.id.adView);

      adRelativeLayout.addView(adView, new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

      adView.getAd();
        
      final MMInterstitial interstitial = new MMInterstitial(this);


      //Add the MMRequest object to your MMInterstitial.
      interstitial.setMMRequest(request);
      interstitial.setApid("105937");
      interstitial.fetch();

      interstitial.setListener(new RequestListenerImpl() {

      @Override

      public void requestCompleted(MMAd mmAd) {
         interstitial.display();
      }
      });  
  
        }

    
    
    private class PowerSchoolClient extends WebViewClient {
    	@Override
    	public boolean shouldOverrideUrlLoading(WebView webview, String ulr)
    	{
    		webview.loadUrl(url);
    		return true;
    	}
    	  	 	
    }
    
    @Override
    public boolean onKeyDown(int KeyCode, KeyEvent event)
    {
    	if ((KeyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack())
    	{
    		mWebView.goBack();
    		return true;
    	}
    	return super.onKeyDown(KeyCode, event);
    }
    
    public boolean onCreateOptionsMenu(Menu menu){
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.test_menu, menu);
    	return true;
   	 }
    
     public boolean   onOptionsItemSelected (MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.item1:
    		finish();
    		 return true;
    	case R.id.item2:
    		startActivity(new Intent("com.onyekwere.powerschoolfree.SWEET"));
    		return true;
    	case R.id.item3:
    		Intent intent = new Intent(Intent.ACTION_VIEW);
    		intent.setData(Uri.parse("market://details?id=com.onyekwere.powerschool"));
    		startActivity(intent);
    		return true;
    		
    	
    	}
    	
    	return false;
     }
     
    @Override
    protected void onStart()
    {
    	super.onStart();
    	Log.i(TAG, "onStart");
    }
    
    @Override
    protected void onRestart()
    {
    	super.onRestart();
    	Log.i(TAG, "onRestart");
    }
    
    @Override
    protected void onPause()
    {
    	super.onPause();
    	Log.i(TAG, "onPause");
    }
    
    @Override
    protected void onResume()
    {
    	super.onResume();
    	Log.i(TAG, "onResume");
    }
    
    @Override
    protected void onDestroy()
    {
    	super.onDestroy();
    	Log.i(TAG, "onDestroy");
    }
    
    @Override
    protected void onStop()
    {
    	super.onStop();
    	Log.i(TAG, "onStop");
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
    	super.onSaveInstanceState(outState);
    	mWebView.saveState(outState);
    	Log.i(TAG, "onSaveInstanceState");
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
    	super.onRestoreInstanceState(savedInstanceState);
    	mWebView.restoreState(savedInstanceState);
    	Log.i(TAG, "onRestoreInstanceState");
    }
}


