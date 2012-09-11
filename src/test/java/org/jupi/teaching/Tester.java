package org.jupi.teaching;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.ws.rs.core.EntityTag;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import static org.junit.Assert.*;

public class Tester {

    @Test
    public void testSimpleGetClient() throws MalformedURLException, IOException {
        URL url = new URL("http://localhost:8080/rest01");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            if ((line = reader.readLine()) != null) {
                assertEquals("Hello world!", line);
            } else {
                fail();
            }
        } finally {
            reader.close();
        }
    }

    @Test
    public void testSimpleGetClient2() throws MalformedURLException, IOException {
        URL url = new URL("http://localhost:8080/rest01/Kitty");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            if ((line = reader.readLine()) != null) {
                assertEquals("Hello Kitty", line);
            } else {
                fail();
            }
        } finally {
            reader.close();
        }
    }

    @Test
    public void testSimpleGetClient3() throws MalformedURLException, IOException {
        URL url = new URL("http://localhost:8080/rest01?who=Kitty");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            if ((line = reader.readLine()) != null) {
                assertEquals("Hello Kitty", line);
            } else {
                fail();
            }
        } finally {
            reader.close();
        }
    }

    @Test
    public void testHttpGetClient() throws MalformedURLException, IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:8080/rest01");
        HttpResponse response = client.execute(request);
        InputStream contentStream = response.getEntity().getContent();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(contentStream));
            String line;
            if ((line = reader.readLine()) != null) {
                assertEquals("Hello world!", line);
            } else {
                fail();
            }
        } finally {
            reader.close();
        }
    }

    @Test
    public void testHttpGetClient2() throws MalformedURLException, IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:8080/rest01/Kitty");
        HttpResponse response = client.execute(request);
        InputStream contentStream = response.getEntity().getContent();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(contentStream));
            String line;
            if ((line = reader.readLine()) != null) {
                assertEquals("Hello Kitty", line);
            } else {
                fail();
            }
        } finally {
            reader.close();
        }
    }

    @Test
    public void testHttpGetClient3() throws MalformedURLException, IOException, URISyntaxException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(
                new URIBuilder("http://localhost:8080/rest01/").addParameter("who", "Kitty").build());
        //HttpGet request = new HttpGet("http://localhost:8080/rest01?who=Kitty");
        HttpResponse response = client.execute(request);
        InputStream contentStream = response.getEntity().getContent();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(contentStream));
            String line;
            if ((line = reader.readLine()) != null) {
                assertEquals("Hello Kitty", line);
            } else {
                fail();
            }
        } finally {
            reader.close();
        }
    }

    @Test
    public void testHttpPostClient() throws MalformedURLException, IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost("http://localhost:8080/rest01/post-user");
        request.setEntity(new StringEntity("Kitty"));
        HttpResponse response = client.execute(request);
        InputStream contentStream = response.getEntity().getContent();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(contentStream));
            String line;
            if ((line = reader.readLine()) != null) {
                assertEquals("Hello Kitty", line);
            } else {
                fail();
            }
        } finally {
            reader.close();
        }
    }

    @Test
    public void testJerseyClient() {
        Client client = Client.create();
        WebResource r = client.resource("http://localhost:8080/rest01/");
        ClientResponse response = r.get(ClientResponse.class);
        EntityTag e = response.getEntityTag();
        String entity = response.getEntity(String.class);
        assertEquals("Hello world!", entity);
    }
}
