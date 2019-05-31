/* Student Name: Aplomb TR Huang;
 * Student V #: 00800816;
 * Class: CMSC 355 SPRING 17
 * Project 2 : Jelly Bean Android APP
 * LOAD_FACTOR: 53.33%
* Reason: Let's consider the jar as a rectangular shape box, then the volume of the jar can be considered as cm^3 .
* Now I assume all the jelly beans are uni-size little cube, that is: A^2 * C, such that they would fill the "rectangular" jar close to full.
* My assumption yields the maximum number of jelly beans the jar can hold is the volume/(A^2 * C).
*  The actual size of jelly bean multiplied a constant pi/6, which is about 53.33% , which is smaller
* but considering the factors of the jar is rounded and when the beans stack, they occupied more space than its actual size,
*  I believe it is right to assume 53.33% is a good factor.
 */

package project0.jellybeanestimator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private EditText Length_Input;
    private EditText Diameter_Input;
    private EditText Jar_Volume;
    private EditText Result_Output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Length_Input = (EditText) findViewById(R.id.Length_Input);
        Diameter_Input = (EditText) findViewById(R.id.Diameter_Input);
        Jar_Volume = (EditText) findViewById(R.id.Jar_Volume);
        Result_Output = (EditText) findViewById(R.id.Result_Output);

    }

    public void handleClick(View view)
    {
        switch (view.getId())
        {
            case R.id.RstButton:
                Length_Input.setText("");
                Diameter_Input.setText("");
                Jar_Volume.setText("");
                Result_Output.setText("");
                break;

            case R.id.EstButton:
                String input_L = Length_Input.getText().toString();  //inputs are turned into strings for checks
                String input_D = Diameter_Input.getText().toString();
                String input_V = Jar_Volume.getText().toString();

                if(input_D.length()>0 && input_L.length() >0 && input_V.length() >0) // this checks to see if all the blanks are filled
                {  double bean_Count,bean_Size;
                    double length = Double.parseDouble(input_L); // turn the input back to double
                    double jar_V = Double.parseDouble(input_V);
                     double diameter = Double.parseDouble(input_D);
                    bean_Size = (Math.PI /6)* diameter*diameter*length; //calculation formula for the size of a bean is given
                    bean_Count = 0.5333 * Math.abs(jar_V/ bean_Size); // calculating estimation with load factor
                    int round_Count = (int) Math.round(bean_Count);  // round the estimation
                   String str = "There are approximately "+ round_Count+" beans in the jar.";

                  Result_Output.setText(str);

                }
                else{
                    Context context = getApplicationContext(); // a block of code from Professor Duke's Temperature conversion app, which pops a warning if there's a blank input.
                    CharSequence text = "Some data is not given; Unable to estimate";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();}

        }

    }

}
