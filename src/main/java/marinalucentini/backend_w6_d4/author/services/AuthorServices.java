package marinalucentini.backend_w6_d4.author.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import marinalucentini.backend_w6_d4.author.entities.Author;
import marinalucentini.backend_w6_d4.author.payload.NewAuthorDto;
import marinalucentini.backend_w6_d4.author.repository.AuthorRepository;
import marinalucentini.backend_w6_d4.exceptions.BadRequestException;
import marinalucentini.backend_w6_d4.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class AuthorServices {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    Cloudinary cloudinary;
    // salvato l'utente correttamente nel db dopo aver fatto il post
    public Author saveAuthor(NewAuthorDto newAuthor){
authorRepository.findByEmail(newAuthor.email()).ifPresent(
        author -> {
            throw new BadRequestException("L'email " + newAuthor.email() + " è già in uso!");
        }
);
Author newAuthorForDb = new Author(newAuthor.name(), newAuthor.lastName(), newAuthor.email(), newAuthor.dateOfBirth());
newAuthorForDb.setAvatar("https://unsplash.com/it/foto/una-donna-con-i-capelli-ricci-in-posa-per-una-foto-tJB3XMRErxQ");

        System.out.println("L'autore " + newAuthorForDb.getName() + " è stato correttamente salvato nel db." );
      return   authorRepository.save(newAuthorForDb);
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
//upload immagine
    public String uploadImage(MultipartFile file) throws IOException{
        return (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
    }
}
