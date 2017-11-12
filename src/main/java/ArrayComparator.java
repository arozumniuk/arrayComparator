package main.java;

import io.restassured.internal.path.xml.NodeChildrenImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class ArrayComparator {
    NodeChildrenImpl xmlItemsList;
    ArrayList<HashMap> jsonItemsList;

    public ArrayComparator(NodeChildrenImpl nodeList, ArrayList mapList) {
        this.jsonItemsList = mapList;
        this.xmlItemsList = nodeList;
    }

    public ArrayList<ProgramItem> fullCompare() throws ParseException {
        ArrayList<ProgramItem> failedItems = new ArrayList<>();
        for (HashMap jsonItem : jsonItemsList) {
            boolean isMatch = false;
            ProgramItem itemToCompare = new ProgramItem(jsonItem);

            for (int a = 0; a < xmlItemsList.size(); a++) {
                if (itemToCompare.isEquals(new ProgramItem(xmlItemsList.get(a)))) {
                    isMatch = true;
                    break;
                }
            }
            if (!isMatch) {
                failedItems.add(itemToCompare);
            }
        }
        return failedItems;
    }
}
