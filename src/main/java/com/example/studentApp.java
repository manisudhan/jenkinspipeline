package com.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class StudentApp {
    private static List<String> students = new ArrayList<>();

    public static void addStudent(String name) {
        if (name != null && !name.trim().isEmpty()) {
            students.add(name);
        }
    }

    public static List<String> getStudents() {
        return students;
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // Endpoint to view students: http://localhost:8080/students
        server.createContext("/students", exchange -> {
            String response = "Student List: " + students.toString();
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        // Endpoint to add a student: http://localhost:8080/add?name=John
        server.createContext("/add", exchange -> {
            String query = exchange.getRequestURI().getQuery();
            String name = "Anonymous";
            if (query != null && query.startsWith("name=")) {
                name = query.substring(5);
            }
            addStudent(name);
            String response = "Successfully added student: " + name;
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.setExecutor(null);
        server.start();
        System.out.println("Student App server started on port 8080...");
    }
}