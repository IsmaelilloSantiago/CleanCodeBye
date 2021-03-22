package es.ulpgc.eite.cleancode.helloworld.Bye;

import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Objects;

import es.ulpgc.eite.cleancode.helloworld.R;
import es.ulpgc.eite.cleancode.helloworld.hello.HelloViewModel;

public class byeViewModel {

    // put the view state here


    String byeMessage = "bye";

    @Override
    public boolean equals( Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        byeViewModel viewModel = (byeViewModel) obj;
        return Objects.equals(byeMessage, viewModel.byeMessage);
    }
    @Override
    public int hashCode() {
        return Objects.hash(byeMessage);
    }

    @Override
    public String toString() {
        return
                "byeMessage: " + byeMessage;
    }
}