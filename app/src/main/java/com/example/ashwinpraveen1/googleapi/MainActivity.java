package com.example.ashwinpraveen1.googleapi;

import android.content.IntentSender;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;



public class MainActivity extends ActionBarActivity implements GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks{

    TextView checking;
    GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GDriveLogin();

    }

    @Override
    public void onConnected(Bundle connectionHint) {

        checking = (TextView) findViewById(R.id.checking);
        checking.setText("working");
//        super.onConnected(connectionHint);
//        IntentSender intentSender = Drive.DriveApi
//                .newOpenFileActivityBuilder()
//                .setMimeType(new String[] { "text/plain", "text/html" })
//                .build(getGoogleApiClient());
//        try {
//            startIntentSenderForResult(
//                    intentSender, REQUEST_CODE_OPENER, null, 0, 0, 0);
//        } catch (SendIntentException e) {
//            Log.w(TAG, "Unable to send intent", e);
//        }
    }

//    ResultCallback<DriveContentsResult> newDriveContentsCallback = new
//            ResultCallback<DriveContentsResult>() {
//                @Override
//                public void onResult(DriveContentsResult result) {
//                    MetadataChangeSet metadataChangeSet = new MetadataChangeSet.Builder()
//                            .setMimeType("text/html").build();
//                    IntentSender intentSender = Drive.DriveApi
//                            .newCreateFileActivityBuilder()
//                            .setInitialMetadata(metadataChangeSet)
//                            .setInitialDriveContents(result.getDriveContents())
//                            .build(getGoogleApiClient());
//                    try {
//                        startIntentSenderForResult(
//                                intentSender, REQUEST_CODE_CREATOR, null, 0, 0, 0);
//                    } catch (SendIntentException e) {
//                        Log.w(TAG, "Unable to send intent", e);
//                    }
//                }
//            }

//    ResultCallback<DriveContentsResult> contentsCallback = new
//            ResultCallback<DriveContentsResult>() {
//                @Override
//                public void onResult(DriveContentsResult result) {
//                    if (!result.getStatus().isSuccess()) {
//                        // Handle error
//                        return;
//                    }
//
//                    MetadataChangeSet metadataChangeSet = new MetadataChangeSet.Builder()
//                            .setMimeType("text/html").build();
//                    IntentSender intentSender = Drive.DriveApi
//                            .newCreateFileActivityBuilder()
//                            .setInitialMetadata(metadataChangeSet)
//                            .setInitialDriveContents(result.getDriveContents())
//                            .build(getGoogleApiClient());
//                    try {
//                        startIntentSenderForResult(intentSender, 1, null, 0, 0, 0);
//                    } catch (SendIntentException e) {
//                        // Handle the exception
//                    }
//                }
//            }

    void GDriveLogin(){

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Drive.API)
                .addScope(Drive.SCOPE_FILE)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }



    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        checking = (TextView) findViewById(R.id.checking);
        checking.setText("Not working");
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, 0);
            } catch (IntentSender.SendIntentException e) {
                // Unable to resolve, message user appropriately
            }
        } else {
            GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this, 0).show();
        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
