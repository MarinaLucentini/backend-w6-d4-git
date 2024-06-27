package marinalucentini.backend_w6_d4.author.services;

import marinalucentini.backend_w6_d4.author.entities.Author;
import marinalucentini.backend_w6_d4.author.repository.AuthorRepository;
import marinalucentini.backend_w6_d4.exceptions.BadRequestException;
import marinalucentini.backend_w6_d4.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorServices {
    @Autowired
    AuthorRepository authorRepository;
    // salvato l'utente correttamente nel db dopo aver fatto il post
    public Author saveAuthor(Author newAuthor){
authorRepository.findByEmail(newAuthor.getEmail()).ifPresent(
        author -> {
            throw new BadRequestException("L'email " + newAuthor.getEmail() + " è già in uso!");
        }
);
newAuthor.setAvatar("https://unsplash.com/it/foto/una-donna-con-i-capelli-ricci-in-posa-per-una-foto-tJB3XMRErxQ");

        System.out.println("L'autore " + newAuthor.getName() + " è stato correttamente salvato nel db." );
      return   authorRepository.save(newAuthor);
    }
    // paginazione
    public Page<Author> getAuthors(int pageNumber, int pageSize, String sortBy){
        if(pageSize> 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return authorRepository.findAll(pageable);
    }
    // get singolo autore
    public Author findById(UUID id){
        return authorRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
    // put autore
    public Author findByIdAndUpdate(UUID id, Author authorModifier){
        Author found= findById(id);
        found.setEmail(authorModifier.getEmail());
        found.setName(authorModifier.getName());
        found.setLastName(authorModifier.getLastName());
        found.setAvatar(authorModifier.getAvatar());
        found.setDateOfBirth(authorModifier.getDateOfBirth());
        System.out.println("L'autore " + found.getName() + "è stato correttamente modificato");
      return   authorRepository.save(found);
    }
    // delete autore
    public void findByIdAndDelete(UUID authorId) {
        Author found = this.findById(authorId);
     authorRepository.delete(found);
    }

}
