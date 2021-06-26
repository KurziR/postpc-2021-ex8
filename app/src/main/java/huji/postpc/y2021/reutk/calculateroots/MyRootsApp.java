package huji.postpc.y2021.reutk.calculateroots;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.WorkManager;

public class MyRootsApp extends Application {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WorkManager workManager = WorkManager.getInstance(this);
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(CalculateRootsWorker.class)
                .build();

        workManager.enqueueUniqueWork(request);

        int work_id = request.id;
    }
}
