package sandip.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    EditText from, to;
    DatePickerDialog dialogFrom, dialogTo;
    int month= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        from = findViewById(R.id.from);
        to = findViewById(R.id.to);

        Calendar now = Calendar.getInstance();

        dialogFrom = DatePickerDialog.newInstance(this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));

        dialogTo = DatePickerDialog.newInstance(this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));

        from.setOnClickListener(this);
        to.setOnClickListener(this);

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        if(view.getTag().equals("datefrom")) {

            from.setText(new DecimalFormat("00").format(dayOfMonth)+"/"+new DecimalFormat("00").format(monthOfYear+1)+"/"+year);

            month = monthOfYear;
        }else if(view.getTag().equals("dateto")) {

            to.setText(new DecimalFormat("00").format(dayOfMonth)+"/"+new DecimalFormat("00").format(monthOfYear+1)+"/"+year);
        }

    }

    @Override
    public void onClick(View view) {

        if(view==from) {

            dialogFrom.show(getFragmentManager(),"datefrom");

        }else if(view==to) {

            Calendar cal = Calendar.getInstance();
            try {

               // SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
                //cal.setTime(sdf.parse(from.getText().toString()));// all done

                SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
                sdf.parse(from.getText().toString());// all done
                cal = sdf.getCalendar();

            }catch (Exception e) {

            }
            cal.add(Calendar.MONTH,month);



            dialogTo.setMinDate(cal);

            dialogTo.show(getFragmentManager(),"dateto");

        }
    }
}
