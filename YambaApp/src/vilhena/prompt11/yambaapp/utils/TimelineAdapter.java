package vilhena.prompt11.yambaapp.utils;

import vilhena.prompt11.yambaapp.R;
import vilhena.prompt11.yambaapp.applications.YambaApplication;
import vilhena.prompt11.yambaapp.data.TimelineStoreContract;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimelineAdapter extends CursorAdapter{

	public class RowData{
		
		public RowData(TextView user, TextView created, TextView text, String id ) {
			_user = user;
			_created = created;
			_text = text;
			_id = id;
		}
		
		private TextView _user;
		private TextView _created;
		private TextView _text;
		private String _id;

		public TextView getUser() {
			return _user;
		}

		public TextView getCreated() {
			return _created;
		}

		public String getId() {
			return _id;
		}
		
		public TextView getText() {
			return _text;
		}
	}
	
	
	
	private Activity _activity;
	
	public TimelineAdapter(Context context, Cursor cursor) {
		super(context, cursor);
		_activity = (Activity)context;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		YambaApplication.Log("TimelineAdapter.bindView");
		fillView(cursor,  (LinearLayout)view);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		YambaApplication.Log("TimelineAdapter.newView");
		LinearLayout row = (LinearLayout)_activity.getLayoutInflater().inflate(R.layout.timeline_item_view, null);
		
		addDataToTag(cursor, row);
		
		row.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View view, MotionEvent event) {
				RowData data = getDataFromTag(view);
				YambaApplication.Log("Touch on id " + data.getId());
				return false;
			}
		});
		
		fillView(cursor, row);
		return row;
	}

	private void addDataToTag(Cursor cursor, LinearLayout row) {
		
		TextView user = ((TextView)row.findViewById(R.id.textTimelineUser));
		TextView created = ((TextView)row.findViewById(R.id.textTimelineCreatedAt));
		TextView text = ((TextView)row.findViewById(R.id.textTimelineText));
		String id = cursor.getString(cursor.getColumnIndex(TimelineStoreContract.TWEET_ID));
		
		RowData tag = new RowData(user,created,text,id);
		
		row.setTag(tag);
	}
	
	public static RowData getDataFromTag(View row){
		return (RowData) row.getTag();
	}
	
	@Override
	protected void onContentChanged() {
		// TODO Auto-generated method stub
		super.onContentChanged();
		
		//execyyte task;
	}

	private void fillView(Cursor cursor, LinearLayout row) {
		String user =cursor.getString(cursor.getColumnIndex(TimelineStoreContract.TWEET_USER_NAME));
		String created =cursor.getString(cursor.getColumnIndex(TimelineStoreContract.TWEET_CREATED_AT));
		String text =cursor.getString(cursor.getColumnIndex(TimelineStoreContract.TWEET_TEXT));
		
		RowData data = getDataFromTag(row);
		
		data.getUser().setText(user);
		data.getCreated().setText(created);
		data.getText().setText(text);
	}

}
