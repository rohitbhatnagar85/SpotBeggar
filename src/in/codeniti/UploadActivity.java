package in.codeniti;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.location.*;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;

import android.provider.MediaStore;

public class UploadActivity extends Activity {
	static final int REQUEST_IMAGE_CAPTURE = 1;
	private ImageView mImageView;
	private Context mycontext;
	
	private boolean gen = false;
	private CheckBox cb= null;
	byte[] imageBytes = new byte[0];
	

	private void dispatchTakePictureIntent() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload);

		Log.i("rohit", "now getting location");
		//getUserLocation();
		((TextView)findViewById(R.id.tv_lat)).setText(""+MainActivity.latitude);
		((TextView)findViewById(R.id.tv_long)).setText(""+MainActivity.longitude);

		cb = (CheckBox)findViewById(R.id.cb_gen);
		
		mycontext = getApplicationContext();
		mImageView = (ImageView) findViewById(R.id.imgcurrent);
		Button search = (Button) findViewById(R.id.clickbut);
		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i("rohit", "click button clicked");
				dispatchTakePictureIntent();

			}
		});

		Button save = (Button)findViewById(R.id.save);
		save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i("rohit", "savek button clicked");
				if(cb!=null){
					gen = cb.isChecked();
					cb.setChecked(false);
				}
				addToDb();
				Toast.makeText(mycontext, "Success! new spot has beed added", Toast.LENGTH_LONG);
				Intent operint = new Intent(UploadActivity.this, DisplayActivity.class);
				finish();
				startActivity(operint);
				
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
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
			imageBytes = stream.toByteArray();
			
			mImageView.setImageBitmap(imageBitmap);
		}

		// Toast.makeText(mycontext, location.toString(), Toast.LENGTH_LONG);
	}

	
	
	public void addToDb(){
		DataStore ds = new DataStore();
		ds.genuine(gen);
		ds.put("image", imageBytes, "beggar.jpg");
		ds.putLocation(MainActivity.latitude, MainActivity.longitude);
		ds.save();
	}

}
