package marinalucentini.backend_w6_d4.author.controllers;

import marinalucentini.backend_w6_d4.author.entities.Author;
import marinalucentini.backend_w6_d4.author.payload.NewAuthorDto;
import marinalucentini.backend_w6_d4.author.payload.NewAuthorResponseDto;
import marinalucentini.backend_w6_d4.author.services.AuthorServices;
import marinalucentini.backend_w6_d4.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    AuthorServices authorServices;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewAuthorResponseDto saveAuthor(@RequestBody @Validated NewAuthorDto newAuthor, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new NewAuthorResponseDto(authorServices.saveAuthor(newAuthor).getId());
    }
    @GetMapping
    public Page<Author> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "name") String sortBy) {
        return authorServices.getAuthors(page, size, sortBy);
    }
@GetMapping ("/{authorId}")
    public Author findById(@PathVariable UUID authorId){
        return authorServices.findById(authorId);
}
@PutMapping("/{authorId}")
    public Author findByIdAndUpdate (@PathVariable UUID authorId, @RequestBody Author authorModificated){
        return authorServices.findByIdAndUpdate(authorId, authorModificated);
}
@DeleteMapping("/{authorId}")
    public void deleteAuthor(@PathVariable UUID authorId){
        authorServices.findByIdAndDelete(authorId);
    System.out.println("L'autore è stato correttamente cancellato");
}
@PostMapping("/{authorId}/avatar")
public String uploadAvatar(@PathVariable UUID authorId, @RequestParam("avatar") MultipartFile image) throws IOException {
    String imageUrl = authorServices.uploadImage(image);
    Author updatedAuthor = authorServices.saveImage(imageUrl, authorId);
    String message = "Immagine caricata nel db correttamente";
    return message;
}
}
