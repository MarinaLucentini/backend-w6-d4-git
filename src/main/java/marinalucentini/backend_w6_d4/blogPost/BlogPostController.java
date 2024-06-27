package marinalucentini.backend_w6_d4.blogPost;

import marinalucentini.backend_w6_d4.blogPost.entities.BlogPost;
import marinalucentini.backend_w6_d4.blogPost.entities.BlogPostPayload;
import marinalucentini.backend_w6_d4.blogPost.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostController {
    @Autowired
    BlogPostService blogPostService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost saveBlogPost(@RequestBody BlogPostPayload blogPost){
        return blogPostService.saveBlogPost(blogPost);
    }
}
