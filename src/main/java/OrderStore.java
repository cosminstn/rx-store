import java.math.BigDecimal;

public class OrderStore extends Store<OrderStore.Order> {

	public static class Order {
		private final int id;
		private final String clientName;
		private final double total;

		public Order(int id, String clientName, double total) {
			this.id = id;
			this.clientName = clientName;
			this.total = total;
		}

		public int getId() {
			return id;
		}

		public String getClientName() {
			return clientName;
		}

		public double getTotal() {
			return total;
		}

		@Override
		public String toString() {
			return String.format("Id %d, client: %s, price: %f", id, clientName, total);
		}
	}
}
