package com.example.userservice.Service;

import com.example.userservice.VO.Order;
import com.example.userservice.VO.ResponseTemplateVO;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    @RateLimiter(name = "ratelimiter_getuser", fallbackMethod = "fallbackMethodGet")
    public ResponseTemplateVO getUserWithOrder(Long userId){
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findById(userId).get();
        vo.setUser(user);
        Order order = restTemplate.getForObject("http://localhost:9001/order/" + user.getOrderId(), Order.class);
        vo.setOrder(order);
        return vo;
    }
    public ResponseTemplateVO fallbackMethodGet(Long userId, RequestNotPermitted requestNotPermitted){
        return new ResponseTemplateVO(new User(), new Order());
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

}
