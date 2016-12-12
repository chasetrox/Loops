package com.example.maxcembalest.loops;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maxcembalest.loops.adapter.FirebaseRecyclerAdapter;
import com.example.maxcembalest.loops.grid.ToneMatrix;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Chase on 12/12/16.
 */

public class ShareActivity extends BaseActivity {

    private EditText etName;
    private Button btnSend;
    private Button btnCancel;
    private String key;
    private ToneMatrix tm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        extractExtras();

        setContentView(R.layout.share_activity);
        setupUI();
    }

    private void setupUI() {
        etName = (EditText) findViewById(R.id.etName);
        initButtons();
    }

    private void extractExtras() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            key = b.getString("key");
            tm = MatrixDataManager.getInstance().load(key);
        } else {
            Toast.makeText(this, "NO KEY", Toast.LENGTH_LONG).show();
        }
    }

    private void initButtons() {
        Button btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFormValid()) {
                    Query q = FirebaseDatabase.getInstance().getReference().child("shared/" + etName.getText().toString());
                    q.getRef().push().setValue(MatrixDataManager.getInstance().generateHashfromTM(tm, key));
                    finish();
                }
            }
        });
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private boolean isFormValid() {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            etName.setError("Required");
            return false;
        }
        return true;
    }
}
