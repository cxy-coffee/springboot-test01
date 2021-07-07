package com.abc.springboot.controller;

import com.abc.springboot.BaseTest;
import com.abc.springboot.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;


public class TestHello extends BaseTest {
    //自动注入WebApplicationContext对象wac，可以用来初始化MockMvc对象mvc。可以没有。
    @Autowired
    private WebApplicationContext wac;

    //核心MockMvc对象mvc，必不可少。
    private MockMvc mvc;
    //应对拦截器的MockHttpSession对象session，如果没有拦截器，可以没有；也可以关闭拦截器，不要session。
    private MockHttpSession session;

    //初始化mvc。如果有session，则也初始化session
    @Before
    public void setUp() throws Exception{
        //初始化mvc方法1：按照Controller初始化
        /*
        mvc = MockMvcBuilders.standaloneSetup(new MyController()).build();
        */

        //初始化mvc方法2：一次性初始化
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();

        //如果有，则初始化session.创建一个模拟用户，加入session中，使请求通过拦截器。
        session = new MockHttpSession();
        User user = new User();
        session.setAttribute("User",user);
    }

    @Test
    public void TestHello() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/getUser.do?id=233&name=cxy&gender=男")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userGender").value("男"))
                .andExpect(MockMvcResultMatchers.content().string(equalTo("{\"userId\":233,\"userName\":\"cxy\",\"userGender\":\"男\"}")))
                .andDo(MockMvcResultHandlers.print());
    }
    /*
    mockMvc.perform执行一个请求
    MockMvcRequestBuilders.get("/getUser.do?id=233&name=cxy&gender=男")构造一个请求，Post请求就用.post方法
    contentType(MediaType.APPLICATION_JSON)代表发送端发送的数据格式是application/json
    accept(MediaType.APPLICATION_JSON_UTF8)代表客户端希望接受的数据类型为application/json;charset=UTF-8
    session(session)注入一个session，这样拦截器才可以通过
    ResultActions.andExpect添加执行完成后的断言
    ResultActions.andExpect(MockMvcResultMatchers.status().isOk())方法看请求的状态响应码是否为200如果不是则抛异常，测试不通过
    andExpect(MockMvcResultMatchers.jsonPath("$.userGender").value("男"))这里jsonPath用来获取userGender字段比对是否为男,不是就测试不通过
    ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情，比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息
     */
}
