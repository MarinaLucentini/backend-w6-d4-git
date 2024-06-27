package marinalucentini.backend_w6_d4.blogPost.repository;

import marinalucentini.backend_w6_d4.blogPost.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, UUID> {
}
