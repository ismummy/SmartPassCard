package edu.orismail.smartpasscard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Card extends Activity implements OnClickListener {

	String matric;
	TextView mat, name, faculty,dept, phone;
	ImageView passport;
	ImageButton home;
	
	String fullname[] = {"Ismail Olalekan","Fakeye Nathaniel","Olajide Mariam","Tolulope Aderonke"};
	String matricNo[] = {"123725","102050","131419","143030"};
	String fac[] = {"FET","FAG","FPAS","FET"};
	String department[] = {"CSE","APH","SLT","EEE"};
	String mobile[] = {"08139263853","08054062440","07034274426","09003785372"};
	int pix[] = {R.drawable.lekan,R.drawable.fakeye,R.drawable.mariam, R.drawable.aderonke};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card);
		Bundle matric = getIntent().getExtras();
		this.matric = matric.getString("matric");
		initComp();
		setData(this.matric);
	} 
	private void setData(String mat)
	{
		for(int i=0; i<matricNo.length; i++)
		{
			if(mat.equals(matricNo[i]))
			{
				name.setText(fullname[i]);
				this.mat.setText(matricNo[i]);
				faculty.setText(fac[i]);
				dept.setText(department[i]);
				phone.setText(mobile[i]);
				passport.setImageResource(pix[i]);
				break;
			}
		}
	}
	private void initComp()
	{
		mat = (TextView)findViewById(R.id.matric);
		name = (TextView) findViewById(R.id.name);
		faculty = (TextView) findViewById(R.id.faculty);
		dept = (TextView) findViewById(R.id.dept);
		phone = (TextView) findViewById(R.id.Phone);
		
		passport = (ImageView)findViewById(R.id.pix);
		home = (ImageButton) findViewById(R.id.home);
		home.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
	
		if(arg0.getId() == R.id.home)
		{
			startActivity(new Intent(Card.this, Home.class));
		}
	}
}
