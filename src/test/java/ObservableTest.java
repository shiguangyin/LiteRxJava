import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author masker
 * @date 28/01/2018
 * @Des
 */
public class ObservableTest {
    @Test
    public void testUnsafe(){
        Observable.unsafeCreate((UnsafeCreateOnSubscribe<String>) subscriber -> {
            subscriber.onNext("onNext");
            subscriber.onComplete();
        }).subscribe(s -> {
            assertEquals(s, "onNext");
        });
    }
}
