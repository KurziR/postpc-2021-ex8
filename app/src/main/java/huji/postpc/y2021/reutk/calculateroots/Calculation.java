package huji.postpc.y2021.reutk.calculateroots;


import com.google.gson.Gson;

import java.io.Serializable;


public class Calculation implements Comparable<Calculation>, Serializable {


    enum Status {
        IN_PROGRESS,
        DONE
    }

    private String requestId;
    int progress = 0;
    long numToCalc;

    Status status;

    long[] roots;

    public Calculation(long number) {
//        this.requestId = requestId;
        this.numToCalc = number;
        this.status = Status.IN_PROGRESS;
        this.roots = new long[2];
    }

    public String getRequestId() {
        return this.requestId;
    }

    public long getNemToCalc() {
        return this.numToCalc;
    }

    public long[] getRoots() {
        return this.roots;
    }

    public int getProgress() {
        return this.progress;
    }

    public Status  getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int compareTo(Calculation o) {
        if (this.status == Status.IN_PROGRESS && o.status == Status.DONE) {
            return -1;
        } else if (this.status == Status.DONE && o.status == Status.IN_PROGRESS) {
            return 1;
        }
        return (int) (numToCalc - o.numToCalc);
    }

    public String calcToString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Calculation stringToCalc(String string) {
        Gson gson = new Gson();
        return gson.fromJson(string, Calculation.class);
    }



}
