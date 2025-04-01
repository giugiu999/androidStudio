package com.example.yiru15_mymoviewishlist;
import androidx.fragment.app.DialogFragment;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Dialog;

public class AddMovieFragment extends DialogFragment {
    interface AddMovieDialogListener {
        void addMovie(Movie movie);
    }
    private AddMovieDialogListener listener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddMovieDialogListener) {
            listener = (AddMovieDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddMovieDialogListener");
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view =
                LayoutInflater.from(getContext()).inflate(R.layout.add_fragment, null);
        EditText editMovieName = view.findViewById(R.id.name);
        EditText editdName = view.findViewById(R.id.dname);
        EditText editg = view.findViewById(R.id.g);
        EditText edittime = view.findViewById(R.id.time);
        EditText editstatus = view.findViewById(R.id.status);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add a movie")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add", (dialog, which) -> {
                    String name = editMovieName.getText().toString();
                    String dname = editdName.getText().toString();
                    String g = editg.getText().toString();
                    String timeStr = edittime.getText().toString();
                    int time = 0;
                    if (!timeStr.isEmpty()) {
                        try {
                            time = Integer.parseInt(timeStr);
                        } catch (NumberFormatException e) {
                            // 如果用户输入的不是有效的整数，设置为默认值或显示错误
                            time = 0;  // 可以选择提示用户输入有效的年份
                        }
                    }
                    String statusStr = editstatus.getText().toString().toLowerCase(); // 转换为小写以支持 "true"/"false"
                    boolean status = false;  // 默认值为 false
                    if (statusStr.equals("true")) {
                        status = true;
                    } else if (statusStr.equals("false")) {
                        status = false;
                    }

                    listener.addMovie(new Movie(name,dname,g,time,status));
                })
                .create();
    }
}
