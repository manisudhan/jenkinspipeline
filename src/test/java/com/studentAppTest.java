package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class StudentAppTest {

    @Test
    public void testAddStudent() {
        int initialSize = StudentApp.getStudents().size();
        StudentApp.addStudent("Mani");
        assertEquals(initialSize + 1, StudentApp.getStudents().size());
        assertTrue(StudentApp.getStudents().contains("Mani"));
    }
}