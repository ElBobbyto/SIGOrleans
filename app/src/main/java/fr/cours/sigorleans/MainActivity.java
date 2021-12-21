package fr.cours.sigorleans;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import android.app.Activity;
import android.os.Bundle;
public class MainActivity extends Activity {

    private MapView         mMapView;
    private MapController   mMapController;
    private final double longOrleans=1.909251;
    private final double latOrleans=47.902964;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Configuration.getInstance().setUserAgentValue(getPackageName());
        mMapView = (MapView) findViewById(R.id.mapview);
        mMapView.setTileSource(TileSourceFactory.MAPNIK);
        //mMapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);
        mMapController = (MapController) mMapView.getController();
        mMapController.setZoom(13);
        GeoPoint gPt = new GeoPoint(latOrleans,longOrleans);
        mMapController.setCenter(gPt);

    }
}