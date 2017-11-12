package test.java;

import io.restassured.internal.path.xml.NodeChildrenImpl;
import main.java.ArrayComparator;
import main.java.Loader;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

public class Tests {


    @Test()
    public void C1_checkIfProgramsTheSame() throws JSONException, IOException, ParserConfigurationException, SAXException, ParseException {
        NodeChildrenImpl iml = new Loader().loadXmlSource();
        ArrayList<HashMap> list = new Loader().loadJsonSource();
        ArrayList errors = new ArrayComparator(iml, list).fullCompare();

        Assert.assertTrue(errors.size() == 0,
                "For some items from JSON didn't find corresponding item from XML: \n" + Arrays.deepToString(errors.toArray()));
    }

    @Test
    public void C2_checkStructureOfResponse() {
        String json = new Loader().getJsonForId(295);
        assertThat(json, matchesJsonSchemaInClasspath("jsonSchema.json"));
    }
}

