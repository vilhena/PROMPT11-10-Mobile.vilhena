package vilhena.prompt11.yambaapp.contents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vilhena.prompt11.yambaapp.applications.YambaApplication;
import vilhena.prompt11.yambaapp.data.DatabaseTimelineStore;
import vilhena.prompt11.yambaapp.data.ITimelineStore;
import vilhena.prompt11.yambaapp.data.MemoryTimelineStore;
import vilhena.prompt11.yambaapp.data.TimelineStoreContract;
import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class TimelineProvider extends ContentProvider {
	
	private static final int STATUS_ITEM_LIST = 0;
	private static final int STATUS_ITEM_ID = 1;
	
	//private MemoryTimelineStore _store;
	
	private static final UriMatcher _UriMatcher;
	private static final String[] _MimeTypes;
		
	static {
		_UriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		_UriMatcher.addURI(TimelineStoreContract.AUTHORITY, "user", STATUS_ITEM_LIST);
		_UriMatcher.addURI(TimelineStoreContract.AUTHORITY, "user/#", STATUS_ITEM_ID);
		_MimeTypes = new String[]{
				TimelineStoreContract.TIMELINE_LIST_TYPE,
				TimelineStoreContract.TIMELINE_ITEM_TYPE
				};
	}
	
	private YambaApplication _application;
	

	@Override
	public boolean onCreate() {
		_application = (YambaApplication)getContext().getApplicationContext();
		return false;
	}
	
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		int matchCode= _UriMatcher.match(uri);
		if(matchCode == UriMatcher.NO_MATCH){
			YambaApplication.Log("getType NoMatch found for " + uri.toString());
			return null;
		}
		return _MimeTypes[matchCode];
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		int matchCode = _UriMatcher.match(uri);
		YambaApplication.Log("query matchCode " + String.valueOf(matchCode));
		YambaApplication.Log("query uri " + String.valueOf(uri));
		
		switch (matchCode) {
		case STATUS_ITEM_LIST:
			YambaApplication.Log("query STATUS_ITEM_LIST found for " + uri.toString());
			
			return TimelineStoreContract.adapt((Iterable<Twitter.Status>)_application.getTimelineStore());
			
			//return _application.getTimelineStore().query(uri, projection, selection, selectionArgs, sortOrder);		
					
		case STATUS_ITEM_ID:
			YambaApplication.Log("query STATUS_ITEM_ID found for " + uri.toString());
			
			long userId  = -1;
			try{
				userId = Long.valueOf(uri.getLastPathSegment());
			}catch (NumberFormatException nfe) {
				YambaApplication.Log("invalid userId detected " + userId);
				return null;
			}
			
			YambaApplication.Log("userId detected " + userId);
			
			Twitter.Status ts = null; //= _application.getTimelineStore().get(userId);
			YambaApplication.Log("status from store " + ts.getId());
			
			List<Twitter.Status> tslist = new ArrayList<Twitter.Status>(1);
			tslist.add(ts);
			
			return TimelineStoreContract.adapt(tslist, projection);
			
		default:

			YambaApplication.Log("query NoMatch found for " + uri.toString());
			return null;
		}
		
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
