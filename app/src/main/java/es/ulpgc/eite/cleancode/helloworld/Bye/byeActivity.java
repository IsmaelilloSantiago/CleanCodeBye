package es.ulpgc.eite.cleancode.helloworld.Bye;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.cleancode.helloworld.R;
import es.ulpgc.eite.cleancode.helloworld.hello.HelloActivity;

public class byeActivity

        extends AppCompatActivity implements byeContract.View {


    public static String TAG = byeActivity.class.getSimpleName();

    private byeContract.Presenter presenter;

    Button botonSayBye;
    Button botonGoHello;
    TextView byeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w("llegos","on create");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bye);
        getSupportActionBar().setTitle(R.string.app_name);


    /*
    if(savedInstanceState == null) {
      AppMediator.resetInstance();
    }
    */
        botonGoHello = findViewById(R.id.goHelloButton);
        botonSayBye = findViewById(R.id.sayByeButton);
        byeMessage = findViewById(R.id.byeMessage);


        botonSayBye.setText(R.string.say_bye_button_label);
        botonGoHello.setText(R.string.go_hello_button_label);

        botonGoHello.setOnClickListener(v -> presenter.goHelloBotonClicked());
        botonSayBye.setOnClickListener(v -> presenter.sayByeBotonClicked());

        // do the setup
          byeScreen.configure(this);




        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        presenter.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        presenter.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }

    @Override
    public void onDataUpdated(byeViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        ((TextView) findViewById(R.id.byeMessage)).setText(viewModel.byeMessage);
    }


    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void displayByeMessage(byeViewModel byeViewModel) {

    }


    @Override
    public void injectPresenter(byeContract.Presenter presenter) {
        this.presenter = presenter;
    }
}