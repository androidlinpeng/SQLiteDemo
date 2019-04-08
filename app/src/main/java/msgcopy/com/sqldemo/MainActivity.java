package msgcopy.com.sqldemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                startActivity(new Intent(MainActivity.this,BasisActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(MainActivity.this,SQLActivity.class));
                break;
        }
    }
}
