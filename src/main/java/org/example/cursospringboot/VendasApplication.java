package org.example.cursospringboot;

import org.example.cursospringboot.domain.entity.Cliente;
import org.example.cursospringboot.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApplication
{
	@Bean
	public CommandLineRunner commandLineRunner(@Autowired ClientesRepository clientesRepository){
		return args -> {
			Cliente c = new Cliente(null, "Joaquim");
			clientesRepository.save(c);

			Cliente c1 = new Cliente(null, "Tom");
			clientesRepository.save(c1);

			Cliente c2 = new Cliente(null, "Fulano");
			clientesRepository.save(c2);
		};
	}

	public static void main(String[] args)
	{
		SpringApplication.run(VendasApplication.class, args);
	}
}
