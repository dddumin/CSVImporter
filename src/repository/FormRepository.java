package repository;

import model.Form;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class FormRepository {
    private ArrayList<Form> formArrayList;

    public FormRepository(String fileName) throws IOException {
        this.formArrayList = load(fileName);
    }



    public Map<String, String> lastHour(){
        Date date = new Date(1500630359000l - 3600000);
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < this.formArrayList.size(); i++) {
            Date date1 = new Date(this.formArrayList.get(i).getTime()*1000);
            if (date.before(date1)){
                if (!map.containsKey(this.formArrayList.get(i).getId()))
                    map.put(this.formArrayList.get(i).getId(), this.formArrayList.get(i).getFormId());
                else {
                    if (!map.get(this.formArrayList.get(i).getId()).equals(this.formArrayList.get(i).getFormId()))
                        map.put(this.formArrayList.get(i).getId(), this.formArrayList.get(i).getFormId());
                }
            }
        }
        return map;
    }

    public List<Map.Entry<String, Integer>> topFive(){
        Map<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < this.formArrayList.size(); i++) {
            if (!map.containsKey(this.formArrayList.get(i).getFormId()))
                map.put(this.formArrayList.get(i).getFormId(), 1);
            else {
                map.replace(this.formArrayList.get(i).getFormId(), map.get(this.formArrayList.get(i).getFormId()) + 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return -(o1.getValue() - o2.getValue());
            }
        });
        return list;
    }

    /**
     * ) Вывести список пользователей, которые начали активность на форме и не дошли до конца.
     * Например, для услуг grp dszn_* начальное состояние start, конечное состояние send.
     * Вывести на каком шаге остановился.
     */

    public HashMap<String, HashMap<String, String>> stop(){
        this.formArrayList.sort(new Comparator<Form>() {
            @Override
            public int compare(Form o1, Form o2) {
                return Long.compare(o1.getTime(), o2.getTime());
            }
        });
        HashMap<String, HashMap<String, String>> map = new HashMap<>();
        for (int i = 0; i < this.formArrayList.size(); i++) {
            Form form = this.formArrayList.get(i);
            if (!map.containsKey(form.getFormId())){
                map.put(form.getFormId(), new HashMap<>());
            }
            map.get(form.getFormId()).put(form.getId(), form.getSubType());
        }
        return map;
    }

    @Override
    public String toString() {
        return "FormRepository{" +
                "formArrayList=" + formArrayList +
                '}';
    }

    private static ArrayList<Form> load(String fileName) throws IOException {
        ArrayList<Form> formArrayList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine();
            while (reader.ready()) {
                String[] mass = reader.readLine().split(";");
                try {
                    formArrayList.add(new Form(mass[0], Long.parseLong(mass[1]), mass[2], mass[3], mass[4], mass[5], mass[6], mass[7], mass[8], mass[11]));
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException | ParseException ignored) {
                }
            }
            return formArrayList;
        }
    }
}

