package com.number26.persistance;

import com.number26.model.Transaction;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Transactions storage
 */
public class MemoryStorage {

    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    private Lock readLock = rwl.readLock();
    private Lock writeLock = rwl.writeLock();
    /**
     * transaction id to transaction map
     */
    private Map<Long, Transaction> transactions = new HashMap<>();
    /**
     * transaction type to set of transaction ids map
     */
    private Map<String, Set<Long>> type2ids = new HashMap<>();

    private MemoryStorage() {}

    public void insert(long id, Transaction transaction) {
        writeLock.lock();
        try {
            transactions.put(id, transaction);
            Set<Long> ids = type2ids.get(transaction.getType());
            if (ids == null) {
                ids = new HashSet<>();
                type2ids.put(transaction.getType(), ids);
            }
            ids.add(id);
        } finally {
            writeLock.unlock();
        }
    }

    public Transaction getById(long id) {
        readLock.lock();
        try {
            return transactions.get(id);
        } finally {
            readLock.unlock();
        }
    }

    public Set<Long> getByType(String type) {
        readLock.lock();
        try {
            return type2ids.get(type);
        } finally {
            readLock.unlock();
        }
    }

    public double sum(Long id) {
        readLock.lock();
        try {
            double sum = 0;
            do {
                Transaction t = transactions.get(id);
                if (t == null) break;
                sum += t.getAmount();
                id = t.getParentId();
            } while (id != null);
            return sum;
        } finally {
            readLock.unlock();
        }
    }

    private static class Holder {
        private static MemoryStorage INSTANCE = new MemoryStorage();
    }

    public static MemoryStorage getInstance() {
        return Holder.INSTANCE;
    }
}
