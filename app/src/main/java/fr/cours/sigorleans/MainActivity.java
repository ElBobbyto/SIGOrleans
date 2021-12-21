package fr.cours.sigorleans;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;

import org.mapsforge.map.android.layers.MyLocationOverlay;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import android.app.Activity;


import java.util.ArrayList;

public class MainActivity extends Activity {

    private MapView map;
    private IMapController mapcontroller;
    private LocationManager locationmanager;
    //private ArrayList<OverlayItem> overlayitems;
    private MyLocationNewOverlay LocationOverlay;
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private final double LONG_ORLEANS=1.909251;
    private final double LAT_ORLEANS=47.902964;
    private final double INIT_ZOOM=15;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Chargement de la configuration
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        Configuration.getInstance().setUserAgentValue(getPackageName());

        //Init map
        setContentView(R.layout.activity_main);
        map = findViewById(R.id.mapview);
        map.setTileSource(TileSourceFactory.MAPNIK);

        //Configuration de la carte
        map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        map.setMultiTouchControls(true);

        //Configuration du pt de départ
        mapcontroller = map.getController();
        mapcontroller.setZoom(INIT_ZOOM);
        GeoPoint startPoint = new GeoPoint(LAT_ORLEANS, LONG_ORLEANS);
        mapcontroller.setCenter(startPoint);

        //Create Overlays
        LocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(ctx),map);
        LocationOverlay.enableMyLocation();
        map.getOverlays().add(LocationOverlay);
    }
    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

}