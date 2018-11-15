package comaa.example.hanhyeonmin.trans;

import java.io.Serializable;
import java.util.ArrayList;

public class ResultBody<T> implements Serializable {
    private String success;
    private String size;
    private ArrayList<T> datas;
    private String error;

    public ResultBody(String success, String size, ArrayList<T> data, String error) {
        this.success = success;
        this.size = size;
        this.datas = data;
        this.error = error;
    }

    public String getSuccess() {
        return success;
    }

    public String getSize() {
        return size;
    }

    public ArrayList<T> getDatas() {
        return datas;
    }

    public void setData(ArrayList<T> data) {
        this.datas = data;
    }

    public String getError() {
        return error;
    }
}
