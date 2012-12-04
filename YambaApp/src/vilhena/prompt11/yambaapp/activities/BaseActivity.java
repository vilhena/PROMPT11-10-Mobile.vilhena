package vilhena.prompt11.yambaapp.activities;

import vilhena.prompt11.yambaapp.R;
import vilhena.prompt11.yambaapp.applications.YambaApplication;
import vilhena.prompt11.yambaapp.data.TimelineStoreContract;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;

public class BaseActivity extends Activity {

	protected YambaApplication _application;
	protected SharedPreferences _settings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		YambaApplication.Log("BaseActivity.onCreate");
		super.onCreate(savedInstanceState);
		
		_application = (YambaApplication) getApplication();
		
	}
	
	
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO Aqui valido se faz sentido ter determinadas
		YambaApplication.Log("BaseActivity.onMenuOpened");
		return super.onMenuOpened(featureId, menu);
	}
	
	
	
	@Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
		YambaApplication.Log("BaseActivity.onOptionsItemSelected");
		
		switch (item.getItemId()) {
		case R.id.preferences:
			YambaApplication.Log("Preferences Selected");
			startActivity(new Intent(this, vilhena.prompt11.yambaapp.activities.UserPreferencesActivity.class));
			
			break;
		case R.id.sendnew:
			YambaApplication.Log("Send New Selected");
			startActivity(new Intent(this, vilhena.prompt11.yambaapp.activities.UpdateStatusActivity.class));
			
			break;
		case R.id.refresh:
			YambaApplication.Log("Refresh Selected");
			_application.getContentResolver().notifyChange(TimelineStoreContract.CONTENT_URI, null);
			
			
			break;

		default:
			break;
		}
		
		
    	
    	
    	return true;
    };
    
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
    	YambaApplication.Log("BaseActivity.onCreateOptionsMenu");
    	getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    };
	
}
