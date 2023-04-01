package qing.jin.calendar.Controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("{id}")
    public Content findById(@PathVariable Integer id) {
        return contentCollectionRepo.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resources Not Found"));
    }
}
