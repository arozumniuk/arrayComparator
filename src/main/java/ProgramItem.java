package main.java;

import io.restassured.path.xml.element.Node;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import static main.java.Loader.getOffset;

public class ProgramItem {
    Node xmlItem;
    HashMap jsonItem;

    private String name;

    private Date startDate;
    private Date endDate;

    @Override
    public String toString() {
        return "ProgramItem{ name='" + name + '\'' + ", startDate=" + startDate + ", endDate=" + endDate + '}' + "\n";
    }

    public ProgramItem(Node xmlItem) throws ParseException {
        this.xmlItem = xmlItem;
        name = xmlItem.getNode("title").value();

        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss Z");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        startDate = isoFormat.parse(xmlItem.getAttribute("start"));
        endDate = isoFormat.parse(xmlItem.getAttribute("stop"));
    }

    public ProgramItem(HashMap jsonItem) throws ParseException {
        this.jsonItem = jsonItem;
        name = jsonItem.get("title").toString();

        SimpleDateFormat isoFormat = new SimpleDateFormat("MMM d, yyyy HH:mm:ss aaa");
        isoFormat.setTimeZone(TimeZone.getTimeZone(TimeZone.getAvailableIDs(getOffset() * 1000)[0]));
        startDate = isoFormat.parse(jsonItem.get("start").toString());
        endDate = isoFormat.parse(jsonItem.get("end").toString());

    }

    public boolean isEquals(ProgramItem item) {
        return item.name.equals(name)
                && item.startDate.equals(startDate)
                && item.endDate.equals(endDate);
    }
}
