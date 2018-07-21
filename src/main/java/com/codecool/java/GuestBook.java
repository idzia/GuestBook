package com.codecool.java;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuestBook implements HttpHandler {

    private GuestbookDB gbookDB;

    public GuestBook() {
        gbookDB = new GuestbookDB();

    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        List<Message> messageList = gbookDB.getMessageList();
        String response = "";

        if (method.equals("GET")) {

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/gbook.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("messageList", gbookDB.getMessageList());

            response = template.render(model);
        }

        if (method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            System.out.println(formData);

            Map inputs = parseFormData(formData);

            String text = inputs.get("message").toString();
            String name = inputs.get("name").toString();
            String date = inputs.get("date").toString();

            Message newMessage = new Message(text, name, date);
            messageList.add(newMessage);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/gbook.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("messageList", gbookDB.getMessageList());

            response = template.render(model);
        }

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String value = URLDecoder.decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }


}
