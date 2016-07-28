package utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by YanTi on 2016/7/27.
 */
public class AuthorLoadData {

    private  Context mContext;
    private String path;
    public AuthorLoadData(Context context,String path) {
        this.mContext = context;
        this.path = path;
    }

    public OnDataLoadedListener mListener;

    public void setListener(OnDataLoadedListener listener) {
        mListener = listener;
    }

    public void loadData(){

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(path).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String json = response.body().string();


                if (!TextUtils.isEmpty(json)){
                    ((Activity)mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mListener.onDataLoadedListener(json);
                        }
                    });
                }


            }
        });

    }

    public interface OnDataLoadedListener{
        void onDataLoadedListener(String json);
    }
}
