package qing.jin.calendar.Controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import qing.jin.calendar.Model.Content;
import qing.jin.calendar.Model.Status;
import qing.jin.calendar.Model.Type;
import qing.jin.calendar.Repo.ContentCollectionRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content") //localhost:8080/api/content
public class ContentController {
    private final ContentCollectionRepo contentCollectionRepo;
    @Autowired
    public ContentController(ContentCollectionRepo contentCollectionRepo) {
        this.contentCollectionRepo = contentCollectionRepo;
    }

    @GetMapping("")
    public List<Content> findAll() {
        return contentCollectionRepo.findall();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        return contentCollectionRepo.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resources Not Found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Content newContent) {
        contentCollectionRepo.save(newContent);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public void update(@RequestBody Content existingContent, @PathVariable Integer id) {
        if(!contentCollectionRepo.existById(id)) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content does not exist");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        contentCollectionRepo.delete(id);
    }
}
