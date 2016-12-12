package com.example.maxcembalest.loops.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.maxcembalest.loops.LoopActivity;
import com.example.maxcembalest.loops.R;

/**
 * Created by peter on 2016. 11. 28..
 */

public class LoopNameFragment extends DialogFragment implements DialogInterface.OnClickListener {

    EditText editName;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        editName = new EditText(getActivity());
        editName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

        return new android.app.AlertDialog.Builder(getActivity()).setTitle(R.string.loopname).setMessage(R.string.enterloopname)
                .setPositiveButton("Add", (DialogInterface.OnClickListener) this).setNegativeButton("CANCEL", null).setView(editName).create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        String value = editName.getText().toString();
        LoopActivity callingActivity = (LoopActivity) getActivity();
        callingActivity.onUserSelectValue(value);
        dialogInterface.dismiss();
    }
}
