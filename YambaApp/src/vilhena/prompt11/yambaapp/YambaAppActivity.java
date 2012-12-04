package vilhena.prompt11.yambaapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class YambaAppActivity extends Activity {
    /** Called when the activity is first created. */
	
	private TextSwitcher mSwitcher;
	private Toast limitMessage;
	private SharedPreferences settings;
	
	private static int max_int = 140;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {

    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        limitMessage = Toast.makeText(YambaAppActivity.this, getText(R.string.MaxLimitMessage), 2000);
        
        findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent(YambaAppActivity.this, ListViewActivity.class));
			}
		});
        
    	//findViewById(R.id.button2).setOnClickListener(new OnClickListener() {
		//	
		//	public void onClick(View v) {
		//		stopService(new Intent(YambaAppActivity.this,TimelinePull.class));
		//	}
		//});
        
        
        
        mSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
        mSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
			
			public View makeView() {
				TextView t = new TextView(YambaAppActivity.this);
		        t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
		        t.setTextSize(36);
		        return t;
			}
		});

        //settings = getSharedPreferences("yamba_prefs", 0);
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        
        
        settings.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener(){

//			public void onSharedPreferenceChanged(
//					SharedPreferences sharedPreferences, String key) {
//				Log.i("onSharedPreferenceChanged", String.format("key %s", key));
//			}

        	
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
				//if(key == "autoUpdateTimeline"){
				
				Log.i("onSharedPreferenceChanged", String.format("key %s", key));
				
				
				Boolean isactive = sharedPreferences.getBoolean("autoUpdateTimeline", false);
				
				if(isactive){
					startService(new Intent(YambaAppActivity.this,TimelinePull.class));
				}
				else {
					stopService(new Intent(YambaAppActivity.this,TimelinePull.class));
				}
			}
        	
        });
        
        Animation in = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
        
        
        TextView mytext = (TextView) findViewById(R.id.editTextMultiline);
       	mytext.addTextChangedListener(new TextWatcher(){

       		
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				TextView chars = (TextView) findViewById(R.id.textViewCharCount);
				chars.setText(Integer.toString(max_int -  s.length()));
		        mSwitcher.setText(Integer.toString(max_int -  s.length()));
		        
		        if(s.length()>max_int){//change this
		        	chars.setTextColor(Color.RED);
		        	limitMessage.show();
		        }
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

					s = s.subSequence(0, start);
			}
       	
       	});
        
        TextView chars = (TextView) findViewById(R.id.textViewCharCount);
        
        chars.setText(""+max_int);
        
        Button btt = (Button) findViewById(R.id.buttonSubmit);
        btt.setOnClickListener(mSendListener);
        
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    };
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	super.onRestoreInstanceState(savedInstanceState);
    };
    
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
    	
    	startActivity(new Intent(this, vilhena.prompt11.yambaapp.YambaMenuActivity.class));
    	return true;
    };
    
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
    	getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    };
    
//    private class CalculateTask extends AsyncTask<String, Void, Boolean> {
//        protected Boolean doInBackground(String... urls) {
//        	
//        	Boolean success = true;
//        	try	
//        	{
//        		Twitter t =  new Twitter(
//        				settings.getString("username", "")
//        				,settings.getString("password", ""));
//        		
//            	t.setAPIRootUrl(settings.getString("url", ""));
//        		t.updateStatus(urls[0]);
//        	}
//        	catch (Exception e) {
//				success = false;
//			}
//        	
//            return success;
//        }
//
//        protected void onPostExecute(Boolean result) {
//        	Button btt = (Button) findViewById(R.id.buttonSubmit);
//        	btt.setEnabled(true);
//        	dialog.dismiss();
//        	Toast.makeText(YambaAppActivity.this, "Success", 4000).show();
//        }
//        
//        protected void onPreExecute(){
//        	Button btt = (Button) findViewById(R.id.buttonSubmit);
//        	btt.setEnabled(false);
//        	
//            dialog = ProgressDialog.show(YambaAppActivity.this, "", 
//                    getText(R.string.loadingMessage));
//        }
//    }
//    
    
    
    private OnClickListener mSendListener = new OnClickListener() {
        public void onClick(View v) {
          // do something when the button is clicked
        	
        	TextView chars = (TextView) findViewById(R.id.textViewCharCount);
            
            int count = Integer.parseInt(chars.getText().toString());
            
            if(count < 0){
            	limitMessage.show();
            	
            	return;
            }
            	
            
            
            Log.i("Yamba", Long.toString(Thread.currentThread().getId()));
            startService(new Intent(YambaAppActivity.this,TimelinePull.class));
            
            
            TextView text = (TextView) findViewById(R.id.editTextMultiline);
            
            Intent intent = new Intent(YambaAppActivity.this, SendUpdateIntentService.class);
            
            Bundle data = new Bundle();
            data.putString("text", text.getText().toString());
            data.putString("username",settings.getString("username", "vilhena"));
            data.putString("password",settings.getString("password", "laimas"));
            data.putString("url",settings.getString("url", "http://yamba.marakana.com/api"));
            
            intent.putExtra("XPTO", data);
            
            startService(intent);
            
            //intent.setClass(YambaAppActivity.this, TimelinePull.class);
            //startService(intent);
            
            //task = new CalculateTask();
            //task.execute(text.getText().toString());
            
        }
    };

}