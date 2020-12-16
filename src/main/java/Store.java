import com.jakewharton.rxrelay3.BehaviorRelay;
import com.jakewharton.rxrelay3.Relay;
import io.reactivex.rxjava3.core.Observable;

public class Store<T> {
	private final Relay<T> storeSubject;

	public Store() {
		this.storeSubject = BehaviorRelay.<T>create().toSerialized();
	}

	public Store(T defaultValue) {
		this.storeSubject = BehaviorRelay.createDefault(defaultValue).toSerialized();
	}

	public Observable<T> observe() {
		return storeSubject
				.distinctUntilChanged();
	}

	public void publish(T value) {
		storeSubject.accept(value);
	}
}