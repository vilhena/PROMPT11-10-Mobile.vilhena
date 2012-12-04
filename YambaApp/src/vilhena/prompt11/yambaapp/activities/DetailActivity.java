package vilhena.prompt11.yambaapp.activities;

import vilhena.prompt11.yambaapp.R;
import vilhena.prompt11.yambaapp.applications.YambaApplication;
import vilhena.prompt11.yambaapp.data.TimelineStoreContract;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends BaseActivity{
	
	public static final String KEY_INTENT_DATA = "vilhena.detail.activity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		YambaApplication.Log("DetailActivity.onCreate");
		setContentView(R.layout.detail_status_view);
		
		processIntent(getIntent());
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		YambaApplication.Log("DetailActivity.onNewIntent");
		super.onNewIntent(intent);
		processIntent(intent);
	}
	

	private void processIntent(Intent request) {
		YambaApplication.Log("DetailActivity.processIntent");
		
		Bundle data = request.getBundleExtra(KEY_INTENT_DATA);
		
		((TextView)findViewById(R.id.textViewFromUser)).setText(data.getString(TimelineStoreContract.TWEET_USER_NAME));
		((TextView)findViewById(R.id.textViewAtDate)).setText(data.getString(TimelineStoreContract.TWEET_CREATED_AT));
		((TextView)findViewById(R.id.textViewText)).setText(data.getString(TimelineStoreContract.TWEET_TEXT));
	}
	
	
	
}
