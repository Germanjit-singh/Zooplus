import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 * @author germanjit singh version 1.0
 * */
@SpringBootApplication
@ComponentScan(basePackages = {"com.zooplus"})
@EnableJpaRepositories(basePackages = {"com.zooplus.persistence"})
@EntityScan("com.zooplus.model.dataobject")
public class ZooplusStarterApp {
    public static void main(String[] args) {
        SpringApplication.run(ZooplusStarterApp.class, args);
    }
}
