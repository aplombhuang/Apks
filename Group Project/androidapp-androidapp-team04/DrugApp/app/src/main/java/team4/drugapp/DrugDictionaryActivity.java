package team4.drugapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DrugDictionaryActivity extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_dictionary);

        listView = (ExpandableListView) findViewById(R.id.fDrugs_List);
        initData();
        listAdapter = new ExpandableListAdapter(this,listDataHeader, listHash);
        listView.setAdapter(listAdapter);

    }
    public void startDrugInteractionActivity(View view) {
        Intent startDrugInteractionIntent = new Intent(this, DrugInteractionActivity.class);
        startActivity(startDrugInteractionIntent);

    }

    public void startSettingsActivity(View view) {
        Intent startSettingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(startSettingsIntent);
    }


    public void startDrugDetailActivity(View view) {
        Intent startDrugDetailIntent = new Intent(this, DrugDetailActivity.class);
        startActivity(startDrugDetailIntent);

    }


    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();
        listDataHeader.add("Drug begins with A");
        listDataHeader.add("Drug begins with B");
        listDataHeader.add("Drug begins with C");
        listDataHeader.add("Drug begins with D");
        listDataHeader.add("Drug begins with E");
        listDataHeader.add("Drug begins with F");
        listDataHeader.add("Drug begins with G");
        listDataHeader.add("Drug begins with H");
        listDataHeader.add("Drug begins with I");
        listDataHeader.add("Drug begins with J");
        listDataHeader.add("Drug begins with K");
        listDataHeader.add("Drug begins with L");
        listDataHeader.add("Drug begins with M");
        listDataHeader.add("Drug begins with N");
        listDataHeader.add("Drug begins with O");
        listDataHeader.add("Drug begins with P");
        listDataHeader.add("Drug begins with Q");
        listDataHeader.add("Drug begins with R");
        listDataHeader.add("Drug begins with S");
        listDataHeader.add("Drug begins with T");
        listDataHeader.add("Drug begins with U");
        listDataHeader.add("Drug begins with V");
        listDataHeader.add("Drug begins with W");
        listDataHeader.add("Drug begins with X");
        listDataHeader.add("Drug begins with Y");
        listDataHeader.add("Drug begins with Z");

        List<String> Drug_A = new ArrayList<>();
        Drug_A.add("Advil");

        List<String> Drug_B = new ArrayList<>();
        Drug_B.add("Bacitracin");

        List<String> Drug_C = new ArrayList<>();
        Drug_C.add("Clonidine");

        List<String> Drug_D = new ArrayList<>();
        Drug_D.add("Digoxin");

        List<String> Drug_E = new ArrayList<>();
        Drug_E.add("Exelon");

        List<String> Drug_F = new ArrayList<>();
        Drug_F.add("Fentanyl");

        List<String> Drug_G = new ArrayList<>();
        Drug_G.add("Glucophage");

        List<String> Drug_H = new ArrayList<>();
        Drug_H.add("Heparin");

        List<String> Drug_I = new ArrayList<>();
        Drug_I.add("Ibuprofen");

        List<String> Drug_J = new ArrayList<>();
        Drug_J.add("Januvia");

        List<String> Drug_K = new ArrayList<>();
        Drug_K.add("Kadian");

        List<String> Drug_L = new ArrayList<>();
        Drug_L.add("Levodopa");
        Drug_L.add("Lithium");//yep, that's right
        Drug_L.add("Lorazepam");

        List<String> Drug_M = new ArrayList<>();
        Drug_M.add("Methadone");
        Drug_M.add("Morphine");

        List<String> Drug_N = new ArrayList<>();
        Drug_N.add("Naloxone");

        List<String> Drug_O = new ArrayList<>();
        Drug_O.add("Oxycodone");

        List<String> Drug_P = new ArrayList<>();
        Drug_P.add("Penicillin");

        List<String> Drug_Q = new ArrayList<>();
        Drug_Q.add("Quinidine");

        List<String> Drug_R = new ArrayList<>();
        Drug_R.add("Ranexa");

        List<String> Drug_S = new ArrayList<>();
        Drug_S.add("Synthroid");

        List<String> Drug_T = new ArrayList<>();
        Drug_T.add("Tamoxifen");
        Drug_T.add("Temazepam");
        Drug_T.add("Tizanidine");
        Drug_T.add("Tylenol");

        List<String> Drug_U = new ArrayList<>();
        Drug_U.add("Ultresa");

        List<String> Drug_V = new ArrayList<>();
        Drug_V.add("Vancomycin");
        Drug_V.add("Vicodin");//Thank you Dr. House
        Drug_V.add("Viagra");

        List<String> Drug_W = new ArrayList<>();
        Drug_W.add("Warfarin");//killer and saver

        List<String> Drug_X = new ArrayList<>();
        Drug_X.add("Xenazine");

        List<String> Drug_Y = new ArrayList<>();
        Drug_Y.add("Yaz");//oddly difficult to fine one

        List<String> Drug_Z = new ArrayList<>();
        Drug_Z.add("Zolpidem");
        Drug_Z.add("Zyvox");


        listHash.put(listDataHeader.get(0), Drug_A);
        listHash.put(listDataHeader.get(1), Drug_B);
        listHash.put(listDataHeader.get(2), Drug_C);
        listHash.put(listDataHeader.get(3), Drug_D);
        listHash.put(listDataHeader.get(4), Drug_E);
        listHash.put(listDataHeader.get(5), Drug_F);
        listHash.put(listDataHeader.get(6), Drug_G);
        listHash.put(listDataHeader.get(7), Drug_H);
        listHash.put(listDataHeader.get(8), Drug_I);
        listHash.put(listDataHeader.get(9), Drug_J);
        listHash.put(listDataHeader.get(10), Drug_K);
        listHash.put(listDataHeader.get(11), Drug_L);
        listHash.put(listDataHeader.get(12), Drug_M);
        listHash.put(listDataHeader.get(13), Drug_N);
        listHash.put(listDataHeader.get(14), Drug_O);
        listHash.put(listDataHeader.get(15), Drug_P);
        listHash.put(listDataHeader.get(16), Drug_Q);
        listHash.put(listDataHeader.get(17), Drug_R);
        listHash.put(listDataHeader.get(18), Drug_S);
        listHash.put(listDataHeader.get(19), Drug_T);
        listHash.put(listDataHeader.get(20), Drug_U);
        listHash.put(listDataHeader.get(21), Drug_V);
        listHash.put(listDataHeader.get(22), Drug_W);
        listHash.put(listDataHeader.get(23), Drug_X);
        listHash.put(listDataHeader.get(24), Drug_Y);
        listHash.put(listDataHeader.get(25), Drug_Z);


    }
}
