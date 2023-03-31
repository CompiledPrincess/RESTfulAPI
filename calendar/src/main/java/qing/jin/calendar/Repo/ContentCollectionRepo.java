package qing.jin.calendar.Repo;

import org.springframework.stereotype.Repository;
import qing.jin.calendar.Model.Content;

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
}
