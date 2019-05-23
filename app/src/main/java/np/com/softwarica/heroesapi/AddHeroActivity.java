package np.com.softwarica.heroesapi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import HeroAPI.HeroesAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddHeroActivity extends AppCompatActivity {
    EditText etname, etdesc;
    ImageView imgView;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hero);
        etname=findViewById(R.id.etHeroName);
        etdesc=findViewById(R.id.etHeroDesc);
        btnSave=findViewById(R.id.btnSave);
        imgView=findViewById(R.id.imgView);
//        loadFormURL();
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }

            private void BrowseImage() {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,0);
            }

            @Override
            public void hashCode() {
                return super.hashCode();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }

            private void save() {
                String name=etname.getText().toString();
                String desc=etdesc.getText().toString();
                Map<String,String> map =new HashMap<>();
                map.put("name",name);
                map.put("desc", desc);

                Retrofit retrofit= new Retrofit.Builder().baseUrl("http://10.0.2.2:3000").addConverterFactory(GsonConverterFactory.create()).build();
                HeroesAPI heroesAPI=retrofit.create(HeroesAPI.class);
              //  Call<Void> heroesCall=heroesAPI.addHero(name, desc);
                Call<Void> heroesCall=heroesAPI.addHero(map);
                heroesCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(AddHeroActivity.this, "Code: "+response.code(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(AddHeroActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
    private void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy= new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }
//    public void loadFormURL(){
//        StrictMode();
//        try {
//            String imgUrl="https://www.sideshow.com/product-asset/903429/feature";
//            URL url=new URL(imgUrl);
//            imgView.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
//        }catch (IOException e){
//            Toast.makeText(AddHeroActivity.this, "Error", Toast.LENGTH_SHORT).show();
//        }
//    }
}
