package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Form {
    private String id;
    private long time;
    private String group;
    private String type;
    private String subType;
    private String url;
    private String organization;
    private String formId;
    private String code;
    private final String itpa = " ";
    private final String sudirResponse = " ";
    private Date date;

    public Form(String id, long time, String group, String type, String subType, String url, String organization, String formId, String code, String date) throws ParseException {
        this.id = id;
        this.time = time;
        this.group = group;
        this.type = type;
        this.subType = subType;
        this.url = url;
        this.organization = organization;
        this.formId = formId;
        this.code = code;
        this.date = new SimpleDateFormat("yyyy-MM-dd-HH").parse(date);
    }

    public String getId() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public String getSubType() {
        return subType;
    }

    public String getFormId() {
        return formId;
    }


    @Override
    public String toString() {
        return "Form{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", group='" + group + '\'' +
                ", type='" + type + '\'' +
                ", subType='" + subType + '\'' +
                ", url='" + url + '\'' +
                ", organization='" + organization + '\'' +
                ", formId='" + formId + '\'' +
                ", date=" + date +
                '}';
    }
}
