package main.fb.suren.com.myhibernatetestproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by suren on 16/7/17.
 */

public class FragmentOne extends Fragment {
    Dao<SimpleData,Integer> doa;
    Button button;

    private DatabaseHelper databaseHelper = null;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_one,container,false);
        button = (Button) view.findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleData simpleData = new SimpleData(System.currentTimeMillis());
                Log.i(MainActivity.class.getName(),"Button Clicked");
                try {

                    doa.create(simpleData);


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            doa = getHelper().getSimpleDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return view;
    }

    protected DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper =
                    OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

}
