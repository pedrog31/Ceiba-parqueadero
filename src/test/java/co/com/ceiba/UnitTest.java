package co.com.ceiba;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.test.annotation.IfProfileValue;

@Retention(RetentionPolicy.RUNTIME)
@IfProfileValue(name="testprofile", value="unittest")
public @interface UnitTest {
	
}
