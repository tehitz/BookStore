package com.example.BookStore;

import com.example.BookStore.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);

	@Autowired
	private BookRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				log.info("save a couple of books");
                crepository.save(new Category("Horror"));
                crepository.save(new Category("Fantasy"));
                crepository.save(new Category("Biography"));
				crepository.save(new Category("Drama"));

				brepository.save(new Book("Rööperi", "Harri Nykänen", "2007", "wko4rwer", 22.66, crepository.findByName("Biography").get(0)));
				brepository.save(new Book("IT", "Stephen King", "2017", "8hbo88o", 25.99, crepository.findByName("Horror").get(0)));

				User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
				User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
				urepository.save(user1);
				urepository.save(user2);

				log.info("fetch all books");
				for (Book book : brepository.findAll()) {
					log.info(book.toString());
				}

			};
		};
	}

}
