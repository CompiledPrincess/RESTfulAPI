package qing.jin.calendar.Repo;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import qing.jin.calendar.Model.Content;
import qing.jin.calendar.Model.Status;
import qing.jin.calendar.Model.Type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepo {

    private final List<Content> content = new ArrayList<>();

    public ContentCollectionRepo() {  }

    public List<Content> findall(){
        return content;
    }

    public Optional<Content> findById(Integer id) {
        return content.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    @PostConstruct
    private void init() {
        Content c = new Content(
                1,
                "Qing",
                "Qing testing",
                Status.IN_PROGRESS,
                Type.COURSE,
                LocalDateTime.now(),
                null, "https://github.com/CompiledPrincess"
        );
        content.add(c);

    }

    public void save(Content newContent) {
        content.add(newContent);
        content.removeIf(c -> c.id().equals(newContent.id()));
    }

    public boolean existById (Integer id) {
      return content.stream().filter(c -> c.id().equals(id)).count() == 1;
    }

    public void delete(Integer id) {
       content.removeIf(c -> c.id().equals(id));
    }
}
