package tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Pageable;

import tacos.Order;
import java.util.List;
import tacos.User;

public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
