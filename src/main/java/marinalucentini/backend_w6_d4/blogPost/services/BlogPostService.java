package marinalucentini.backend_w6_d4.blogPost.services;

import marinalucentini.backend_w6_d4.author.entities.Author;
import marinalucentini.backend_w6_d4.author.services.AuthorServices;
import marinalucentini.backend_w6_d4.blogPost.entities.BlogPost;
import marinalucentini.backend_w6_d4.blogPost.entities.BlogPostPayload;
import marinalucentini.backend_w6_d4.blogPost.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogPostService {
    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    AuthorServices authorServices;
    public BlogPost saveBlogPost(BlogPostPayload newBlogPost){
Author author = authorServices.findById(newBlogPost.getAuthorId());
BlogPost blogPost = new BlogPost();
            blogPost.setAuthor(author);
            blogPost.setCathegory(newBlogPost.getCathegory());
            blogPost.setBody(newBlogPost.getBody());
            blogPost.setCover("https://unsplash.com/it/foto/una-donna-che-porta-gli-occhiali-e-una-camicia-verde-l51FHWunCuE");
            blogPost.setTitle(newBlogPost.getTitle());
            blogPost.setReadingTime(newBlogPost.getReadingTime());
        System.out.println("Il post " + blogPost.getTitle() + " è stato correttamente salvato nel db." );

        return    blogPostRepository.save(blogPost);
    }
}
