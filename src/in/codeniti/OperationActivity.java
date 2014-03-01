package in.codeniti;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;
public class OperationActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oper);
		ImageButton but = (ImageButton)findViewById(R.id.button1);
//		Spannable buttonLabbel = new SpannableString("Display");
//		buttonLabbel.setSpan(new ImageSpan(getApplicationContext(), R.drawable.ic_launcher), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//	    but.setText(buttonLabbel);
	    
	    but.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("rohit", "display clicked");
				Intent operint = new Intent(OperationActivity.this, DisplayActivity.class);
				startActivity(operint);
			}
		});
	    
	    
	    
		ImageButton upload = (ImageButton) findViewById(R.id.uploadbut);
		upload.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("rohit", "upload clicked");
				Intent operint = new Intent(OperationActivity.this, UploadActivity.class);
				startActivity(operint);
			}
		});
		
		
//		GridView gridview = (GridView) findViewById(R.id.gridview);
//	    gridview.setAdapter(new ImageAdapter(this));
//
//	    gridview.setOnItemClickListener(new OnItemClickListener() {
//	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//	            Toast.makeText(OperationActivity.this, "" + position, Toast.LENGTH_SHORT).show();
//	        }
//	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
