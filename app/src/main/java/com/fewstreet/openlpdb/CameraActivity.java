package com.fewstreet.openlpdb;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.hardware.Camera;

public class CameraActivity extends ActionBarActivity {

    private Camera mCamera;
    private CameraPreview mPreview;
    private final static String TAG = "CameraActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "On Create method called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_view);

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
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
