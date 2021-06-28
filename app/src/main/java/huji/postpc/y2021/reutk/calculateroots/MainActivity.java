package huji.postpc.y2021.reutk.calculateroots;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.Serializable;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    CalculateRootsAdapter adapter = new CalculateRootsAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerCalcRootsList = findViewById(R.id.CalcListView);
        recyclerCalcRootsList.setAdapter(adapter);
        recyclerCalcRootsList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        Button buttonCalcRoots = findViewById(R.id.buttonCalculateRoots);
        EditText numToCalc = findViewById(R.id.numToCalc);


        buttonCalcRoots.setOnClickListener(v -> {
            String userInputString = numToCalc.getText().toString();
            if (!isValidInput(userInputString)) {
                Toast toast = Toast.makeText(this, "Please insert a positive number", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            numToCalc.setText(""); // cleanup text in edit-text
            long userInputLong = Long.parseLong(userInputString);
            Calculation calc = new Calculation(userInputLong);
            addCalcWorker(calc, true);
        });
    }

    public void addCalcWorker(Calculation calc, boolean shouldCreateWorker) {
        WorkManager workManager = WorkManager.getInstance(this);
        adapter.addCalc(calc);
        adapter.notifyDataSetChanged();
        String tag = "calc-" + calc.getNemToCalc();
        if (shouldCreateWorker) {
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(CalculateRootsWorker.class)
                    .setInputData(new Data.Builder()
                            .putLong("targetNum", calc.getNemToCalc())
                            .build())
                    .addTag(tag)
                    .build();
            workManager.enqueue(request);
        }

        LiveData<List<WorkInfo>> info = WorkManager.getInstance(MainActivity.this)
                .getWorkInfosByTagLiveData(tag);
        info.observe(this, new Observer<List<WorkInfo>>() {
            @Override
            public void onChanged(List<WorkInfo> workInfos) {
                for (WorkInfo workInfo : workInfos) {
                    if (workInfo.getState() == WorkInfo.State.SUCCEEDED){
                        Data outputData = workInfo.getOutputData();
                        calc.setRoots(outputData.getLongArray("roots"));
                        calc.setStatus(Calculation.Status.DONE);
                    } else if(workInfo.getState() == WorkInfo.State.RUNNING){
                        int progress = workInfo.getProgress().getInt("progress", 0);
                        calc.setProgress(progress);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        List<Calculation> calculations = adapter.getCalculations();
        outState.putSerializable("calculations", (Serializable) calculations);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        List<Calculation> calculations = (List<Calculation>) savedInstanceState.getSerializable("calculations");
        for (Calculation calc:calculations) {
            if (calc.getStatus() == Calculation.Status.IN_PROGRESS) {
                addCalcWorker(calc, false);
            } else {
                adapter.addCalc(calc);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private boolean isValidInput (String userInputString){
        return userInputString.length() > 0 && Long.parseLong(userInputString) > 0;
    }
}


