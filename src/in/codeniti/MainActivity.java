package in.codeniti;

import com.parse.Parse;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static double latitude = 0;
	public static double longitude = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getUserLocation();
		Parse.initialize(this.getApplicationContext(), "QCgWP64B2hpcupjT22Mfm0BpstNpaz3MgvtwiRlW", "rZCMtlHwjJJfyhryPDl7WKO9pFy5IrhIPrJVVIms");
		ImageButton login = (ImageButton) findViewById(R.id.loginbut);
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent operint = new Intent(MainActivity.this, OperationActivity.class);
				startActivity(operint);
			}
		});
		
		ImageButton login2 = (ImageButton) findViewById(R.id.loginbut2);
		login2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent operint = new Intent(MainActivity.this, OperationActivity.class);
				startActivity(operint);
			}
		});
		
		ImageButton login3 = (ImageButton) findViewById(R.id.loginbut3);
		login3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent operint = new Intent(MainActivity.this, OperationActivity.class);
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

	private void getUserLocation() {
		Log.d("Rohit", "Coming here ");
		LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				// Called when a new location is found by the network location
				// provider.
				// makeUseOfNewLocation(location);
				latitude = location.getLatitude();
				longitude = location.getLongitude();
				Log.d("Rohit", "Coming here agin");
				System.out.println(" Location latitude "
						+ location.getLatitude());
				System.out.println("Location longitude "
						+ location.getLongitude());
					}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}
		};
		Log.d("Rohit", "Coming here third");
		// Register the listener with the Location Manager to receive location
		// updates
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

	}
}
