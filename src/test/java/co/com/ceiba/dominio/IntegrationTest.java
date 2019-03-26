package co.com.ceiba.dominio;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;

@Retention(RetentionPolicy.RUNTIME)
@IfProfileValue(name="testprofile", value="integrationtest")
@SpringBootTest
public @interface IntegrationTest {
}
