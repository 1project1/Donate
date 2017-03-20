package app.project.donate.ngolocator;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by ArupPc on 19-03-2017.
 */

public class UserLocation extends Service {

    Context context;
    double latitude, longitude;
    Location location;
    LocationManager locationManager;
    LocationListener locationListener;
    private final String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.INTERNET};


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
