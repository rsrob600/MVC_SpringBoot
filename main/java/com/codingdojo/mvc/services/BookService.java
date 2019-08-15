package com.codingdojo.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.mvc.models.Book;
import com.codingdojo.mvc.repositories.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	// returns all the books
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    // retrieves a book
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
    // updates a book
    public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
    	
    	// declare book object variable and find match with inbound ID param
    	Book bookToUpdate = findBook(id);
    	// check if book matches the id and set all new attributes to the object variable
		if(bookToUpdate.getId() == id) {
			bookToUpdate.setTitle(title);
			bookToUpdate.setDescription(desc);
			bookToUpdate.setLanguage(lang);
			bookToUpdate.setNumberOfPages(numOfPages);
			// save the attributes back to the object (with insert/or update)
			return createBook(bookToUpdate);
		}else {
			return null;
		}
    }
    // deletes a book
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
    
}
