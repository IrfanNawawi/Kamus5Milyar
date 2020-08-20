package com.example.irfannawawi.kamus5milyar.view;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.irfannawawi.kamus5milyar.R;
import com.example.irfannawawi.kamus5milyar.db.KamusHelper;
import com.example.irfannawawi.kamus5milyar.model.KamusItem;
import com.example.irfannawawi.kamus5milyar.preference.AppPreference;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = findViewById(R.id.progresBar);
        new LoadData().execute();
    }

    public ArrayList<KamusItem> preLoadRaw(int trans) {
        ArrayList<KamusItem> mahasiswaModels = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(trans);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                KamusItem kamusItem;

                kamusItem = new KamusItem(splitstr[0], splitstr[1]);
                mahasiswaModels.add(kamusItem);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mahasiswaModels;
    }

    private class LoadData extends AsyncTask<Void, Integer, Void> {
        final String TAG = LoadData.class.getSimpleName();

        KamusHelper helper;
        AppPreference preference;
        double progress;
        double maxProgress = 100;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            helper = new KamusHelper(SplashActivity.this);
            preference = new AppPreference(SplashActivity.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Boolean firstRun = preference.getFirstRun();
            if (firstRun) {
                ArrayList<KamusItem> inggris = preLoadRaw(R.raw.english_indonesia);
                ArrayList<KamusItem> indonesia = preLoadRaw(R.raw.indonesia_english);
                helper.open();
                progress = 30;
                publishProgress((int) progress);
                Double progressMaxInsert = 80.0;
                Double proressDiff = (progressMaxInsert - progress) / (inggris.size() + indonesia.size());
                helper.beginTransaction();

                try {
                    for (KamusItem ing : inggris) {
                        helper.insertTransaction(ing, true);
                        progress += proressDiff;
                        publishProgress((int) progress);
                    }
                    for (KamusItem idn : indonesia) {
                        helper.insertTransaction(idn, false);
                        progress += proressDiff;
                        publishProgress((int) progress);
                    }
                    // Jika semua proses telah di set success maka akan di commit ke database
                    helper.setTransactionSuccess();
                } catch (Exception e) {
                    // Jika gagal maka do nothing
                    Log.e(TAG, "doInBackground: Exception");
                }

                helper.endTransaction();

                helper.close();
                preference.setFirstRun(false);
                publishProgress((int) maxProgress);
            } else {
                try {
                    synchronized (this) {
                        this.wait(2000);
                        publishProgress(50);
                        this.wait(2000);
                        publishProgress((int) maxProgress);
                    }
                } catch (Exception e) {

                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }
}
