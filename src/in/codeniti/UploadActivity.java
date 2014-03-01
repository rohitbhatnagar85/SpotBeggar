package in.codeniti;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class UploadActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload);
		Button search = (Button) findViewById(R.id.clickbut);
		search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Camera camera;
				 int cameraId = 0;
				Log.i("rohit", "click button clicked");
				
				 Camera.PictureCallback mPicture = new Camera.PictureCallback() {

			        @Override
			        public void onPictureTaken(byte[] data, Camera camera) {

			            File pictureFile = getOutputMediaFile();
			            if (pictureFile == null) {
			                return;
			            }

			            try {
			                FileOutputStream fos = new FileOutputStream(pictureFile);
			                fos.write(data);
			                fos.close();
			            } catch (FileNotFoundException e) {
			                Log.d("MotionDetector", "File not found: " + e.getMessage());
			            } catch (IOException e) {
			                Log.d("MotionDetector", "Error accessing file: " + e.getMessage());
			            }
			        }
			    };
			    
				if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
				      Toast.makeText(UploadActivity.this, "Camera not found",Toast.LENGTH_LONG).show();
				    } else {
				      cameraId = CameraInfo.CAMERA_FACING_BACK;
				      if (cameraId < 0) {
				        Toast.makeText(UploadActivity.this, "No front facing camera found.",Toast.LENGTH_LONG).show();
				      } else {
				        camera = Camera.open(cameraId);
				        camera.takePicture(null, null, mPicture);

				        //camera.takePicture(null, null,null,null);
				      }
				    }
			}
		});
		
	}
	private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "motiondetect");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir, timeStamp + ".jpg");

        return mediaFile;
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	


}
