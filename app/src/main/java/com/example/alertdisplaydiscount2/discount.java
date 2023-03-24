package com.example.alertdisplaydiscount2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class discount extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
    String City = "Taipei";

    String Key = "3e489c6bcf41245d69b876f81f130c11";

    String url = "https://api.openweathermap.org/data/2.5/weather?q=" + City + "&units=metric&appid=" + Key;

    public class DownloadJson extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {

            URL url;

            HttpURLConnection httpURLConnection;

            InputStream inputStream;

            InputStreamReader inputStreamReader;

            String result = "";
            try {

                url = new URL(strings[0]);

                httpURLConnection = (HttpURLConnection) url.openConnection();

                inputStream = httpURLConnection.getInputStream();

                inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data != -1) {
                    result += (char) data;

                    data = inputStreamReader.read();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    public class DownloadIcon extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {

            Bitmap bitmap =  null;

            URL url ;

            HttpsURLConnection httpsURLConnection;

            InputStream inputStream;



            try {
                url = new URL(strings[0]);

                httpsURLConnection=(HttpsURLConnection)url.openConnection();

                inputStream = httpsURLConnection.getInputStream();

                bitmap = BitmapFactory.decodeStream(inputStream);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }

    TextView txtCity,txtTime,txtValueFeelLike,txtValueHumidity,txtVision,txtTemp;
    ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_discount);

        button = (Button)findViewById(R.id.spinning_button);
        txtCity=findViewById(R.id.txtCity);
        txtTime=findViewById(R.id.txtTime);
        txtValueFeelLike = findViewById(R.id.txtValueFeelLike);
        txtValueHumidity = findViewById(R.id.txtValueHumidity);
        txtVision = findViewById(R.id.txtValueVision);
        txtTemp = findViewById(R.id.txtValue);
        imageView = findViewById(R.id.imgIcon);
        DownloadJson downloadJson = new DownloadJson();

        try {

            String result = downloadJson.execute(url).get();

            JSONObject jsonObject=new JSONObject(result);

            String temp = jsonObject.getJSONObject("main").getString("temp");

            String feel_Like = jsonObject.getJSONObject("main").getString("feels_like");

            String visibility = jsonObject.getString("visibility");

            String humidity = jsonObject.getJSONObject("main").getString("humidity");

            Long time = jsonObject.getLong("dt");

            String sTime = new SimpleDateFormat("dd-M-yyyy hh:mm:ss", Locale.TAIWAN).format(new Date(time*1000));

            txtTime.setText(sTime);
            txtCity.setText(City);
            txtVision.setText(visibility);
            txtValueFeelLike.setText(feel_Like);
            txtValueHumidity.setText(humidity);
            txtTemp.setText(temp+"℃");

            String nameIcon = "10d";

            nameIcon = jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon");

            String urlIcon ="https://openweathermap.org/img/wn/"+ nameIcon +"@4x.png";

            DownloadIcon downloadIcon = new DownloadIcon();

            Bitmap bitmap = downloadIcon.execute(urlIcon).get();

            imageView.setImageBitmap(bitmap);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Button nextPageBtn = (Button)findViewById(R.id.spinning_button);
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(discount.this,spinner_page.class);
                startActivity(intent);
            }
        });

        bottomNavigationView= findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_discount);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_sort:
                        startActivity(new Intent(getApplicationContext(),sort.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_discount:

                        return true;
                    case R.id.nav_shoppingcar:
                        startActivity(new Intent(getApplicationContext(),shoppingcart.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_member:
                        startActivity(new Intent(getApplicationContext(),member.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), home.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });

    }
}