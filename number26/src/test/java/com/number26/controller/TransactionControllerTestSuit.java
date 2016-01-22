package com.number26.controller;

import com.number26.model.Transaction;
import com.number26.persistance.MemoryStorage;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class TransactionControllerTestSuit {

    private static Transaction t1;
    private static Transaction t2;
    private static Transaction t3;

    @BeforeClass
    public static void setUp() {
        t1 = new Transaction();
        t1.setAmount(100);
        t1.setType("phone");
        MemoryStorage.getInstance().insert(1, t1);

        t2 = new Transaction();
        t2.setAmount(20);
        t2.setType("internet");
        t2.setParentId(1l);
        MemoryStorage.getInstance().insert(2, t2);

        t3 = new Transaction();
        t3.setAmount(-5);
        t3.setType("cashback");
        t3.setParentId(2l);
        MemoryStorage.getInstance().insert(3, t3);

        Transaction t4 = new Transaction();
        t4.setAmount(-2);
        t4.setType("cashback");
        t4.setParentId(1l);
        MemoryStorage.getInstance().insert(4, t4);

    }

    @Test
    public void testGetTransaction() {
        assertEquals(t3, TransactionController.getTransaction("3"));
        assertEquals(t1, TransactionController.getTransaction("1"));
        assertEquals(t2, TransactionController.getTransaction("2"));
        assertNull(TransactionController.getTransaction("150"));
        assertNotEquals(t1, TransactionController.getTransaction("2"));
    }

    @Test
    public void testGetIdsWithType() {
        assertEquals(new HashSet<>(Arrays.asList(3l, 4l)), TransactionController.getIdsWithType("cashback"));
        assertEquals(new HashSet<>(Arrays.asList(2l)), TransactionController.getIdsWithType("internet"));
        assertNull(TransactionController.getIdsWithType("notexist"));
    }

    @Test
    public void testGetSum() {
        assertEquals(100.0, TransactionController.getSum("1").get("sum").doubleValue(), 0.001);
        assertEquals(115.0, TransactionController.getSum("3").get("sum").doubleValue(), 0.001);
        assertEquals(98.0, TransactionController.getSum("4").get("sum").doubleValue(), 0.001);
        assertEquals(0, TransactionController.getSum("150").get("sum").doubleValue(), 0.001);
    }

    @Test
    public void testSave() {
        assertEquals("ok", TransactionController.save("5", "{\"parent_id\": 4, \"amount\": 300, \"type\": \"other\"}").get("result"));
        assertEquals("error", TransactionController.save("5", "not a json").get("result"));

    }
}
