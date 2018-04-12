package com.nathan.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nathan.client.UserFeignClient.HystrixClientFallback;
import com.nathan.entity.User;

@FeignClient(name="microservice-provider-user", fallback=HystrixClientFallback.class)
public interface UserFeignClient {

	@RequestMapping("/{id}")
	public User findByIdFeign(@RequestParam("id") Long id);
	
	@Component
	class HystrixClientFallback implements UserFeignClient {

		private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFallback.class);
		
		@Override
		public User findByIdFeign(Long id) {
			HystrixClientFallback.LOGGER.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
			User user = new User();
			user.setId(-1L);
			user.setUsername("default username");
			user.setAge(0);
			return user;
		}
		
	}

}
