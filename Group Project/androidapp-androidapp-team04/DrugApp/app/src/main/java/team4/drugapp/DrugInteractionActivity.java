package team4.drugapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.*;


public class DrugInteractionActivity extends AppCompatActivity {

    public void startSettingsActivity(View view) {
        Intent startSettingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(startSettingsIntent);



    }



    private EditText Drug1;
    private EditText Drug2;
    private String entereddrug2;
    private TextView description;
//    private Button interactionTV;

    private ProgressDialog dialog;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_interaction);


        Button Search = (Button) findViewById(R.id.interactionSearch_button);
        description = (TextView) findViewById(R.id.interactionDescription);
        Drug1 = (EditText) findViewById(R.id.Drug1);
        Drug2 = (EditText) findViewById(R.id.Drug2);
        entereddrug2 = Drug2.getText().toString();
        //interactionTV = (Button) findViewById(R.id.interactionTV);


        Search.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                //   creating two calls first is for the RXCUI id and second one is uses RXCUI to get interaction descriptions
                String entereddrug = Drug1.getText().toString();
                entereddrug2 = Drug2.getText().toString();


                new JSONTask().execute("https://rxnav.nlm.nih.gov/REST/rxcui?name=" + entereddrug, // url for getting RXCUI
                        "https://rxnav.nlm.nih.gov/REST/interaction/interaction.json?rxcui="); // url for getting descriptions


            }

        });
    }




    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            String RXCUI=null;
            HttpURLConnection connection1 = null;
            HttpURLConnection connection = null;
            BufferedReader reader1 = null;
            BufferedReader reader = null;

            try{                                        // begin try for RXCUI
                URL url1 = new URL(params[0]);
                connection1 = (HttpURLConnection) url1.openConnection();
                connection1.connect();
                InputStream stream1 = connection1.getInputStream();

                reader1 = new BufferedReader(new InputStreamReader(stream1));

                StringBuffer buffer1 = new StringBuffer();
                String line ="";
                while((line = reader1.readLine()) != null) {
                    buffer1.append(line);
                }

                if (buffer1.toString().startsWith("<?")) {
                    String s = buffer1.toString();


                    int j = s.indexOf("<rxnormId>");

                    if (j > 0) {
                        j = j + 10;
                        int k = s.indexOf("</rxnormId>");
                        RXCUI = s.substring(j, k);


                    } else {
                        //drug entered was not found
                        RXCUI = null;
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                if(RXCUI != null) {
                    URL url = new URL(params[1] + RXCUI);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();


                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();

                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    String finalJson = buffer.toString();

                    JSONObject parentObject = new JSONObject(finalJson);
                    String nlmDisclaimer = parentObject.getString("nlmDisclaimer");

                    JSONArray parentArray = parentObject.getJSONArray("interactionTypeGroup");
                    JSONObject stest = parentArray.getJSONObject(0);
                    String sourceDisclaimer = stest.getString("sourceDisclaimer");
                    JSONArray nextArray = stest.getJSONArray("interactionType");
                    JSONObject stest2 = nextArray.getJSONObject(0);
                    JSONObject minConceptItem = stest2.getJSONObject("minConceptItem");
                    String rxcui = minConceptItem.getString("rxcui");
                    String name = minConceptItem.getString("name");
                    JSONArray thirdArray = stest2.getJSONArray("interactionPair");


                    StringBuffer finalBufferedData = new StringBuffer();
                    finalBufferedData.append("Name : " + name + "-" + "rxcui : " + rxcui + "\n");
                    boolean foundone = false;
                    for (int i = 0; i < thirdArray.length(); i++) {
                        JSONObject finalObject = thirdArray.getJSONObject(i);


                        String description = finalObject.getString("description");
                        if (entereddrug2 !=null) {
                            if (description.contains(entereddrug2)) {
                                foundone = true;
                                finalBufferedData.append((i + 1) + ") " + description + "\n");
                            }

                        }
                        else {
                            finalBufferedData.append((i + 1) + ") " + description + "\n");
                        }

                    }
                    if (entereddrug2 != null & !foundone ){
                        finalBufferedData.append("Search term returned no results \n");
                    }
                    //  String json = JsonConvert.SerializeXmlNode(buffer.toString());
                    return finalBufferedData.toString();
                }
                else
                {return "No interactions in list. Drug was not found. Check spelling";}

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            description.setText(result);

            Log.d("DESCRIPTION: ", result);


        }

    }
}