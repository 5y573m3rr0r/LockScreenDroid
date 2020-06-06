package com.noname.demolockscreendroid;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button disableButton, enableButton,lockButton;
    public static final int RESULT_ENABLE =11;
    private DevicePolicyManager devicePolicyManager;
    private ActivityManager activityManager;
    private ComponentName componentName;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar customizedToolbar = (Toolbar) findViewById(R.id.action_toolbar);
        setSupportActionBar(customizedToolbar);
        devicePolicyManager= (DevicePolicyManager)getSystemService( DEVICE_POLICY_SERVICE );
        activityManager = (ActivityManager)getSystemService( ACTIVITY_SERVICE );
        componentName = new ComponentName( this , DeviceAdmin.class );
        lockButton = (Button)findViewById( R.id.lock );
        enableButton = (Button)findViewById( R.id.enable_pernmission_button );
        disableButton = (Button)findViewById( R.id.disable_permission_button );
        lockButton.setOnClickListener( this::onClick );
        enableButton.setOnClickListener( this::onClick );
        disableButton.setOnClickListener( this::onClick );
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isActive = devicePolicyManager.isAdminActive( componentName );
    }

    @Override
    public void onClick(View view) {
    if(view ==lockButton){
            boolean active = devicePolicyManager.isAdminActive( componentName );
            if(active){
                devicePolicyManager.lockNow();
            }else{
                Toast.makeText( this,"Please enable Admin Device Permission",Toast.LENGTH_SHORT ).show();
            }
    } else if ( view == enableButton){
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra( DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName );
        intent.putExtra( DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Additional text explains why we need permission" );
        startActivityForResult( intent,RESULT_ENABLE );
    } else if (view == disableButton) {
        devicePolicyManager.removeActiveAdmin( componentName );
        disableButton.setVisibility( View.GONE );
        enableButton.setVisibility( View.VISIBLE );
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case RESULT_ENABLE:
                if(resultCode == Activity.RESULT_OK){
                    Toast.makeText( MainActivity.this, "You ENABLED device Admin permission",Toast.LENGTH_SHORT ).show();
                }else{
                    Toast.makeText( MainActivity.this, "ERROR to ENABLE device Admin permission",Toast.LENGTH_SHORT ).show();
                }
                break;
        }
        super.onActivityResult( requestCode, resultCode, data );

    }
}