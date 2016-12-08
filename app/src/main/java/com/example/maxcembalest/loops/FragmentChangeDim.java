package com.example.maxcembalest.loops;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by maxcembalest on 12/8/16.
 */

public class FragmentChangeDim extends DialogFragment implements DialogInterface.OnClickListener {


    private String[] options1 = {"1", "2", "3", "4", "5", "6", "7", "8",};
    private String[] options2 = {"50", "100", "200", "300", "500", "750", "1000"};
    private OptionsFragmentInterface optionsFragmentInterface;

    String type;

    public interface OptionsFragmentInterface {
        public void onOptionsFragmentResult(String newItem, String type);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            optionsFragmentInterface =
                    (OptionsFragmentInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OptionsFragmentInterface");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        type = getArguments().getString(LoopActivity.KEY_TYPE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (type == "row" || type == "col") {
            builder.setTitle("Change " + type + " dimension");
            builder.setItems(options1, this);
        }
        else{
            builder.setTitle("Change note duration (milliseconds)");
            builder.setItems(options2, this);
        }
        AlertDialog alert = builder.create();

        return alert;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if (type == "row" || type == "col") {
            optionsFragmentInterface.onOptionsFragmentResult(options1[i], type);
        }
        else{
            optionsFragmentInterface.onOptionsFragmentResult(options2[i], type);
        }
    }
}
