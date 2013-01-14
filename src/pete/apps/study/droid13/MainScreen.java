package pete.apps.study.droid13;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import java.lang.Object;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;
import android.util.Log;

public class MainScreen extends ListActivity implements OnScrollListener
{
	EndlessAdapter mAdapter = new EndlessAdapter();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		setListAdapter(mAdapter);
		getListView().setOnScrollListener(this);
    }
	
	public void onScroll(AbsListView view, int firstVisible, int visibleCount, int totalCount) {
		 Log.d("Droid13", firstVisible + " | " + visibleCount + " | " + totalCount);
		 boolean loadMore = firstVisible + visibleCount >= totalCount;
		 if(loadMore) {
			mAdapter.count += visibleCount;
			mAdapter.notifyDataSetChanged();
		 }
	}

	public void onScrollStateChanged(AbsListView v, int s) { }
	
	public class EndlessAdapter extends BaseAdapter {
		int count = 40;

		public int getCount() { return count; }
		public Object getItem(int pos) { return pos; }
		public long getItemId(int pos) { return pos; }

		public View getView(int pos, View v, ViewGroup parent) {
			TextView view;
			if(v != null) {
				view = (TextView)v;
			} else { 
				view = new TextView(MainScreen.this);
			}

			view.setText("> Item " + pos);
			return view;
		}
	}
}
