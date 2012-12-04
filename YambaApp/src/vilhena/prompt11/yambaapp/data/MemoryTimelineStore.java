package vilhena.prompt11.yambaapp.data;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import vilhena.prompt11.yambaapp.applications.YambaApplication;
import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;

public class MemoryTimelineStore implements ITimelineStore, Iterable<Twitter.Status>, Map<Long, Twitter.Status>, Iterator<Twitter.Status> {

	private static AtomicReference<ConcurrentHashMap<Long, Twitter.Status>> _store;
	
	public MemoryTimelineStore() {
		YambaApplication.Log("TimelineStore.timelineStore");
		//singleton
		if(_store == null){
			_store = new AtomicReference<ConcurrentHashMap<Long, Twitter.Status>>();
			_store.set(new ConcurrentHashMap<Long, Twitter.Status>());
		}
	}
	

	public Iterator<Status> iterator() {
		YambaApplication.Log("TimelineStore.iterator");
		return _store.get().values().iterator();
	}


	public void clear() {
		_store.getAndSet(new ConcurrentHashMap<Long, Twitter.Status>());
	}


	public boolean containsKey(Object key) {
		return _store.get().containsKey(key);
	}


	public boolean containsValue(Object value) {
		return _store.get().containsValue(value);
	}


	public Set<Entry<Long, Status>> entrySet() {
		return _store.get().entrySet();
	}


	public Status get(Object key) {
		return _store.get().get(key);
	}


	public boolean isEmpty() {
		return _store.get().isEmpty();
	}


	public Set<Long> keySet() {
		return _store.get().keySet();
	}


	public Status put(Long key, Status value) {
		return _store.get().put(key, value);
	}


	public void putAll(Map<? extends Long, ? extends Status> arg0) {
		_store.get().putAll(arg0);
	}


	public Status remove(Object key) {
		return _store.get().remove(key);
	}


	public int size() {
		return _store.get().size();
	}


	public Collection<Status> values() {
		return _store.get().values();
	}


	public boolean hasNext() {
		return iterator().hasNext();
	}


	public Status next() {
		return iterator().next();
	}


	public void remove() {
		iterator().remove();
	}


	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}


	public int insert(ContentValues values) {
		// TODO Auto-generated method stub
		return 0;
	}

}
