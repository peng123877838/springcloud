/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.member.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月1日 下午4:50:21<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
public class MemberController {
	@Value("${server.port}")
	private String port;
	
	@RequestMapping("/getMember2")
	@HystrixCommand(fallbackMethod = "getMember2Fail", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100"),
            @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "1"),
            @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "10"),
            @HystrixProperty(name = "execution.isolation.strategy", value ="SEMAPHORE")
    })
	public MemberParam getMember2(MemberParam param) {
		System.out.println(Thread.currentThread().getName()+"---"+Thread.currentThread().getThreadGroup().getName());
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		param.setName("######success#######");
		return param;
	}
	
	public MemberParam getMember2Fail(MemberParam param) {
		System.out.println("###########getMember2Fail###############");
		param.setName("*****getMember2Fail******");
		return param;
	}
}
