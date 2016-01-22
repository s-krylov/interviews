package com.number26.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.number26.model.Transaction;
import com.number26.persistance.MemoryStorage;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class TransactionController {

    private static final ObjectReader transactionReader = new ObjectMapper().readerFor(Transaction.class);

    private TransactionController() {}

    public static Transaction getTransaction(String id) {
        return MemoryStorage.getInstance().getById(Long.parseLong(id));
    }

    public static Set<Long> getIdsWithType(String type) {
        return MemoryStorage.getInstance().getByType(type);
    }

    public static Map<String, Double> getSum(String id) {
        return Collections.singletonMap("sum", MemoryStorage.getInstance().sum(Long.parseLong(id)));
    }

    public static Map<String, String> save(String id, String content) {
        String result;
        try {
            MemoryStorage.getInstance().insert(Long.parseLong(id), transactionReader.readValue(content));
            result = "ok";
        } catch (IOException ioe) {
            result = "error";
        }
        return Collections.singletonMap("result", result);
    }
}
