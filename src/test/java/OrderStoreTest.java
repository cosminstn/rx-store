import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.awaitility.Awaitility.await;

class OrderStoreTest {

	@Test
	void testReceivesLastState_afterSubscribe() {
		var onNextCount = new AtomicInteger();
		var store = new OrderStore();
		AtomicReference<OrderStore.Order> firstOrderReceived = new AtomicReference<>();

		store.publish(new OrderStore.Order(1, "Cosmin", 30));
		store.publish(new OrderStore.Order(2, "Cezar", 520));

		var disposable = store.observe().subscribe(order -> {
			firstOrderReceived.compareAndSet(null, order);
			System.out.println("Received order: " + order.toString());
			onNextCount.incrementAndGet();
		});
		store.publish(new OrderStore.Order(5, "Georgiana", 150));
		store.publish(new OrderStore.Order(12, "Crizantema", 99));

		await().atLeast(1, TimeUnit.SECONDS);

		disposable.dispose();
		assert(onNextCount.get() == 3);
		assert(firstOrderReceived.get().getId() == 2);
	}

}