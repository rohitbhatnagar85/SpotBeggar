package in.codeniti;

import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;
public class DisplayActivity extends Activity {
	public static List<HashMap<String, Object>> beggars;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disp);
		Toast.makeText(getApplicationContext(), "Loading images please wait!!", Toast.LENGTH_LONG);
		DataStore ds = new DataStore();
		ds.findNearBy(MainActivity.latitude, MainActivity.longitude, new DataStore.DataBack() {
			
			@Override
			public void queryResult(List<HashMap<String, Object>> res) {
				beggars = res;
				GridView gridview = (GridView) findViewById(R.id.gridview);
			    gridview.setAdapter(new ImageAdapter(DisplayActivity.this));

			    gridview.setOnItemClickListener(new OnItemClickListener() {
			        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
			            Toast.makeText(DisplayActivity.this, "" + position, Toast.LENGTH_SHORT).show();
			        }
			    });
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_refresh:
	        	Intent intent = getIntent();
	            finish();
	            startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
