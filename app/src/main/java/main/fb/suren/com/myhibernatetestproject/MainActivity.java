package main.fb.suren.com.myhibernatetestproject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.sql.SQLException;
import java.util.List;

import main.fb.suren.com.myhibernatetestproject.DatabaseHelper;

public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> {

    Button button;
    EditText textView;
    Dao<SimpleData,Integer> doa;



    private void populateTextView(Dao<SimpleData,Integer> doa){
        try {
            List<SimpleData> list = doa.queryForAll();
            StringBuffer sb = new StringBuffer("START-\n");

            for(int i = list.size()-1; i>=0; i--){
                SimpleData simpleData = list.get(i);
                sb.append(simpleData.toString()+"\n");
            }

            textView.setText(sb.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.fragment_main);
        try {
            doa = getHelper().getSimpleDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }



      /*  textView = (EditText) findViewById(R.id.textOut);
        button = (Button) findViewById(R.id.button);
        populateTextView(doa);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleData simpleData = new SimpleData(System.currentTimeMillis());
                Log.i(MainActivity.class.getName(),"Button Clicked");
                try {

                    doa.create(simpleData);
                    populateTextView(doa);

                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        });*/

    }

    public void selectFrag(View view){
        Fragment fragment;




        if(view == findViewById(R.id.button_frag1)){
            fragment = new FragmentOne();
        } else {
            fragment = new FragmentTwo();
        }

        FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_place,fragment);
        ft.commit();
    }
}
