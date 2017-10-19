package edu.orismail.smartpasscard;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.google.zxing.integration.android.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class Home extends Activity implements OnClickListener {

	TextView tv1,tv2,tv3,tv4, content, format, type, time;
	ImageButton scan, pass;
	String sFormat, sContent, sType, sTime;
	ArrayList<String> matric;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initComp();
    }

    private void initComp()
    {
    	content = (TextView) findViewById(R.id.content);
    	format = (TextView) findViewById(R.id.format);
    	type = (TextView) findViewById(R.id.type);
    	time = (TextView) findViewById(R.id.time);
    	
    	tv1 = (TextView) findViewById(R.id.tv1);
    	tv2 = (TextView) findViewById(R.id.tv2);
    	tv3 = (TextView) findViewById(R.id.tv3);
    	tv4 = (TextView) findViewById(R.id.tv4);
    	
    	setVisible(TextView.INVISIBLE);
    	
    	scan = (ImageButton) findViewById(R.id.scan);
    	scan.setOnClickListener(this);
    	
    	pass = (ImageButton) findViewById(R.id.pass);
    	pass.setOnClickListener(this);
    	
    	matric = new ArrayList<String>();
    	
    	matric.add("123735");
    	matric.add("102050");
    	matric.add("143030");
    	matric.add("131419");
    }

    private void setVisible(int a)
    {
    	tv1.setVisibility(a);
    	tv2.setVisibility(a);
    	tv3.setVisibility(a);
    	tv4.setVisibility(a);
    	
    	content.setVisibility(a);
    	format.setVisibility(a);
    	type.setVisibility(a);
    	time.setVisibility(a);
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.getId() == R.id.scan)
		{
			setVisible(TextView.INVISIBLE);
			IntentIntegrator scanIntegrator = new IntentIntegrator(Home.this);
			scanIntegrator.initiateScan();
		}
		else if(arg0.getId() == R.id.pass)
		{
			
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		
		if(scanResult != null)
		{
			sContent = scanResult.getContents();
			sFormat = scanResult.getFormatName();
			sType = scanResult.getErrorCorrectionLevel();
			
			GregorianCalendar calendar = new GregorianCalendar();
			int a = calendar.get(Calendar.YEAR);
			int b = calendar.get(Calendar.MONTH) + 1;
			int c = calendar.get(Calendar.DAY_OF_MONTH);
			
			int d = calendar.get(Calendar.HOUR_OF_DAY);
			int e = calendar.get(Calendar.MINUTE);
			
			sTime = c + "/" + b + "/" + a + " " + d + ":" + e;
			
			if(matric.contains(sContent))
			{
				Bundle matric = new Bundle();
				matric.putString("matric", sContent);
				Intent intent = new Intent(Home.this, Card.class);
				intent.putExtras(matric);
				startActivity(intent);
			}
			else
			{
				setVisible(TextView.VISIBLE);
				content.setText(sContent);
				format.setText(sFormat);
				type.setText(sType);
				time.setText(sTime);
			}
		}
		else
		{
			Toast t = Toast.makeText(Home.this, "No Scan Data Recieved", Toast.LENGTH_LONG);
			t.show();
		}
	}
}
