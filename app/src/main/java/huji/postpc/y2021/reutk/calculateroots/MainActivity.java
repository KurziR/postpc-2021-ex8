package huji.postpc.y2021.reutk.calculateroots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        WorkManager workManager = WorkManager.getInstance(this);
//        MyRootsApp app = (MyRootsApp) getApplication();
//
//        LiveData<List<WorkInfo>> all_works_live_data = workManager.getWorkInfosLiveData();
//        all_works_live_data.observe(this, new Observer<List<WorkInfo>>() {
//            @Override
//            public void onChanged(List<WorkInfo> workInfos) {
//
//            }
//        });

        FloatingActionButton buttonCreateTodoItem = findViewById(R.id.buttonCalculateRoots);
        EditText editTextInsertTask = findViewById(R.id.editTextInputNumber);

        buttonCreateTodoItem.setOnClickListener(v -> {
            String userInputString = editTextInsertTask.getText().toString();
            if(userInputString.equals(""))
                return;
            holder.addNewInProgressItem(userInputString);
            editTextInsertTask.setText(""); // cleanup text in edit-text
            Objects.requireNonNull(recyclerTodoItemsList.getAdapter()).notifyDataSetChanged();
            adapter.notifyDataSetChanged();
        });


    }
}