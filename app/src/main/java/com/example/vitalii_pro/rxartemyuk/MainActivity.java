package com.example.vitalii_pro.rxartemyuk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Button start1;
    Button stop;

    TextView textView;

    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);

        findViewById(R.id.start1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                disposable = Observable.interval(2,1, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                textView.setText(String.valueOf(aLong));
                                Log.d("myLog", "accept: " + aLong);
                            }
                        });


            }
        });

        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disposable.dispose();
            }
        });


    }
}
