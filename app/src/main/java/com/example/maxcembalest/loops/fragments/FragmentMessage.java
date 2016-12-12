package com.example.maxcembalest.loops.fragments;

/**
 * Created by Chase on 12/12/16.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.maxcembalest.loops.ProjectsActivity;
import com.example.maxcembalest.loops.R;

/**
 * Created by maxcembalest on 12/9/16.
 */

public class FragmentMessage extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String message = getArguments().getString(ProjectsActivity.KEY_MSG);

        AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(getActivity());

        alertDialogBuilder.setMessage(message);

        return alertDialogBuilder.create();
    }
}