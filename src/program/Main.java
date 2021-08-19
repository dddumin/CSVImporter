package program;

import model.Form;
import repository.FormRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {


        try {
            FormRepository forms = new FormRepository("test_case.csv");
            System.out.println(forms.lastHour());
            for (int i = 0; i < 5; i++) {
                System.out.println(forms.topFive().get(i));
            };
            HashMap<String, HashMap<String, String>> map = forms.stop();
            for (Map.Entry<String, HashMap<String, String>> entry : map.entrySet()) {
                System.out.println("FormId: " + entry.getKey());
                for (Map.Entry<String, String> entryString : entry.getValue().entrySet()) {
                    if (!entryString.getValue().equals("send")){
                        System.out.println("User Id" + entryString.getKey() + " stopped at a step " + entryString.getValue());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
