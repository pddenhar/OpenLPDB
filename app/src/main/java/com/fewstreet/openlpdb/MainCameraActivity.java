package com.fewstreet.openlpdb;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainCameraActivity extends ActionBarActivity {

    private Camera mCamera;
    private CameraPreviewSurface mPreview;
    private final static String TAG = "MainCameraActivity";
    private ArrayList <PlateData> foundPlates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "On Create method called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_view);

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreviewSurface(this);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);

        //set up the list view with an adapter
        foundPlates = new ArrayList<PlateData>();
        final ListView platesListView = (ListView) findViewById(R.id.found_plates);
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, foundPlates);
        platesListView.setAdapter(adapter);
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "On Resume method called");
        // Create an instance of Camera
        mCamera = getCameraInstance();
        mPreview.setCamera(mCamera);
        mPreview.startCameraPreview();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "On Pause method called");
        releaseCamera();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "On Destroy method called");
        super.onDestroy();
        releasePreview();
    }

    private void releaseCamera(){
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }
    private void releasePreview() {
        if(mPreview != null){
            mPreview.destroyDrawingCache();
            mPreview = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camera_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
            Log.d(TAG, "Camera was not available to activity");
        }
        return c; // returns null if camera is unavailable
    }
}
