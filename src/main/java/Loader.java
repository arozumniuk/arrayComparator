package main.java;

import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.path.xml.XmlPath;
import main.java.Managers.LocalManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class Loader {

    public ArrayList loadJsonSource() {
        ArrayList<ArrayList> programs = LocalManager.getApi().sendGet("http://epg.megogo.net/channel?external_id=295").jsonPath().get("data.programs");
        return programs.get(0);
    }

    public NodeChildrenImpl loadXmlSource() throws IOException, ParserConfigurationException, SAXException {
        return LocalManager.getApi().sendGet("http://www.vsetv.com/export/megogo/epg/3.xml").xmlPath(XmlPath.CompatibilityMode.HTML).get("tv.programme");
    }

    private static int offset = -1;

    public static int getOffset() {
        if (offset != -1) {
            return offset;
        }
        int utc_offset = LocalManager.getApi().sendGet("http://epg.megogo.net/time").jsonPath().get("data.utc_offset");
        offset = utc_offset;
        return utc_offset;
    }

    public String getJsonForId(int id){
        return LocalManager.getApi().sendGet("http://epg.megogo.net/channel?external_id=" + id).getBody().asString();
    }
}
