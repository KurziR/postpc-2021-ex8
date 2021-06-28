package huji.postpc.y2021.reutk.calculateroots;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class CalculateRootsWorker extends Worker {

    private final long MAX_DURATION_MILLIS = 10*60*1000;
    private final Context context;

    public CalculateRootsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        long timeStartMs = System.currentTimeMillis();
        long targetNum = getInputData().getLong("targetNum", 0);
        long root = getInputData().getLong("startingNum", 3);
        Log.d("worker", "start work" +  targetNum + " " + root);
        if (targetNum % 2 == 0) {
            return returnResult(2, targetNum / 2);
        }
        long sqrt = (long) Math.ceil(Math.sqrt(targetNum));
        int currentProgress = 0;
        while (System.currentTimeMillis() - timeStartMs < MAX_DURATION_MILLIS) {
            if (targetNum % root == 0) {
                return returnResult(root, targetNum / root);
            } else if (root > sqrt) {
                return returnResult(targetNum);
            } else if ((int) (100 * root / sqrt) > currentProgress) {
                currentProgress = (int) (100 * root / sqrt);
                setProgressAsync(
                        new Data.Builder()
                                .putInt("progress", currentProgress)
                                .build()
                );
            }
            root += 2;
        }
        String tag = "calc-"+ String.valueOf(targetNum);
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(CalculateRootsWorker.class)
                .setInputData(new Data.Builder()
                        .putLong("targetNum", targetNum)
                        .putLong("startingNum", root)
                        .build())
                .addTag(tag)
                .build();
        WorkManager workManager = WorkManager.getInstance(context);
        workManager.enqueue(request);
        Log.d("worker", "finished" + root);
        return Result.failure();
    }


    private Result returnResult(long... roots) {
        return Result.success(
                new Data.Builder()
                        .putLongArray("roots", roots)
                        .build()
        );
    }
}

