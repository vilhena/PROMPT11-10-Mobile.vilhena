package vilhena.prompt11.yambaapp.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public interface ITimelineStore {

	public abstract Cursor query(Uri uri, String[] projection,
			String selection, String[] selectionArgs, String sortOrder);

	public abstract int insert(ContentValues values);

}