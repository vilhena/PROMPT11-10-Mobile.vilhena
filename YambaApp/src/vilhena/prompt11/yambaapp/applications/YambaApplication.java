package vilhena.prompt11.yambaapp.applications;

import java.util.Date;

import vilhena.prompt11.yambaapp.data.DatabaseTimelineStore;
import vilhena.prompt11.yambaapp.data.ITimelineStore;
import vilhena.prompt11.yambaapp.data.MemoryTimelineStore;
import winterwell.jtwitter.Twitter;
import android.app.AlarmManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public final class YambaApplication extends Application implements SharedPreferences.OnSharedPreferenceChangeListener{

	private static final String LOG_TAG = "vilhena.yambaapp"; 
	
	private ITimelineStore _store;
	
	protected SharedPreferences _settings;
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(YambaApplication.LOG_TAG, "Application.onCreate");
		
		_store = new MemoryTimelineStore();
		//_store = new DatabaseTimelineStore(this);
		
		_settings = PreferenceManager.getDefaultSharedPreferences(this);
		_settings.registerOnSharedPreferenceChangeListener(this);
	}
	
	
	public static String dateToMessage(Date date) {
		String message = "";
		Date now = new Date();
		long dateDiff =  (now.getTime() - date.getTime())/1000;
		
		if(dateDiff < 60){ //one minute
			message = "just now";
		}else if (dateDiff < (60 * 60)) { //one hour
			message = "about " + Math.round(dateDiff/60) + " minutes";
		}else if (dateDiff < (60 * 60 * 24)) {
			message = "about " + Math.round(dateDiff/(60 * 60)) + " hours";
		}else {
			message = "a long time";
		}
		return message;
	}
	
	public SharedPreferences getSettings(){
		return _settings;
	}
	
	public static void Log(String text) {
		Log.d(YambaApplication.LOG_TAG, text);
	}
	
	public ITimelineStore getTimelineStore(){
		
		return _store;
	}
	
	public void AddSomeSampleTweets(int total){
		
		MemoryTimelineStore store = new MemoryTimelineStore();
		for (int i = 0; i < total; i++) {
			@SuppressWarnings("deprecation")
			Twitter.Status tw1 = new Twitter.Status(new Twitter.User("vilhena"), "my first tweet " + i, i);
			store.put(tw1.id, tw1);	
		}
	}


	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub
		
	}
	
	public Twitter getTwitter(){
		//TODO: return the twitter updater
		return null;
	}
	
	
	public void enableAutoUpdateTimeline(){
		//TODO: check status
//		
//		Intent i = new Intent(this, MyService.class);
//		
//		PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
//		AlarmManager am = getAlarmManager();
//		am.cancel(pi); // cancel any existing alarms
//		am.setInexactRepeating(AlarmManager.RTC,
//		    SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_DAY,
//		    AlarmManager.INTERVAL_DAY, pi);
//		
		
	}
	
	private AlarmManager getAlarmManager(){
		Context ctx = getApplicationContext();
		return (AlarmManager)  ctx.getSystemService(Context.ALARM_SERVICE); 
	}
	
	
}
