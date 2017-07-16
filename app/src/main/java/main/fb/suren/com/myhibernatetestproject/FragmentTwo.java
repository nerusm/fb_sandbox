package main.fb.suren.com.myhibernatetestproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by suren on 16/7/17.
 */

public class FragmentTwo extends Fragment {
    Dao<SimpleData,Integer> doa;
    EditText editText;

    private DatabaseHelper databaseHelper = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two,container,false);
        editText = (EditText) view.findViewById(R.id.editTextMultiLine);
        editText.setText("sdfsdfsdf");
        populateTextView(doa);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Log.i("TEST","In OnCreate");
//            editText = (EditText) getView().findViewById(R.id.editTextMultiLine);
            doa = getHelper().getSimpleDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        populateTextView(doa);
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

    private void populateTextView(Dao<SimpleData,Integer> doa){
        try {
            List<SimpleData> list = doa.queryForAll();
            StringBuffer sb = new StringBuffer("START-\n");

            for(int i = list.size()-1; i>=0; i--){
                SimpleData simpleData = list.get(i);
                sb.append(simpleData.toString()+"\n");
            }

            editText.setText(sb.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
