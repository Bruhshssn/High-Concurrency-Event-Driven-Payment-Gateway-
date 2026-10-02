The main goal of this project is to simulate the backend architecture and concurrency challenges involved in payment processing without integrating with a real banking or payment provider.

So the project works like:
1.The API receives the payment request.
2. The payment is stored in PostgreSQL.
3. A PaymentInitiatedEvent is published to Kafka.
4. The API can return a response without waiting for the processor.
5. The payment processor consumes the event asynchronously.
6. Redis is used to acquire a distributed lock for the payment.
7. The payment processor performs the simulated processing.
8. A PaymentStatusUpdateEvent is published to Kafka.
9. The API service consumes the status event.
10. The payment status is updated in PostgreSQL.

I kept it light and simple as this is my first kafka project and I am still a learner. Thank you.
