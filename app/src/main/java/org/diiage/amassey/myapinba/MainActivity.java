package org.diiage.amassey.myapinba;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.diiage.amassey.myapinba.Models.Badge;
import org.diiage.amassey.myapinba.Models.Clan;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Clan> clans;
    ListView listClans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String baseUrlApi = getResources().getString(R.string.base_url_api);


        URL baseUrl = null;

        try{
            baseUrl = new URL(baseUrlApi);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        asyncTask.execute(baseUrlApi);
    }

    @SuppressLint("StaticFieldLeak")
    AsyncTask<String, Void, JSONArray> asyncTask = new AsyncTask<String, Void, JSONArray>() {
        @Override
        protected JSONArray doInBackground(String... strings) {

            URL baseUrl = null;
            try {
                baseUrl = new URL(strings[0]);

                InputStream inputStream = null;

                //Ouverture de la connexion depuis l'url
                HttpURLConnection connection = (HttpURLConnection)baseUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("auth", "d1735961cc7c4d7588b4535967c6aabff14b307d5c3445b5a11d5231b502abb2");
                inputStream = connection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();

                String lineBuffer = null;
                while ((lineBuffer = bufferedReader.readLine()) != null){
                    stringBuilder.append(lineBuffer);
                }

                String data = stringBuilder.toString();

                return new JSONArray(data);


            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);
            try {

                clans = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = null;

                    jsonObject = jsonArray.getJSONObject(i);

                    Clan clan = new Clan();

                    clan.setTag(jsonObject.getString("tag"));
                    clan.setName(jsonObject.getString("name"));
                    clan.setScore(jsonObject.getInt("score"));

                    JSONObject jsonObjectBadge = jsonObject.getJSONObject("badge");
                    Badge badge = new Badge();
                    badge.setName(jsonObjectBadge.getString("name"));
                    badge.setCategory(jsonObjectBadge.getString("category"));
                    badge.setId(jsonObjectBadge.getInt("id"));
                    badge.setImage(jsonObjectBadge.getString("image"));
                    clan.setBadge(badge);

                    clans.add(clan);

                }

                listClans = findViewById(R.id.listViewClans);

                listClans.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                });

                ClanAdapter scoreAdapter = new ClanAdapter(MainActivity.this, clans);

                listClans.setAdapter(scoreAdapter);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private static class ClanAdapter extends BaseAdapter {

        Activity context;
        ArrayList<Clan> clans;
        ClanViewHolder clanViewHolder;

        public ClanAdapter(Activity context, ArrayList<Clan> clans) {
            this.context = context;
            this.clans = clans;
        }

        @Override
        public int getCount() {
            return clans.size();
        }

        @Override
        public Object getItem(int i) {
            return clans.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            View v;

            if(view != null){
                v = view;
                clanViewHolder = (ClanViewHolder) view.getTag();
            }else {
                LayoutInflater layoutInflater = this.context.getLayoutInflater();

                v = layoutInflater.inflate(R.layout.item_clan, viewGroup, false);

                TextView lblTag = v.findViewById(R.id.textViewTag);
                TextView lblName = v.findViewById(R.id.textViewName);
                TextView lblScore = v.findViewById(R.id.textViewScore);

                ImageView imageViewBadge = v.findViewById(R.id.imageViewBadge);

                clanViewHolder = new ClanViewHolder(lblTag, lblName, lblScore, imageViewBadge);

                v.setTag(clanViewHolder);
            }

            Clan clan = clans.get(position);

            clanViewHolder.Tag.setText(clan.getTag());
            clanViewHolder.Name.setText(clan.getName());
            clanViewHolder.Score.setText(clan.getScore().toString());

            //Set image
            Picasso.get().load(clan.getBadge().getImage()).into(clanViewHolder.ImageViewBadge);

            return v;
        }
    }

    public static class ClanViewHolder{

        public TextView Tag;
        public TextView Name;
        public TextView Score;
        public ImageView ImageViewBadge;

        public ClanViewHolder(TextView tag, TextView name, TextView score, ImageView imageViewBadge) {
            Tag = tag;
            Name = name;
            Score = score;
            ImageViewBadge = imageViewBadge;
        }
    }
}
