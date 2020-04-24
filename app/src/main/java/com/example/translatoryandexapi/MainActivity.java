package com.example.translatoryandexapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView textView;
    private MyResponse responseFromServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText);
        textView =(TextView) findViewById(R.id.tv);
        Button button = (Button) findViewById(R.id.button);
        View.OnClickListener listener = (new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String STR = et.getText().toString();
               new MyAsyncTask().execute(STR);
           }
           });

               button.setOnClickListener(listener);

    }
    class MyAsyncTask extends AsyncTask<String, String, MyResponse>{
        private String url = "https://translate.yandex.net";
        String key = "trnsl.1.1.20200417T132019Z.75bd4f4c6b4fddfd.352f7e55b8c8f39300b636e5b7f17af5a941a055";
        String str;

        @Override
        protected MyResponse doInBackground(String... params){
            str = params[0];
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserService service = retrofit.create(UserService.class);
            Call <MyResponse> response = service.getText(key,str,"en-ru", "plain");
            try{
                Response <MyResponse> userResponse = response.execute();
                responseFromServer = userResponse.body();
            } catch (IOException e){
                e.printStackTrace();
            }
            return responseFromServer;
        }
        @Override
        protected void onPostExecute(MyResponse result){
            super.onPostExecute(result);
            textView.setText(result.toString());
        }
    }
}
