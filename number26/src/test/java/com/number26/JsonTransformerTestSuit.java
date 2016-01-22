package com.number26;

import com.number26.model.Transaction;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonTransformerTestSuit {

    @Test
    public void testRender() throws Exception {
        JsonTransformer jsonTransformer = new JsonTransformer();

        Transaction transaction = new Transaction();
        transaction.setAmount(100);
        transaction.setType("phone");
        assertEquals("{\"parent_id\":null,\"amount\":100.0,\"type\":\"phone\"}", jsonTransformer.render(transaction));

        transaction = new Transaction();
        transaction.setAmount(20);
        transaction.setType("internet");
        transaction.setParentId(1l);
        assertEquals("{\"parent_id\":1,\"amount\":20.0,\"type\":\"internet\"}", jsonTransformer.render(transaction));
    }

}
