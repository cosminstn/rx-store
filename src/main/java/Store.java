import com.jakewharton.rxrelay3.BehaviorRelay;
import com.jakewharton.rxrelay3.Relay;
import io.reactivex.rxjava3.core.Observable;

/**
 * Inspired from <a href="https://gist.githubusercontent.com/czyrux/f46d818d65cbeee4e80de7ba4504252e/raw/2920355cc8b70d5261c4949a9565e3f979a7475b/Store.java">Store.java</a>
 * and adapted for rxjava3 and rxrelay3.
 * @author Cosmin Stoian
 * @param <T>
 */
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