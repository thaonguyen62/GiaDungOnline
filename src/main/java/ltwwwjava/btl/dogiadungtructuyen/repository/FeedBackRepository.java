package ltwwwjava.btl.dogiadungtructuyen.repository;

import ltwwwjava.btl.dogiadungtructuyen.model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedBackRepository extends MongoRepository<Feedback, String> {

}
