package url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {

    public static final String BASE_URL = "hppt://10.2.2:3000/";

    public static String Cookie="";

    public static Retrofit getInstance()
    {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;


    }

}
