package com.number26;

import com.number26.controller.TransactionController;

import static spark.Spark.*;

/**
 * Entry point
 */
public class Server {

    public static void main(String [] args) {
        JsonTransformer jsonTransformer = new JsonTransformer();
        get("/transactionservice/transaction/:id", (request, response) -> {
            return TransactionController.getTransaction(request.params(":id"));
        }, jsonTransformer);
        get("/transactionservice/types/:type", (request, response) -> {
            return TransactionController.getIdsWithType(request.params(":type"));
        }, jsonTransformer);
        get("/transactionservice/sum/:id", (request, response) -> {
            return TransactionController.getSum(request.params(":id"));
        }, jsonTransformer);
        put("/transactionservice/transaction/:id", (request, response) -> {
            return TransactionController.save(request.params(":id"), request.body());
        }, jsonTransformer);
    }
}
