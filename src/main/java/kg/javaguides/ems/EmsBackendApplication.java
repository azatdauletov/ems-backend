package kg.javaguides.ems;

import kg.javaguides.ems.entity.Role;
import kg.javaguides.ems.entity.User;
import kg.javaguides.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EmsBackendApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmsBackendApplication.class, args);
	}

	public void run(String... args) {
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if (null == adminAccount) {
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setFirstName("admin");
			user.setLastName("admin");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}

}
