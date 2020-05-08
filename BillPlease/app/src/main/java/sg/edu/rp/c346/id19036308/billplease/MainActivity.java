package sg.edu.rp.c346.id19036308.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText numOfPax;
    ToggleButton svs;
    ToggleButton gst;
    EditText discount;
    TextView total;
    TextView eachAmount;
    Button split;
    Button reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amountInput);
        numOfPax = findViewById(R.id.paxInput);
        svs = findViewById(R.id.tbSvs);
        gst = findViewById(R.id.tbGst);
        discount = findViewById(R.id.discountInput);
        total = findViewById(R.id.totalDisplay);
        eachAmount = findViewById(R.id.eachDisplay);
        split = findViewById(R.id.splitBtn);
        reset = findViewById(R.id.resetBtn);


        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText().toString().trim().length()!=0 && numOfPax.getText().toString().trim().length()!=0)
                {
                    double newAmount = 0.0;
                    if (!svs.isChecked() && !gst.isChecked()){
                        newAmount = Double.parseDouble(amount.getText().toString());
                    }
                    else if (svs.isChecked() && !gst.isChecked()) {
                        newAmount = Double.parseDouble(amount.getText().toString())*1.1;
                }
                    else if (!svs.isChecked() && gst.isChecked()) {
                        newAmount = Double.parseDouble(amount.getText().toString()) * 1.07;
                    }
                    else{
                        newAmount = Double.parseDouble(amount.getText().toString()) * 1.17;
                    }
                    //Discount
                    if(discount.getText().toString().trim().length() !=0){
                        newAmount *= 1- Double.parseDouble(discount.getText().toString())/100;
                    }
                    total.setText("Total Bill: $" + String.format("%.2f", newAmount));
                    int numOfPerson = Integer.parseInt(numOfPax.getText().toString());
                    if(numOfPerson != 1) {
                        eachAmount.setText("Each Pays: $" + String.format("%.2f", newAmount / numOfPerson));
                    } else {
                        eachAmount.setText("Each Pays: $" + String.format("%.2f", newAmount));
                    }
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                numOfPax.setText("");
                discount.setText("");
                total.setText("Total Bill: ");
                eachAmount.setText("Each Pays: ");

            }
        });


    }

}
