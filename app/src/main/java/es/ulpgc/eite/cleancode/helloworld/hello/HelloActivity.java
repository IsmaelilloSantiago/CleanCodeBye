package es.ulpgc.eite.cleancode.helloworld.hello;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import es.ulpgc.eite.cleancode.helloworld.Bye.byeActivity;
import es.ulpgc.eite.cleancode.helloworld.R;
import es.ulpgc.eite.cleancode.helloworld.app.AppMediator;
import es.ulpgc.eite.cleancode.helloworld.app.HelloToByeState;


public class HelloActivity
    extends AppCompatActivity implements HelloContract.View {

  public static String TAG = HelloActivity.class.getSimpleName();


  HelloContract.Presenter presenter;

  Button sayHelloButton, goByeButton;
  TextView helloMessage;
  int requestCode = 1;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hello);

    getSupportActionBar().setTitle(R.string.hello_screen_title);



    sayHelloButton = findViewById(R.id.sayHelloButton);
    goByeButton = findViewById(R.id.goByeButton);
    helloMessage = findViewById(R.id.helloMessage);

    sayHelloButton.setOnClickListener(v -> presenter.sayHelloButtonClicked());

    goByeButton.setOnClickListener(v -> presenter.goByeButtonClicked());

    sayHelloButton.setText(getSayHelloButtonLabel());
    goByeButton.setText(getGoByeButtonLabel());

    /*if(savedInstanceState != null){
      presenter.onResumeCalled();
    }*/

    if(savedInstanceState == null) {
      AppMediator.resetInstance();
      //comentado por mi

  }





    // do the setup
    HelloScreen.configure(this);
    //if(savedInstanceState == null) {
     // presenter.onResumeCalled();
    //}

      //AppMediator.resetInstance();
      //comentado por mi


    //}

  }

  @Override
  protected void onResume() {

    super.onResume();

    // do some work
    Log.w("antes","llega");
    presenter.onResumeCalled();
  }


  @Override
  protected void onStop() {
    super.onStop();

  }



  @Override
  public void displayHelloData(HelloViewModel viewModel) {
    Log.e(TAG, "displayHelloData()");

    // deal with the data
    helloMessage.setText(viewModel.helloMessage);
  }

  @Override
  public void navigateToByeScreen() {
    Log.w("llegos","2");
    Intent a = new Intent(this, byeActivity.class);
    startActivityForResult(a,requestCode);

    Log.w("llegos","5");
  }


  private String getGoByeButtonLabel() {
    return getResources().getString(R.string.go_bye_button_label);
  }

  private String getSayHelloButtonLabel() {
    return getResources().getString(R.string.say_hello_button_label);
  }

  @Override
  protected void onPause() {
    super.onPause();


  }



  @Override
  public void injectPresenter(HelloContract.Presenter presenter) {
    this.presenter = presenter;
  }


}
